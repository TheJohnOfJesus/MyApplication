package com.example.john.myapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;

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
        //        PushSdk.initSdk(getApplicationContext(),new Handler());
        //        DBManager.getInstance().init(getApplicationContext());
        sContext = this;
        initTBS();
    }

    public static Context getInstance() {
        return sContext;
    }

    private void initTBS() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
//        QbSdk.setDownloadWithoutWifi(true);//非wifi条件下允许下载X5内核
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.i("xxx", "onCoreInitFinished: "+arg0);
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//                Log.i("xxx", "onViewInitFinished: ");
//            }
//        };
//        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
}
