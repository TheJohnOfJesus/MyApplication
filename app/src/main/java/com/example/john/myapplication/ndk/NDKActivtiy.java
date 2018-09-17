package com.example.john.myapplication.ndk;

import android.app.Activity;
import android.os.Bundle;

import com.example.john.myapplication.R;

public class NDKActivtiy extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndkactivtiy);
//        ((TextView)findViewById(R.id.tv_ndk_text)).setText(NdkTest.getInstance().stringFromJNI());
//        ((TextView)findViewById(R.id.tv_ndk_text)).setText(NdkTest.getInstance().stringFromJNI()+"&&&&&&"+NdkTest.getInstance().stringFromJNIA("indata"));
    }

}