package com.example.john.myapplication.m2048;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.john.myapplication.R;
import com.john.m2048.MGameView;

public class MSurfaceActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ActivityTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msurface1);
        MGameView gameView = (MGameView) findViewById(R.id.mv_game);
    }
}
