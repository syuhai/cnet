package com.cnetsample.demo.resp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * @author Cuckoo
 * @date 2016-02-13
 * @description
 *      商品列表-商品分类
 */
public class ProductType implements Serializable {
    //分类id
    @SerializedName("category_id")
    private String id;
    //分类图片
    @SerializedName("image")
    private String photoUrl = null ;
    //分类名称
    @SerializedName("name")
    private String typeName = null ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
