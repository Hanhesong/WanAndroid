package me.hhs.wanandroid.model.login;

import android.content.Context;

import me.hhs.wanandroid.RetrofitUtils;
import me.hhs.wanandroid.Serve;
import me.hhs.wanandroid.entity.LoginBean;
import me.hhs.wanandroid.model.IOnResponseListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class LoginModelImpl implements ILoginModel, Serve {

    public Retrofit mRetrofit;

    public LoginModelImpl() {
        mRetrofit = RetrofitUtils.getRetrofit(BASE_URL);
    }

    @Override
    public void goLogin(Context context, String username, String password, final IOnResponseListener<LoginBean> onResponseListener) {
        GoLoginService goLoginService = mRetrofit.create(GoLoginService.class);
        Call<LoginBean> call = goLoginService.goLogin(username, password);
        Callback<LoginBean> callback = new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                int errorCode = response.body().getErrorCode();
                if (errorCode == 0) {
                    onResponseListener.onSuccess(response.body());
                } else if (errorCode == -1) {
                    onResponseListener.onFailure(response.body().getErrorCode()+"", response.body().getErrorMsg());
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                onResponseListener.onError(t);
            }
        };
        call.enqueue(callback);
    }

    interface GoLoginService {
        @POST("user/login")
        @FormUrlEncoded
        Call<LoginBean> goLogin(@Field("username") String username, @Field("password") String password);
    }
}
