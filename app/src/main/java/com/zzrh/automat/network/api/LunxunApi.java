package com.zzrh.automat.network.api;

import com.zzrh.automat.bean.LunxunInfo;
import com.zzrh.automat.common.Urls;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/3/6.
 */

public interface LunxunApi {
	@GET(Urls.lunxun)
	Observable<LunxunInfo> lunxun(@Query("mid") String mid);
}
