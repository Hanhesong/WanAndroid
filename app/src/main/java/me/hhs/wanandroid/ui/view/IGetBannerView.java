package me.hhs.wanandroid.ui.view;

import me.hhs.wanandroid.entity.BannerBean;

public interface IGetBannerView {

    void showGetBannerSuccess(BannerBean bannerBean);

    void showGetBannerFailure(String code, String msg);

    void showGetBannerError(Throwable throwable);




}
