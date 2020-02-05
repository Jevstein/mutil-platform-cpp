#include "sdk.h"
#include "calc.h"

SDK_API ICalc* CALLCON create_calc(ICalcCbk* cbk)
{
	return new Calc(cbk);
}

SDK_API	void CALLCON destroy_calc(ICalc* calc)
{
	if (calc)
		delete calc;
}
