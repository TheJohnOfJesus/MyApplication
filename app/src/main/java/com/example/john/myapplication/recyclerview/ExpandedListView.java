package com.example.john.myapplication.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by John on 2017/3/24.
 * <br>解决ScrollView嵌套Listview，ListView不能全部显示问题<br/>
 */

public class ExpandedListView extends ListView {
    public ExpandedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST));
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY/2);
    }
}
