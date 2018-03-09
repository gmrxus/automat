package com.zzrh.automat.module.ad;

import com.zzrh.automat.base.BasePresenter;
import com.zzrh.automat.base.BaseView;

import java.io.File;

/**
 * Created by Administrator on 2018/3/2.
 */

public interface AdContract {
	interface View extends BaseView<Presenter> {
		void showImg(File[] imgFiles);

		void showVideo();

		void showDefImg();

	}

	interface Presenter extends BasePresenter {
		/**
		 * 加载资源
		 */
		void loadScreensaverRes();
	}
}
