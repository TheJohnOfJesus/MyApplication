package com.example.countdown;

/**
 * Created by jjtx-dev106 on 2017/10/10.
 */

public class TimeCountMain {
    private static CountDown countDown;
    private static int count;

    public static void main(String args[]) {
//        CountDown[] mythreadArray = new CountDown[100];
//        for (int i = 0; i < 100; i++) {
//            final int index=i;
//            mythreadArray[i] = new CountDown(new OnCountDownStateListener() {
//                @Override
//                public void onTimeOut() {
//                    System.out.println("index="+index+"第" + (count++) + "次");
//                }
//            });
//            mythreadArray[i].setTimeOut(0);
//        }
//        for (int i = 0; i < 100; i++) {
//            mythreadArray[i].start();
//        }
//        for (int i = 0; i < 100; i++) {
//            mythreadArray[i].setRunning(false);
//        }

//        new TempClass();

        countDown=new CountDown(new OnCountDownStateListener() {
            @Override
            public void onTimeOut() {
                System.out.println("第"+(count++)+"次");
//                countDown.setRunning(false);
                countDown.shutDown();
            }
        });
        countDown.setTimeOut(1000);
        countDown.start();
    }
}
