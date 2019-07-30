package me.hhs.wanandroid.presenter.banner;

import android.content.Context;

import me.hhs.wanandroid.entity.BannerBean;
import me.hhs.wanandroid.model.IOnResponseListener;

public interface IGetBannerPresenter extends IOnResponseListener<BannerBean> {
    void getBanner(Context context);
}
