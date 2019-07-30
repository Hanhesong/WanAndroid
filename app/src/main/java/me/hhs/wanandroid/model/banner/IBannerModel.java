package me.hhs.wanandroid.model.banner;

import android.content.Context;

import me.hhs.wanandroid.entity.BannerBean;
import me.hhs.wanandroid.model.IOnResponseListener;

public interface IBannerModel {
    void getBanner(Context context, IOnResponseListener<BannerBean> onResponseListener);
}
