package me.hhs.wanandroid.presenter.login;

import android.content.Context;

import me.hhs.wanandroid.entity.LoginBean;
import me.hhs.wanandroid.model.login.ILoginModel;
import me.hhs.wanandroid.model.login.LoginModelFactory;
import me.hhs.wanandroid.ui.view.IGoLoginView;

public class GoLoginPresenterImpl implements IGoLoginPresenter {

    private ILoginModel model;
    private IGoLoginView view;

    public GoLoginPresenterImpl(IGoLoginView iGoLoginView) {
        model = LoginModelFactory.getInstance();
        view = iGoLoginView;
    }

    @Override
    public void goLogin(Context context, String username, String password) {
        model.goLogin(context, username, password, this);
    }

    @Override
    public void onSuccess(LoginBean data) {
        view.showGoLoginSuccess(data);
    }

    @Override
    public void onFailure(String code, String msg) {
        view.showGoLoginFailure(code, msg);
    }

    @Override
    public void onError(Throwable throwable) {
        view.showGoLoginError(throwable);
    }
}
