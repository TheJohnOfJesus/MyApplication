package com.example.john.java;

import android.graphics.Path;

/**
 * 获取各种几何形状,包括但不限于二维三维形状
 * Created by John on 2017/10/27.
 */

public class ShapeUtils {

    /**
     * 获取多边形
     * <p>内角和=180*(n-2)
     * <p>外角和=360
     * <p>外角=180*(n-2)/n
     *
     * @param lineCount 边的数量(n>2)
     * @return lineCount<3时,返回null,否则返回正常path
     */
    public static Path getPolygon(int lineCount) {
        if (lineCount < 3) return null;
        Path path = new Path();
        double s = 0;
        int n = 10;
        int x[] = new int[]{};
        int y[] = new int[]{};
        for (int i = 0; i < n; i++) s += ((double) x[i] * y[(i + 1) % n] - (double) x[(i + 1) % n] * y[i]) / 2;
        s = Math.abs(s);
        return path;
    }

    /**
     * 获取一个五角星
     * <p>五角星的外接圆的中心点为坐标系的(0,0)点,右+&&下+
     * <p>起始坐标点在(0,r)位置
     *
     * @param dx 相对外接圆心,在X轴的偏移量
     * @param dy 相对外接圆心,在Y轴的的偏移量
     * @param r  外接圆的半径
     * @return
     */
    public static Path getFivePointStar(float dx, float dy, float r) {
        Path path = new Path();
        float sin18 = getSin(18) * r;
        float cos18 = getCos(18) * r;
        float sin54 = getSin(54) * r;
        float cos54 = getCos(54) * r;
        path.moveTo(0 + dx, -r + dy);//A1
        path.lineTo(cos54 + dx, sin54 + dy);//A3
        path.lineTo(-cos18 + dx, -sin18 + dy);//A5
        path.lineTo(cos18 + dx, -sin18 + dy);//A2
        path.lineTo(-cos54 + dx, sin54 + dy);//A4
        path.lineTo(0 + dx, -r + dy);//A1
        path.close();
        return path;
    }

    private static float getSin(double degree) {
        return (float) Math.sin(getDegree(degree));
    }

    private static float getCos(double degree) {
        return (float) Math.cos(getDegree(degree));
    }

    private static float getTan(double degree) {
        return (float) Math.tan(getDegree(degree));
    }

    private static double getDegree(double degree) {
        return degree * Math.PI / 180;
    }
}
