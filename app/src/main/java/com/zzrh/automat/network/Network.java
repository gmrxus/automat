package com.zzrh.automat.network;

import com.zzrh.automat.common.Urls;
import com.zzrh.automat.network.api.DownloadApi;
import com.zzrh.automat.network.api.LunxunApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/3/6.
 */

public class Network {
	private static LunxunApi lunxunApi;
	private static DownloadApi downloadApi;

	private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
	private static CallAdapter.Factory rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
	private static OkHttpClient okHttpClient = new OkHttpClient();

	public static LunxunApi getLunxunApi() {
		if (lunxunApi == null) {
			Retrofit retrofit = new Retrofit.Builder()
					.client(okHttpClient)
					.baseUrl(Urls.IP)
					.addConverterFactory(gsonConverterFactory)
					.addCallAdapterFactory(rxJava2CallAdapterFactory)
					.build();
			lunxunApi = retrofit.create(LunxunApi.class);
		}
		return lunxunApi;
	}

	public static DownloadApi getDownloadApi() {
		if (downloadApi == null) {
			downloadApi = new Retrofit.Builder()
					.client(okHttpClient)
					.baseUrl(Urls.IP)
					.addConverterFactory(gsonConverterFactory)
					.addCallAdapterFactory(rxJava2CallAdapterFactory)
					.build()
					.create(DownloadApi.class);
		}
		return downloadApi;
	}


}
