package me.hhs.wanandroid.presenter.collect;

import android.content.Context;

import me.hhs.wanandroid.entity.CollectArticleBean;
import me.hhs.wanandroid.model.IOnResponseListener;

public interface IGetCollectPrecenter extends IOnResponseListener<CollectArticleBean> {
    void getCollect(Context context, int page);
}
