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
import me.hhs.wanandroid.fragment.SystemSettingFragment;

public class LoginActivity extends BaseActivity {

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private SystemSettingFragment systemSettingFragment;
    private String from;

    @BindView(R.id.login_titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.btn_go_login_register)
    Button btnGoRegister;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        super.initData();
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        systemSettingFragment = new SystemSettingFragment();
        from = getIntent().getStringExtra("sys");
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
        if (from == null) {
            transaction.replace(R.id.loginFragment_content, loginFragment);
            transaction.commit();
            mTitleBar.setTitle(R.string.user_login);
        } else {
            transaction.replace(R.id.loginFragment_content, systemSettingFragment);
            transaction.commit();
            mTitleBar.setTitle(R.string.system_setting);
            btnGoRegister.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_go_login_register)
    public void goRegisterOrLogin() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.loginFragment_content, registerFragment);
        transaction.commit();
        mTitleBar.setTitle(R.string.user_register);
        btnGoRegister.setVisibility(View.GONE);
    }

}
