//
//  ViewController.m
//  calc_f_demo
//
//  Created by hdk on 2019/7/17.
//  Copyright © 2019年 jevstein. All rights reserved.
//

#import "ViewController.h"
#import "sdk.h"

class CalcCbk: public ICalcCbk
{
public:
    CalcCbk() {}
    virtual ~CalcCbk() {}
public:
    virtual int on_result(const char *result) { printf("'%s': ", result); return 0; }
};

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    [self calcTest];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)calcTest{
    printf("====== This is an example of using a framework in iOS ======\n");
    
    ICalc *calc = jvt_create_calc();
    if (calc){
        printf("note: %s\n", calc->note());
        //printf("bind calc callback ...\n");
        CalcCbk cbk;  calc->bind(&cbk);
        
        int a = 100;
        int b = 10;
        printf("add(%d, %d) = %d\n", a, b, calc->add(a, b));
        printf("sub(%d, %d) = %d\n", a, b, calc->sub(a, b));
        printf("mul(%d, %d) = %.2f\n", a, b, calc->mul((double)a, (double)b));
        printf("div(%d, %d) = %.2f\n", a, b, calc->div((double)a, (double)b));
        
        jvt_destroy_calc(calc);
    } else {
        printf("error: failed to call jvt_create_calc!\n");
    }
    
    printf("====== the end ======\n");
}

@end
