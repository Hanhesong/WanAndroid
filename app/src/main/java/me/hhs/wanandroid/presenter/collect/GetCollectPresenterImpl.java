package me.hhs.wanandroid.presenter.collect;

import android.content.Context;

import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.model.collect.CollectModelFactory;
import me.hhs.wanandroid.model.collect.ICollectModel;
import me.hhs.wanandroid.ui.view.IGetCollectView;

public class GetCollectPresenterImpl implements IGetCollectPrecenter {

    private IGetCollectView view;
    private ICollectModel model;

    public GetCollectPresenterImpl(IGetCollectView iGetCollectView) {
        view = iGetCollectView;
        model = CollectModelFactory.getInstance();

    }

    @Override
    public void getCollect(Context context, int page) {
        model.getCollect(context, page, this);
    }

    @Override
    public void onSuccess(ArticleDataBean data) {
        view.getCollectSuccess(data);
    }

    @Override
    public void onFailure(String code, String msg) {
        view.getCollectFailure(code, msg);
    }

    @Override
    public void onError(Throwable throwable) {
        view.getCollectError(throwable);
    }
}
