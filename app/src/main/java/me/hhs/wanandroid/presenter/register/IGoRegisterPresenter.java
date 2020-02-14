package me.hhs.wanandroid.presenter.register;

import android.content.Context;

import me.hhs.wanandroid.entity.LoginBean;
import me.hhs.wanandroid.model.IOnResponseListener;

public interface IGoRegisterPresenter extends IOnResponseListener<LoginBean> {
    void goRegister(Context context, String username, String password, String repassword);
}
