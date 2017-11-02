package com.example.john.myapplication.countdown;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.john.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class CountDownActivity extends Activity {
    boolean isTimer1Running = false;
    boolean isTimer2Running = false;
    boolean isTimer3Running = false;
    Timer timer;
    TimerTask timerTask1, timerTask2, timerTask3;
    TextView time1, time2, time3;
    int timeCount1 = 0;
    int timeCount2 = 0;
    int timeCount3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        time1 = (TextView) findViewById(R.id.tv_cd1);
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimer1Running) {
                    timeCount1 = 0;
                    timerTask1.cancel();
                    isTimer1Running = false;
                } else {
                    timerTask1 = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    time1.setText(String.valueOf(timeCount1++));
                                }
                            });
                        }
                    };
                    isTimer1Running = true;
                    timer.schedule(timerTask1, 0, 1000);
                }
            }
        });
        time2 = (TextView) findViewById(R.id.tv_cd2);
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimer2Running) {
                    timeCount2 = 0;
                    timerTask2.cancel();
                    isTimer2Running = false;
                } else {
                    timerTask2 = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    time2.setText(String.valueOf(timeCount2++));
                                }
                            });
                        }
                    };
                    isTimer2Running = true;
                    timer.schedule(timerTask2, 0, 1000);
                }
            }
        });
        time3 = (TextView) findViewById(R.id.tv_cd3);
        time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimer3Running) {
                    timeCount3 = 0;
                    timerTask3.cancel();
                    isTimer3Running = false;
                } else {
                    timerTask3 = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    time3.setText(String.valueOf(timeCount3++));
                                }
                            });
                        }
                    };
                    isTimer3Running = true;
                    timer.schedule(timerTask3, 0, 1000);
                }
            }
        });
        timer = new Timer();
    }

    @Override
    protected void onDestroy() {
        if (timer != null) timer.cancel();
        super.onDestroy();
    }

    //==============================================================================推荐使用以下两种方式=============================================
    //方法一
//    public class timerTask extends Activity{
//        private int recLen = 0;
//        private TextView txtView;
//
//        public void onCreate(Bundle savedInstanceState){
//            super.onCreate(savedInstanceState);
//
//            setContentView(R.layout.timertask);
//            txtView = (TextView)findViewById(R.id.txttime);
//
//            handler.postDelayed(runnable, 1000);
//        }
//
//        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                recLen++;
//                txtView.setText("" + recLen);
//                handler.postDelayed(this, 1000);
//            }
//        };
//    }

//方法二
//    public class timerTask extends Activity{
//        private int recLen = 0;
//        private TextView txtView;
//
//        public void onCreate(Bundle savedInstanceState){
//            super.onCreate(savedInstanceState);
//
//            setContentView(R.layout.timertask);
//            txtView = (TextView)findViewById(R.id.txttime);
//
//            new Thread(new MyThread()).start();         // start thread
//        }
//
//        final Handler handler = new Handler(){          // handle
//            public void handleMessage(Message msg){
//                switch (msg.what) {
//                    case 1:
//                        recLen++;
//                        txtView.setText("" + recLen);
//                }
//                super.handleMessage(msg);
//            }
//        };
//
//        public class MyThread implements Runnable{      // thread
//            @Override
//            public void run(){
//                while(true){
//                    try{
//                        Thread.sleep(1000);     // sleep 1000ms
//                        Message message = new Message();
//                        message.what = 1;
//                        handler.sendMessage(message);
//                    }catch (Exception e) {
//                    }
}
