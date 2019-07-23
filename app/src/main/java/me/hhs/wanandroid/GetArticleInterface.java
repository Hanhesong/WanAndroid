package me.hhs.wanandroid;

import me.hhs.wanandroid.entity.SuperHomePageBean;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetArticleInterface {

       @GET("article/list/1/json")
    Call<SuperHomePageBean> getArticle();

}
