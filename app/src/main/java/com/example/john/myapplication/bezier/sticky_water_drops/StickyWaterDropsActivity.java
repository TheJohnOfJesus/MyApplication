package com.example.john.myapplication.bezier.sticky_water_drops;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.john.myapplication.R;
import com.example.john.myapplication.bezier.sticky_water_drops.view.PullView;

public class StickyWaterDropsActivity extends Activity {

    PullView pullView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_water_drops);
        pullView = (PullView) findViewById(R.id.pullView);
    }

    private float mTouchStartY;
    private static final float TOUCH_MOVE_MAX_Y = 300;
    private static final float SLIPPAGE_FACTOR = 0.5f;// 拖动阻力因子 0~1

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                if (y >= mTouchStartY) {
                    float moveSize = (y - mTouchStartY) * SLIPPAGE_FACTOR;
                    float progress = moveSize >= TOUCH_MOVE_MAX_Y ? 1 : (moveSize / TOUCH_MOVE_MAX_Y);
                    pullView.setProgress(progress);
                }
                return true;
            case MotionEvent.ACTION_UP:
                pullView.release();
                return true;
            default:
                break;

        }
        return false;
    }
}
