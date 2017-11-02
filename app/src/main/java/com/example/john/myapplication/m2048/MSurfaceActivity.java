package com.example.john.myapplication.m2048;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.john.myapplication.R;
import com.example.john.myapplication.utils.DisplayUtil;

import java.util.Random;

public class MSurfaceActivity extends Activity implements SurfaceHolder.Callback {
    private SurfaceView surfaceView;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;
    private int x = 0;
    private int y = 0;
    private boolean isDrawing = false;
    private Cube[][] clubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msurface);
        surfaceView = (SurfaceView) findViewById(R.id.activity_msurface);
        mHolder = surfaceView.getHolder();
        mHolder.addCallback(this);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPath.moveTo(0, 400);
        isDrawing = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isDrawing) {
                    draw();
                    x += 3;
                    y = (int) (100 * Math.sin(x * Math.PI / 180) + 400);
                    mPath.lineTo(x, y);

                    try {
                        Thread.sleep(3000);
                        isDrawing = false;
                    } catch (Exception e) {

                    }
                }
            }
        }).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            drawCube(mCanvas);
//            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    private void drawCube(Canvas mCanvas) {
        // SurfaceView背景
        mCanvas.save();
        mCanvas.drawColor(Color.WHITE);
        mCanvas.restore();
        mCanvas.save();
        mPaint.setColor(Color.CYAN);
        mCanvas.drawRect(new Rect(0, startTopMargin, width, startTopMargin + width), mPaint);
        mCanvas.restore();
        RectF rectF;
        int left=0,top;
        for (int i=0;i<column;i++){
            for (int j=0;j<column;j++){
                mPaint.setColor(Color.GREEN);
                left=leftMargin+j*(cubeWidth+spacing);
                top=startTopMargin + leftMargin+i*(cubeWidth+spacing);
                rectF=new RectF(left,top , left + cubeWidth, top + cubeWidth);
                mCanvas.save();
                mCanvas.drawRoundRect(rectF,cubeWidth/7,cubeWidth/7, mPaint);
                mCanvas.restore();
                mCanvas.save();
                mPaint.setColor(Color.BLACK);
                mPaint.setTextSize(cubeWidth * 2 / 3);
                int origin = left + (int)((cubeWidth - mPaint.measureText("2")) / 2);
                int baseLine = (int) (top +(cubeWidth/2 - mPaint.getFontMetrics().bottom/2 - mPaint.getFontMetrics().top/2));
                mCanvas.drawText("2", origin, baseLine, mPaint);
                mCanvas.restore();
            }
        }
    }

    //TODO
    private void generateCube() {
       Random random= new Random();
        int num= (int) Math.pow(2,random.nextInt(3)+1);//生成[0-3)间的整数->生成2,4,8三个数
    }

    class Cube {
        Point position;
        RectF shape;
        int colorBg;
        int num;
        int corner;
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
        initBaseData();
    }

    private void initBaseData() {
        width = (int) DisplayUtil.getDeviceWidthInPix(this);
        height = (int) DisplayUtil.getDeviceHeightInPix(this);

        spacing = DisplayUtil.dp2px(this, 16);
        leftMargin = DisplayUtil.dp2px(this, 32);
        int totalCubWith = width - leftMargin * 2 - spacing * (column - 1);
        cubeWidth = totalCubWith / column;
        if (cubeWidth * 4 != totalCubWith) {
            leftMargin += ((totalCubWith - cubeWidth) / 2);
        }
        startTopMargin = (height - width) / 2;
        clubs=new Cube[column][column];
    }

    private final int column = 4;
    private int height, width, cubeWidth;
    private int spacing, leftMargin, startTopMargin;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return surfaceView.onTouchEvent(event);
//        return super.onTouchEvent(event);
    }
}
