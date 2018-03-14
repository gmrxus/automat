package com.zzrh.automat.network.api;

import com.zzrh.automat.bean.GoodsInfo;
import com.zzrh.automat.bean.PayCodeInfo;
import com.zzrh.automat.common.Urls;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Gmrxus on 2018/3/12.
 */

public interface GoodsApi {
    @POST(Urls.goodsList)
    Observable<GoodsInfo> loadGoodsList(@Query("mid") String mid);

    @GET(Urls.payCode)
    Observable<PayCodeInfo> loadPayCode(@Query("machineGoodsId") String machineGoodsId,
                                        @Query("payType") String payType);
}
