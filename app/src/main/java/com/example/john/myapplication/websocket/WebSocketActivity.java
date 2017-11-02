package com.example.john.myapplication.websocket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.john.myapplication.R;
import com.example.john.myapplication.base.BaseNetRequest;
import com.example.john.myapplication.utils.AppUtil;
//import com.example.jjtx_dev106.myapplication.websocket.javawebsocket.MClient;
import com.example.john.myapplication.websocket.okhttp3websocket.MClient;

//import com.example.jjtx_dev106.myapplication.websocket.javawebsocket.MClient;

public class WebSocketActivity extends Activity implements View.OnClickListener {
    private final String TAG = WebSocketActivity.class.getSimpleName();
    private TextView connect, disconnect, send;
    //    private MClient client;
    private MClient client;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        connect = (TextView) findViewById(R.id.connect);
        disconnect = (TextView) findViewById(R.id.disconnect);
        send = (TextView) findViewById(R.id.send);
        connect.setOnClickListener(this);
        disconnect.setOnClickListener(this);
        send.setOnClickListener(this);
        getUrl();
        client = new MClient(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect:
                try {
                    url = "ws://124.248.37.243:18011/puke/websocket.ws//" + 100 + "/1";
//                    client = new MClient(new URI(url), new Draft_17());
//                    client.connect();
                    client.connect(url);
//                    client.connect("ws://124.248.37.243:18021/puke/websocket.ws//1/1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.disconnect:
//                client.disconnect();
                break;
            case R.id.send:
                client.send("conned");
                break;
        }
    }

    private void getUrl() {
        WebSocketRequest.getInstance(this).isClientRegist(AppUtil.getDeviceId(this), new BaseNetRequest.OnNetResponseListener() {
            @Override
            public void onFailure(final String errorMsg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WebSocketActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(JSONObject obj) {
                if ("1000".equals(obj.getString("code"))) {
                    url = obj.getJSONObject("resultData").getString("url");
                } else {
                    Log.i(TAG, "onResponse: ");
                }
            }
        });
    }
}
