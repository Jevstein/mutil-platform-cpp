/*
 *  main.cpp
 *  main
 *
 *  Created by Jevstein on 2019/8/14 18:22.
 *  Copyright @ 2019year Jevstein. All rights reserved.
 *
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <string.h>
#include <dlfcn.h>// dlopen + dlsym + dlclose
#include "sdk.h"

typedef ICalc* (*PFuncCreateCalc)();
typedef void (*PFuncDestroyCalc)(ICalc* calc);

class CalcCbk: public ICalcCbk
{
public:
	CalcCbk() {}
	~CalcCbk() {}
public:
	virtual int on_result(const char *result) { printf("'%s': ", result); return 0; }
};

#define LIB_NAME "./libcalc_sdk.so"

int main(int argc, char* argv[])
{
	printf("====== This is an example of using a share library in mac ======\n");

	do 
	{
		void* module = dlopen(LIB_NAME, RTLD_NOW);
		if (module == NULL)
		{
			printf("error: failed to load %s!\nerror code: %s\n", LIB_NAME, dlerror());
			break;
		}

		PFuncCreateCalc create_func = (PFuncCreateCalc)dlsym(module, "jvt_create_calc");
		if (!create_func)
		{
			printf("error: failed to get function jvt_create_calc!\nerror code: %s\n", dlerror());
			break;
		}

		ICalc *calc = create_func();
		if (calc)
		{
			printf("note: %s\n", calc->note());
			//printf("bind calc callback ...\n");	
			CalcCbk *cbk = new CalcCbk();  calc->bind_cbk(cbk);

			int a = 100;
			int b = 10;
			printf("add(%d, %d) = %d\n", a, b, calc->add(a, b));
			printf("sub(%d, %d) = %d\n", a, b, calc->sub(a, b));
			printf("mul(%d, %d) = %.2f\n", a, b, calc->mul((double)a, (double)b));
			printf("div(%d, %d) = %.2f\n", a, b, calc->div((double)a, (double)b));

			if (cbk)
				delete cbk;
		}
		else
		{
			printf("error: failed to create calc!\n");
			break;
		}

		PFuncDestroyCalc destroy_func = (PFuncDestroyCalc)dlsym(module, "jvt_destroy_calc");
		if (!destroy_func)
		{
			printf("error: failed to get function jvt_destroy_calc!\n");
			break;
		}

		destroy_func(calc);

		dlclose(module);
	} while (0);

	printf("====== the end ======\n");
	return 0;
}