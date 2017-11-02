package com.example.john.myapplication;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.example.john.myapplication.manager.DBManager;
import com.example.john.myapplication.push.PushSdk;
import com.example.john.myapplication.utils.CrashHandler;

/**
 * Created by jjtx-dev106 on 2017/7/27.
 */

public class JohnApplication extends Application {
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());
        PushSdk.initSdk(getApplicationContext(),new Handler());
        DBManager.getInstance().init(getApplicationContext());
        sContext=this;
    }
    public static Context getInstance(){
        return sContext;
    }
}
