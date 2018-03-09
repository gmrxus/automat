package com.zzrh.automat.module.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zzrh.automat.R;
import com.zzrh.automat.base.BaseActivity;
import com.zzrh.automat.bean.Goods;

/**
 * Created by Administrator on 2018/3/6.
 */

public class HomePageActivity extends BaseActivity implements HomePageContract.View {

	private HomePageContract.Presenter mPresenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		mPresenter = new HomePagePresenter();
	}

	@Override
	public void setPresenter(HomePageContract.Presenter presenter) {
		mPresenter = presenter;
	}

	@Override
	public void showGoods() {

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
}
