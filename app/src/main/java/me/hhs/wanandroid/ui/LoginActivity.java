package me.hhs.wanandroid.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import butterknife.BindView;
import butterknife.OnClick;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.fragment.LoginFragment;
import me.hhs.wanandroid.fragment.RegisterFragment;

public class LoginActivity extends BaseActivity {

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;

    @BindView(R.id.login_titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.btn_go_login_register)
    Button tbnGoRegister;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        super.initData();
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        setDefaultFragment();
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.loginFragment_content, loginFragment);
        transaction.commit();
        mTitleBar.setTitle(R.string.user_login);
    }

    @OnClick(R.id.btn_go_login_register)
    public void goRegisterOrLogin() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.loginFragment_content, registerFragment);
        transaction.commit();
        mTitleBar.setTitle(R.string.user_register);
        tbnGoRegister.setVisibility(View.GONE);
    }

}
