package com.cnetsample.demo.resp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by c on 16/9/7.
 */
public class RespUploadImg {
    @SerializedName("image")
    private String imgPath = null ;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
