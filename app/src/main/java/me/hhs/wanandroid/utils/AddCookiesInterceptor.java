package me.hhs.wanandroid.utils;

import android.content.Context;

import java.io.IOException;
import java.util.HashSet;

import me.hhs.wanandroid.WanAndroidApplication;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preference = (HashSet<String>) WanAndroidApplication.getContext().getSharedPreferences("cookieData", Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (preference != null) {
            for (String cookie : preference) {
                builder.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
