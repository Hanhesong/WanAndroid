package me.hhs.wanandroid.presenter.login;

import android.content.Context;

import me.hhs.wanandroid.entity.LoginBean;
import me.hhs.wanandroid.model.IOnResponseListener;

public interface IGoLoginPresenter extends IOnResponseListener<LoginBean> {
    void goLogin(Context context, String username, String password);
}
