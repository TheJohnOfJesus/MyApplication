package com.example.john.myapplication.elevation_translationz;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.RelativeLayout;
import android.support.v7.widget.AppCompatTextView;

import com.example.john.myapplication.R;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class ElevationActivity extends Activity {

    private final String TAG="xxx";

    @BindView(R.id.red)
    AppCompatTextView red;
    @BindView(R.id.green)
    AppCompatTextView green;
    @BindView(R.id.blue)
    com.example.john.myapplication.view.ShadowView blue;
    @BindView(R.id.activity_elevation)
    RelativeLayout activityElevation;
    private static Context sContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevation);
        ButterKnife.bind(this);
        sContext=this;

//        red.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.d(TAG, "ACTION_DOWN on view.");
//                        red.setTranslationZ(120);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.d(TAG, "ACTION_UP on view.");
//                        red.setTranslationZ(0);
//                        break;
//                    default:
//                        return false;
//                }
//                return false;
//            }
//        });
    }

    @OnClick({R.id.red, R.id.green, R.id.blue, R.id.activity_elevation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.red:
                break;
            case R.id.green:
                break;
            case R.id.blue:
                break;
            case R.id.activity_elevation:
                break;
        }
    }
}
