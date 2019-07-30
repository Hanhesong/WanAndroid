package me.hhs.wanandroid.model.banner;

import android.content.Context;

import me.hhs.wanandroid.RetrofitUtils;
import me.hhs.wanandroid.Serve;
import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.entity.BannerBean;
import me.hhs.wanandroid.model.IOnResponseListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class BannerModelImpl implements IBannerModel, Serve {

    private Retrofit mretrofit;

    public BannerModelImpl() {
        mretrofit = RetrofitUtils.getRetrofit(Serve.BASE_URL);
    }

    @Override
    public void getBanner(Context context, final IOnResponseListener<BannerBean> onResponseListener) {
        GetBannerService service = mretrofit.create(GetBannerService.class);
        Call<BannerBean> call = service.getBanner();
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                if (response.body().getErrorCode()==0){
                    onResponseListener.onSuccess(response.body());
                }else {
                    //onResponseListener.onFailure(response.body().getErrorCode(),response.body().getErrorMsg());
                }
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                onResponseListener.onError(t);

            }
        });
    }

    interface GetBannerService{
        @GET("banner/json")
        Call<BannerBean> getBanner();
    }
}
