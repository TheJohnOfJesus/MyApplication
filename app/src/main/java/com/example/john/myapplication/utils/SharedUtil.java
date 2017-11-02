package com.example.john.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * app私有目录下保存SharedPreferences文件
 * 
 * @author qianjunping
 * 
 */
public class SharedUtil {

    public static void clear(Context context, String fileName){
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

	/**
	 * 将map集合映射添加到fileName的SharedPreferences中
	 * 
	 * @param context
	 * @param fileName
	 * @param map
	 */
	public static void put(Context context, String fileName, Map<String, String> map) {
		if (map != null && map.size() > 0) {
			SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
			Editor editor = preferences.edit();
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String value = map.get(key);
				editor.putString(key, value);
			}
			editor.commit();
		}
	}

	/**
	 * 将给定字符串添加到fileName的SharedPreferences中
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public static void putString(Context context, String fileName, String key, String value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 删除本地文件中存在的注册信息
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @return true--删除成功，false--尚未注册，删除失败
	 */
	public static boolean deleteString(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.remove(key);
		return editor.commit();
	}

	/**
	 * 根据key获得SharedPreferences中的value
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @return 如果SharedPreferences不存在key的映射，返回""
	 */
	public static String getString(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		return preferences.getString(key, "");
	}
	
	/**
	 * 将给定的int值添加到fileName的SharedPreferences中
	 * 
	 * @param context 上下文
	 * @param fileName 文件名
	 * @param key 键名
	 * @param value 键值
	 */
	public static void putInt(Context context, String fileName, String key, int value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	/**
	 * 根据key获得SharedPreferences中int类型的value
	 * 
	 * @param context 上下文
	 * @param fileName 文件名
	 * @param key 键名
	 * @return 如果SharedPreferences不存在key的映射，返回默认值0；如果存在，则返回value值。
	 */
	public static int getInt(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		return preferences.getInt(key, 0);
	}
	
	/**
	 * 将给定的float值添加到fileName的SharedPreferences中
	 * 
	 * @param context 上下文
	 * @param fileName 文件名
	 * @param key 键名
	 * @param value 键值
	 */
	public static void putFloat(Context context, String fileName, String key, float value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putFloat(key, value);
		editor.commit();
	}
	
	/**
	 * 根据key获得SharedPreferences中float类型的value
	 * 
	 * @param context 上下文
	 * @param fileName 文件名
	 * @param key 键名
	 * @return 如果SharedPreferences不存在key的映射，返回默认值0；如果存在，则返回value值。
	 */
	public static float getFloat(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		return preferences.getFloat(key, 0);
	}
	
	/**
	 * 将给定的float值添加到fileName的SharedPreferences中
	 * 
	 * @param context 上下文
	 * @param fileName 文件名
	 * @param key 键名
	 * @param value 键值
	 */
	public static void putBoolean(Context context, String fileName, String key, boolean value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	/**
	 * 根据key获得SharedPreferences中float类型的value
	 * 
	 * @param context 上下文
	 * @param fileName 文件名
	 * @param key 键名
	 * @return 如果SharedPreferences不存在key的映射，返回默认值0；如果存在，则返回value值。
	 */
	public static boolean getBoolean(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		return preferences.getBoolean(key, false);
	}
	
}
