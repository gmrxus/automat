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

	interface Conifg {
		//单柜的mid
//		String mid = "000BABC8D313";
		String mid = "HK001";


		//轮询间隔
		int LUNXUN_JIANGE = 3;
		//图片轮播时间间隔
		int IMG_XUNHUAN_JIANGE = 2;

		int TYPE_IMG = 3;
		int TYPE_VIDEO = 2;
		int TYPE_DEF = 9999;


		String IMG_PATH = "automat/screenImg/";
	}

	interface SPKeys {
		//屏保图片的版本
		String IMG_VERSION = "imgVersion";
	}

	public interface Action {
		String PIC = "zzrh.automat.pic";
	}
}
