package com.zzrh.automat.module.homepage;

import com.zzrh.automat.base.BasePresenter;
import com.zzrh.automat.base.BaseView;
import com.zzrh.automat.bean.Goods;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public interface HomePageContract {
    interface View extends BaseView<Presenter> {
        void showGoods(List<Goods> goods);

        void refresh();

        void showDetail(Goods goods);

        void playMusic();

        void showPayCode();

        void showMsg(String msg);
    }

    interface Presenter extends BasePresenter {
        //加载商品数据
        void loadData();

        //加载支付数据
        String loadPaydata();

        void showGoodsForHd(String hdId);

    }

}
