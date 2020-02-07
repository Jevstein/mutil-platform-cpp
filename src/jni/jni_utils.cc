#include "imcomm/jni_utils.h"
#include "build/build_config.h"

#if defined(OS_ANDROID) && (OS_ANDROID == 1)
#include <jni.h>
#include <android/log.h>
#include "JniGlobalRef.h"
#include "JniHelpers.h"
#include "JavaThreadUtils.h"
#endif


NAMESPACE_START(imcomm)

#if defined(OS_ANDROID) && (OS_ANDROID == 1)

JNIEnv* JniUtils::GetCurrentThreadJniEnv()
{
    auto curr_env = spotify::jni::JavaThreadUtils::getEnvForCurrentThread();
    if (curr_env)return curr_env;

    struct EnvThreadDetach {
        static void DetachRoutine(void* a) {
            spotify::jni::JavaThreadUtils::detatchCurrentThreadFromJVM();
        }
    };
    pthread_key_t env_th_key;
    if (0 != pthread_key_create(&env_th_key, &EnvThreadDetach::DetachRoutine)) {
        return nullptr;
    }

    curr_env = spotify::jni::JavaThreadUtils::attachCurrentThreadToJVM("imsdk-SetAppPublicCallback");
    return curr_env;
}

jvalue JniUtils::CallMethodByName(jobject obj, jmethodID mid, const char* ret_type, ...)
{
    JNIEnv* env = JniUtils::GetCurrentThreadJniEnv();

    assert(env != nullptr);
    assert(obj != nullptr);
    assert(mid != nullptr);
    assert(ret_type);
    assert(*ret_type != '/0');

    va_list args;
    va_start(args, ret_type);

    std::string mth_ret_type(ret_type);

    jvalue result;

    if (kTypeVoid == mth_ret_type) {
        env->CallVoidMethodV(obj, mid, args);
    }
    else if (kTypeString == mth_ret_type || kTypeObject == mth_ret_type) {
        result.l = env->CallObjectMethodV(obj, mid, args);
    }
    else if (kTypeBool == mth_ret_type) {
        result.z = env->CallBooleanMethodV(obj, mid, args);
    }
    else if (kTypeByte == mth_ret_type) {
        result.b = env->CallByteMethodV(obj, mid, args);
    }
    else if (kTypeChar == mth_ret_type) {
        result.c = env->CallCharMethodV(obj, mid, args);
    }
    else if (kTypeShort == mth_ret_type) {
        result.s = env->CallShortMethodV(obj, mid, args);
    }
    else if (kTypeInt == mth_ret_type) {
        result.i = env->CallIntMethodV(obj, mid, args);
    }
    else if (kTypeLong == mth_ret_type) {
        result.j = env->CallLongMethodV(obj, mid, args);
    }
    else if (kTypeFloat == mth_ret_type) {
        result.f = env->CallFloatMethodV(obj, mid, args);
    }
    else if (kTypeDouble == mth_ret_type) {
        result.d = env->CallDoubleMethodV(obj, mid, args);
    }
    else {
        env->FatalError("CallMethodByName: 无效的的返回值");
    }

    va_end(args);
    return result;
}

#endif

NAMESPACE_END