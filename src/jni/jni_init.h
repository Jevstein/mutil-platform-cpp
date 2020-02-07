#ifndef _SDK_JNI_INIT_H_
#define _SDK_JNI_INIT_H_
#include <jni.h>

#if defined(__cplusplus)
#define EXT_C extern "C"
#else
#define EXT_C
#endif


EXT_C JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved);


#endif	//_SDK_JNI_INIT_H_