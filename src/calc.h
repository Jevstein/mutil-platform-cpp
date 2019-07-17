#ifndef _JVT_CALC_H_
#define _JVT_CALC_H_
#include "sdk.h"

class Calc : public ICalc
{
public:
	Calc();
	virtual ~Calc();

public:
    virtual const char* note();
    virtual void bind_cbk(ICalcCbk* cbk);
	
	virtual int add(int a, int b);
	virtual int sub(int a, int b);
	virtual double mul(double a, double b);
	virtual double div(double a, double b);
	
private:
	ICalcCbk* cbk_;
};

#endif//_JVT_CALC_H_
