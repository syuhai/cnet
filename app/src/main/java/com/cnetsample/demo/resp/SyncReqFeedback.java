package com.cnetsample.demo.resp;

import com.cnet.def.http.request.CAbstractRequst;
import com.google.gson.annotations.SerializedName;

import java.io.File;

/**
 * Created by c on 16/9/7.
 */
public class SyncReqFeedback extends CAbstractRequst {
    @SerializedName("file")
    private File filePath = null ;
    public SyncReqFeedback() {
        super(RespAppStartImg.class);
        setBaseUrl("http://www.chunlaizhuang.com/rest/v2/");
        setPostFixUrl("feedback/upload_image");
    }
}
