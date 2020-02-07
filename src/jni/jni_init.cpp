#include "sdk_afm.h"
#include "api_jni/jni_init.h"
#include "api_jni/sdk_jni.h"


EXT_C JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
    auto log_tag = LogCore::Instance()->GetLogTag();
    __android_log_print(ANDROID_LOG_INFO, log_tag.c_str(), "Initializing JNI...");

    JNIEnv *env = jniHelpersInitialize(jvm);
    if (env == NULL) {
        __android_log_print(ANDROID_LOG_ERROR, log_tag.c_str(), "Initializing JNI error!");
        return -1;
    }

    static auto sdk_jni_api_ = MakeShared(SdkJniApi, env);

    __android_log_print(ANDROID_LOG_INFO, log_tag.c_str(), "Initializing JNI complete.");
    return JAVA_VERSION;
}
