package com.example.john.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.john.myapplication.utils.DisplayUtil;

/**
 * Created by John on 2017/8/21.
 */

public class ShadowView extends View {
    private Context context = null;
    private float density = 1.0f;
    private int z = 10;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int startShadowColor = 0x00999999;
    private int endShadowColor = 0x66999999;

    public ShadowView(Context context) {
        this(context, null);
    }


    public ShadowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO自动生成的构造函数存根
        this.context = context;
        density = DisplayUtil.getDensity(context);
    }

    protected int getInterpolationColor(int c1, int c2, int ratio) {
        ratio = ratio < 0 ? 0 : ratio;
        ratio = ratio > 255 ? 255 : ratio;

        int r1 = Color.red(c1);
        int g1 = Color.green(c1);
        int b1 = Color.blue(c1);
        int a1 = Color.alpha(c1);

        int r2 = Color.red(c2);
        int g2 = Color.green(c2);
        int b2 = Color.blue(c2);
        int a2 = Color.alpha(c2);

        int r = (r1 * (255 - ratio) + r2 * ratio) >> 8;
        int g = (g1 * (255 - ratio) + g2 * ratio) >> 8;
        int b = (b1 * (255 - ratio) + b2 * ratio) >> 8;
        int a = (a1 * (255 - ratio) + a2 * ratio) >> 8;

        return Color.argb(a, r, g, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getMeasuredWidth();
        int h = getMeasuredHeight() - z;
        int radius = (w < h ? w : h) >> 1;
        float x = radius;
        float y = radius;

        // 阴影
        int step = 255 / z;
        for (int i = z; i > 0; i--) {
            int shadowColor = getInterpolationColor(startShadowColor, endShadowColor, step * (z - i));
            paint.setColor(shadowColor);
            canvas.drawCircle(x, y + i, radius, paint);
        }

        // 设定颜色
        paint.setColor(0xffffffff);
//        paint.setColor(0xffba68c8);
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO自动生成的方法存根
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (56 * density);
            if (specMode == MeasureSpec.AT_MOST) {
                result = result < specSize ? result : specSize;
            }
        }
        return result;
    }


    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (56 * density);
            if (specMode == MeasureSpec.AT_MOST) {
                result = result < specSize ? result : specSize;
            }
        }
        return result + z;
    }

    private String getModeName(int specMode) {
        // TODO自动生成的方法存根
        if (specMode == MeasureSpec.UNSPECIFIED) {
            return "UNSPECIFIED";
        } else if (specMode == MeasureSpec.EXACTLY) {
            return "EXACTLY";
        } else if (specMode == MeasureSpec.AT_MOST) {
            return "AT_MOST";
        }
        return "";

    }
}
