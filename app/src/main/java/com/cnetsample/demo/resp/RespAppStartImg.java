package com.cnetsample.demo.resp;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Cuckoo
 * @date 2016-04-21
 * @descritpion
 *      app启动图
 */
public class RespAppStartImg {
        //启动图地址
        @SerializedName("splash")
        private String imgUlr = null ;

        public String getImgUlr() {
                return imgUlr;
        }

        public void setImgUlr(String imgUlr) {
                this.imgUlr = imgUlr;
        }
}
