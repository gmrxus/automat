package com.zzrh.automat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zzrh.automat.bean.Goods;
import com.zzrh.automat.module.homepage.HomePageFragment;
import com.zzrh.automat.util.ListPageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gmrxus on 2018/3/13.
 */

public class HomePageViewPagerAdapter extends FragmentPagerAdapter {
    private int horizontal_int = 1;
    private int vertical_int = 1;
    private int page_int;
    private List<Goods> goodsList;
    private List<Fragment> fragmentList;

    public HomePageViewPagerAdapter(FragmentManager fm, List<Goods> goodsList, int h_int, int v_int) {
        super(fm);
        this.goodsList = goodsList;
        this.horizontal_int = h_int;
        this.vertical_int = v_int;
        fragmentList = new ArrayList<>();
        page_int = (int) Math.ceil(goodsList.size() / horizontal_int / vertical_int);
        List<List<Goods>> lists = ListPageUtil.splitList(goodsList, page_int);
        for (int i = 0; i < page_int; i++) {
            HomePageFragment fragment = new HomePageFragment(horizontal_int, vertical_int, lists.get(i));
            fragmentList.add(fragment);
        }
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return page_int;
    }
}
