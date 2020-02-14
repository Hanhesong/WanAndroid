package me.hhs.wanandroid.fragment;

import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.entity.LoginBean;
import me.hhs.wanandroid.presenter.login.GoLoginPresenterImpl;
import me.hhs.wanandroid.presenter.login.IGoLoginPresenter;
import me.hhs.wanandroid.ui.view.IGoLoginView;

public class LoginFragment extends BaseFragment implements IGoLoginView {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_password)
    EditText etPassword;

    private IGoLoginPresenter goLoginPresenter;

    @Override
    protected void initData() {
        super.initData();
        goLoginPresenter = new GoLoginPresenterImpl(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    public void showGoLoginSuccess(LoginBean loginBean) {
        doToast(loginBean.getErrorCode() + loginBean.getData().getUsername());
    }

    @Override
    public void showGoLoginFailure(String code, String msg) {
        doToast(code + msg);
    }

    @Override
    public void showGoLoginError(Throwable throwable) {
        doToast("" + throwable);
    }

    @OnClick(R.id.btn_login)
    public void goLogin() {
        goLoginPresenter.goLogin(getContext(), etUserName.getText().toString(), etPassword.getText().toString());
    }

}
