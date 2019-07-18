package me.hhs.wanandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.hhs.wanandroid.ui.BaseActivity;

/**
 * Created by KevinSong on 2019/7/18
 */
public abstract class BaseFragment extends Fragment {

    private BaseActivity baseActivity;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), null);
        unbinder = ButterKnife.bind(this, view);
        baseActivity = (BaseActivity) getActivity();
        initViews();
        return view;
    }

    protected void initViews() {
    }

    protected void doToast(String msg) {
        baseActivity.doToast(msg);
    }

    public abstract int getLayoutResId();

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
