package com.john.m2048;

import android.graphics.Point;
import android.view.MotionEvent;

import com.john.m2048.entity.ACTION;

/**
 * @Description
 * @Author John.
 * @Date On2018/9/17
 * @Updater John.
 **/
public class TouchHelper {
    private Point point = new Point();
    private OnGestureListener mListener;

    public TouchHelper(OnGestureListener listener) {
        mListener = listener;
    }

    public void onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                point.set((int) event.getRawX(), (int) event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();
                int offsetX = x - point.x;
                int offsetY = y - point.y;
                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    //横向偏移量要大于纵向偏移量
                    if (offsetX > 0) {
                        action(ACTION.RIGHT);
                    } else {
                        action(ACTION.LEFT);
                    }
                } else {
                    //纵向偏移量要大于横向偏移量
                    if (offsetY > 0) {
                        action(ACTION.DOWN);
                    } else {
                        action(ACTION.UP);
                    }
                }
                break;
        }
    }

    private void action(ACTION action) {
        if (mListener != null) mListener.onGesture(action);
    }

    interface OnGestureListener {
        void onGesture(ACTION action);
    }
}
