package me.hhs.wanandroid.model.article;

/**
 * Created by KevinSong on 2019/7/26
 */
public class ArticlesModelFactory {
    public static IArticlesModel articlesModel;

    public static IArticlesModel getInstance() {
        if (articlesModel == null) {
            articlesModel = new ArticlesModelImpl();
        }
        return articlesModel;
    }


}
