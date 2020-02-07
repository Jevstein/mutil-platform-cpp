#ifndef _JNI_SCOPED_JSTRING_H_
#define _JNI_SCOPED_JSTRING_H_

#include <jni.h>
#include <string>

class ScopedJstring {
public:
	static jstring string2jstring(JNIEnv *env, const std::string &cstr);
	static std::string Jstring2ByteArrForCstring(JNIEnv* env, jstring jsrc);

public:
	ScopedJstring(JNIEnv* _env, jstring _jstr);
	ScopedJstring(JNIEnv* _env, const char* _char);

	~ScopedJstring();

	const char* GetChar() const;
	std::string GetCStr() const;
	jstring GetJstr() const;

private:
	ScopedJstring();
	ScopedJstring(const ScopedJstring&);
	ScopedJstring& operator=(const ScopedJstring&);

private:
	JNIEnv* env_;
	jstring jstr_;
	const char* char_;
	bool jstr2char_;
	std::string cstr_;
};

#endif /* _JNI_SCOPED_JSTRING_H_ */
