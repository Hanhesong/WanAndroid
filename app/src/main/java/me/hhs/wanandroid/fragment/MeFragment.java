package me.hhs.wanandroid.fragment;

import android.content.Intent;
import android.view.View;
import butterknife.OnClick;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.ui.LoginActivity;

/**
 * Created by KevinSong on 2019/7/18
 */
public class MeFragment extends BaseFragment {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @OnClick({R.id.user_name, R.id.ll_user_collect, R.id.ll_system_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.ll_user_collect:
                doToast("我的收藏");
                break;
            case R.id.ll_system_setting:
                doToast("系统设置");
                break;
            default:
                break;
        }
    }
}
