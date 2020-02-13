package me.hhs.wanandroid.presenter.article;

import android.content.Context;

import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.model.IOnResponseListener;

/**
 * Created by KevinSong on 2019/7/26
 */
public interface IGetArticlesPresenter extends IOnResponseListener<ArticleDataBean> {
         void  getArticles(Context context,int page);
}
