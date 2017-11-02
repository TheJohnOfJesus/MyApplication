package com.example.john.myapplication.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by jjtx-dev106 on 2017/10/23.
 */

public class MyImpl extends Binder implements IMyAidlInterface {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public String testAIDL(String str, String str1) throws RemoteException {
        return null;
    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
