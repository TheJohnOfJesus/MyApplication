package com.example.john.myapplication.websocket;

import android.content.Context;

import com.example.john.myapplication.base.BaseNetRequest;
import com.example.john.myapplication.websocket.okhttp3websocket.MClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 2017/8/28.
 */

public class WebSocketRequest extends BaseNetRequest {
    private WebSocketRequest(Context context) {
        super(context);
    }

    public static WebSocketRequest getInstance(Context context) {
        if (!(mManager instanceof WebSocketRequest)) {
            mManager = new WebSocketRequest(context);
        }
        return (WebSocketRequest) mManager;
    }

    public void isClientRegist(String boxId, OnNetResponseListener listener) {
        url = urlHost + "/v2/projector/prjBox/isexit";
        Map<String, String> map = new HashMap<String, String>();
        map.put("boxId", boxId);
//        map.put("boxId", AppUtil.getDeviceId(mContext));
        netRequest(map, true, listener);
//        netRequest(map,false,listener);
    }

    public void connect(String urls, MClient listener) {
        socketRequest(urls, listener);
    }
}
