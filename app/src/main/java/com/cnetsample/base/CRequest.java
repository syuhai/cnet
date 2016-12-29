package com.cnetsample.base;

import com.cnet.def.http.request.CAbstractRequst;
import com.cnet.def.http.request.CRequestConstants;
import com.cnet.def.http.request.IBaseRequest;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 *
 * @author Cuckoo
 * @date 2016-09-01
 * @description
 * 		To manage all requests params and config of http(https)
 */
public class CRequest<T> extends CAbstractRequst implements IBaseRequest{

	/**是否显示loading框*/
	@SerializedName(CRequestConstants.TAG_IGNORE)
	private transient boolean isShowLoadding = true ;
	/**错误消息提示类型*/
	@SerializedName(CRequestConstants.TAG_IGNORE)
	private transient int showMsgType = CRequestConstants.SHOW_MSGTYPE_DIALOG;
	/**需要同步执行的请求列表**/
	@SerializedName(CRequestConstants.TAG_IGNORE)
	private transient ArrayList<CAbstractRequst<T>> syncReqList = null ;

	public CRequest(Class<T> respObjClass){
		super(respObjClass);
		setBaseUrl("http://www.chunlaizhuang.com/rest/v2/");
	}

	/************************************************************/
	/***********************Get and set method*******************/
	/************************************************************/

	@Override
	public boolean isShowLoadding() {
		return isShowLoadding;
	}

	public void setShowLoadding(boolean showLoadding) {
		isShowLoadding = showLoadding;
	}

	@Override
	public int getShowMsgType() {
		return showMsgType;
	}

	public void setShowMsgType(int showMsgType) {
		this.showMsgType = showMsgType;
	}

	@Override
	public ArrayList<CAbstractRequst<T>> getSyncReqList() {
		return syncReqList;
	}

	@Override
	public CAbstractRequst getMarjorReqeust() {
		return this;
	}

	/**
	 * 添加同步子请求
	 * @param request
     */
	public void addSyncRequest(CAbstractRequst request){
		if(syncReqList == null ){
			syncReqList = new ArrayList<>();
		}
		syncReqList.add(request);
	}
}
