package com.example.john.myapplication.service;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.john.myapplication.JohnApplication;

/**
 * Created by John on 2017/10/30.
 */

public class DialogService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
//        startActivity();
    }
}
