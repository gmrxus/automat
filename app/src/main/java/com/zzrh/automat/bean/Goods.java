package com.zzrh.automat.bean;

/**
 * Created by Administrator on 2018/3/7.
 */

public class Goods {

    /**
     * hdStatus : 1
     * goodsStorage : 10
     * goodsUrl :
     * goodsActivityName :
     * goodsPrice :
     * hdId : 66
     * machineGoodsId : f18ece42569749d1a8f536127ee8cff0
     * goodsActivityPrice :
     * goodsName :
     */

    private String hdStatus;
    private String goodsStorage;
    private String goodsUrl;
    private String goodsActivityName;
    private String goodsPrice;
    private String hdId;
    private String machineGoodsId;
    private String goodsActivityPrice;
    private String goodsName;

    public String getHdStatus() {
        return hdStatus;
    }

    public void setHdStatus(String hdStatus) {
        this.hdStatus = hdStatus;
    }

    public String getGoodsStorage() {
        return goodsStorage;
    }

    public void setGoodsStorage(String goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsActivityName() {
        return goodsActivityName;
    }

    public void setGoodsActivityName(String goodsActivityName) {
        this.goodsActivityName = goodsActivityName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getHdId() {
        return hdId;
    }

    public void setHdId(String hdId) {
        this.hdId = hdId;
    }

    public String getMachineGoodsId() {
        return machineGoodsId;
    }

    public void setMachineGoodsId(String machineGoodsId) {
        this.machineGoodsId = machineGoodsId;
    }

    public String getGoodsActivityPrice() {
        return goodsActivityPrice;
    }

    public void setGoodsActivityPrice(String goodsActivityPrice) {
        this.goodsActivityPrice = goodsActivityPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "hdStatus='" + hdStatus + '\'' +
                ", goodsStorage='" + goodsStorage + '\'' +
                ", goodsUrl='" + goodsUrl + '\'' +
                ", goodsActivityName='" + goodsActivityName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", hdId='" + hdId + '\'' +
                ", machineGoodsId='" + machineGoodsId + '\'' +
                ", goodsActivityPrice='" + goodsActivityPrice + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }
}
