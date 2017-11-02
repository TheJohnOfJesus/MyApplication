package com.example.countdown;

/**
 * Created by jjtx-dev106 on 2017/10/11.
 */

public class TempClass implements OnCountDownStateListener {
    CountDown countDown1;
    CountDown countDown2;

    public TempClass() {
        countDown1 = new CountDown(this);
        countDown2 = new CountDown(this);
        countDown1.setTimeOut(1000);
        countDown1.start();
        countDown2.setTimeOut(1000);
        countDown2.start();
    }

    @Override
    public void onTimeOut() {
        System.out.println("TempClass"+"");
    }
}
