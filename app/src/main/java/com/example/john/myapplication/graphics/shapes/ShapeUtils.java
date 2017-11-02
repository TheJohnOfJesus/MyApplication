package com.example.john.myapplication.graphics.shapes;

import android.graphics.Path;

/**
 * Created by John on 2017/10/27.
 */

public class ShapeUtils {
    /**
     * 获取一个五边形</p>
     * 五边形的外接圆的中心点为坐标系的(0,0)点,右+&&下+
     * @param dx 相对外接圆心,在X轴的偏移量
     * @param dy 相对外接圆心,在Y轴的的偏移量
     * @param r 外接圆的半径
     * @return
     */
    public static Path getFivePointStar(float dx,float dy,float r){
        Path path=new Path();
        float sin18= getSin(18)*r;
        float cos18= getCos(18)*r;
        float sin54= getSin(54)*r;
        float cos54= getCos(54)*r;
        path.moveTo(0+dx,-r+dy);//A1
        path.lineTo(cos54+dx,sin54+dy);//A3
        path.lineTo(-cos18+dx,-sin18+dy);//A5
        path.lineTo(cos18+dx,-sin18+dy);//A2
        path.lineTo(-cos54+dx,sin54+dy);//A4
        path.lineTo(0+dx,-r+dy);//A1
        return path;
    }
    private static float getSin(double degree){
        return (float) Math.sin(degree * Math.PI / 180);
    }
    private static float getCos(double degree){
        return (float) Math.cos( degree* Math.PI / 180);
    }
    private static float getTan(double degree){
        return (float) Math.tan( degree* Math.PI / 180);
    }
}
