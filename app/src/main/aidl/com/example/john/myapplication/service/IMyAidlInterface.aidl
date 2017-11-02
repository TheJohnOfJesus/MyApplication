// IMyAidlInterface.aidl
package com.example.john.myapplication.service;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    String testAIDL(String str,String str1);
//    void testAIDL(String str);
//    void testAIDL(String str,int intValue,Double dValue);
}
