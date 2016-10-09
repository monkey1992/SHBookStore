package com.xiaoyu.shbookstore.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.xiaoyu.shbookstore.config.GlobalParams;

public class NetUtil {
	/**
	 * 检查是否有网络连接
	 * @param context
	 * @return
	 */
	public static boolean checkNet(Context context) {
		boolean isMobile = checkConnection(context, ConnectivityManager.TYPE_MOBILE);
		boolean isWifi = checkConnection(context, ConnectivityManager.TYPE_WIFI);	
		if(isMobile) {
			readAPN(context);
		}
		if (!isMobile && !isWifi) {
			return false;
		}
		return true;
	}

	private static void readAPN(Context context) {
		ContentResolver resolver = context.getContentResolver();
		Uri PREFERRED_APN_URI = Uri.parse("content://content://telephony/carriers/preferapn");
		Cursor cursor = resolver.query(PREFERRED_APN_URI, null, null, null, null);	
		if(cursor != null && cursor.isFirst()) {
			GlobalParams.PROXY = cursor.getString(cursor.getColumnIndex("proxy"));
			GlobalParams.PORT = cursor.getInt(cursor.getColumnIndex("port"));
		}
	}

	private static boolean checkConnection(Context context,int netType) {
		ConnectivityManager cm = 
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getNetworkInfo(netType);
		boolean isConnected = networkInfo.isConnected();	
		return isConnected;
	}
}
