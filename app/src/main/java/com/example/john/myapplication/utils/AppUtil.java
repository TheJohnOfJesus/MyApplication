package com.example.john.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * android 应用程序工具
 *
 * @author qianjunping
 */
public class AppUtil {

    /**
     * 强制退出应用程序
     */
    public static void exit() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

    /**
     * 获取deviceId
     * 修改
     *
     * @param context 上下文
     * @return
     */
    public static String getDeviceId(Context context) {
        String device = ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();

        if (TextUtils.isEmpty(device)) {
            device = SharedUtil.getString(context, "divice", "id");
            if (TextUtils.isEmpty(device)) {
                device = Secure.getString(context.getContentResolver(),
                        Secure.ANDROID_ID);
                SharedUtil.putString(context, "divice", "id", device);
            }
            return device;
        } else {
            return device;
        }
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    /**
     * 获取应用的唯一标识包名
     *
     * @param context
     * @return 返回包名，应用的唯一标识，例如：com.nqsky.meap.test
     */
    public static String getAppKey(Context context) {
        return context.getPackageName();
    }

    /**
     * 获得清单文件的MetaData
     *
     * @param context 上下文
     * @param key     MetaData的name
     * @return
     */
    public static Object getAppMetaData(Context context, String key) {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (applicationInfo == null) {
                return null;
            }
            Bundle bundle = applicationInfo.metaData;
            if (bundle == null) {
                return null;
            }

            return bundle.get(key);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得app的 viersionCode
     *
     * @param context     上下文
     * @param packageName 包名
     * @return
     */
    public static int getAppVersionCode(Context context, String packageName) {

        PackageInfo info = null;
        try {
            if (packageName != null) {
                info = context.getPackageManager().getPackageInfo(packageName,
                        PackageManager.GET_CONFIGURATIONS);
            } else {
                info = context.getPackageManager().getPackageInfo(
                        context.getPackageName(),
                        PackageManager.GET_CONFIGURATIONS);
            }
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取应用包名路径作为应用唯一标识
     *
     * @param context 上下文
     * @return 返回包名路径，例如/data/app/com.nqsky.meap.test-2.apk
     */
    public static String getAppId(Context context) {
        return context.getPackageCodePath();
    }

    /**
     * 获得app的VersionName
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 返回应用的VersionName
     */
    public static String getAppVersionName(Context context, String packageName) {

        PackageInfo info = null;
        try {
            if (packageName != null) {
                info = context.getPackageManager().getPackageInfo(packageName,
                        PackageManager.GET_CONFIGURATIONS);
            } else {
                info = context.getPackageManager().getPackageInfo(
                        context.getPackageName(),
                        PackageManager.GET_CONFIGURATIONS);
            }
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得app的VersionName
     *
     * @param context 上下文
     * @return
     */
    public static String getAppVersionName(Context context) {

        PackageInfo info = null;
        try {
            if (context.getPackageName() != null) {
                info = context.getPackageManager().getPackageInfo(
                        context.getPackageName(),
                        PackageManager.GET_CONFIGURATIONS);
            } else {
                info = context.getPackageManager().getPackageInfo(
                        context.getPackageName(),
                        PackageManager.GET_CONFIGURATIONS);
            }
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取手机分辨率
     *
     * @param context Activity类型的上下文
     * @return 返回width*height的格式，单位px
     */
    public static String getPhoneResolution(Activity context) {
        DisplayMetrics displaysMetrics = new DisplayMetrics();
        // 获取手机窗口的Display 来初始化DisplayMetrics 对象 getManager()获取显示定制窗口的管理器。
        // 获取默认显示Display对象
        // 通过Display 对象的数据来初始化一个DisplayMetrics 对象
        context.getWindowManager().getDefaultDisplay()
                .getMetrics(displaysMetrics);
        return displaysMetrics.widthPixels + "*" + displaysMetrics.heightPixels;
    }

    /**
     * 获取手机分辨率
     *
     * @param context Activity类型的上下文
     * @return 返回width*height的格式，单位px
     */
    public static String getPhoneResolution(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowMgr = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowMgr.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels + "*" + dm.heightPixels;
    }

    /**
     * 自动安装
     *
     * @param context 上下文
     * @param apkPath apk包的路径
     * @return 是否安装成功
     */
    public static boolean autoInstallApk(Context context, String apkPath) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(new File(apkPath)),
                    "application/vnd.android.package-archive");
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 静默安装
     *
     * @param path 文件路径
     * @return 是否安装成功
     */
    public static boolean slientInstall(final String path) {
        new Thread() {
            public void run() {
                Process process = null;
                OutputStream out = null;
                InputStream in = null;
                try {
                    // 请求root
                    process = Runtime.getRuntime().exec("su");
                    out = process.getOutputStream();
                    // 调用安装
                    out.write(("pm install -r " + path + "\n").getBytes());
                    in = process.getInputStream();
                    int len = 0;
                    byte[] bs = new byte[256];
                    while (-1 != (len = in.read(bs))) {
                        String state = new String(bs, 0, len);
                        if (state.equals("Success\n")) {
                            // 安装成功后的操作
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.flush();
                            out.close();
                        }
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return false;
    }

    /**
     * 判断应用是否安装
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 返回true表示已经安装，返回false表示未安装。
     */
    public static boolean isInstalled(Context context, String packageName) {
        PackageInfo pInfo = null;
        if (packageName != null) {
            try {
                pInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return pInfo != null;
    }

    /**
     * 根据apk包判断是否已经安装
     *
     * @param context 上下文
     * @param apkPath apk包路径
     * @return
     */
    public static boolean isInstallByApkPath(Context context, String apkPath) {
        return isInstalled(context, getPackageNameFromApk(context, apkPath));
    }

    /**
     * 从apk文件获得程序的包名
     *
     * @param context 上下文
     * @param apkPath apk包路径
     * @return
     */
    public static String getPackageNameFromApk(Context context, String apkPath) {

        PackageInfo apkInfo = context.getPackageManager()
                .getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        Log.i(context.getClass().getSimpleName(), "apkPath :: " + apkPath);
        if (apkInfo != null) {
            return apkInfo.packageName;
        }
        return null;
    }

    /**
     * 得到应用程序名
     *
     * @param context 上下文
     * @return
     */
    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        String applicationName = "";
        try {
            packageManager = context.getPackageManager();
            ApplicationInfo packageInfo = packageManager.getApplicationInfo(
                    context.getPackageName(), 0);
            applicationName = (String) packageManager
                    .getApplicationLabel(packageInfo);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return applicationName;
    }

    /**
     * 卸载应用
     *
     * @param context     上下文
     * @param packageName 包路径
     */
    public static void unInstallApp(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE, Uri.parse("package:"
                + packageName));
        context.startActivity(intent);
    }

    /**
     * 根据包名启动应用
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void startApp(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(
                packageName);
        context.startActivity(intent);
    }

    /**
     * 从assets目录下拿取小文本文件的方法
     *
     * @param r        Resources对象
     * @param fileName Assets目录下的文件名
     * @return 文本文件的内容
     */
    public static String getStrFromAssets(Resources r, String fileName) {
        String result = null;
        try {
            InputStream in = r.getAssets().open(fileName);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((ch = in.read()) != -1) {
                baos.write(ch);
            }
            byte[] buff = baos.toByteArray();
            baos.close();
            in.close();
            result = new String(buff, "UTF-8");
            result = result.replaceAll("\\r\\n", "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取系统当前时间
     *
     * @return "yyyy-MM-dd hh:mm:ss"的格式时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        return sdf.format(date);
    }

    /**
     * 获取当前手机连接网络的类型，WI-FI，2G，3G，4G
     *
     * @param context
     * @return 返回当前手机连接网络的类型，WI-FI，2G，3G，4G
     */
    public static String getAccess(Context context) {
        String access = "";
        if (NetWorkUtil.isNetworkConnected(context)) {
            if (NetWorkUtil.isWifiConnected(context)) {
                access = "WiFi";
            } else if (NetWorkUtil.isMobileConnected(context)) {
                access = "Mobile";
            }
        }
        return access;
    }

    /**
     * 获取手机系统版本号
     *
     * @return 返回手机系统版本号
     */
    public static String getOSV() {
        return android.os.Build.VERSION.RELEASE;
    }

//    /**
//     * 取手机类型
//     *
//     * @return
//     */
//    public static String getDeviceType() {
//        return android.os.Build.MODEL;
//    }

    /**
     * 取手机设备型号
     *
     * @return
     */
    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 取手机厂商
     *
     * @return
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

//    /**
//     * 取手机运营商
//     *
//     * @return
//     */
//    public static String getDeviceProviderName(Context context) {
//        return new SIMcardInfo(context).getProvidersName();
//    }
//
//    /**
//     * 取手机运营商
//     *
//     * @return
//     */
//    public static String getDeviceIMSIS(Context context) {
//        return new SIMcardInfo(context).getIMSI();
//    }

    public static boolean getDeviceRoot() {
        try {
            if (Runtime.getRuntime().exec("su").getOutputStream() == null) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
