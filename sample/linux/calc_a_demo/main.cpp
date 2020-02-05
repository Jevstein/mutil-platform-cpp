/*
 *  main.cpp
 *  main
 *
 *  Created by Jevstein on 2020/02/01 21:19.
 *  Copyright @ 2020year Jevstein. All rights reserved.
 *
 *  brief:
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include "sdk.h"

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
	printf("====== This is an example of using a static library in mac ======\n");

	CalcCbk *cbk = new CalcCbk();
	ICalc *calc = create_calc(cbk);
	if (calc)
	{
		printf("note: %s\n", calc->note());	

		int a = 100;
		int b = 10;
		printf("add(%d, %d) = %d\n", a, b, calc->add(a, b));
		printf("sub(%d, %d) = %d\n", a, b, calc->sub(a, b));
		printf("mul(%d, %d) = %.2f\n", a, b, calc->mul((double)a, (double)b));
		printf("div(%d, %d) = %.2f\n", a, b, calc->div((double)a, (double)b));

	    destroy_calc(calc);
	}
	else
	{
		printf("error: failed to create calc!\n");
	}
	if (cbk) delete cbk;

	printf("====== the end ======\n");
	return 0;
}