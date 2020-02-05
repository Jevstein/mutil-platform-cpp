/*
 *  main.cpp
 *  main
 *
 *  Created by Jevstein on 2020/02/02 00:27.
 *  Copyright @ 2020year Jevstein. All rights reserved.
 *
 *  brief:
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include "sdk.h"
#ifndef WIN32
#include <dlfcn.h>
#endif

#define LOG_ERR(fmt,...) printf(fmt" ["__FILE__":%d]", ##__VA_ARGS__, __LINE__)
#define LOG_DBG(fmt,...) printf(fmt, ##__VA_ARGS__)

const char* p_last_error()
{
#ifdef WIN32
	LPTSTR lpMsg = NULL;
	DWORD dwLastError = GetLastError();
	FormatMessage(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_IGNORE_INSERTS | FORMAT_MESSAGE_FROM_SYSTEM, NULL, dwLastError, GetUserDefaultLangID(), (LPTSTR)&lpMsg, 0, NULL);
	return lpMsg;
#else
	return dlerror();
#endif
}

#ifdef WIN32
#	define DYNLIB_HANDLE 			HINSTANCE
#	define DYNLIB_LOAD(a) 			LoadLibrary(a)
#	define DYNLIB_GETSYM(a, b) 		GetProcAddress(a, b)
#	define DYNLIB_UNLOAD(a) 		(bool)!FreeLibrary(a)
#else
#	define DYNLIB_HANDLE 			void*
#	define DYNLIB_LOAD(a) 			dlopen(a, RTLD_NOW)
#	define DYNLIB_GETSYM(a, b) 		dlsym(a, b)
#	define DYNLIB_UNLOAD(a) 		dlclose(a)
#endif

class ModuleFactory
{
public:
	ModuleFactory(const char* module_file)
	: handler_(NULL)
	{
		if (module_file != NULL)
		{
			init(module_file);
		}
	}

	~ModuleFactory(void)
	{
		if (handler_ != 0)
		{
			DYNLIB_UNLOAD(handler_);
		}
	}

public:
    template<class T>
    T* create_module(const char* func_name, ICalcCbk *cbk)
    {
        typedef	T* (*PFN)(ICalcCbk *);
        PFN pfn = 0;
        T* func = 0;
        
		pfn = (PFN)load_func(func_name);
        if (pfn)
        {
            func = pfn(cbk);
        }
        return func;
    }

	void* load_func(const char* func_name)
	{
		if (handler_ == 0)
		{
			LOG_ERR("failed to load '%s', error code: handler_ is null", func_name);
			return NULL;
		}

		void *pfn = DYNLIB_GETSYM(handler_, func_name);
		if (!pfn)
		{
			LOG_ERR("failed to dlsym '%s'! error code: '%s'", func_name, p_last_error());
			return NULL;
		}

		return pfn;
	}

protected:
	bool init(const char* module_file)
	{
		handler_ = DYNLIB_LOAD(module_file);
		if (handler_ == 0)
		{
			LOG_ERR("failed to dlopen '%s'! error code: '%s'", module_file, p_last_error());
			return false;
		}
		return true;
	}

private:
	DYNLIB_HANDLE handler_;
};


class CalcCbk: public ICalcCbk
{
public:
	CalcCbk() {}
	~CalcCbk() {}
public:
	virtual int on_result(const char *result) { printf("'%s': ", result); return 0; }
};


int main(int argc, char* argv[])
{
	printf("====== This is an example of using a dynamic library in mac ======\n");

	ModuleFactory fac("../../lib/linux/so/libcalc_sdk.so");

	CalcCbk *cbk = new CalcCbk();
	ICalc *calc = fac.create_module<ICalc>("create_calc", cbk);
	if (calc)
	{
		printf("note: %s\n", calc->note());	

		int a = 100;
		int b = 10;
		printf("add(%d, %d) = %d\n", a, b, calc->add(a, b));
		printf("sub(%d, %d) = %d\n", a, b, calc->sub(a, b));
		printf("mul(%d, %d) = %.2f\n", a, b, calc->mul((double)a, (double)b));
		printf("div(%d, %d) = %.2f\n", a, b, calc->div((double)a, (double)b));

		calc->release();
	}
	else
	{
		printf("error: failed to create calc!\n");
	}
	if (cbk) delete cbk;

	printf("====== the end ======\n");
	return 0;
}