package me.hhs.wanandroid.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ms.banner.Banner;
import com.ms.banner.holder.BannerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.hhs.wanandroid.entity.BannerBean;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.RecyclerViewAdapter;
import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.presenter.article.GetArticlesPresenterImpl;
import me.hhs.wanandroid.presenter.article.IGetArticlesPresenter;
import me.hhs.wanandroid.presenter.banner.GetBannerPresenterImpl;
import me.hhs.wanandroid.presenter.banner.IGetBannerPresenter;
import me.hhs.wanandroid.ui.view.IGetArticlesView;
import me.hhs.wanandroid.ui.view.IGetBannerView;

/**
 * Created by KevinSong on 2019/7/18
 */
public class HomePageFragment extends BaseFragment implements IGetArticlesView, IGetBannerView {

    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<String> imagePath = new ArrayList<>();
    List<ArticleDataBean.ArticleData.Article> articleList;
    private IGetArticlesPresenter getArticlesPresenter;
    private IGetBannerPresenter getBannerPresenter;

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
    }

    @Override
    protected void initData() {
        super.initData();
        getArticlesPresenter = new GetArticlesPresenterImpl(this);
        getBannerPresenter = new GetBannerPresenterImpl(this);
        articleList = new ArrayList<>();
        // requestBanner();
        adapter = new RecyclerViewAdapter(articleList, getContext());
        layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getArticlesPresenter.getArticles(getContext());
        getBannerPresenter.getBanner(getContext());
        bannerAutoPlay();
    }

    @Override
    public void showGetArticlesSuccess(ArticleDataBean articleDataBean) {
        List<ArticleDataBean.ArticleData.Article> list = new ArrayList<>();
        for (int i = 0; i < articleDataBean.getData().getDatas().size(); i++) {
            list.add(articleDataBean.getData().getDatas().get(i));
            Log.i("Song", articleDataBean.getData().toString());
        }
        articleList.clear();
        articleList.addAll(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showGetArticlesFailure(String code, String msg) {

    }

    @Override
    public void showGetArticlesError(Throwable throwable) {

    }

    private void bannerAutoPlay() {
        banner.setAutoPlay(true)
                .setPages(imagePath, new CustomViewHolder())
                .setCurrentPage(0)
                .setDelayTime(3000)
                .start();
    }

    @Override
    public void showGetBannerSuccess(BannerBean bannerBean) {
        List<String> imageList = new ArrayList<>();
        for (int i = 0; i < bannerBean.getData().size(); i++) {
            imageList.add(bannerBean.getData().get(i).getImagePath());
        }
        imagePath.clear();
        imagePath.addAll(imageList);

    }

    @Override
    public void showGetBannerFailure(String code, String msg) {

    }

    @Override
    public void showGetBannerError(Throwable throwable) {

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

    @Override
    public void onStart() {
        super.onStart();
        if (banner != null && !banner.isStart() && banner.isPrepare()) {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        if (banner != null && banner.isStart() && banner.isPrepare()) {
            banner.stopAutoPlay();
        }
        super.onStop();
    }
}
