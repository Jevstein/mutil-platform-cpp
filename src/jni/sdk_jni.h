#ifndef _JVT_SDK_H_
#define _JVT_SDK_H_

#if defined(__cplusplus)
#	define EXT_C extern "C"
#else
#	define EXT_C
#endif

#if defined(WIN32)
#	if defined(BASE_IMPLEMENTATION)
#		define IMSDK_API EXT_C __declspec(dllexport)
#	else
#		define IMSDK_API EXT_C __declspec(dllimport)
#	endif  // defined(BASE_IMPLEMENTATION)
#	define CALLCON
#endif  // defined(BASE_IMPLEMENTATION)

struct ICalcCbk
{
	virtual int on_result(const char *result) = 0;
};

struct ICalc
{
    virtual const char* note() = 0;
    virtual void bind_cbk(ICalcCbk* cbk) = 0;
	
	virtual int add(int a, int b) = 0;         
	virtual int sub(int a, int b) = 0;         
	virtual double mul(double a, double b) = 0;
	virtual double div(double a, double b) = 0;
};

extern "C"
{
	ICalc* jvt_create_calc();
	void jvt_destroy_calc(ICalc* calc);
}

#endif//_JVT_SDK_H_