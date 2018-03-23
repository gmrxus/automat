package com.zzrh.automat.module.GoodsPage;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zzrh.automat.R;
import com.zzrh.automat.bean.Goods;
import com.zzrh.automat.common.Keys;
import com.zzrh.automat.util.QRCodeUtil;
import com.zzrh.automat.util.ScreenUtil;
import com.zzrh.automat.util.TUtil;
import com.zzrh.automat.widget.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Gmrxus on 2018/3/13.
 */

@SuppressLint("ValidFragment")
public class GoodsDialogFragment extends DialogFragment implements GoodsContract.View {

    @BindView(R.id.tv_dialog_homepage_title)
    TextView tvTitle;
    @BindView(R.id.iv_dialog_homepage)
    ImageView ivImg;
    @BindView(R.id.tv_dialog_homepage_price)
    TextView tvPrice;
    @BindView(R.id.tv_dialog_homepage_price_p)
    TextView tvPriceP;
    @BindView(R.id.tv_dialog_homepage_price_3)
    TextView tvPrice1;
    @BindView(R.id.iv_dialog_homepage_qrcode)
    ImageView ivQRCode;
    @BindView(R.id.btn_dialog_homepage_alipay)
    ImageButton btnAlipay;
    @BindView(R.id.btn_dialog_homepage_wxpay)
    ImageButton btnWxpay;
    @BindView(R.id.tv_dialog_homepage_countdown)
    TextView tvCountDown;
    Unbinder unbinder;
    private GoodsContract.Presenter mPresenter;
    private Context mContext;
    private Goods mGoods;
    private CountDownTimer mtimer;
    private Dialog mDialog;
    private Dialog dialog;

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
    public void onResume() {
        super.onResume();
        int screenWidth = ScreenUtil.getScreenWidth(getActivity());
        int screenHeight = ScreenUtil.getScreenHeight(getActivity());
        getDialog().getWindow().setLayout(screenWidth, screenHeight - 50);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start();
        payLoading();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialog = super.onCreateDialog(savedInstanceState);
        mDialog.setCanceledOnTouchOutside(false);
        mtimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (tvCountDown != null) {
                    tvCountDown.setText(millisUntilFinished / 1000 + "");
                }
            }

            @Override
            public void onFinish() {
                dismiss();
            }
        };
        mtimer.start();
        mDialog.setOnCancelListener(dialog -> mtimer.cancel());
        return mDialog;
    }

    @Override
    public void setPresenter(GoodsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showGoods(Goods goods) {
        tvTitle.setText(goods.getGoodsName());
        tvPrice.setText(goods.getGoodsPrice());
        tvPrice1.setText(goods.getGoodsPrice());
        tvPriceP.setText(goods.getGoodsPrice());
        if (TextUtils.isEmpty(goods.getGoodsUrl())) {
            return;
        }
        GlideApp.with(getActivity())
                .load(Keys.Config.GOODS_IMG_PATH + goods.getGoodsUrl())
                .into(ivImg);


    }

    @Override
    public void showPayCode(String url, String type) {
//        ivQRCode.setImageBitmap(bm);
        if (Keys.Config.QR_KEY_ALIPAY.equals(type)) {
            btnAlipay.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_bg_btn_dialog_pay_1));
            btnWxpay.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_bg_btn_dialog_pay_0));
        } else {
            btnAlipay.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_bg_btn_dialog_pay_0));
            btnWxpay.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_bg_btn_dialog_pay_1));
        }
        GlideApp.with(mContext)
                .asBitmap()
                .load(QRCodeUtil.createQRCodeBitmap(url, 300, 300))
//                .placeholder(R.drawable.ic_loadin)
                .into(ivQRCode);
    }


    @Override
    public void showMsg(String msg) {
        TUtil.showToast(this.getActivity(), msg);
    }

    @Override
    public void showPayDialog() {
        dialog = new Dialog(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_goods_pay, null);
        dialog.setContentView(view);
        TextView tvCountdown = view.findViewById(R.id.tv_dialog_pay_countdown);
        ImageView ivProgress = view.findViewById(R.id.iv_dialog_pay_progress);
        Glide.with(mContext).asGif().load(R.drawable.ic_loadin).into(ivProgress);
        dialog.show();
        CountDownTimer timer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvCountdown.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                dialog.cancel();

            }
        };
        dialog.setOnCancelListener(dialog1 -> {
            timer.cancel();
            mDialog.cancel();
        });
        timer.start();


    }

    @Override
    public void showPayFailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("出货失败");
        builder.setMessage("请联系客服: " + Keys.Config.PHONE);
        builder.setPositiveButton("确定", (dialog, which) -> {
            GoodsDialogFragment.this.dialog.cancel();
        });
        builder.show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mtimer != null) {
            mtimer.cancel();
            mtimer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter = null;
        }
        unbinder.unbind();
    }


    @OnClick({R.id.btn_dialog_homepage_alipay, R.id.btn_dialog_homepage_wxpay})
    public void onViewClicked(View view) {
        payLoading();
        switch (view.getId()) {
            case R.id.btn_dialog_homepage_alipay:
                mPresenter.loadPayCode(Keys.Config.QR_KEY_ALIPAY);
                break;
            case R.id.btn_dialog_homepage_wxpay:
                mPresenter.loadPayCode(Keys.Config.QR_KEY_WXPAY);
                break;
        }
    }

    private void payLoading() {
        Glide.with(mContext).asGif().load(R.drawable.ic_loadin).into(ivQRCode);
    }
}
