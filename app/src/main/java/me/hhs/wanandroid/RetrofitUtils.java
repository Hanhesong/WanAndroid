package me.hhs.wanandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by KevinSong on 2019/7/26
 */
public class RetrofitUtils implements Serve {
     private static Retrofit mRetrofit;

     public static  Retrofit getRetrofit(String baseUrl){
         if (mRetrofit ==null){
             Retrofit.Builder builder = new Retrofit.Builder();
             builder.baseUrl(baseUrl);
            // builder.addConverterFactory(ScalarsConverterFactory.create());
             builder.addConverterFactory(GsonConverterFactory.create());

             mRetrofit = builder.build();
         }
         return mRetrofit;
     }




}
