package me.hhs.wanandroid.model.collect;

import android.content.Context;

import me.hhs.wanandroid.entity.CollectArticleBean;
import me.hhs.wanandroid.model.IOnResponseListener;

public interface ICollectModel {
    void getCollect(Context context, int page, IOnResponseListener<CollectArticleBean> onResponseListener);
}
