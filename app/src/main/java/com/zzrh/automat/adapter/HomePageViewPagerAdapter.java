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
    private List<Fragment> fragmentList;

    public HomePageViewPagerAdapter(FragmentManager fm, List<Goods> goodsList, int h_int, int v_int) {
        super(fm);
        this.horizontal_int = h_int;
        this.vertical_int = v_int;
        fragmentList = new ArrayList<>();
        page_int = (int) Math.ceil((float) goodsList.size() / horizontal_int / vertical_int);
        List<List<Goods>> lists = ListPageUtil.sectionList(
                goodsList,
                horizontal_int * vertical_int);
        for (int i = 0; i < page_int; i++) {
            HomePageFragment fragment = new HomePageFragment(goodsList, horizontal_int, vertical_int, lists.get(i), i);
            fragmentList.add(fragment);
        }

//        List<Goods> goodsList1 = new ArrayList<>();
//        for (int i = 0; i < horizontal_int * vertical_int; i++) {
//            goodsList1.add(goodsList.get(i));
//        }

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
