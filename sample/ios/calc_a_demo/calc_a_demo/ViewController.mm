//
//  ViewController.m
//  calc_a_demo
//
//  Created by hdk on 2019/8/5.
//  Copyright © 2019年 Jevstein. All rights reserved.
//

#import "ViewController.h"
#import "sdk.h"
#include <sstream>

#pragma mark - [c++ calc_sdk callback]

// 定义c函数指针类型: 因为c++无法直接使用oc对象，需要通过c函数操作oc
typedef void (*ResultHandler)(void *user, const char *msg);

class CalcCbk: public ICalcCbk
{
public:
    CalcCbk() : user_(NULL) {}
    virtual ~CalcCbk() {}
    
    void registerHandle(void *user, ResultHandler func) { this->user_ = user; this->func_ = func; }
    
public:
    virtual int on_result(const char *result) {
        if (user_)  func_(user_, result);
        else        printf("'%s'->", result);
        return 0;
    }

private:
    void *user_;
    ResultHandler func_;
};

#pragma mark - [objective-c operation]
@interface ViewController () {
    
}
@property (weak, nonatomic) IBOutlet UIButton *btnAction;
@property (weak, nonatomic) IBOutlet UITextView *txtOutput;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)clickAction:(id)sender {
    [self setOutput:@""];
    [self calcTest];
}

- (void)setOutput: (NSString*)text {
    printf("%s\n", [text UTF8String]);//NSLog(text);
    
    [_txtOutput setText: [_txtOutput.text stringByAppendingString:@"\r\n"]];
    [_txtOutput setText: [_txtOutput.text stringByAppendingString:text]];
}

- (void)setResultOutput: (NSString*)text {
    printf("%s", [text UTF8String]);//NSLog(text);
    
    [_txtOutput setText: [_txtOutput.text stringByAppendingString:@"\r\n"]];
    [_txtOutput setText: [_txtOutput.text stringByAppendingString:text]];
}

#pragma mark - [objctive-c call c++ calc_sdk]
void onResultHandler(void* userData, const char *msg) {
    ViewController *vc = (__bridge ViewController*)userData;
    NSString *text = [NSString stringWithUTF8String:msg];
    
//    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
//        dispatch_async(dispatch_get_main_queue(), ^{
                [vc setResultOutput:[NSString stringWithFormat:@"'%@' -> ", text]];
//        });
//    });
}

- (void)calcTest{
    [self setOutput:@"=== This is an example of using a static library in iOS ==="];
    
    ICalc *calc = jvt_create_calc();
    if (calc){
        [self setOutput: [NSString stringWithFormat:@"note: %s",calc->note()]];
        //[self setOutput: @"bind calc callback"];
        CalcCbk cbk;   calc->bind(&cbk);
        cbk.registerHandle((__bridge void*)self, onResultHandler);
        
        int a = 100;
        int b = 10;
        [self setOutput: [NSString stringWithFormat:@"add(%d, %d) = %d", a, b, calc->add(a, b)]];
        [self setOutput: [NSString stringWithFormat:@"sub(%d, %d) = %d", a, b, calc->sub(a, b)]];
        [self setOutput: [NSString stringWithFormat:@"mul(%d, %d) = %.2f", a, b, calc->mul((double)a, (double)b)]];
        [self setOutput: [NSString stringWithFormat:@"div(%d, %d) = %.2f", a, b, calc->div((double)a, (double)b)]];
        
        jvt_destroy_calc(calc);
    } else {
        [self setOutput: @"error: failed to call jvt_create_calc!"];
    }
    
    [self setOutput: @"========= the end ========="];
}
@end
