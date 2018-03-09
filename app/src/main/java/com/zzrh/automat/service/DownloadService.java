package com.zzrh.automat.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zzrh.automat.App;
import com.zzrh.automat.common.Keys;
import com.zzrh.automat.module.ad.AdContract;
import com.zzrh.automat.network.Network;
import com.zzrh.automat.util.LogUtil;
import com.zzrh.automat.util.SDCardUtil;
import com.zzrh.automat.util.SPUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/3/2.
 */

public class DownloadService extends IntentService {
	private static final String TAG = "DownloadService";

	private Context mContext;
	private AdContract.Presenter mPresenter;

	public DownloadService() {
		super(null);

	}

	public DownloadService(String name) {
		super(name);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = App.sContext;
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		String path = intent.getStringExtra("path");
		final int type = intent.getIntExtra("type", 0);
		final String version = intent.getStringExtra("version");

		if (Keys.Conifg.TYPE_VIDEO == type) {
			try {
				path = URLDecoder.decode(path, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		final String finalPath = path;
		Network.getDownloadApi().downloadFromNet(path)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<ResponseBody>() {
					@Override
					public void onSubscribe(Disposable disposable) {

					}

					@Override
					public void onNext(ResponseBody body) {
						if (Keys.Conifg.TYPE_IMG == type) {
							savePic(finalPath, body, version);
						} else {
							saveVideo(finalPath, body);
						}
					}

					@Override
					public void onError(Throwable throwable) {
						LogUtil.logE("下载异常", throwable);
						LogUtil.logD(throwable);
					}

					@Override
					public void onComplete() {

					}
				});
	}


	private void saveVideo(String path, ResponseBody body) {
		removeVideo();
		String[] str = path.split("/");
		try {
			SDCardUtil.saveFileToSDCardPrivateFilesDir(body.bytes(),
					Environment.DIRECTORY_MOVIES, str[str.length - 1],
					App.sContext);
//			removePic();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void savePic(String path, ResponseBody body, final String version) {

		final String[] strs = path.split("/");
		Glide.with(App.sContext)
				.load(path)
				.asBitmap()
				.into(new SimpleTarget<Bitmap>() {
					@Override
					public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
						SDCardUtil.saveBitmapToSDCardPrivateFliesDir(resource,
								App.sContext,
								strs[strs.length - 1], version);
						String lastVersion = (String) SPUtil.get(mContext, Keys.SPKeys.IMG_VERSION, "");
						if (!lastVersion.equals(version)) {
							removePic(lastVersion);
						}
						SPUtil.put(mContext, Keys.SPKeys.IMG_VERSION, version);
						removeVideo();
					}
				});
	}

	private void removePic(String pictureParentPath) {
		SDCardUtil.removeFileFromSDCard(App.sContext
				.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath() + "/" + pictureParentPath);
	}

	private void removeVideo() {
		SDCardUtil.removeFileFromSDCard(App.sContext
				.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getPath());
	}

}
