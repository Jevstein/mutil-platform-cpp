#include "sdk.h"
#include "calc.h"

#include "sdk_afm.h"
#include "api_jni/sdk_jni.h"
#include "JavaString.h"


#define ADD_NAT_METHOD(ret_type, funname, ...) \
    addNativeMethod(#funname, (void*)&SdkJniApi::funname, ret_type, __VA_ARGS__)


void SdkJniApi::initialize(JNIEnv *env)
{
    setClass(env);

	//业务性接口
	ADD_NAT_METHOD(kTypeBool, asyncCall, kTypeString, kTypeString, NULL);

	//功能性接口
	ADD_NAT_METHOD(kTypeBool, createCalc, kTypeObject, NULL);
	ADD_NAT_METHOD(kTypeBool, destroyCalc, NULL);

    registerNativeMethods(env);
}
