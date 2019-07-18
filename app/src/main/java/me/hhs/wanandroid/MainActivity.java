package me.hhs.wanandroid;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.ms.banner.Banner;
import com.ms.banner.holder.BannerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import me.hhs.wanandroid.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rb_homePage)
    RadioButton rbHomePage;
    @BindView(R.id.rb_structure)
    RadioButton rbStructure;
    @BindView(R.id.rb_project)
    RadioButton rbProject;
    @BindView(R.id.rb_publicNumber)
    RadioButton rbPublicNumber;

    private HomePageFragment homePageFragment;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        bannerAutoPlay();
        setDefultFragment();
    }

    private void setDefultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        homePageFragment = new HomePageFragment();
        transaction.replace(R.id.fl_content,homePageFragment);
        transaction.commit();
    }

    private void bannerAutoPlay() {
        String[] imagePath = getResources().getStringArray(R.array.imagePath);
        List list = new ArrayList(Arrays.asList(imagePath));
        banner.setAutoPlay(true)
                .setPages(list, new CustomViewHolder())
                .setDelayTime(3000)
                .start();
    }

    private class CustomViewHolder implements BannerViewHolder<Object> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            mImageView = new ImageView(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            mImageView.setLayoutParams(layoutParams);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return mImageView;
        }

        @Override
        public void onBind(Context context, int position, Object data) {
            Glide.with(context).load(data).into(mImageView);
        }
    }
}
