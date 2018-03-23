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
import com.zzrh.automat.util.SPUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
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
        LogUtil.logD("LunxunService没有执行");
//        lunxun();
        return START_STICKY;
    }

    private void lunxun() {
        Observable.interval(0, Keys.Config.LUNXUN_JIANGE, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Network.getLunxunApi().lunxun(Keys.Config.MID)
                                .subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.io())
                                .subscribe(new Observer<LunxunInfo>() {
                                    @Override
                                    public void onSubscribe(Disposable disposable) {

                                    }

                                    @Override
                                    public void onNext(LunxunInfo lunxunInfo) {
                                        if (BuildConfig.DEBUG) Log.d(TAG, lunxunInfo.toString());
                                        int type = lunxunInfo.getT();
                                        switch (type) {
                                            case Keys.Config.TYPE_T://温度
                                                // TODO: 2018/3/13 温度变更
                                                String data = lunxunInfo.getData();
                                                String[] splitData = data.split("&");
                                                String leftT = splitData[0].split("=")[1];
                                                String rightT = splitData[1].split("=")[1];
                                                if ("null".equals(rightT)) {
                                                    //单柜机,没有右边柜温度值,不用设置右边柜机温度
                                                } else {
                                                    //双柜机,两边温度都要设置
                                                }
                                                break;
                                            case Keys.Config.TYPE_VIDEO://下载视频
                                                startDownloadService(lunxunInfo, type);
                                                break;
                                            case Keys.Config.TYPE_IMG://下载图片
                                                startDownloadService(lunxunInfo, type);
                                                break;
                                            case Keys.Config.TYPE_URL_MALL://商城地址变更
                                                SPUtil.put(mContext, Keys.SPKeys.URL_MALL, lunxunInfo.getData());
                                                break;
                                            case Keys.Config.TYPE_WELCOME_V://更换欢迎用语
                                                SPUtil.put(mContext, Keys.SPKeys.WELCOME_V, lunxunInfo.getData());
                                                break;
                                            case Keys.Config.TYPE_URL_LUNXUN://轮询地址变更
                                                SPUtil.put(mContext, Keys.SPKeys.URL_LUNXUN, lunxunInfo.getData());
                                                break;
                                            case Keys.Config.TYPE_URL_FANKUI://出货反馈地址变更
                                                SPUtil.put(mContext, Keys.SPKeys.URL_FANKUI, lunxunInfo.getData());
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
