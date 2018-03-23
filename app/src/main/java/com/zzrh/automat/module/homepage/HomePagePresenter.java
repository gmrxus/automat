package com.zzrh.automat.module.homepage;


import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.zzrh.automat.bean.Goods;
import com.zzrh.automat.bean.GoodsInfo;
import com.zzrh.automat.common.Keys;
import com.zzrh.automat.network.Network;
import com.zzrh.automat.util.SDCardUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleOperator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/6.
 */

public class HomePagePresenter implements HomePageContract.Presenter {
    private static final String TAG = "HomePagePresenter";
    private Context mContext;
    private HomePageContract.View mView;
    private Gson gson = new Gson();
    private String mid = Keys.Config.MID;
    private GoodsInfo info;
    private List<Goods> mGoodsList = new ArrayList<>();

    public HomePagePresenter(Context mContext, HomePageContract.View mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void start() {
        loadData();
    }

    @Override
    public void loadData() {
        Single.create((SingleOnSubscribe<String>) singleEmitter -> {
            String s = SDCardUtil.loadFileFromSDCardStr(SDCardUtil.getSDCardPrivateFilesDir(mContext,
                    Environment.DIRECTORY_DOCUMENTS) + "/" + Keys.Config.GOODS_LIST_CACHE_FILE_NAME);
            singleEmitter.onSuccess(s);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .lift((SingleOperator<GoodsInfo, String>) singleObserver -> new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onSuccess(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            info = gson.fromJson(s, GoodsInfo.class);
                            mGoodsList.clear();
                            mGoodsList.addAll(info.getData().getGoodslist());
                            singleObserver.onSuccess(info);
                        } else {
                            loadGoodsListNet();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        loadGoodsListNet();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GoodsInfo>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onSuccess(GoodsInfo info) {
                        List<Goods> goodsList = info.getData().getGoodslist();
                        mView.showGoods(goodsList);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                });
    }

    @Override
    public String loadPaydata() {
        return null;
    }

    @Override
    public void showGoodsForHd(String hdId) {
        if (!TextUtils.isEmpty(hdId)) {
            for (Goods goods : mGoodsList) {
                if (hdId.equals(goods.getHdId())) {
                    mView.showDetail(goods);
                    return;
                }
            }
            mView.showMsg("货道输入有误");
        }
    }

    private void loadGoodsListNet() {
        Network.getGoodsApi()
                .loadGoodsList(mid)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(info -> {
                    String infoJson = gson.toJson(info);
                    SDCardUtil.saveFileToSDCardPrivateFilesDir(infoJson.getBytes(),
                            Environment.DIRECTORY_DOCUMENTS,
                            Keys.Config.GOODS_LIST_CACHE_FILE_NAME,
                            mContext);
                    return info;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    List<Goods> goodslist = info.getData().getGoodslist();
                    mGoodsList.clear();
                    mGoodsList.addAll(goodslist);
                    mView.showGoods(goodslist);
                });
    }


}
