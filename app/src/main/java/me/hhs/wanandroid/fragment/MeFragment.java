package me.hhs.wanandroid.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.ui.LoginActivity;
import me.hhs.wanandroid.utils.SPUtils;

/**
 * Created by KevinSong on 2019/7/18
 */
public class MeFragment extends BaseFragment {


    @BindView(R.id.user_name)
    TextView tvUserName;
    @BindView(R.id.user_id)
    TextView tvUserID;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        super.initData();
        tvUserName.setText((String) SPUtils.get(getContext(), "username", getString(R.string.go_login)));
        tvUserID.setText((String) SPUtils.get(getContext(), "userid", "id: "));
    }

    @OnClick({R.id.user_name, R.id.ll_user_collect, R.id.ll_system_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                if (tvUserName.getText().equals(getString(R.string.go_login)))
                startActivityForResult(new Intent(getContext(), LoginActivity.class), 100);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String username = data.getStringExtra("username");
            int userid = data.getIntExtra("userid", 0);
            SPUtils.put(getContext(), "username", username);
            SPUtils.put(getContext(), "userid", "id: " + userid);
            tvUserName.setText(username);
            tvUserID.setText("id: " + userid);

        }
    }
}
