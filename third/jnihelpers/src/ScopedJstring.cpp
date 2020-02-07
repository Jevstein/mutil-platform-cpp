#include "ScopedJstring.h"

#include <stddef.h>
#include <assert.h>

jstring ScopedJstring::string2jstring(JNIEnv *env, const std::string &cstr){
	assert(env);
	if (env->ExceptionOccurred()) {
		return NULL;
	}

	jclass strClass = env->FindClass("java/lang/String");
	jmethodID ctorID = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");

	std::string::size_type iOutSize = cstr.length();
	jbyteArray bytes = env->NewByteArray((jsize)iOutSize);
	env->SetByteArrayRegion(bytes, 0, (jsize)iOutSize, (jbyte*)(cstr.data()));
	jstring encoding = env->NewStringUTF("utf-8");

	jstring jstr = (jstring) env->NewObject(strClass, ctorID, bytes, encoding);

	env->DeleteLocalRef(bytes);
	env->DeleteLocalRef(encoding);
	env->DeleteLocalRef(strClass);

	return jstr;
}

std::string ScopedJstring::Jstring2ByteArrForCstring(JNIEnv* env, jstring jsrc)
{
	std::string strRet;
	if(!jsrc){
		return strRet;
	}

	jclass clsstring = env->FindClass("java/lang/String");
	jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
	jbyteArray barr = (jbyteArray)env->CallObjectMethod(jsrc, mid, ScopedJstring(env, "UTF-8").GetJstr());

	int len = env->GetArrayLength(barr);
	jbyte *bytearr = env->GetByteArrayElements(barr, JNI_FALSE);

	strRet.append((const char*)bytearr, len);

	env->ReleaseByteArrayElements(barr, bytearr, 0);
	env->DeleteLocalRef(clsstring);

	return strRet;
}

ScopedJstring::ScopedJstring(JNIEnv* _env, jstring _jstr)
	: env_(_env)
	, jstr_((jstring)_env->NewLocalRef(_jstr))
	, char_(NULL)
	, jstr2char_(true) {
		assert(env_);
		cstr_.clear();
		if (NULL == env_ || NULL == jstr_) {
			return;
		}

		if (env_->ExceptionOccurred()) {
			return;
		}

		char_ =  env_->GetStringUTFChars(jstr_, NULL);
		jsize str_len = env_->GetStringUTFLength(jstr_);
		cstr_.append(char_, str_len);
}

ScopedJstring::ScopedJstring(JNIEnv* _env, const char* _char)
	: env_(_env)
	, jstr_(NULL)
	, char_(_char)
	, jstr2char_(false) {

		assert(env_);
		if (NULL == env_ || NULL == _char) {
			return;
		}

		if (env_->ExceptionOccurred()) {
			return;
		}

		jclass strClass = env_->FindClass("java/lang/String");
		jmethodID ctorID = env_->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");

		jbyteArray bytes = env_->NewByteArray((jsize)strlen(char_));
		env_->SetByteArrayRegion(bytes, 0, (jsize)strlen(char_), (jbyte*) char_);
		jstring encoding = env_->NewStringUTF("utf-8");

		jstr_ = (jstring) env_->NewObject(strClass, ctorID, bytes, encoding);

		env_->DeleteLocalRef(bytes);
		env_->DeleteLocalRef(encoding);
		env_->DeleteLocalRef(strClass);
}

ScopedJstring::~ScopedJstring() {
	if (NULL == env_ || NULL == jstr_ || NULL == char_) {
		return;
	}

	if (env_->ExceptionOccurred()) {
		return;
	}

	if (jstr2char_) {
		env_->ReleaseStringUTFChars(jstr_, char_);
	}
	env_->DeleteLocalRef(jstr_);
}

const char* ScopedJstring::GetChar() const {
	if (env_->ExceptionOccurred()) {
		return NULL;
	}

	return char_;
}

std::string ScopedJstring::GetCStr() const{
	if (env_->ExceptionOccurred()) {
		return "";
	}

	return cstr_;
}

jstring ScopedJstring::GetJstr() const {
	if (env_->ExceptionOccurred()) {
		return NULL;
	}

	return jstr_;
}

