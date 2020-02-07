#ifndef _IMCOMM_JNI_UTILS_H_
#define _IMCOMM_JNI_UTILS_H_
#include <string>
#include <thread>
#include <cstdint>
#include "macros.h"
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

#define JNISTR2BYTECSTR(env, jni_str) ScopedJstring::Jstring2ByteArrForCstring(env, jni_str)
#define JNISTR2CSTR(env, jni_str) spotify::jni::JavaString(env, jni_str).get()
#define CSTR2JNISTR(env, c_str) spotify::jni::JavaString(c_str).toJavaString(env).leak()
#define CDATA2JNIBYTEARRAY(env, data, size, copy) spotify::jni::ByteArray(data, size, copy).toJavaByteArray(env).leak()

class JniUtils {
public:
    static JNIEnv* GetCurrentThreadJniEnv();
    static jvalue CallMethodByName(jobject obj, jmethodID mid, const char* ret_type, ...);
};


#endif

NAMESPACE_END

#endif  // _IMCOMM_JNI_UTILS_H_