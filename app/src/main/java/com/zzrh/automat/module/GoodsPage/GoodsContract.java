package com.zzrh.automat.module.GoodsPage;

import com.zzrh.automat.base.BasePresenter;
import com.zzrh.automat.base.BaseView;
import com.zzrh.automat.bean.Goods;

/**
 * Created by Gmrxus on 2018/3/13.
 */

public interface GoodsContract {
    interface View extends BaseView<Presenter> {
        void showGoods(Goods goods);

        void showPayCode(String url, String type);

        void showMsg(String msg);
    }

    interface Presenter extends BasePresenter {
        void loadGoods(Goods goods);

        void loadPayCode(String payTyep);
    }
}
