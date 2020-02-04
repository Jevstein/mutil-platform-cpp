#ifndef _JVT_SDK_H_
#define _JVT_SDK_H_

#if defined(__cplusplus)
#	define EXT_C extern "C"
#else
#	define EXT_C
#endif

#ifdef WIN32
#	ifdef CALC_SDK_DLL_EXPORTS
#		define SDK_API EXT_C __declspec(dllexport)
#	else
#		define SDK_API EXT_C __declspec(dllimport)
#	endif//CALC_SDK_DLL_EXPORTS
#	define CALLCON /*__stdcall*/
#else
#	define SDK_API extern
#	define CALLCON
#endif//WIN32

//#if defined(__cplusplus)
class ICalcCbk
{
public:
    virtual ~ICalcCbk(){}
	virtual int on_result(const char *result) = 0;
};

class ICalc
{
public:
    virtual ~ICalc(){}
    virtual const char* note() = 0;
    virtual void bind(ICalcCbk* cbk) = 0;
	
	virtual int add(int a, int b) = 0;         
	virtual int sub(int a, int b) = 0;         
	virtual double mul(double a, double b) = 0;
	virtual double div(double a, double b) = 0;
};
//#endif//__cplusplus

SDK_API ICalc* CALLCON jvt_create_calc();
SDK_API	void CALLCON jvt_destroy_calc(ICalc* calc);

#endif//_JVT_SDK_H_
