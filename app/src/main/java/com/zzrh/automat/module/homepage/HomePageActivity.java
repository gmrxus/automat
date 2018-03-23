package com.zzrh.automat.module.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.maning.library.SwitcherView;
import com.zzrh.automat.R;
import com.zzrh.automat.adapter.HomePageViewPagerAdapter;
import com.zzrh.automat.base.BaseActivity;
import com.zzrh.automat.bean.Goods;
import com.zzrh.automat.common.Keys;
import com.zzrh.automat.module.GoodsPage.GoodsDialogFragment;
import com.zzrh.automat.util.LogUtil;
import com.zzrh.automat.util.TUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/6.
 */

public class HomePageActivity extends BaseActivity implements HomePageContract.View {

    @BindView(R.id.tc_homepage)
    TextClock tcHomepage;
    @BindView(R.id.iv_homepage_setting)
    ImageView ivHomepageSetting;
    @BindView(R.id.rl_homepage)
    RelativeLayout rlHomepage;
    @BindView(R.id.tv_homepage_title)
    TextView tvHomepageTitle;
    //    @BindView(R.id.rv_homepage)
//    RecyclerView rvHomepage;
    @BindView(R.id.btn_homepage_bottom_1)
    Button btnHomepageBottom1;
    @BindView(R.id.btn_homepage_bottom_2)
    Button btnHomepageBottom2;
    @BindView(R.id.btn_homepage_bottom_3)
    Button btnHomepageBottom3;
    @BindView(R.id.btn_homepage_bottom_4)
    Button btnHomepageBottom4;
    @BindView(R.id.btn_homepage_bottom_5)
    Button btnHomepageBottom5;
    @BindView(R.id.btn_homepage_bottom_6)
    Button btnHomepageBottom6;
    @BindView(R.id.btn_homepage_bottom_7)
    Button btnHomepageBottom7;
    @BindView(R.id.btn_homepage_bottom_8)
    Button btnHomepageBottom8;
    @BindView(R.id.btn_homepage_bottom_9)
    Button btnHomepageBottom9;
    @BindView(R.id.btn_homepage_bottom_a)
    Button btnHomepageBottomA;
    @BindView(R.id.btn_homepage_bottom_b)
    Button btnHomepageBottomB;
    @BindView(R.id.btn_homepage_bottom_c)
    Button btnHomepageBottomC;
    @BindView(R.id.tv_homepage_huodao)
    EditText tvHuodao;
    @BindView(R.id.btn_homepage_confirm)
    Button btnHomepageConfirm;
    @BindView(R.id.rl_homepage_bottom)
    RelativeLayout rlHomepageBottom;
    @BindView(R.id.vp_homepage)
    ViewPager vpHomepage;
    @BindView(R.id.tv_homepage_number)
    SwitcherView tvHomepageNumber;
    private HomePageContract.Presenter mPresenter;
    private GoodsDialogFragment fragment;
    private ArrayList<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);
        mPresenter = new HomePagePresenter(this, this);
        mPresenter.start();
        strings.add("电话: " + Keys.Config.PHONE);
        strings.add("编号: " + Keys.Config.MID);
        tvHomepageNumber.setResource(strings);//加入显示内容,集合类型

    }

    @Override
    protected void onResume() {
        super.onResume();
        tvHomepageNumber.startRolling();//设置进入和退出的时间间隔

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tvHomepageNumber.destroySwitcher();
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showGoods(final List<Goods> goodsList) {
        LogUtil.logD("显示商品列表");
        HomePageViewPagerAdapter adapter = new HomePageViewPagerAdapter(getSupportFragmentManager(),
                goodsList,
                Keys.Config.PAGE_COLUMN_V,
                Keys.Config.PAGE_COLUMN_H);
        vpHomepage.setOffscreenPageLimit(3);
        vpHomepage.setAdapter(adapter);
    }

    @Override
    public void refresh() {

    }


    @Override
    public void showDetail(Goods goods) {
        fragment = new GoodsDialogFragment(this, goods);
        fragment.show(getSupportFragmentManager(), "GoodsDialogFragment");
    }

    @Override
    public void playMusic() {

    }

    @Override
    public void showPayCode() {

    }

    @Override
    public void showMsg(String msg) {
        TUtil.showToast(this, msg);
    }

    @OnClick({R.id.btn_homepage_bottom_1, R.id.btn_homepage_bottom_2, R.id.btn_homepage_bottom_3,
            R.id.btn_homepage_bottom_4, R.id.btn_homepage_bottom_5, R.id.btn_homepage_bottom_6,
            R.id.btn_homepage_bottom_7, R.id.btn_homepage_bottom_8, R.id.btn_homepage_bottom_9,
            R.id.btn_homepage_bottom_a, R.id.btn_homepage_bottom_b, R.id.btn_homepage_bottom_c,
            R.id.btn_homepage_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_homepage_bottom_1:
                selectCounter("1");
                break;
            case R.id.btn_homepage_bottom_2:
                selectCounter("2");
                break;
            case R.id.btn_homepage_bottom_3:
                selectCounter("3");
                break;
            case R.id.btn_homepage_bottom_4:
                selectCounter("4");
                break;
            case R.id.btn_homepage_bottom_5:
                selectCounter("5");
                break;
            case R.id.btn_homepage_bottom_6:
                selectCounter("6");
                break;
            case R.id.btn_homepage_bottom_7:
                selectCounter("7");
                break;
            case R.id.btn_homepage_bottom_8:
                selectCounter("8");
                break;
            case R.id.btn_homepage_bottom_9:
                selectCounter("9");
                break;
            case R.id.btn_homepage_bottom_a:
                selectCounter("A");
                break;
            case R.id.btn_homepage_bottom_b:
                selectCounter("B");
                break;
            case R.id.btn_homepage_bottom_c:
                selectCounter("C");
                break;
            case R.id.btn_homepage_confirm://选择货道
                mPresenter.showGoodsForHd(tvHuodao.getText().toString());
                break;
        }
    }

    private void selectCounter(String number) {
        Editable text = tvHuodao.getText();
        int length = text.length();
        if (1 == length) {
            text.append(number);
        } else {
            tvHuodao.setText(number);
        }
    }

}
