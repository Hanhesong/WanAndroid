package me.hhs.wanandroid.fragment;

import android.content.Intent;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.entity.LoginBean;
import me.hhs.wanandroid.presenter.register.GoRegisterPresenterImpl;
import me.hhs.wanandroid.presenter.register.IGoRegisterPresenter;
import me.hhs.wanandroid.ui.view.IGoRegisterView;

public class RegisterFragment extends BaseFragment implements IGoRegisterView {

    @BindView(R.id.et_user_name)
    EditText etUsername;
    @BindView(R.id.et_user_password)
    EditText etPassword;
    @BindView(R.id.et_user_password_confirm)
    EditText etPasswordConfirm;

    private IGoRegisterPresenter goRegisterPresenter;

    @Override
    protected void initData() {
        super.initData();
        goRegisterPresenter = new GoRegisterPresenterImpl(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_register;
    }

    @Override
    public void showGoRegisterSuccess(LoginBean loginBean) {
        doToast(getString(R.string.register_successful));
        Intent intent = new Intent();
        intent.putExtra("username", loginBean.getData().getUsername());
        intent.putExtra("userid", loginBean.getData().getId());
        getActivity().setResult(100, intent);
        getActivity().finish();
    }

    @Override
    public void showGoRegisterFailure(String errorCode, String errorMsg) {
        doToast(getString(R.string.register_failed) + " errorCode: " + errorCode + errorMsg);
    }

    @Override
    public void showGoRegisterError(Throwable throwable) {

    }

    @OnClick(R.id.btn_register)
    public void goRegister() {
        goRegisterPresenter.goRegister(getContext(), etUsername.getText().toString(), etPassword.getText().toString(), etPasswordConfirm.getText().toString());
    }
}
