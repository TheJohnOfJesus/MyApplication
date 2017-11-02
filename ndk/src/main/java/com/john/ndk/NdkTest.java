package com.john.ndk;

import java.util.ArrayList;

/**
 * Created by jjtx-dev106 on 2017/10/23.
 */

public class NdkTest {
//    javac com.john.ndk.NdkTest
//    javah -jni com.john.ndk.NdkTest
    static {
//        System.loadLibrary("native-lib");
        System.loadLibrary("test-lib");
    }
    private static NdkTest mInstance;
    public static synchronized NdkTest getInstance(){
        if (mInstance==null)mInstance=new NdkTest();
        return mInstance;
    }
    public native String stringFromJNI();
    public native String stringFromJNIA(String in);
    public native String stringFromJNIB(ArrayList<String> arraylist);
    public native String stringFromJNIC(ArrayList<Entity> arraylist);
    public native String stringFromJNID(ArrayList<Integer> arraylist);
    public native String stringFromJNIE(String[] arraylist);

}
class Entity{

}
