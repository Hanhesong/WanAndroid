package me.hhs.wanandroid.model.collect;

import android.content.Context;

import me.hhs.wanandroid.RetrofitUtils;
import me.hhs.wanandroid.Serve;
import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.model.IOnResponseListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class CollectModelImpl implements ICollectModel, Serve {

    private Retrofit mRetrofit;

    public CollectModelImpl() {
        mRetrofit = RetrofitUtils.getRetrofit(BASE_URL);
    }

    @Override
    public void getCollect(Context context, int page, final IOnResponseListener<ArticleDataBean> onResponseListener) {
        GetCollectArticleService service = mRetrofit.create(GetCollectArticleService.class);
        Call<ArticleDataBean> call = service.getCollect(page);
        Callback<ArticleDataBean> callback = new Callback<ArticleDataBean>() {
            @Override
            public void onResponse(Call<ArticleDataBean> call, Response<ArticleDataBean> response) {
                if (response.body().getErrCode() == 0) {
                    onResponseListener.onSuccess(response.body());
                }else {
                    onResponseListener.onFailure(response.body().getErrCode()+"",response.body().getErrMsg());
                }
            }

            @Override
            public void onFailure(Call<ArticleDataBean> call, Throwable t) {
                onResponseListener.onError(t);

            }
        };
        call.enqueue(callback);
    }


    interface GetCollectArticleService {
        @GET("lg/collect/list/{page}/json")
        Call<ArticleDataBean> getCollect(@Path("page") int page);

    }

}
