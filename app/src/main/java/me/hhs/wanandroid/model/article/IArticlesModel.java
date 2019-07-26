package me.hhs.wanandroid.model.article;

import android.content.Context;

import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.model.IOnResponseListener;

/**
 * Created by KevinSong on 2019/7/26
 */
public interface IArticlesModel {
    void getArticles(Context context, IOnResponseListener<ArticleDataBean> onResponseListener);
}
