package com.example.john.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by John on 2017/6/30.
 */

public class SplashActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
