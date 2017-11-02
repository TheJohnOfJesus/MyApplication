package com.example.john.myapplication.putlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by John on 2017/8/13.
 */

public class TestLinearLayout extends LinearLayout {
    private final int topMargin = 20;
    private final int leftMargin = 20;
    private int mHeight = 0;

    public TestLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int top = 0;
        View view;
        for (int i = 0; i < getChildCount(); i++) {
            view = getChildAt(i);
            view.layout(leftMargin, (top += topMargin), leftMargin + view.getMeasuredWidth(), top + view.getMeasuredHeight());
            top += view.getHeight();
        }
        mHeight = top;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
////        int scrollDist=10;
//        int distY=t-oldt;
//        int distX=l-oldl;
//        int top=0;
//        top=distY;
//        View view;
//        for (int i=0;i<getChildCount();i++){
//            view=getChildAt(i);
//            if (view.getVisibility()==VISIBLE)
//            view.layout(20+distX,(top+=30),20+view.getMeasuredWidth(),top+view.getMeasuredHeight());
//            top+=view.getHeight();
//        }
    }

    private int topScroll = 0, top;
    float downX = 0, downY = 0;
    boolean doAddScroll = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        return super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                top = topScroll;
                Log.i("xxx", "ACTION_DOWN:  (" + event.getX() + " , " + event.getY() + ")");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("xxx", "ACTION_MOVE:  (" + event.getX() + " , " + event.getY() + ")");
                top = topScroll;
                float scrollX, scrollY;
                scrollX = event.getX() - downX;
                scrollY = event.getY() - downY;
                float distViewX, distViewY = 0;
                View view;
//                top = 0;
                for (int i = 0; i < getChildCount(); i++) {
                    view = getChildAt(i);
                    if (view.getVisibility() == VISIBLE) {
                        if (topScroll > mHeight) {
//                            top -= topMargin;
                        } else {
                            top += topMargin;
                            distViewY = top + scrollY;
                            if ((distViewY <= 1280 && distViewY > 0) || (distViewY <= 0 && distViewY >= -1280 && (view.getMeasuredHeight() +
                                    distViewY) > 0)) {
                                view.layout(leftMargin, (int) distViewY, leftMargin + view.getMeasuredWidth(), (int) distViewY + view
                                        .getMeasuredHeight());
                                doAddScroll = true;
                            } else {
                                layoutViewToUnknow(true, view);
                            }
                            top += view.getMeasuredHeight();
                        }
                    }
                }
//                topScroll += (int) scrollY;
//                topScroll += (int) distViewY;
                break;
            case MotionEvent.ACTION_UP:
                if (doAddScroll) {
                    float tempScroll =topScroll+ event.getY() - downY;
                    if (Math.abs(tempScroll) > 1280) {
                        topScroll = 1280;
                    } else {
                        topScroll = (int)tempScroll;
                    }
                } else {
//                    topScroll=1280;
                }
                doAddScroll = false;
                downX = 0;
                downY = 0;
                Log.i("xxx", "ACTION_UP:  (" + event.getX() + " , " + event.getY() + ")     topScroll:" + topScroll);
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void layoutViewToUnknow(boolean top, View view) {
        if (top) {
            view.layout(0, -view.getMeasuredHeight(), 0, view.getMeasuredHeight());
        } else {
            view.layout(0, 1280, 0, 1280 + view.getMeasuredHeight());
        }
    }
}
