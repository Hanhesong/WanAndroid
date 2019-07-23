package me.hhs.wanandroid.fragment;

import android.content.Context;
import android.content.SyncStatusObserver;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ms.banner.Banner;
import com.ms.banner.holder.BannerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.hhs.wanandroid.GetArticleInterface;
import me.hhs.wanandroid.entity.BannerBean;
import me.hhs.wanandroid.GetBannerRequest;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.RecyclerViewAdapter;
import me.hhs.wanandroid.entity.ArticleBean;
import me.hhs.wanandroid.entity.SuperHomePageBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KevinSong on 2019/7/18
 */
public class HomePageFragment extends BaseFragment {

    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<String> imagePath = new ArrayList<>();
    List<ArticleBean> articleList = new ArrayList<>();


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_rv)
    RecyclerView recyclerView;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initViews() {
        super.initViews();
        adapter = new RecyclerViewAdapter(articleList, getContext());
        layoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    protected void initData() {
        super.initData();
        requestBanner();
        requestArticle();
        bannerAutoPlay();
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void requestBanner() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetBannerRequest request = retrofit.create(GetBannerRequest.class);
        Call<BannerBean> call = request.getBannerCall();
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                List<String> l = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    l.add(response.body().getData().get(i).getImagePath());
                }
                imagePath.addAll(l);
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });
    }


    private void requestArticle() {
       Retrofit retrofit = new Retrofit.Builder()
                         .baseUrl("https://www.wanandroid.com/")
                         .addConverterFactory(GsonConverterFactory.create())
                          .build();
        GetArticleInterface request = retrofit.create(GetArticleInterface.class);
        Call<SuperHomePageBean> call = request.getArticle();
        call.enqueue(new Callback<SuperHomePageBean>() {
            @Override
            public void onResponse(Call<SuperHomePageBean> call, Response<SuperHomePageBean> response) {
                 List<ArticleBean> list = new ArrayList<>();
                 for (int i = 0; i < response.body().getData().getDatas().size();i++){
                     list.add(response.body().getData().getDatas().get(i));
                     System.out.println(response.body().getData().getDatas().get(i).getSuperCapterName());
                 }
                 articleList.clear();
                 articleList.addAll(list);
                 adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SuperHomePageBean> call, Throwable t) {

            }
        });






    }

    private void bannerAutoPlay() {
        //       imagePath = getResources().getStringArray(R.array.imagePath);
        //        List list = new ArrayList(Arrays.asList(imagePath));
        banner.setAutoPlay(true)
                .setPages(imagePath, new CustomViewHolder())
                .setDelayTime(3000)
                .start();
    }

    private class CustomViewHolder implements BannerViewHolder<Object> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            mImageView = new ImageView(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            mImageView.setLayoutParams(layoutParams);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return mImageView;
        }

        @Override
        public void onBind(Context context, int position, Object data) {
            Glide.with(context).load(data).into(mImageView);
        }
    }
}
