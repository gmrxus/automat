package com.zzrh.automat.common;

/**
 * Created by Administrator on 2018/3/2.
 */

public interface Keys {
    interface Path {
        //图片文件路径
        String LOAD_FILE_PATH_IMAGE = "automatfile/image/";
        //视频文件路径
        String LOAD_FILE_PATH_VIDEO = "automatfile/video/";
    }

    interface Config {
        //单柜的mid
//        String MID = "000BABC8D313";
        String MID = "HK001";


        //轮询间隔
        int LUNXUN_JIANGE = 3;
        //图片轮播时间间隔
        int IMG_XUNHUAN_JIANGE = 2;

        String GOODS_IMG_PATH = "http://www.4fz3.com/filebase";
        String IMG_PATH = "automat/screenImg/";
        String GOODS_LIST_CACHE_FILE_NAME = "goodsListCache";
        //获取支付二维码出错
        String CORE_FAIL = "fail";
        //获取支付二维码成功
        String CORE_SUCCESS = "success";
        int PAGE_COLUMN_V = 4;//首页列表纵数(竖)
        int PAGE_COLUMN_H = 4;//首页列表列数(横)


        int TYPE_T = 1;//温度设置
        int TYPE_VIDEO = 2;//视频屏保
        int TYPE_IMG = 3;//图片屏保
        int TYPE_URL_MALL = 5;//商城url更换
        int TYPE_WELCOME_V = 4;//更换欢迎用语
        int TYPE_URL_LUNXUN = 6;//轮询url更换
        int TYPE_URL_FANKUI = 7;//出货反馈url更换
        int TYPE_DEF = 9999;//无信息


        String PHONE = "13987654321";
        String QR_KEY_ALIPAY = "1";
        String QR_KEY_WXPAY = "0";
    }

    interface SPKeys {
        //屏保图片的版本
        String IMG_VERSION = "imgVersion";//图片屏保版本
        String GOODS_LIST = "goodsListJson";//商品列表
        String MID = "MID";//MID
        String URL_MALL = "mallUrl";//商城地址
        String URL_LUNXUN = "lunxunUrl";//轮询地址
        String URL_FANKUI = "fankuiUrl";//反馈地址
        String WELCOME_V = "welcomeV";//欢迎用语
    }

    public interface Action {
        String PIC = "zzrh.automat.pic";
    }
}
