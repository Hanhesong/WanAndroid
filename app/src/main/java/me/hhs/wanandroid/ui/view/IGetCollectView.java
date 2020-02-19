package me.hhs.wanandroid.ui.view;

import me.hhs.wanandroid.entity.ArticleDataBean;

public interface IGetCollectView {
    void getCollectSuccess(ArticleDataBean articleDataBean);

    void getCollectFailure(String errorCode, String errorMsg);

    void getCollectError(Throwable throwable);

}
