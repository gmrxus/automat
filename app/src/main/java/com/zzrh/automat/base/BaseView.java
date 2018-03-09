package com.zzrh.automat.base;

/**
 * Created by Administrator on 2018/3/2.
 */

public interface BaseView<T extends BasePresenter> {
	void setPresenter(T presenter);
}
