package me.hhs.wanandroid.model.article;

import android.content.Context;

import me.hhs.wanandroid.RetrofitUtils;
import me.hhs.wanandroid.Serve;
import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.model.IOnResponseListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

/**
 * Created by KevinSong on 2019/7/26
 */
public class ArticlesModelImpl implements IArticlesModel, Serve {
    public Retrofit mretrofit;

    //1 获取Retrofit实例
    public ArticlesModelImpl() {
         mretrofit = RetrofitUtils.getRetrofit(BASE_URL);
    }

    @Override
    public void getArticles(Context context,int page, final IOnResponseListener<ArticleDataBean> onResponseListener) {
       //3.获取网络请求业务对象
        GetArticlesService service = mretrofit.create(GetArticlesService.class);
        //4.获取请求对象
        Call<ArticleDataBean> call = service.getArticle(page);
        //5.设计请求对象
        Callback<ArticleDataBean> callback = new Callback<ArticleDataBean>() {
            @Override
            public void onResponse(Call<ArticleDataBean> call, Response<ArticleDataBean> response) {
                   int errCode = response.body().getErrCode();
                   if (errCode==0){
                       onResponseListener.onSuccess(response.body());
                   }
            }

            @Override
            public void onFailure(Call<ArticleDataBean> call, Throwable t) {
                onResponseListener.onError(t);

            }
        } ;
        //添加到请求队列
        call.enqueue(callback);
    }

    //2.设计网络请求
       interface GetArticlesService{
        @HTTP(method = "GET",path = "article/list/{page}/json")
        Call<ArticleDataBean> getArticle(@Path("page") int page);
    }
}
