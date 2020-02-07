#ifndef _JVT_SDK_H_
#define _JVT_SDK_H_
#include <jni.h>


#define JNIFUN_DECL(RETTYPR, funname) static RETTYPR funname
#define ENV_OBJ JNIEnv* env, jobject obj


class SdkJniApi : public spotify::jni::JavaClass
{
public:
    SdkJniApi() : JavaClass() {}
    SdkJniApi(JNIEnv *env) : JavaClass(env) {
        initialize(env);
    }
    ~SdkJniApi() {}

    void mapFields() {}
    const char* getCanonicalName() const {return MAKE_CANONICAL_NAME("com/im/sdk", ImsdkNative);}
    void initialize(JNIEnv *env);

private:
    /*============================== 功能性接口 ==============================*/
	/*创建计算器
	@param [in] cb_instance		回调对象: 由APP负责具体实现
	@return true=成功，false=失败
	*/
    JNIFUN_DECL(jboolean, createCalc)(ENV_OBJ, jobject cb_instance);

	/*释放资源
	@return true=成功，false=失败
	*/
	JNIFUN_DECL(jboolean, destroyCalc)(ENV_OBJ);

	/*============================== 业务性接口 ==============================*/
	/*计算器操作
	@param[in] jskey	操作键，json格式，如：{"opcode": 101，"id": 123}
	@param[in] jsreq	APP请求内容，json格式，见protocol
	@return true = 成功，false = 失败: 失败原因为json格式不匹配
	*/
	JNIFUN_DECL(jboolean, asyncCall)(ENV_OBJ, jstring jskey, jstring jsreq);
};

#endif//_JVT_SDK_H_