package com.example.john.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by John on 2017/8/17.
 */

public class MNestedScrollView extends ScrollView {
    private int dynamicId;

    public MNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDynamicViewId(int dynamicId) {
        this.dynamicId = dynamicId;
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.i("MScrollView", "onNestedScroll:   dxConsumed:"+dxConsumed+"   dyConsumed:"+dyConsumed+"   dxUnconsumed:"+dxUnconsumed+"   dyUnconsumed:"+dyUnconsumed);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("MScrollView", "onScrollChanged:   l:"+l+"   t:"+t+"   oldl:"+oldl+"   oldt:"+oldt);
    }
}
