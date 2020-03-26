package com.hebccc.multimedia.video;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description $desc$
 * @date 2019/4/18 14:25
 */
public class TinyWindow extends JzvdStd {
    private final ViewMoveHolder holder = new ViewMoveHolder();
    private int lastScreenMode=SCREEN_WINDOW_TINY;

    public TinyWindow(Context context) {
        super(context);
    }

    public TinyWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setUp(JZDataSource jzDataSource, int screen, JZMediaInterface jzMediaInterface) {
        super.setUp(jzDataSource, screen, jzMediaInterface);
    }

    /**
     * 转到当前activity的最上面
     */
    public void goToFront() {
        if (currentState == CURRENT_STATE_NORMAL || currentState == CURRENT_STATE_ERROR || currentState == CURRENT_STATE_AUTO_COMPLETE ||
                currentScreen == SCREEN_WINDOW_TINY)
            return;
        //将当前
        ViewGroup vg = (ViewGroup) getParent();
        vg.removeView(this);
        Jzvd jj = cloneMe();
        vg.addView(jj);
        CONTAINER_LIST.add(vg);
        holder.decorView = (ViewGroup) (JZUtils.scanForActivity(getContext())).getWindow().getDecorView();//和他也没有关系
        float sWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        float sHeight = getContext().getResources().getDisplayMetrics().heightPixels;
        holder.xSpace = sWidth / 10;
        float viewWidth = holder.xSpace * 9;
        float viewHeight = viewWidth / 4 * 3;
        holder.ySpace = sHeight - viewHeight;
        holder.params = new LayoutParams((int) viewWidth, (int) viewHeight);
        holder.params.leftMargin = (int) holder.xSpace / 2;
        holder.params.topMargin = (int) (sHeight - viewHeight) / 2;
        //添加滑动事件等
        holder.decorView.addView(this, holder.params);
        setScreenTiny();
    }

    @Override
    public void gotoScreenNormal() {
        if (lastScreenMode == SCREEN_WINDOW_TINY) {
            gobakFullscreenTime = System.currentTimeMillis();//退出全屏
            ViewGroup vg = (ViewGroup) (JZUtils.scanForActivity(getContext())).getWindow().getDecorView();
            vg.removeView(this);
            CONTAINER_LIST.getLast().removeAllViews();
            CONTAINER_LIST.getLast().addView(this, new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            CONTAINER_LIST.pop();
            setScreenNormal();
        } else {
            super.gotoScreenNormal();
        }
    }

    @Override
    public void gotoScreenFullscreen() {
        lastScreenMode=currentScreen;
        super.gotoScreenFullscreen();
    }

    public Jzvd cloneMe() {
        TinyWindow jzvd = null;
        try {
            Constructor<TinyWindow> constructor = (Constructor<TinyWindow>) this.getClass().getConstructor(Context.class);
            jzvd = constructor.newInstance(getContext());
            jzvd.jzDataSource = jzDataSource.cloneMe();//jzvd应该是idle状态
            jzvd.mediaInterface=mediaInterface;
            jzvd.setId(getId());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return jzvd;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);
        if (currentScreen == SCREEN_WINDOW_TINY) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    holder.lastX = event.getX();
                    holder.lastY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float curX = event.getX();
                    float curY = event.getY();
                    //计算X方向偏移量
                    holder.params.leftMargin = (int) (holder.params.leftMargin + (curX - holder.lastX));
                    if (holder.params.leftMargin > holder.xSpace) {
                        holder.params.leftMargin = (int) holder.xSpace;
                    } else if (holder.params.leftMargin < 0) {
                        holder.params.leftMargin = 0;
                    }
                    //计算Y方向偏移量
                    holder.params.topMargin = (int) (holder.params.topMargin + (curY - holder.lastY));
                    if (holder.params.topMargin > holder.ySpace) {
                        holder.params.topMargin = (int) holder.ySpace;
                    } else if (holder.params.topMargin < 0) {
                        holder.params.topMargin = 0;
                    }
                    Log.i("xxx", "onTouch: leftMargin=" + holder.params.leftMargin + "\nleftMargin=" + holder.params.rightMargin +
                            "\ntopMargin=" + holder.params.topMargin + "\nbottomMargin=" + holder.params.bottomMargin);
                    holder.decorView.updateViewLayout(this, holder.params);
                    holder.lastX = curX;
                    holder.lastY = curY;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
        return false;
    }

    class ViewMoveHolder {
        ViewGroup decorView;//页面根布局
        LayoutParams params;//浮动窗口 param参数
        float lastX = 0, lastY = 0;//touch浮动窗口时,point
        float xSpace, ySpace;//最小单位
    }
}
