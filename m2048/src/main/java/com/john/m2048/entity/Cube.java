package com.john.m2048.entity;

import android.graphics.Point;
import android.graphics.RectF;

/**
 * Created by John on 2017/10/10.
 */

public class Cube {
    public Point position;
    public RectF shape;
    public int colorBg;
    public int text;//显示的数值
    public int corner;//圆角值
    Cube left;
    Cube right;
}
