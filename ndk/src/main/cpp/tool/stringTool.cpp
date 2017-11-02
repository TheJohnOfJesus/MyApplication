#include "string"
#include "jni.h"
std::string jstring2str(JNIEnv* env1, jstring jstr) {
    char*   rtn   =   NULL;

    jclass   clsstring   =   env1->FindClass("java/lang/String");
    jstring   strencode   =   env1->NewStringUTF("GB2312");
    jmethodID   mid   =   env1->GetMethodID(clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
    jbyteArray   barr=   (jbyteArray)env1->CallObjectMethod(jstr,mid,strencode);
    jsize   alen   =   env1->GetArrayLength(barr);
    jbyte*   ba   =   env1->GetByteArrayElements(barr,JNI_FALSE);
    if(alen   >   0)
    {
        rtn   =   (char*)malloc(alen+1);
        memcpy(rtn,ba,alen);
        rtn[alen]=0;
    }
    env1->ReleaseByteArrayElements(barr,ba,0);
    std::string stemp(rtn);
    free(rtn);
    return   stemp;
}