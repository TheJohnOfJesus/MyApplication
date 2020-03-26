package com.example.john.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.john.myapplication.bezier.sticky_water_drops.StickyWaterDropsActivity;
import com.example.john.myapplication.capture.GetPicture;
import com.example.john.myapplication.countdown.CountDownActivity;
import com.example.john.myapplication.download.DownLoadActivity;
import com.example.john.myapplication.elevation_translationz.ElevationActivity;
import com.example.john.myapplication.listview.ListViewActivity;
import com.example.john.myapplication.m2048.MSurfaceActivity;
import com.example.john.myapplication.manager.PermissionManager;
import com.example.john.myapplication.ndk.NDKActivtiy;
import com.example.john.myapplication.putlayout.PutLayoutActivity;
import com.example.john.myapplication.recyclerview.RVSourceActivity;
import com.example.john.myapplication.recyclerview.RecycleViewActivity;
import com.example.john.myapplication.video.MediaVideoActivity;
import com.example.john.myapplication.websocket.WebSocketActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    public static Activity instance;
    private LinearLayout recycleView1, recycleView2, capture, compress, layout, elevation, surface, websocket,
            listview, download, ndk, coutdown, beizier, tbsVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        ButterKnife.bind(this);
        instance = this;
        initView();
    }

    private void initView() {
        recycleView1 = (LinearLayout) findViewById(R.id.ll_main_recycleview1);
        recycleView2 = (LinearLayout) findViewById(R.id.ll_main_recycleview2);
        capture = (LinearLayout) findViewById(R.id.ll_main_capture_compress);
        compress = (LinearLayout) findViewById(R.id.ll_main_album_compress);
        layout = (LinearLayout) findViewById(R.id.ll_main_layout);
        elevation = (LinearLayout) findViewById(R.id.ll_main_elevation);
        surface = (LinearLayout) findViewById(R.id.ll_main_surface);
        websocket = (LinearLayout) findViewById(R.id.ll_main_websocket);
        listview = (LinearLayout) findViewById(R.id.ll_main_listview);
        download = (LinearLayout) findViewById(R.id.ll_main_download);
        ndk = (LinearLayout) findViewById(R.id.ll_main_ndk);
        coutdown = (LinearLayout) findViewById(R.id.ll_main_coutdown);
        beizier = (LinearLayout) findViewById(R.id.ll_main_beizer);
        tbsVideo = (LinearLayout) findViewById(R.id.ll_main_tbs_video);
        recycleView1.setOnClickListener(this);
        recycleView2.setOnClickListener(this);
        capture.setOnClickListener(this);
        compress.setOnClickListener(this);
        layout.setOnClickListener(this);
        elevation.setOnClickListener(this);
        surface.setOnClickListener(this);
        websocket.setOnClickListener(this);
        listview.setOnClickListener(this);
        download.setOnClickListener(this);
        ndk.setOnClickListener(this);
        coutdown.setOnClickListener(this);
        beizier.setOnClickListener(this);
        tbsVideo.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //        StatusBarUtil.addStatusBarView(this, R.color.color_yellow);
    }

    //    @OnClick({R.id.ll_main_recycleview1, R.id.ll_main_recycleview2, R.id.ll_main_capture_compress, R.id.ll_main_album_compress, R.id
    //            .ll_main_layout, R.id.ll_main_elevation, R
    //            .id.ll_main_surface, R.id.ll_main_websocket, R.id.ll_main_listview, R.id.ll_main_download})
    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.ll_main_recycleview1:
                intent = new Intent(this, RVSourceActivity.class);
                break;
            case R.id.ll_main_recycleview2:
                intent = new Intent(this, RecycleViewActivity.class);
                break;
            case R.id.ll_main_capture_compress:
                GetPicture.getPic(0, 400, 590, 100, 0);
                break;
            case R.id.ll_main_album_compress:
                GetPicture.getPic(1, 400, 590, 100, 0);
                break;
            case R.id.ll_main_layout:
                intent = new Intent(this, PutLayoutActivity.class);
                break;
            case R.id.ll_main_elevation:
                intent = new Intent(this, ElevationActivity.class);
                break;
            case R.id.ll_main_surface:
                intent = new Intent(this, MSurfaceActivity.class);
                break;
            case R.id.ll_main_websocket:
                intent = new Intent(this, WebSocketActivity.class);
                break;
            case R.id.ll_main_listview:
                intent = new Intent(this, ListViewActivity.class);
                break;
            case R.id.ll_main_download:
                intent = new Intent(this, DownLoadActivity.class);
                break;
            case R.id.ll_main_ndk:
                intent = new Intent(this, NDKActivtiy.class);
                break;
            case R.id.ll_main_coutdown:
                intent = new Intent(this, CountDownActivity.class);
                break;
            case R.id.ll_main_beizer:
                intent = new Intent(this, StickyWaterDropsActivity.class);
                break;
            case R.id.ll_main_tbs_video:
                intent = new Intent(this, MediaVideoActivity.class);
                break;
        }
        if (intent != null) startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GetPicture.onActivityResullt(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
