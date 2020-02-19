package me.hhs.wanandroid.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import butterknife.OnClick;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.utils.SPUtils;


public class SystemSettingFragment extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_systemsetting;
    }

    @OnClick(R.id.ll_system_setting)
    public void doClick() {
        getBaseActivity().setAlertDialog(null, "确定退出登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //  doToast("退出登录");
                SPUtils.clear(getContext());
                //退出后要清除保存的cookie
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cookieData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("cookie");
                editor.apply();

                getActivity().setResult(Activity.RESULT_OK,null);
                getActivity().finish();
            }
        });
    }
}
