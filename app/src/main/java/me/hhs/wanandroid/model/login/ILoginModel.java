package me.hhs.wanandroid.model.login;

import android.content.Context;
import me.hhs.wanandroid.entity.LoginBean;
import me.hhs.wanandroid.model.IOnResponseListener;

public interface ILoginModel {
    void goLogin(Context context, String username, String password, IOnResponseListener<LoginBean> onResponseListener);
}
