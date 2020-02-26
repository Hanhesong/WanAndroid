package me.hhs.wanandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import me.hhs.wanandroid.WanAndroidApplication;
import okhttp3.Interceptor;
import okhttp3.Response;

//创建拦截器拦截cookie
public class ReceivedCookiesInterceptor implements Interceptor {

    public ReceivedCookiesInterceptor() {
        super();
    }


    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
               // Log.i("Songzzz", "拦截的cookie是" + header);
                cookies.add(header);
            }
            SharedPreferences sharedPreferences = WanAndroidApplication.getContext().getSharedPreferences("cookieData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("cookie",cookies);
            editor.apply();
        }

        return originalResponse;
    }
}
