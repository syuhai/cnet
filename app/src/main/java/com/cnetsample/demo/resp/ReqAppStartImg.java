package com.cnetsample.demo.resp;


import com.cnet.def.http.request.CRequestConstants;
import com.cnetsample.base.CRequest;

/**
 * Created by c on 16/9/7.
 */
public class ReqAppStartImg extends CRequest {

    public ReqAppStartImg() {
        super(RespAppStartImg.class);
        setPostFixUrl("splash");
        setShowLoadding(true);
        setShowMsgType(CRequestConstants.SHOW_MSGTYPE_TOAST);
    }
}
