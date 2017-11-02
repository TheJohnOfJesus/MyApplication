package com.example.john.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by John on 2017/8/17.
 */

public class MNestedTextView extends TextView {
    public MNestedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//    @Override
//    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//        Log.i("MNestedTextView", "onNestedScroll:   dxConsumed:"+dxConsumed+"   dyConsumed:"+dyConsumed+"   dxUnconsumed:"+dxUnconsumed+"   dyUnconsumed:"+dyUnconsumed);
//    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("MNestedTextView", "onScrollChanged:   l:"+l+"   t:"+t+"   oldl:"+oldl+"   oldt:"+oldt);
    }
}
