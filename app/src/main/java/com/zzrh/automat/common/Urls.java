package com.zzrh.automat.common;

/**
 * Created by Administrator on 2018/3/2.
 */

public interface Urls {

    String IP = "http://www.4fz3.com/";
    //?MID=000BABC8D313
    String lunxun = "/front/vendingApi/adminApi/uploadInfo";
    //获取商品列表
    String goodsList = "/front/vendingApi/goodsApi/goodslist";
    //获取支付的二维码
    String payCode = "/front/weChatScanOnePayment/createQRcode";
}
