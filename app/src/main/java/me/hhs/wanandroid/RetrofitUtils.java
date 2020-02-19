package me.hhs.wanandroid;

import me.hhs.wanandroid.utils.AddCookiesInterceptor;
import me.hhs.wanandroid.utils.ReceivedCookiesInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KevinSong on 2019/7/26
 */
public class RetrofitUtils implements Serve {
    private static Retrofit mRetrofit;

    public static Retrofit getRetrofit(String baseUrl) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addInterceptor(new AddCookiesInterceptor())
                .build();
        if (mRetrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(baseUrl);
            builder.client(okHttpClient);
            builder.addConverterFactory(GsonConverterFactory.create());
            mRetrofit = builder.build();
        }
        return mRetrofit;
    }
}
