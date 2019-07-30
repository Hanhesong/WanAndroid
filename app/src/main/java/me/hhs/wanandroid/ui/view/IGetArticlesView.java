package me.hhs.wanandroid.ui.view;

import me.hhs.wanandroid.entity.ArticleDataBean;

/**
 * Created by KevinSong on 2019/7/26
 */
public interface IGetArticlesView {

    void showGetArticlesSuccess(ArticleDataBean articleDataBean);

    void showGetArticlesFailure(String code, String msg);

    void showGetArticlesError(Throwable throwable);
}
