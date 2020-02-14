package me.hhs.wanandroid.presenter.register;

import android.content.Context;

import me.hhs.wanandroid.entity.LoginBean;
import me.hhs.wanandroid.model.register.IRegisterModel;
import me.hhs.wanandroid.model.register.RegisterModelFactory;
import me.hhs.wanandroid.ui.view.IGoRegisterView;

public class GoRegisterPresenterImpl implements IGoRegisterPresenter {

    private IGoRegisterView view;
    private IRegisterModel model;

    public GoRegisterPresenterImpl(IGoRegisterView goRegisterView) {
        view = goRegisterView;
        model = RegisterModelFactory.getInstance();

    }

    @Override
    public void goRegister(Context context, String username, String password, String repassword) {
        model.goRegister(context, username, password, repassword, this);
    }

    @Override
    public void onSuccess(LoginBean data) {
        view.showGoRegisterSuccess(data);
    }

    @Override
    public void onFailure(String code, String msg) {
        view.showGoRegisterFailure(code, msg);

    }

    @Override
    public void onError(Throwable throwable) {
        view.showGoRegisterError(throwable);
    }
}
