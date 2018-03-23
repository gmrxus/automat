package com.zzrh.automat.module.GoodsPage;

import android.content.Context;

import com.zzrh.automat.bean.Goods;
import com.zzrh.automat.bean.PayCodeInfo;
import com.zzrh.automat.common.Keys;
import com.zzrh.automat.network.Network;
import com.zzrh.automat.util.LogUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gmrxus on 2018/3/13.
 */

public class GoodsPresenter implements GoodsContract.Presenter {
    private Context mContext;
    private GoodsContract.View mView;
    private Goods mGoods;

    public GoodsPresenter(Context mContext, GoodsContract.View mView, Goods goods) {
        this.mContext = mContext;
        this.mView = mView;
        this.mGoods = goods;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadGoods(mGoods);
    }

    @Override
    public void loadGoods(Goods goods) {
        mView.showGoods(goods);
        loadPayCode(Keys.Config.QR_KEY_ALIPAY);
    }

    @Override
    public void loadPayCode(final String payType) {
        Network.getGoodsApi()
                .loadPayCode(mGoods.getMachineGoodsId(), payType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PayCodeInfo>() {
                    @Override
                    public void accept(PayCodeInfo payCodeInfo) throws Exception {
                        LogUtil.logD(payCodeInfo);
                        if (Keys.Config.CORE_SUCCESS.equals(payCodeInfo.getReturnCode())) {
                            mView.showPayCode(payCodeInfo.getData().getQrUrl(), payType);
                        } else {
                            mView.showMsg(payCodeInfo.getMessage());
                        }
                    }
                });
    }
}
