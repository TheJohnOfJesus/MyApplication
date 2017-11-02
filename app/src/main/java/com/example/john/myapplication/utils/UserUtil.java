package com.example.john.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by John on 2017/8/13.
 */

public class UserUtil {
    public static void savePushToken(Context context, String pushToken) {
        SharedPreferences preferences = context.getSharedPreferences("appConstant", Context
                .MODE_PRIVATE);
        String serialize = preferences.getString("pushToken", "");
        if (serialize == null || serialize.length() == 0) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("pushToken", pushToken);
            editor.commit();
        }
    }

    public static String getPushToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("appConstant", Context
                .MODE_PRIVATE);
        return preferences.getString("pushToken", "");
    }
}
