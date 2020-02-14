package me.hhs.wanandroid.ui.view;

import me.hhs.wanandroid.entity.LoginBean;

public interface IGoLoginView {
    void showGoLoginSuccess(LoginBean loginBean);

    void showGoLoginFailure(String code, String msg);

    void showGoLoginError(Throwable throwable);
}
