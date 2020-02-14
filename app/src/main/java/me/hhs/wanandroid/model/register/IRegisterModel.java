package me.hhs.wanandroid.model.register;

import android.content.Context;

import me.hhs.wanandroid.entity.LoginBean;
import me.hhs.wanandroid.model.IOnResponseListener;

public interface IRegisterModel {
    void goRegister(Context context, String username, String password, String rePassword, IOnResponseListener<LoginBean> onResponseListener);
}
