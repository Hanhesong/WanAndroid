package me.hhs.wanandroid.model.register;

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

public class RegisterModelImpl implements IRegisterModel {

    private Retrofit mRetrofit;

    public RegisterModelImpl() {
        mRetrofit = RetrofitUtils.getRetrofit(Serve.BASE_URL);
    }

    @Override
    public void goRegister(Context context, String username, String password, final String rePassword, final IOnResponseListener<LoginBean> onResponseListener) {
        goRegisterService goRegisterService = mRetrofit.create(RegisterModelImpl.goRegisterService.class);
        Call<LoginBean> call = goRegisterService.goLoginBean(username, password, rePassword);
        Callback<LoginBean> callback = new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                int errorCode = response.body().getErrorCode();
                if (errorCode == 0) {
                    onResponseListener.onSuccess(response.body());
                } else {
                    onResponseListener.onFailure(response.body().getErrorCode() + "", response.body().getErrorMsg());
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                onResponseListener.onError(t);
            }
        };
        call.enqueue(callback);


    }

    interface goRegisterService {
        @FormUrlEncoded
        @POST("user/register")
        Call<LoginBean> goLoginBean(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
    }
}
