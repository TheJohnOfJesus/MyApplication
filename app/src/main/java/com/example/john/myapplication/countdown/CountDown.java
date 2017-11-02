package com.example.john.myapplication.countdown;

/**
 * Created by John on 2017/10/10.
 */

public class CountDown extends Thread {
    private OnCountDownStateListener listener;
    private volatile boolean isRunning = false;//
    private int timeOut = -1;

    public CountDown(OnCountDownStateListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
//        super.run();
        while (isRunning) {
            try {
                Thread.sleep(timeOut);
                if (listener!=null)listener.onTimeOut();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 启动前需要先设置倒计时的时间
     * 如果未设置倒计时的时间,
     */
    @Override
    public synchronized void start() {
        if (timeOut == -1) return;
        if (!isRunning)isRunning=true;
        super.start();
    }

    /**
     * timeOut 需要为>=0的值
     *
     * @param timeOut
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * 默认为运行状态
     *
     * @param running
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
