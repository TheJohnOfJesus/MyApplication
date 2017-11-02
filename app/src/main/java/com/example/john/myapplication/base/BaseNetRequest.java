package com.example.john.myapplication.base;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.example.john.myapplication.R;
import com.example.john.myapplication.utils.MD5Util;
import com.example.john.myapplication.utils.NetWorkUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.WebSocketListener;

/**
 * Created by john on 2017/3/9.
 */

public class BaseNetRequest {
    protected Context mContext;
    protected String url, urlHost;
    //    protected String url, urlHost = "http://124.248.37.243:18011";
    protected static BaseNetRequest mManager;
    private OkHttpClient mOkHttpClient;
    private Set<String> requests = new HashSet<>();

    protected BaseNetRequest(Context context) {
        if (mContext == null) mContext = context;
        if (urlHost == null || urlHost.length() == 0) url = urlHost = "http://app.chinapokercarnival.cn:18011";
//        if (urlHost==null||urlHost.length()==0)url=urlHost=(String) AppUtil.getAppMetaData(mContext,"JJTX_SERVER_HOST");
        if (mOkHttpClient == null) mOkHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)//读取超时
                .connectTimeout(10, TimeUnit.SECONDS) // 连接超时
                .writeTimeout(60, TimeUnit.SECONDS) // 写入超时
                .addInterceptor(new LogInterceptor())
                .build();
    }

    /**
     * @param map      请求body构建者
     * @param post     请求方式
     * @param listener 请求毁掉
     */
    protected void netRequest(Map<String, String> map, boolean post, final OnNetResponseListener listener) {
        if (!NetWorkUtil.isNetworkAvailable(mContext)) {
            listener.onFailure(mContext.getResources().getString(R.string.app_net_nouseful));
            return;
        }
        if (requests.contains(url)) return;
        requests.add(url);
        final String tempUrl = url;
        String param = "";
        FormBody.Builder builder = new FormBody.Builder();
        List<String> list = sortKeys(map.keySet().toArray());
        String value = "";
        for (int i = 0; i < list.size(); i++) {
            value = map.get(list.get(i)) == null ? "" : map.get(list.get(i));
            builder.add(list.get(i), value);
            try {
                param += "".equals(param) ? list.get(i) + "=" + URLEncoder.encode(value, "UTF-8") : "&" + list.get(i) + "=" + URLEncoder.encode
                        (value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        RequestBody formBody = builder.add("sign", getSign(param)).build();
        Request.Builder request = new Request.Builder().url(url.toString());
        if (post) {
            request.post(formBody);
        } else {
            request.get();
        }
        Call call = mOkHttpClient.newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requests.remove(tempUrl);
                listener.onFailure(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                requests.remove(tempUrl);
                if (response.isSuccessful()) {
                    try {
                        String content = response.body().string();
                        listener.onResponse((JSONObject) JSONObject.parse(content));
                    } catch (Exception e) {
                        listener.onFailure(e.toString());
                    }

                } else {
                    listener.onFailure(response.body().string());
                }
            }
        });
    }

    public void socketRequest(String urls, WebSocketListener listener) {
        okhttp3.Request.Builder builder = new Request.Builder();
//        mOkHttpClient.newWebSocket(builder.url(urls).build(), listener);
//        mOkHttpClient.dispatcher().executorService().shutdown();

        OkHttpClient client = new OkHttpClient();
        client.retryOnConnectionFailure();
        client.newWebSocket(builder.url(urls).build(), listener);
        client.dispatcher().executorService().shutdown();
    }

    public interface OnNetResponseListener {
        void onFailure(String errorMsg);

        void onResponse(JSONObject obj);
    }

    private class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.i("zcb", "request:" + request.toString());
//            long t1 = System.nanoTime();
            Response response = chain.proceed(chain.request());
//            long t2 = System.nanoTime();
//            Log.i("zcb", String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.i("zcb", "response body:" + content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }

    /**
     * 给参数键名排序
     */
    public List<String> sortKeys(Object[] keys) {
        List<String> keyList = new ArrayList<String>();

        for (int i = 0; i < keys.length; i++) {
            keyList.add((String) keys[i]);
        }
        Collections.sort(keyList);
        return keyList;
    }

    /**
     * 获取签名，该值为网络请求常用值
     */
    public String getSign(String str) {
//        return MessageDigest;
        return MD5Util.getMD5(str + "cfc85df1-7d87-4a7e-87ff-6f23d0277889");
    }
}
