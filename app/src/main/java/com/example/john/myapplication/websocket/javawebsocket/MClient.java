package com.example.john.myapplication.websocket.javawebsocket;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

/**
 * Created by John on 2017/8/28.
 */

public class MClient extends WebSocketClient {

    private String TAG = MClient.class.getSimpleName();

    public MClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    public MClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    public MClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.i(TAG, "opened connection: ");
    }

    @Override
    public void onMessage(String message) {
        Log.i(TAG, "received: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(TAG, "Connection closed by " + (remote ? "remote peer" : "us"));
    }

    @Override
    public void onError(Exception ex) {
        Log.i(TAG, "onError: " + ex.toString());
    }

    @Override
    public void onFragment(Framedata fragment) {
        super.onFragment(fragment);
        Log.i(TAG, "received fragment: " + new String(fragment.getPayloadData().array()));
    }
}
