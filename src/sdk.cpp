#include "sdk.h"
#include "calc.h"

SDK_API ICalc* CALLCON jvt_create_calc()
{
	return new Calc();
}

SDK_API	void CALLCON jvt_destroy_calc(ICalc* calc)
{
	if (calc)
		delete calc;
}
