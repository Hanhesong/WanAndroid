package me.hhs.wanandroid.ui;

import android.content.Intent;
import android.os.Handler;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import me.hhs.wanandroid.R;

public class WelcomeActivity extends BaseActivity {

    private int countDown = 5;
    private Timer timer = new Timer();
    private Handler handler = new Handler();
    private Runnable runnable;

    @BindView(R.id.btn_jump)
    Button btnJump;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
        super.initData();
        timer.schedule(task, 1000, 1000);
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);
    }

    @OnClick(R.id.btn_jump)
    public void jumpNext() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnJump.setText(getString(R.string.jump_over) + " " + countDown);
                    countDown--;
                    if (countDown < 0) {
                        timer.cancel();
                    }
                }
            });
        }
    };
}
