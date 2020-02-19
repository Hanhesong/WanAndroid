package me.hhs.wanandroid.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import butterknife.BindView;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.fragment.CollectFragment;
import me.hhs.wanandroid.fragment.SystemSettingFragment;

public class SystemActivity extends BaseActivity {

    private SystemSettingFragment settingFragment;
    private CollectFragment collectFragment;
    private String from;
    @BindView(R.id.system_titleBar)
    TitleBar sysTitleBar;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_system;
    }

    @Override
    protected void initData() {
        super.initData();
        settingFragment = new SystemSettingFragment();
        collectFragment = new CollectFragment();
        from = getIntent().getStringExtra("from");
        setDefaultFragment();
        sysTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
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
        if (from.equals("collect")) {
            transaction.replace(R.id.systemFragment_content, collectFragment);
            transaction.commit();
            sysTitleBar.setTitle(R.string.user_collect);
        } else if (from.equals("system")) {
            transaction.replace(R.id.systemFragment_content, settingFragment);
            transaction.commit();
            sysTitleBar.setTitle(R.string.system_setting);
        }
    }
}
