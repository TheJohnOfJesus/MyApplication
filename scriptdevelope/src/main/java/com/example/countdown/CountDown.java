package com.example.countdown;

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
        try {
            while (isRunning) {
                // 使用中断机制，来终止线程
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted ...");
                    break;
                }
                Thread.sleep(timeOut);
                if (listener != null) listener.onTimeOut();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //玻璃球,羽毛球,桌球,乒乓球,篮球,足球
    public void shutDown() {
//        isRunning = false;
        Thread.currentThread().interrupt();
    }

    /**
     * 启动前需要先设置倒计时的时间
     * 如果未设置倒计时的时间,
     */
    @Override
    public synchronized void start() {
        if (timeOut == -1) return;
        if (!isRunning) isRunning = true;
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
