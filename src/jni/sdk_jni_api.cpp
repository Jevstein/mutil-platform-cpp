#include "sdk_afm.h"
#include "api_jni/sdk_jni.h"
#include "JavaString.h"
#include "ScopedJstring.h"


#define JNIFUN(ret_type, funname) ret_type SdkJniApi::funname
#define CALL_API_PROXY(apiname, ...)\
    (ApiProxy::Instance()->apiname(__VA_ARGS__) ? JNI_TRUE : JNI_FALSE)
#define CALL_API_PROXY_NORET(apiname, ...)\
    ApiProxy::Instance()->apiname(__VA_ARGS__)
#define JNISTR2CSTR_API(jni_str) JNISTR2CSTR(env, jni_str)
#define JNISTR2BYTECSTR_API(jni_str) JNISTR2BYTECSTR(env, jni_str)


JNIFUN(jboolean, asyncCall)(ENV_OBJ, jstring jskey, jstring jsreq)
{
	return CALL_API_PROXY(AsyncCall, JNISTR2CSTR_API(jskey), JNISTR2CSTR_API(jsreq), nullptr);
	// return CALL_API_PROXY(AsyncCall, JNISTR2CSTR_API(jstr_key), JNISTR2BYTECSTR_API(jstr_req), nullptr);
}

JNIFUN(jboolean, createCalc)(ENV_OBJ, jobject cb_instance)
{
    return CALL_API_PROXY(createCalc, cb_instance, JNISTR2CSTR_API(basepath));
}

JNIFUN(jboolean, destroyCalc)(ENV_OBJ)
{
	return CALL_API_PROXY(destroyCalc);
}