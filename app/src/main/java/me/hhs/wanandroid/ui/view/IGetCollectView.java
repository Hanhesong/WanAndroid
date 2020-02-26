package me.hhs.wanandroid.ui.view;

import me.hhs.wanandroid.entity.CollectArticleBean;

public interface IGetCollectView {
    void getCollectSuccess(CollectArticleBean collectArticleBean);

    void getCollectFailure(String errorCode, String errorMsg);

    void getCollectError(Throwable throwable);

}
