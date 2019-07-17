/*
 *  main.cpp
 *  main
 *
 *  Created by Jevstein on 2019/7/16 18:56.
 *  Copyright @ 2019year Jevstein. All rights reserved.
 *
 *  brief:
 *    windows下c++调用dll的方法如下,
 *    1.静态调用(简单)：
 *      方法1：声明 #pragma comment(lib, "calc_sdk.lib")
 *      方法2: 将calc_sdk_dll.lib放置到: 项目属性/链接器/输入/附加依赖项/
 *
 *    2.动态调用(通用.本示例采用)：
 *      1)定义函数指针
 *      2)通过LoadLibrary(),GetProcAddress()等方法，找到导出函数，从而动态加载
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <Windows.h>
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

int main(int argc, char* argv[])
{
	printf("====== This is an example of using a dynamic library in windows ======\n");

	do 
	{
		HMODULE module = LoadLibrary("calc_sdk.dll");
		if (module == NULL)
		{
			printf("error: failed to load calc_sdk.dll!\n");
			break;
		}

		PFuncCreateCalc create_func = (PFuncCreateCalc)GetProcAddress(module, "jvt_create_calc");
		if (!create_func)
		{
			//注意: define CALLCON __stdcall: sdk导出时若增加了调用约定，函数会被重命名。
			//      windows查看dll的导出函数，可用软件depends.exe
			//      若出现重命名问题，还可以通过编写def文件解决
			printf("error: failed to get function jvt_create_calc!\n");
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

		PFuncDestroyCalc destroy_func = (PFuncDestroyCalc)GetProcAddress(module, "jvt_destroy_calc");
		if (!destroy_func)
		{
			printf("error: failed to get function jvt_destroy_calc!\n");
			break;
		}

		destroy_func(calc);

		FreeLibrary(module);
	} while (0);

	printf("====== the end ======\n");
	system("Pause");
	return 0;
}