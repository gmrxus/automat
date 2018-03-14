package com.zzrh.automat.module.homepage;


import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.zzrh.automat.bean.Goods;
import com.zzrh.automat.bean.GoodsInfo;
import com.zzrh.automat.common.Keys;
import com.zzrh.automat.network.Network;
import com.zzrh.automat.util.LogUtil;
import com.zzrh.automat.util.SDCardUtil;
import com.zzrh.automat.util.SPUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/6.
 */

public class HomePagePresenter implements HomePageContract.Presenter {
    private Context mContext;
    private HomePageContract.View mView;

    public HomePagePresenter(Context mContext, HomePageContract.View mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void start() {
        loadData();
    }

    @Override
    public List<Goods> loadData() {
        final Gson gson = new Gson();
        String mid = Keys.Conifg.mid;
        final String goodsListJson = (String) SPUtil.get(mContext, Keys.SPKeys.GOODS_LIST, "");
        if (TextUtils.isEmpty(goodsListJson)) {
            Network.getGoodsApi()
                    .loadGoodsList(mid)
                    .flatMap(new Function<GoodsInfo, ObservableSource<List<Goods>>>() {
                        @Override
                        public ObservableSource<List<Goods>> apply(GoodsInfo info) throws Exception {
                            String goodsInfoJson = gson.toJson(info);
                            SDCardUtil.saveFileToSDCardPrivateFilesDir(goodsInfoJson.getBytes(),
                                    Environment.DIRECTORY_DOCUMENTS,
                                    Keys.Conifg.GOODS_LIST_CACHE_FILE_NAME, mContext);

                            return Observable.just(info.getData().getGoodslist());
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Goods>>() {
                        @Override
                        public void accept(List<Goods> goodsList) throws Exception {
                            LogUtil.logD("获取商品列表正常");
                            mView.showGoods(goodsList);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            LogUtil.logE("获取商品列表异常", throwable);
                        }
                    });

        }
        return null;
    }

    @Override
    public String loadPaydata() {
        return null;
    }
}
