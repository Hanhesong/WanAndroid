package me.hhs.wanandroid.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.ButterKnife;
import me.hhs.wanandroid.R;

/**
 * Created by KevinSong on 2019/7/16
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        initData();
    }

    protected void initData() {
    }

    //声明为抽象的，让子类继承从而加载布局
    protected abstract int getLayoutResID();

    public void doToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void setAlertDialog(String title, String msg, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton(getString(R.string.definited), onClickListener);
        builder.create().show();
    }



}
