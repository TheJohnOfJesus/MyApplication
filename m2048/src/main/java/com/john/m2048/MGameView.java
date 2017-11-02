package com.example.john.m2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by John on 2017/10/10.
 */

public class MGameView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private boolean isDrawing = false;
    private LoopThread thread;
    private Canvas mCanvas;
    private Paint mPaint;
    private Cube[][] clubs;
    private GestureDetector mGestureDetector;

    public MGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        init();
        mGestureDetector = new GestureDetector(context, new MyGestureDetector());
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        final int FLING_MIN_DISTANCE = 50;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();

//            if (x > FLING_MIN_DISTANCE && Math.abs(velocityX) > Math.abs(velocityY)) {
//                action(ACTION.RIGHT);
//
//            } else if (x < -FLING_MIN_DISTANCE && Math.abs(velocityX) > Math.abs(velocityY)) {
//                action(ACTION.LEFT);
//
//            } else if (y > FLING_MIN_DISTANCE && Math.abs(velocityX) < Math.abs(velocityY)) {
//                action(ACTION.DOWM);
//
//            } else if (y < -FLING_MIN_DISTANCE && Math.abs(velocityX) < Math.abs(velocityY)) {
//                action(ACTION.UP);
//            }
            return true;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
        initBaseData();
        thread = new LoopThread();
    }

    class LoopThread extends Thread {
        @Override
        public void run() {
            while (isDrawing) {
//                draw();
//                x += 3;
//                y = (int) (100 * Math.sin(x * Math.PI / 180) + 400);
//                mPath.lineTo(x, y);
//
//                try {
//                    Thread.sleep(3000);
//                    isDrawing = false;
//                } catch (Exception e) {
//
//                }
            }
        }
    }

    private void initBaseData() {
//        width = (int) DisplayUtil.getDeviceWidthInPix(getContext());
//        height = (int) DisplayUtil.getDeviceHeightInPix(getContext());
//        width = (int) DisplayUtil.getDeviceWidthInPix(getContext());
//        height = (int) DisplayUtil.getDeviceHeightInPix(getContext());

        spacing = DisplayUtil.dp2px(getContext(), 16);
        leftMargin = DisplayUtil.dp2px(getContext(), 32);
        int totalCubWith = width - leftMargin * 2 - spacing * (column - 1);
        cubeWidth = totalCubWith / column;
        if (cubeWidth * 4 != totalCubWith) {
            leftMargin += ((totalCubWith - cubeWidth) / 2);
        }
        startTopMargin = (height - width) / 2;
        clubs = new Cube[column][column];
    }

    private final int column = 4;
    private int height, width, cubeWidth;
    private int spacing, leftMargin, startTopMargin;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredHeight();
        height = getMeasuredHeight();
    }
}
