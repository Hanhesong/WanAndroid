package me.hhs.wanandroid;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by KevinSong on 2019/7/23
 */
public interface GetBannerRequest {
    @GET("banner/json")
    Call<BannerBean> getBannerCall();
}
