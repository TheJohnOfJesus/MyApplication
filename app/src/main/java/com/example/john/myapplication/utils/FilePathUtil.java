package com.example.john.myapplication.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by John on 2017/7/26.
 */

public class FilePathUtil {
    public static String SDCARD_PATH;
    public static String DEFAULT_STORAGE_DIRECTORY;
    public static String MEMORY_STORAGE_DIRECTORY;
    public static String DEFAULT_APP_DIRECTORY;// 应用路径

//    public static String getUserImagePath(Context context,String name){
//        String path="";
//    }
    /**
     * 获取该用户的图片路径
     *
     * @param context Context
     * @return /sdcard/company/appname/userid/image
     */
    public static String getUserImagePath(Context context) {
        if (context != null && getAppPath(context) != null) {
            File file = new File(getAppPath(context) + File.separator + "image" + File.separator);
            if (!file.exists()) {
                file.mkdirs();
            }

            return file.getAbsolutePath();
        }
        return null;
    }
    /**
     * 获取应用根路径
     *
     * @param context Context
     * @return /mnt/sdcard/company/appname
     */
    public static String getAppPath(Context context) {
        if (context != null && DEFAULT_STORAGE_DIRECTORY != null) {
            String[] temp = context.getPackageName().split("\\.");
            temp[1]="john";
            temp[2]="MyApplication";
            File file;
            if (temp.length>2){
                file= new File(DEFAULT_STORAGE_DIRECTORY + File.separator + temp[1] + File.separator + temp[2]);
            }else{
                file= new File(DEFAULT_STORAGE_DIRECTORY + File.separator + context.getPackageName());
            }

            if (!file.exists()) {
                file.mkdirs();
            }
            DEFAULT_APP_DIRECTORY = file.getAbsolutePath();
            return DEFAULT_APP_DIRECTORY;
        }
        return null;
    }
    /**
     * @param context Context
     * @return /sdcard/company/appname/crash
     */
    public static String getCrashPath(Context context) {
        if (context != null && getAppPath(context) != null) {
            File file = new File(getAppPath(context) + "/crash");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
        return null;
    }
    /**
     * @param context Context
     * @return /sdcard/company/appname/crash
     */
    public static String getFilesPath(Context context) {
        if (context != null && getAppPath(context) != null) {
            File file = new File(getAppPath(context) + "/files");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
        return null;
    }
    static {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
            DEFAULT_STORAGE_DIRECTORY = SDCARD_PATH;
        } else {
            MEMORY_STORAGE_DIRECTORY = Environment.getDataDirectory().getAbsolutePath();
            DEFAULT_STORAGE_DIRECTORY = MEMORY_STORAGE_DIRECTORY;
        }
    }
}
