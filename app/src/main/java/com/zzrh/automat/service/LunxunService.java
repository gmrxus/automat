package com.zzrh.automat.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zzrh.automat.App;
import com.zzrh.automat.BuildConfig;
import com.zzrh.automat.bean.LunxunInfo;
import com.zzrh.automat.common.Keys;
import com.zzrh.automat.network.Network;
import com.zzrh.automat.util.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/6.
 */

public class LunxunService extends Service {
	private static final String TAG = "LunxunService";
	public static final String ACTION = "com.zzrh.automat.Service.LunxunService";

	public static boolean isServiceRunning = false;
	private static Context mContext = null;
	private CountDownTimer countDownTimer = null;
	private Intent mIntent;


	@Override
	public void onCreate() {
		super.onCreate();
		mContext = App.sContext;
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		throw new UnsupportedOperationException("非法绑定服务");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtil.logD("LunxunService开始执行");
		lunxun();
		return START_STICKY;
	}

	private void lunxun() {
		Observable.interval(0, Keys.Conifg.LUNXUN_JIANGE, TimeUnit.SECONDS)
				.doOnNext(new Consumer<Long>() {
					@Override
					public void accept(Long aLong) throws Exception {
						Network.getLunxunApi().lunxun(Keys.Conifg.mid)
								.subscribeOn(Schedulers.io())
								.observeOn(AndroidSchedulers.mainThread())
								.subscribe(new Observer<LunxunInfo>() {
									@Override
									public void onSubscribe(Disposable disposable) {

									}

									@Override
									public void onNext(LunxunInfo lunxunInfo) {
										if (BuildConfig.DEBUG) Log.d(TAG, lunxunInfo.toString());
										int type = lunxunInfo.getT();
										switch (type) {
											case Keys.Conifg.TYPE_IMG://下载图片
												startDownloadService(lunxunInfo, type);
												break;
											case Keys.Conifg.TYPE_VIDEO://下载视频
												startDownloadService(lunxunInfo, type);
												break;
											case 9999:
												break;
										}
									}

									@Override
									public void onError(Throwable throwable) {
										LogUtil.logE("轮询异常", throwable);
									}

									@Override
									public void onComplete() {

									}
								});
					}


				}).subscribe(new Consumer<Long>() {
			@Override
			public void accept(Long aLong) throws Exception {
				if (BuildConfig.DEBUG) Log.d(TAG, "aLong:" + aLong);
			}
		});

	}

	private void startDownloadService(LunxunInfo lunxunInfo, int type) {
		if (mIntent == null) {
			mIntent = new Intent(mContext, DownloadService.class);
		}
		mIntent.putExtra("path", lunxunInfo.getData());
		mIntent.putExtra("type", type);
		if (type == 3) {
			mIntent.putExtra("version", lunxunInfo.getVersion());
		}
		startService(mIntent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (countDownTimer != null) {
			countDownTimer.cancel();
			countDownTimer = null;
		}
	}
}
