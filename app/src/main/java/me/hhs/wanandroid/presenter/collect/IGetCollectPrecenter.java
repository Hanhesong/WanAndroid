package me.hhs.wanandroid.presenter.collect;

import android.content.Context;

import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.model.IOnResponseListener;

public interface IGetCollectPrecenter extends IOnResponseListener<ArticleDataBean> {
    void getCollect(Context context, int page);
}
