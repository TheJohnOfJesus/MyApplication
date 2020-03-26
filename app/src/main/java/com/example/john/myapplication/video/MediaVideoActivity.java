package com.example.john.myapplication.video;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.john.myapplication.R;
import com.hebccc.multimedia.video.Jzvd;
import com.hebccc.multimedia.video.JzvdStd;
import com.hebccc.multimedia.video.TinyWindow;
import com.hebccc.multimedia.video.exo.JZMediaExo;

public class MediaVideoActivity extends Activity {
    private LinearLayout llRoot;
    private JzvdStd video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        llRoot = findViewById(R.id.ll_root);
        showFile();
    }

    private void showFile() {
        View videoView = LayoutInflater.from(this).inflate(R.layout.item_video, null);
        video = videoView.findViewById(R.id.jz_video);
        String link = Environment.getExternalStorageDirectory() + "/shipin.mp4";
        video.setUp(link, "测试", JzvdStd.SCREEN_NORMAL, new JZMediaExo(video));
//        tinyWindow.onPrepared();
        video.seekToInAdvance=20*1000;
//        tinyWindow.mediaInterface.seekTo(20*1000);
        video.startButton.callOnClick();
        llRoot.addView(videoView);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Log.i("xxx", "onBackPressed: video.getCurrentPositionWhenPlaying()="+video.getCurrentPositionWhenPlaying()/1000);
//        Log.i("xxx", "onBackPressed: video.getDuration()="+video.getDuration()/60);
    }
}
