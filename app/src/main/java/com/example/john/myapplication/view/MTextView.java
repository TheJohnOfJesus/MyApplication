package com.example.john.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by John on 2017/8/31.
 */

public class MTextView extends View {
    private int textColor= Color.WHITE;
    private int textSize=15;
    private Paint mPaint;
    public MTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        Typeface.create()
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drate;
    }
}
