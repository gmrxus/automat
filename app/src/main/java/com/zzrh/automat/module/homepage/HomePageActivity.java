package com.zzrh.automat.module.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.gcssloop.widget.PagerGridLayoutManager;
import com.maning.library.SwitcherView;
import com.zzrh.automat.R;
import com.zzrh.automat.adapter.HomePageViewPagerAdapter;
import com.zzrh.automat.base.BaseActivity;
import com.zzrh.automat.bean.Goods;
import com.zzrh.automat.common.Keys;
import com.zzrh.automat.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/6.
 */

public class HomePageActivity extends BaseActivity implements HomePageContract.View
        , PagerGridLayoutManager.PageListener {

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
    TextView tvHomepageHuodao;
    @BindView(R.id.btn_homepage_confirm)
    Button btnHomepageConfirm;
    @BindView(R.id.rl_homepage_bottom)
    RelativeLayout rlHomepageBottom;
    @BindView(R.id.tv_homepage_number)
    SwitcherView tvHomepageNumber;
    @BindView(R.id.btn_homepage_bottom_1)
    Button btnHomepageBottom1;
    @BindView(R.id.vp_homepage)
    ViewPager vpHomepage;
    private HomePageContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
//        PagerGridLayoutManager layoutManager = new PagerGridLayoutManager(4, 4, PagerGridLayoutManager.HORIZONTAL);
//        layoutManager.setPageListener(this);    // 设置页面变化监听器
//        rvHomepage.setLayoutManager(layoutManager);
//        layoutManager.setChangeSelectInScrolling(false);
//        PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
//        pageSnapHelper.attachToRecyclerView(rvHomepage);


        ArrayList<String> strings = new ArrayList<>();
        strings.add("电话: 13909876543");
        strings.add("编号: 9088-987-654");
        tvHomepageNumber.setResource(strings);
        mPresenter = new HomePagePresenter(this, this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        tvHomepageNumber.startRolling();
        mPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tvHomepageNumber.destroySwitcher();
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showGoods(final List<Goods> goodsList) {
        LogUtil.logD("显示商品列表");

        HomePageViewPagerAdapter adapter = new HomePageViewPagerAdapter(getSupportFragmentManager(), goodsList, Keys.Conifg.PAGE_COLUMN_H, Keys.Conifg.PAGE_COLUMN_V);
        vpHomepage.setAdapter(adapter);
//        HomepageRVAdapter adapter = new HomepageRVAdapter(this, goodsList);
////        rvHomepage.setAdapter(adapter);
//        adapter.setOnItemClickListener(new HomepageRVAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                LogUtil.logD("点击了" + position);
//                GoodsDialogFragment dialog = new GoodsDialogFragment(HomePageActivity.this,
//                        goodsList.get(position));
//                dialog.show(getSupportFragmentManager(), "GoodsDialogFragment");
//            }
//        });

    }

    @Override
    public void refresh() {

    }

    @Override
    public void showDetail(Goods goods) {

    }

    @Override
    public void playMusic() {

    }

    @Override
    public void showPayCode() {

    }

    @OnClick({R.id.btn_homepage_bottom_1, R.id.btn_homepage_bottom_2, R.id.btn_homepage_bottom_3,
            R.id.btn_homepage_bottom_4, R.id.btn_homepage_bottom_5, R.id.btn_homepage_bottom_6,
            R.id.btn_homepage_bottom_7, R.id.btn_homepage_bottom_8, R.id.btn_homepage_bottom_9,
            R.id.btn_homepage_bottom_a, R.id.btn_homepage_bottom_b, R.id.btn_homepage_bottom_c,
            R.id.btn_homepage_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_homepage_bottom_1:
                break;
            case R.id.btn_homepage_bottom_2:
                break;
            case R.id.btn_homepage_bottom_3:
                break;
            case R.id.btn_homepage_bottom_4:
                break;
            case R.id.btn_homepage_bottom_5:
                break;
            case R.id.btn_homepage_bottom_6:
                break;
            case R.id.btn_homepage_bottom_7:
                break;
            case R.id.btn_homepage_bottom_8:
                break;
            case R.id.btn_homepage_bottom_9:
                break;
            case R.id.btn_homepage_bottom_a:
                break;
            case R.id.btn_homepage_bottom_b:
                break;
            case R.id.btn_homepage_bottom_c:
                break;
            case R.id.btn_homepage_confirm:
                break;
        }
    }

    @Override
    public void onPageSizeChanged(int pageSize) {
//        Log.e("TAG", "总页数 = " + pageSize);
    }

    @Override
    public void onPageSelect(int pageIndex) {
//        Log.e("TAG", "选中页码 = " + (pageIndex + 1));
    }


}
