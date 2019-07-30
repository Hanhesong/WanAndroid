package me.hhs.wanandroid.presenter.article;

import android.content.Context;

import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.model.article.ArticlesModelFactory;
import me.hhs.wanandroid.model.article.IArticlesModel;
import me.hhs.wanandroid.ui.view.IGetArticlesView;

/**
 * Created by KevinSong on 2019/7/26
 */
public class GetArticlesPresenterImpl implements IGetArticlesPresenter {

    private IArticlesModel model;
    private IGetArticlesView view;

    public GetArticlesPresenterImpl(IGetArticlesView view) {
        this.model = ArticlesModelFactory.getInstance();
        this.view = view;
    }

    @Override
    public void getArticles(Context context) {
        model.getArticles(context, this);
    }

    @Override
    public void onSuccess(ArticleDataBean data) {
        view.showGetArticlesSuccess(data);
    }

    @Override
    public void onFailure(String code, String msg) {
        view.showGetArticlesFailure(code, msg);
    }

    @Override
    public void onError(Throwable throwable) {
        view.showGetArticlesError(throwable);
    }
}
