package com.example.john.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by John on 2017/8/17.
 */

public class MNestedLinearLayout extends LinearLayout{
    public MNestedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.i("MNestedLinearLayout", "onNestedScroll:   dxConsumed:"+dxConsumed+"   dyConsumed:"+dyConsumed+"   dxUnconsumed:"+dxUnconsumed+"   dyUnconsumed:"+dyUnconsumed);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("MNestedLinearLayout", "onScrollChanged:   l:"+l+"   t:"+t+"   oldl:"+oldl+"   oldt:"+oldt);
    }
}
