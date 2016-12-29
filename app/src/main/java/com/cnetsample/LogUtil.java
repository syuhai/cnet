package com.cnetsample;


import android.util.Log;

import com.cnet.util.StringUtil;


public class LogUtil {
	
	private final static boolean DEBUG=true;
	private final static String TAG = Constants.APP_FOLDER;
	
	public static void e(String tag, String msg, Throwable e) {
		Log.e(tag, msg, e);
	}

	public static void e(String tag, String msg) {
		Log.e(tag, msg,null);
	}


	public static void e(String msg){
		e(TAG, msg, null);
	}
	
	public static void e(Exception e){
		if( e != null ){
			e(e.getMessage());
		}
	}
	
	public static void d(String tag, String info) {
		Log.d(tag,info);
	}
	
	public static void d(String info) {
		d(TAG, info);
	}
	
	public static void w(String tag, String warn) {
		Log.w("", tag + " " + warn);
	}
	
	public static void w(String warn) {
		w(TAG, warn);
	}
	
	public static void i(String tag, String info) {
		Log.i("", tag + " " + info);
	}
	
	public static void i(String info) {
		i(TAG, info);
	}
	
	/**
	 * 统一开关log显示
	 * @param log
	 */
	 public static void printLog(String log){
		 if(DEBUG){
			 if(StringUtil.isEmpty(log)){
				 return;
			 }
			 Log.i(TAG, log);
		 }
	 }
}
