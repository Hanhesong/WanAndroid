package me.hhs.wanandroid.model.collect;

import android.content.Context;

import me.hhs.wanandroid.RetrofitUtils;
import me.hhs.wanandroid.Serve;
import me.hhs.wanandroid.entity.CollectArticleBean;
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
    public void getCollect(Context context, int page, final IOnResponseListener<CollectArticleBean> onResponseListener) {
        GetCollectArticlesService service = mRetrofit.create(GetCollectArticlesService.class);
        Call<CollectArticleBean> call = service.getCollect(page);
        Callback<CollectArticleBean> callback = new Callback<CollectArticleBean>() {
            @Override
            public void onResponse(Call<CollectArticleBean> call, Response<CollectArticleBean> response) {
                if (response.body().getErrorCode() == 0) {
                    onResponseListener.onSuccess(response.body());
                } else {
                    onResponseListener.onFailure(response.body().getErrorCode() + "", response.body().getErrorMsg());
                }
            }

            @Override
            public void onFailure(Call<CollectArticleBean> call, Throwable t) {
                onResponseListener.onError(t);

            }
        };
        call.enqueue(callback);
    }


    interface GetCollectArticlesService {
        @GET("lg/collect/list/{page}/json")
        Call<CollectArticleBean> getCollect(@Path("page") int page);

    }

}
