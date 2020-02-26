package me.hhs.wanandroid.fragment;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.hhs.wanandroid.CollectArticleLvAdapter;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.Serve;
import me.hhs.wanandroid.entity.CollectArticleBean;
import me.hhs.wanandroid.presenter.collect.GetCollectPresenterImpl;
import me.hhs.wanandroid.presenter.collect.IGetCollectPrecenter;
import me.hhs.wanandroid.ui.view.IGetCollectView;


public class CollectFragment extends BaseFragment implements Serve, IGetCollectView, CollectArticleLvAdapter.DeleteItemCallBack {

    @BindView(R.id.lv_CollectArticles)
    ListView listView;


    private IGetCollectPrecenter getCollectPrecenter;
    private List<CollectArticleBean.CollectArticle> collectArticleList;
    private CollectArticleLvAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initData() {
        super.initData();
        getCollectPrecenter = new GetCollectPresenterImpl(this);
        getCollectPrecenter.getCollect(getContext(), 0);
        collectArticleList = new ArrayList<>();
        adapter = new CollectArticleLvAdapter(getContext(), collectArticleList);
        listView.setAdapter(adapter);
        adapter.setDeleteItemCallBack(this);
    }


    @Override
    public void getCollectSuccess(CollectArticleBean articleDataBean) {
        if (articleDataBean != null) {
            for (int i = 0; i < articleDataBean.getData().getDatas().size(); i++) {
                collectArticleList.add(articleDataBean.getData().getDatas().get(i));
            }
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getCollectFailure(String errorCode, String errorMsg) {

    }

    @Override
    public void getCollectError(Throwable throwable) {

    }

    @Override
    public void deletePosition(int position) {
        collectArticleList.remove(position);
        adapter.notifyDataSetChanged();
    }
}
