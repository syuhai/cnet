package com.cnetsample.demo.resp;

import com.cnet.util.StringUtil;
import com.cnetsample.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Cuckoo
 * @date 2016-02-13
 * @description
 *      商品信息
 */
public class ProductItem implements Serializable {
    //订单详情表示购买数量,其它商品列表页表示库存数量
    @SerializedName("quantity")
    private int buyCount = -1 ;
    @SerializedName("product_id")
    private String sku = null ;
    @SerializedName("name")
    private String productName = null ;
    @SerializedName("image")
    private String photoUrl = null ;
//    //大中小三图
//    @SerializedName("image")
//    private ArrayList<String> imgUrlList = null ;
    //卖价
    @SerializedName("special")
    private String salePrice = null ;
    //标价
    @SerializedName("price")
    private String price = null ;
    //商品总金额：单价*数量
    @SerializedName("total")
    private String totalPrice = null ;
    //折扣
    private String discount = null ;
    @SerializedName("in_stock")
    private int inStock = 1;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhotoUrl() {
//        if(Constants.JUST_FOR_DEMO){
//            return photoUrl;
//        }else {
//            return ClzUtil.getImgUrl(imgUrlList);
//        }
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSalePrice() {
        if (StringUtil.isEmpty(salePrice)) {
            salePrice = price;
        }
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public String getTotalPrice() {
        if(Constants.JUST_FOR_DEMO){
            return getPrice();
        }
        return totalPrice;
    }

    public String getDiscount() {
        if(Constants.JUST_FOR_DEMO){
            if(discount == null ){
                discount = "";
                if(new Random().nextBoolean()){
                    discount = new Random().nextInt(10)+ "折";
                }
            }
        }
        return discount;
    }

    /**
     * 是否有库存
     * @return
     */
    public boolean isInStock(){
        //订单详情表示购买数量,其它商品列表页表示库存数量
        if(buyCount != -1){
            //当不要Count为-1时 表示接口没有返回这个字段
            return getBuyCount() > 0;
        }
        return true ;
    }
}
