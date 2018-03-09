package com.zzrh.automat.network.api;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/3/7.
 */

public interface DownloadApi {
	@GET
	Observable<ResponseBody> downloadFromNet(@Url String path);
}
