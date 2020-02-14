package me.hhs.wanandroid.ui.view;

import me.hhs.wanandroid.entity.LoginBean;

public interface IGoRegisterView {
    void showGoRegisterSuccess(LoginBean loginBean);

    void showGoRegisterFailure(String errorCode, String errorMsg);

    void showGoRegisterError(Throwable throwable);
}
