package com.cnetsample;



public class Constants {
	/**是否使用Demo数据*/
	public static final boolean JUST_FOR_DEMO = true ;
	/**是否使用DataServer获取网络数据*/
	public static final long DEMO_WAIT_FOR_QUERY = 1500;
	
	public static final String APP_FOLDER="CLight";
	
	public static final int MSG_QUERY_CANEL = 1001 ;
	public static final int MSG_QUERY_ERROR = 1002 ;
	public static final int MSG_SHOW_MSG = 1003 ;

	/**
	 * 服务器根路径地址
	 */
    public volatile static String BASE_ADDRESS="http://mobile.api.myjiankang.cn/";

    /** 获取最新版本 */
	public volatile static String GET_LAST_VERSION = "http://192.168.1.151:8080/UserManager/newapk.apk";
	
	public static void refreshAllUrl() {
		
	}
	
	public static void refreshAllUrl(String serverIP, String port) {
		//192.168.1.151:8080/UserManager/newapk.apk
	}
	
	public static String getRootUrl(){
		return BASE_ADDRESS;
	}
}