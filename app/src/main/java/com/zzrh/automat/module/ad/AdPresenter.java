package com.zzrh.automat.module.ad;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.zzrh.automat.common.Keys;
import com.zzrh.automat.service.LunxunService;
import com.zzrh.automat.util.SPUtil;

import java.io.File;

/**
 * Created by Administrator on 2018/3/2.
 */

public class AdPresenter implements AdContract.Presenter {
	private Context mContext;
	private AdContract.View mView;

	public AdPresenter(Context context, AdContract.View view) {
		mContext = context;
		mView = view;
		mView.setPresenter(this);
		start();
	}

	@Override
	public void start() {
		mContext.startService(new Intent(mContext, LunxunService.class));
	}

	@Override
	public void loadScreensaverRes() {
		// TODO: 2018/3/7 判断显示视频还是图片
		String version = (String) SPUtil.get(mContext, Keys.SPKeys.IMG_VERSION, "");
		String picPath = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath() + "/" + version;
		File[] imgFiles = new File(picPath).listFiles();
//		File[] imgFiles = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).listFiles();
		if (imgFiles == null || imgFiles.length == 0) {
			File[] files = mContext.getExternalFilesDir(Environment.DIRECTORY_MOVIES).listFiles();
			if (files == null || files.length == 0) {
				mView.showDefImg();
			} else {
				mView.showVideo();
			}
		} else {
			mView.showImg(imgFiles);
		}
	}
}
