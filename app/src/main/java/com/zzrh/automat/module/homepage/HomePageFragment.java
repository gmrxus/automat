package com.zzrh.automat.module.homepage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzrh.automat.R;
import com.zzrh.automat.adapter.HomepageRVAdapter;
import com.zzrh.automat.bean.Goods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Gmrxus on 2018/3/13.
 */

@SuppressLint("ValidFragment")
public class HomePageFragment extends Fragment {
    @BindView(R.id.rv_homgpage_fragment)
    RecyclerView rv;
    Unbinder unbinder;

    private int horizontal_int;
    private int vertical_int;
    private List<Goods> goodsListPage;
    private int position;
    private List<Goods> goodsList;
    private HomePageActivity mActivity;

    @SuppressLint("ValidFragment")
    public HomePageFragment(List<Goods> goodsList, int horizontal_int, int vertical_int, List<Goods> goodsListPage, int position) {
        this.goodsList = goodsList;
        this.horizontal_int = horizontal_int;
        this.vertical_int = vertical_int;
        this.goodsListPage = goodsListPage;
        this.position = position;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (HomePageActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        unbinder = ButterKnife.bind(this, view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), vertical_int);
        rv.setLayoutManager(gridLayoutManager);
        HomepageRVAdapter adapter = new HomepageRVAdapter(this.getActivity(), goodsListPage, position);
        adapter.setOnItemClickListener((v, position) -> mActivity.showDetail(goodsList.get(position)));
        rv.setAdapter(adapter);
        return view;
    }

    @Override

    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
