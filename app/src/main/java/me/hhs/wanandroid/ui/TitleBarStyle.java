package me.hhs.wanandroid.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.hjq.bar.style.BaseTitleBarStyle;

import me.hhs.wanandroid.R;

public class TitleBarStyle extends BaseTitleBarStyle {
    public TitleBarStyle(Context context) {
        super(context);
    }

    @Override
    public Drawable getBackground() {
        return null;
    }

    @Override
    public Drawable getBackIcon() {
        return getDrawable(R.drawable.ic_close);
    }

    @Override
    public int getLeftColor() {
        return 0;
    }

    @Override
    public int getTitleColor() {
        return 0;
    }

    @Override
    public int getRightColor() {
        return 0;
    }

    @Override
    public boolean isLineVisible() {
        return false;
    }

    @Override
    public Drawable getLineDrawable() {
        return null;
    }

    @Override
    public Drawable getLeftBackground() {
        return null;
    }

    @Override
    public Drawable getRightBackground() {
        return null;
    }
}
