package com.cnetsample.demo.resp;

import com.cnetsample.base.CRequest;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Cuckoo
 * @date 2016-03-27
 * @description
 *      获取子分类商品列表
 */
public class ReqSubCategoryProductList extends CRequest {

//        "category_id": 20, //分类 ID 号(必填)
//        "sort": "price", //排列类型(可选,参数:name/price/rating/model/sale)
//        "order": "DESC", //排列方式(可选,参数:DESC/ASC)
//        "page": 1 //页数(可选,默认为 1)
    @SerializedName("category_id")
    public String categoryID = null ;
    protected String sort = "price" ;
    protected String order = null ;
    @SerializedName("page")
    protected int pageIndex = 1 ;

    public ReqSubCategoryProductList(){
        super(SubCategoryProductList.class);
        this.categoryID = "2" ;
            order = "ASC";
        this.pageIndex = 1;
        setShowLoadding(true);
        setPostFixUrl("categories/products");
    }

}
