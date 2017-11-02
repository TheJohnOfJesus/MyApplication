package com.example.john.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by John on 2017/4/6.
 */

public class StatusBarUtil {
    public static int getStatusBarHeight(Context context){
        int result=0;
        int resourceId=context.getResources().getIdentifier("status_bar_height","dimen","android");
        if (resourceId>0){
            result=context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 添加状态栏
     * @param context
     * @param resourceId
     */
    public static void addStatusBarView(Context context,int resourceId){

        ViewGroup decorView= (ViewGroup) ((Activity)context).findViewById(android.R.id.content);
        if (decorView.getChildCount()==1){
            View view = new View(context);
            view.setBackgroundColor(context.getResources().getColor(resourceId));
//        view.setBackgroundColor(resourceId);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getStatusBarHeight(context));
            decorView.addView(view,params);
        }else{
            decorView.getChildAt(1).setBackgroundColor(context.getResources().getColor(resourceId));
        }
    }
}
