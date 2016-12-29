package com.cnetsample.demo.resp;

import com.cnet.def.http.CHttpMethod;
import com.cnet.def.http.request.CRequestConstants;
import com.cnetsample.base.CRequest;
import com.google.gson.annotations.SerializedName;

import java.io.File;

/**
 * Created by c on 16/9/7.
 */
public class ReqFeedback extends CRequest {
    @SerializedName("file")
    public File filePath = null ;
    public ReqFeedback() {
        super(RespAppStartImg.class);
        setReqMethod(CHttpMethod.POST);
        setBaseUrl("http://www.chunlaizhuang.com/rest/v2/");
        setPostFixUrl("feedback/upload_image");
        setShowLoadding(true);
        setShowMsgType(CRequestConstants.SHOW_MSGTYPE_TOAST);
    }
}
