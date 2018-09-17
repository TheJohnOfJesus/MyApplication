package com.john.m2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.john.m2048.entity.ACTION;
import com.john.m2048.entity.Cube;

import java.util.Random;

/**
 * Created by John on 2017/10/10.
 */

public class MGameView extends SurfaceView implements SurfaceHolder.Callback, TouchHelper.OnGestureListener {
    private final String TAG = "xxx";
    private SurfaceHolder mHolder;
    private volatile boolean isDrawing = false;
    private volatile boolean isPrepared = false;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;
    private volatile Cube[][] cubs;
    TouchHelper touchHelper;

    public MGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchHelper.onTouch(event);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isPrepared = true;
        if (width != 0 && height != 0) {
            draw();
        }
        Log.i(TAG, "surfaceCreated: ");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isPrepared = false;
        Log.i(TAG, "surfaceDestroyed: ");
    }

    private void init() {
        touchHelper = new TouchHelper(this);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public void onGesture(ACTION action) {
        switch (action) {
            case UP:
                break;
            case DOWN:
                break;
            case LEFT:
                Logic.left(cubs);
                if (!isDrawing) {
                    draw();
                    generateCube(true, true);
                    draw();
                }
                break;
            case RIGHT:
                break;
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            drawCube(mCanvas);
            isDrawing = false;
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
        int left = 0, top;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < column; j++) {
                mPaint.setColor(Color.GREEN);
                left = leftMargin + j * (cubeWidth + spacing);
                top = startTopMargin + leftMargin + i * (cubeWidth + spacing);
                rectF = new RectF(left, top, left + cubeWidth, top + cubeWidth);
                //画圆角矩形背景
                mCanvas.save();
                mCanvas.drawRoundRect(rectF, cubeWidth / 7, cubeWidth / 7, mPaint);
                mCanvas.restore();
                //画文字
                mCanvas.save();
                mPaint.setColor(Color.BLACK);
                mPaint.setTextSize(cubeWidth * 2 / 3);
                int origin = left + (int) ((cubeWidth - mPaint.measureText("2")) / 2);
                int baseLine = (int) (top + (cubeWidth / 2 - mPaint.getFontMetrics().bottom / 2 - mPaint.getFontMetrics().top / 2));
                mCanvas.drawText(cubs[i][j].text == 0 ? "" : String.valueOf(cubs[i][j].text), origin, baseLine, mPaint);
                mCanvas.restore();
            }
        }
    }

    private void initBaseData() {
        spacing = DisplayUtil.dp2px(getContext(), 16);
        leftMargin = DisplayUtil.dp2px(getContext(), 32);
        int totalCubWith = width - leftMargin * 2 - spacing * (column - 1);
        cubeWidth = totalCubWith / column;
        if (cubeWidth * 4 != totalCubWith) {
            leftMargin += ((totalCubWith - cubeWidth) / 2);
        }
        startTopMargin = (height - width) / 2;
        cubs = new Cube[column][column];
        initCubes(cubs);
    }

    private void initCubes(Cube[][] cubes) {
        for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes[i].length; j++) {
                cubes[i][j] = new Cube();
            }
        }
        generateCube(true, true);
    }

    private final int column = 4;
    private int height, width, cubeWidth;
    private int spacing, leftMargin, startTopMargin;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        if (width != 0 && height != 0) {
            initBaseData();
            if (isPrepared) {
                draw();
            }
        }
    }

    private void generateCube(boolean first, boolean second) {
        Random random = new Random();
        int x = random.nextInt(column);
        int y = random.nextInt(column);
        if (first) {
            if (cubs[x][y].text != 0) {
                generateCube(true, false);
            } else {
                cubs[x][y].text = random.nextInt(column) % column == 0 ? 2 : 4;
            }
        }
        if (second) {
            if (cubs[x][y].text != 0) {
                generateCube(false, true);
            } else {
                cubs[x][y].text = random.nextInt(column) % column == 0 ? 2 : 4;
            }
        }
    }
}
