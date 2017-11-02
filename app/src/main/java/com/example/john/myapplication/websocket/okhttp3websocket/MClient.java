package com.example.john.myapplication.websocket.okhttp3websocket;

import android.content.Context;
import android.util.Log;

import com.example.john.myapplication.websocket.WebSocketRequest;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by John on 2017/8/28.
 */

public class MClient extends WebSocketListener {
    private Context mContext;
    private String TAG = com.example.john.myapplication.websocket.javawebsocket.MClient.class.getSimpleName();
    private WebSocket socket;

    public MClient(Context context) {
        mContext = context;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
//        super.onOpen(webSocket, response);
        socket = webSocket;
        Log.i(TAG, "opened connection: ");
        socket.send("conned");
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
//        super.onFailure(webSocket, t, response);
        Log.i(TAG, "onFailure:\n      Throwable:" + t.toString() + "\nResponse:" + response);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
//        super.onMessage(webSocket, bytes);
        Log.i(TAG, "onMessage: ByteString:" + bytes.toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
//        super.onMessage(webSocket, text);
        Log.i(TAG, "onMessage: String:   " + text);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
//        super.onClosing(webSocket, code, reason);
        Log.i(TAG, "Connection closed ::::  code=" + code + ":::: reason:" + reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
//        super.onClosed(webSocket, code, reason);
        Log.i(TAG, "Connection closed ::::  code=" + code + ":::: reason:" + reason);
    }

    public WebSocket getSocket() {
        return socket;
    }

    public void send(String msg) {
        if (socket != null) {
            socket.send(msg);
        }
    }

    public void connect(String url) {
        WebSocketRequest.getInstance(mContext).connect(url, this);
    }

    public void disconnect() {
//        socket.cancel();
        socket.close(1000, "close");
    }
}
