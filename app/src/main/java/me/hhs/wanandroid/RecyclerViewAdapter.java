package me.hhs.wanandroid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.ui.LoginActivity;
import me.hhs.wanandroid.ui.WebActivity;
import me.hhs.wanandroid.utils.SPUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by KevinSong on 2019/7/22
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<ArticleDataBean.ArticleData.Article> list;
    private Context mContext;
    private Retrofit mRetrofit;


    public RecyclerViewAdapter(List<ArticleDataBean.ArticleData.Article> list, Context context) {
        this.list = list;
        this.mContext = context;
        mRetrofit = RetrofitUtils.getRetrofit(Serve.BASE_URL);

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvArticleTitle;
        ImageButton ibCollect;
        TextView tvArticleAuthor;
        TextView tvArticleCategory;
        TextView tvArticleDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvArticleTitle = itemView.findViewById(R.id.tv_homefragment_article_title);
            tvArticleAuthor = itemView.findViewById(R.id.tv_homefragment_article_author);
            tvArticleCategory = itemView.findViewById(R.id.tv_homefragment_article_category);
            tvArticleDate = itemView.findViewById(R.id.tv_homefragment_article_date);
            ibCollect = itemView.findViewById(R.id.ib_fragment_artitle_collect);
        }
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_home_fragment, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.MyViewHolder myViewHolder, final int i) {
        final int id = list.get(i).getId();
        myViewHolder.tvArticleTitle.setText(list.get(i).getTitle());
        myViewHolder.tvArticleAuthor.setText(list.get(i).getAuthor());
        myViewHolder.tvArticleCategory.setText(list.get(i).getSuperChapterName() + "/" + list.get(i).getChapterName());
        myViewHolder.tvArticleDate.setText(list.get(i).getNiceDate());
        myViewHolder.ibCollect.setSelected(list.get(i).isCollect());
        myViewHolder.ibCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder.ibCollect.isSelected()) {
                    myViewHolder.ibCollect.setSelected(false);
                    //访问取消收藏的api
                    unCollectArticle(id);
                } else if (SPUtils.get(mContext, "username", mContext.getString(R.string.go_login)).equals(mContext.getString(R.string.go_login))) {
                    //跳转到登录界面
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    myViewHolder.ibCollect.setSelected(true);
                    //访问收藏的api
                    collectArticle(id);
                }
            }
        });

        //这个方法是加载相应图片的，暂时用不到。
        // Glide.with(mContext).load(R.drawable.selector_collect).into(myViewHolder.ibCollect);
        final String articleLink = list.get(i).getLink();
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("articleLink", articleLink);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface CollectArticleService {
        @POST("lg/collect/{id}/json")
        Call<ArticleDataBean> CollectArticle(@Path("id") int id);
    }

    public void collectArticle(int id) {
        CollectArticleService service = mRetrofit.create(CollectArticleService.class);
        Call<ArticleDataBean> call = service.CollectArticle(id);
        Callback<ArticleDataBean> callback = new Callback<ArticleDataBean>() {
            @Override
            public void onResponse(Call<ArticleDataBean> call, Response<ArticleDataBean> response) {
                if (response.body().getErrCode() == 0) {
                    Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleDataBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }


    //https://www.wanandroid.com/lg/uncollect_originId/2333/json
    interface UnCollectArticleService {
        @POST("lg/uncollect_originId/{id}/json")
        Call<ArticleDataBean> UnCollectArticle(@Path("id") int id);
    }

    public void unCollectArticle(int id) {
        UnCollectArticleService service = mRetrofit.create(UnCollectArticleService.class);
        Call<ArticleDataBean> call = service.UnCollectArticle(id);
        Callback<ArticleDataBean> callback = new Callback<ArticleDataBean>() {
            @Override
            public void onResponse(Call<ArticleDataBean> call, Response<ArticleDataBean> response) {
                if (response.body().getErrCode() == 0) {
                    Toast.makeText(mContext, "取消收藏成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArticleDataBean> call, Throwable t) {
            }
        };
        call.enqueue(callback);
    }

    interface GetCollectArticlesService {
        @GET("lg/collect/list/{page}/json")
        Call<ArticleDataBean> getCollect(@Path("page") int page);

    }

}
