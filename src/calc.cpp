#include "calc.h"
#include <cmath>

#define MIN_VALUE 1e-8
#define IS_DOUBLE_ZERO(d)  (fabs(d) < MIN_VALUE)

Calc::Calc()
: cbk_(NULL)
{
}

Calc::~Calc()
{
}

void Calc::release()
{
	delete this;
}

const char* Calc::note()
{
	return "This is a simple calculator!";
}

void Calc::bind_cbk(ICalcCbk* cbk)
{
	cbk_ = cbk;
}

int Calc::add(int a, int b)
{
	if (cbk_)
		cbk_->on_result("a + b");
	
	return a + b;
}

int Calc::sub(int a, int b)
{
	if (cbk_)
		cbk_->on_result("a - b");
	
	return a - b;
}

double Calc::mul(double a, double b)
{
	if (cbk_)
		cbk_->on_result("a * b");
	
	return a * b;
}

double Calc::div(double a, double b)
{
	if (cbk_)
		cbk_->on_result("a / b");
	
	return IS_DOUBLE_ZERO(b) ? 0 : (a / b);
}
