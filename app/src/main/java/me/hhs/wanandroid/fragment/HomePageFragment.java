package me.hhs.wanandroid.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ms.banner.Banner;
import com.ms.banner.holder.BannerViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
public class HomePageFragment extends BaseFragment implements IGetArticlesView, IGetBannerView, OnRefreshListener, OnLoadMoreListener {

    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<String> imagePath = new ArrayList<>();
    List<ArticleDataBean.ArticleData.Article> articleList;
    private IGetArticlesPresenter getArticlesPresenter;
    private IGetBannerPresenter getBannerPresenter;
    private int page = 0;

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_rv)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    // TODO: 2019/7/31 emptyView
//    @BindView(R.id.empty_view)
//    TextView tvEmpty;

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
        //设置关于refresh的属性
        smartRefreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadMoreListener(this);
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
       //smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
       // smartRefreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        //smartRefreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        smartRefreshLayout.setEnableLoadMore(true);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showGetArticlesSuccess(ArticleDataBean articleDataBean) {
        List<ArticleDataBean.ArticleData.Article> list = new ArrayList<>();
        for (int i = 0; i < articleDataBean.getData().getDatas().size(); i++) {
            list.add(articleDataBean.getData().getDatas().get(i));
        }
        if (page==0){
            articleList.clear();
        }
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
        banner.setPages(imagePath, new CustomViewHolder())
                .setAutoPlay(true)
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
        //获取BannerPath成功后就可以开始轮播。但还存在连续切换fragment崩溃的bug,切换的时候banner就不更新了。
        // TODO: 2019/7/31
        bannerAutoPlay();

    }

    @Override
    public void showGetBannerFailure(String code, String msg) {

    }

    @Override
    public void showGetBannerError(Throwable throwable) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
         getBannerPresenter.getBanner(getContext());
         getArticlesPresenter.getArticles(getContext(),0);
         page=0;
         refreshLayout.finishRefresh(2000);

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        getArticlesPresenter.getArticles(getContext(),page);
        refreshLayout.finishLoadMore(1000);
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

    public void onRefreshArticle(){
        if (smartRefreshLayout!=null)
            smartRefreshLayout.autoRefresh(500);
    }
}
