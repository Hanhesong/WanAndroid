package me.hhs.wanandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.entity.CollectArticleBean;
import me.hhs.wanandroid.ui.WebActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class CollectArticleLvAdapter extends BaseAdapter {

    private List<CollectArticleBean.CollectArticle> collectArticleList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private Retrofit mRetrofit;
    private DeleteItemCallBack deleteItemCallBack;
    private int savePosition;


    public CollectArticleLvAdapter(Context context, List<CollectArticleBean.CollectArticle> list) {
        mContext = context;
        collectArticleList = list;
        mLayoutInflater = LayoutInflater.from(context);
        mRetrofit = RetrofitUtils.getRetrofit(Serve.BASE_URL);
    }


    @Override
    public int getCount() {
        return collectArticleList.size();
    }

    @Override
    public Object getItem(int position) {
        return collectArticleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.list_item_home_fragment, null);
            viewHolder.ibCollect = (ImageButton) convertView.findViewById(R.id.ib_fragment_artitle_collect);
            viewHolder.tvArticleAuthor = (TextView) convertView.findViewById(R.id.tv_homefragment_article_author);
            viewHolder.tvArticleCategory = (TextView) convertView.findViewById(R.id.tv_homefragment_article_category);
            viewHolder.tvArticleTitle = (TextView) convertView.findViewById(R.id.tv_homefragment_article_title);
            viewHolder.tvArtlcleDate = (TextView) convertView.findViewById(R.id.tv_homefragment_article_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final CollectArticleBean.CollectArticle article = collectArticleList.get(position);
        final int articleId = article.getId();
        final int originId = article.getOriginId();
        viewHolder.tvArticleAuthor.setText(article.getAuthor());
        viewHolder.tvArticleTitle.setText(article.getTitle());
        viewHolder.tvArtlcleDate.setText(article.getNiceDate());
        viewHolder.tvArticleCategory.setText(article.getChapterName());
        viewHolder.ibCollect.setSelected(true);
        viewHolder.ibCollect.setTag(position);
        viewHolder.ibCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.ibCollect.setSelected(false);
                unCollectArticle(articleId, originId);
                savePosition = Integer.parseInt(v.getTag().toString());
                deleteItemCallBack.deletePosition(savePosition);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("articleLink", article.getLink());
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }

    private static class ViewHolder {
        TextView tvArticleTitle;
        ImageButton ibCollect;
        TextView tvArticleAuthor;
        TextView tvArticleCategory;
        TextView tvArtlcleDate;
    }


    public interface DeleteItemCallBack {
        void deletePosition(int position);
    }

    public void setDeleteItemCallBack(DeleteItemCallBack deleteItemCallBack) {
        this.deleteItemCallBack = deleteItemCallBack;
    }


    interface UnCollectArticleService {
        @POST("lg/uncollect/{id}/json")
        @FormUrlEncoded
        Call<ArticleDataBean> UnCollectArticle(@Path("id") int id, @Field("originId") int originId);
    }

    public void unCollectArticle(int id, int originId) {
        UnCollectArticleService service = mRetrofit.create(UnCollectArticleService.class);
        Call<ArticleDataBean> call = service.UnCollectArticle(id, originId);
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

}
