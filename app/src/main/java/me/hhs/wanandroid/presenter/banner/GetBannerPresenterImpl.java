package me.hhs.wanandroid.presenter.banner;

import android.content.Context;

import me.hhs.wanandroid.entity.BannerBean;
import me.hhs.wanandroid.model.banner.BannerModelFactory;
import me.hhs.wanandroid.model.banner.IBannerModel;
import me.hhs.wanandroid.ui.view.IGetBannerView;

public class GetBannerPresenterImpl implements IGetBannerPresenter {

    private IBannerModel model;
    private IGetBannerView view;

    public GetBannerPresenterImpl(IGetBannerView view) {
        model = BannerModelFactory.getInstance();
        this.view = view;
    }


    @Override
    public void getBanner(Context context) {
        model.getBanner(context, this);
    }


    @Override
    public void onSuccess(BannerBean data) {
        view.showGetBannerSuccess(data);
    }

    @Override
    public void onFailure(String code, String msg) {
        view.showGetBannerFailure(code, msg);
    }

    @Override
    public void onError(Throwable throwable) {
        view.showGetBannerError(throwable);
    }
}
