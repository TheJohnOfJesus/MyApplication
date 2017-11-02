package com.example.john.myapplication.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 尺寸相关工具类
 * Created by John on 2017/3/17.
 */

public class DisplayUtil {
    public static int px2dp(Context context,float pxValue){
        return (int)(pxValue/context.getResources().getDisplayMetrics().density+0.5f);
    }
    public static int dp2px(Context context,float dpValue){
        return (int)(dpValue*context.getResources().getDisplayMetrics().density+0.5f);
    }
    public static int px2sp(Context context,float pxValue){
        return (int)(pxValue/context.getResources().getDisplayMetrics().scaledDensity+0.5f);
    }
    public static int sp2px(Context context,float spValue){
        return (int)(spValue*context.getResources().getDisplayMetrics().scaledDensity+0.5f);
    }
    public static float getDensity(Context context){
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
//        int densityDpi = mDisplayMetrics.densityDpi;
//        Log.d(TAG,"Screen Ratio: ["+width+"x"+height+"],density="+density+",densityDpi="+densityDpi);
//        Log.d(TAG,"Screen mDisplayMetrics: "+mDisplayMetrics);
        return density;
    }
    public static float getDeviceWidthInPix(Context context){
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        return mDisplayMetrics.widthPixels;
    }
    public static float getDeviceHeightInPix(Context context){
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        return mDisplayMetrics.heightPixels;
    }

}
