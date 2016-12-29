package com.cnetsample.demo.resp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by c on 16/9/7.
 */
public class SubCategoryProductList {
    @SerializedName("total_page")
    private int totalPage = 0 ;
    @SerializedName("category_id")
    private String subCategoryID = null ;
    @SerializedName("products")
    private ArrayList<ProductItem> productList = null;
    //子分类
    @SerializedName("subcategories")
    private ArrayList<ProductType> subCategoryList = null ;

    public ArrayList<ProductType> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(ArrayList<ProductType> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(String subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public ArrayList<ProductItem> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductItem> productList) {
        this.productList = productList;
    }
}
