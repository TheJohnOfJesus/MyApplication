package com.example.john.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @Title NetWorkUtil
 * @Package com.nqsky.util.netstate
 * @Description 是检测网络的一个工具包
 * @author jiangneng
 * @date 2013-1-22 下午 9:35
 * @version V1.0
 */
public class NetWorkUtil {
	public enum NetType {
		wifi, CMNET, CMWAP, noneNet
	}

	/**
	 * 网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (info != null) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 当前手机是否有可用网络 (所有网络类型)
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						if (info.isAvailable()) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断WIFI网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断MOBILE网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	
	/**
	 * 返回手机类型，
	 * 	NETWORK_TYPE_CDMA 		网络类型为CDMA
     * 	NETWORK_TYPE_EDGE 		网络类型为EDGE
     * 	NETWORK_TYPE_EVDO_0 	网络类型为EVDO0
     * 	NETWORK_TYPE_EVDO_A		网络类型为EVDOA
     * 	NETWORK_TYPE_GPRS 		网络类型为GPRS
     * 	NETWORK_TYPE_HSDPA	 	网络类型为HSDPA
     * 	NETWORK_TYPE_HSPA 		网络类型为HSPA
     * 	NETWORK_TYPE_HSUPA 		网络类型为HSUPA
     * 	NETWORK_TYPE_UMTS 		网络类型为UMTS
	 * @param context
	 * @return
	 */
	public static String getMoblieNetType(Context context){
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (info == null){
			return "null";
		}
		return info.getSubtypeName();
	}

	/**
	 * 获取当前网络连接的类型信息
	 * 
	 * @param context
	 * @return
	 */
	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

    public static String getConnectedTypeName(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getTypeName();
            }
        }
        return "";
    }

	/**
	 * 
	 * @author 白猫
	 * 
	 *         获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap 网络3：net网络
	 * 
	 * @param context
	 * 
	 * @return
	 */
	public static NetType getAPNType(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return NetType.noneNet;
		}
		int nType = networkInfo.getType();

		if (nType == ConnectivityManager.TYPE_MOBILE) {
			if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
				return NetType.CMNET;
			}

			else {
				return NetType.CMWAP;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			return NetType.wifi;
		}
		return NetType.noneNet;

	}
	/**
	 *
	 * @author 白猫
	 *
	 *         获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap 网络3：net网络
	 *
	 * @param context
	 *
	 * @return
	 */
	public static int getType(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return 1;
		}
		int nType = networkInfo.getType();

		if (nType == ConnectivityManager.TYPE_MOBILE) {
			return 3;
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			return 2;
		}
		return 1;

	}
}
