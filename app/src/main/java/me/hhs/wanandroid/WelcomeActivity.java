package me.hhs.wanandroid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    private int countDown = 5;
    private Timer timer = new Timer();
    private Handler handler = new Handler();
    private Runnable runnable;


    @BindView(R.id.btn_jump)
    Button btnJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

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
