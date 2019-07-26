package me.hhs.wanandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KevinSong on 2019/7/26
 */
public class RetrofitUtils implements Serve {
     private static Retrofit mArticleRetrofit;

     public static  Retrofit  getArticlesRetrofit(String baseUrl){
         if (mArticleRetrofit ==null){
             Retrofit.Builder builder = new Retrofit.Builder();
             builder.baseUrl(baseUrl);
             builder.addConverterFactory(GsonConverterFactory.create());
             mArticleRetrofit = builder.build();
         }

         return mArticleRetrofit;
     }




}
