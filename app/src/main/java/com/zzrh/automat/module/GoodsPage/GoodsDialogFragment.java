package com.zzrh.automat.module.GoodsPage;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzrh.automat.R;
import com.zzrh.automat.bean.Goods;
import com.zzrh.automat.util.QRCodeUtil;
import com.zzrh.automat.util.TUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Gmrxus on 2018/3/13.
 */

@SuppressLint("ValidFragment")
public class GoodsDialogFragment extends DialogFragment implements GoodsContract.View {
    @BindView(R.id.tv_dialog_goods_title)
    TextView tvTitle;
    @BindView(R.id.iv_dialog_goods_img)
    ImageView ivGoodsImg;
    @BindView(R.id.tv_dialog_goods_price)
    TextView tvPrice;
    @BindView(R.id.tv_dialog_goods_money)
    TextView tvMoney;
    @BindView(R.id.iv_dialog_goods_core_img)
    ImageView ivCoreImg;
    @BindView(R.id.tv_dialog_goods_countdown)
    TextView tvCountdown;
    @BindView(R.id.ivbtn_dialog_goods_alipay)
    ImageButton ivbtnAlipay;
    @BindView(R.id.ivbtn_dialog_goods_wxpay)
    ImageButton ivbtnWxpay;
    Unbinder unbinder;
    private GoodsContract.Presenter mPresenter;
    private Context mContext;
    private Goods mGoods;

    public GoodsDialogFragment(Context mContext, Goods mGoods) {
        this.mContext = mContext;
        this.mGoods = mGoods;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new GoodsPresenter(mContext, this, mGoods);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_homepage_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void setPresenter(GoodsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showGoods(Goods goods) {

    }

    @Override
    public void showPayCode(String url, String type) {
        Bitmap bm = QRCodeUtil.createQRCodeBitmap(url, 300, 300);
//        Glide.with(this).load(bm).into(ivCoreImg);
        ivCoreImg.setImageBitmap(bm);
    }

    @Override
    public void showMsg(String msg) {
        TUtil.showToast(this.getActivity(), msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter = null;
        }
    }

    @OnClick({R.id.ivbtn_dialog_goods_alipay, R.id.ivbtn_dialog_goods_wxpay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivbtn_dialog_goods_alipay:
                break;
            case R.id.ivbtn_dialog_goods_wxpay:
                break;
        }
    }
}
