package me.hhs.wanandroid.ui;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.ms.banner.Banner;
import com.ms.banner.holder.BannerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.hhs.wanandroid.fragment.HomePageFragment;
import me.hhs.wanandroid.fragment.ProjectFragment;
import me.hhs.wanandroid.fragment.PublicNumberFragment;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.fragment.StructureFragment;

public class MainActivity extends BaseActivity {


    @BindView(R.id.rb_homePage)
    RadioButton rbHomePage;
    @BindView(R.id.rb_structure)
    RadioButton rbStructure;
    @BindView(R.id.rb_project)
    RadioButton rbProject;
    @BindView(R.id.rb_publicNumber)
    RadioButton rbPublicNumber;

    private HomePageFragment homePageFragment;
    private StructureFragment structureFragment;
    private ProjectFragment projectFragment;
    private PublicNumberFragment publicNumberFragment;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();

        homePageFragment = new HomePageFragment();
        structureFragment = new StructureFragment();
        projectFragment = new ProjectFragment();
        publicNumberFragment = new PublicNumberFragment();
        setDefultFragment();
    }

    private void setDefultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content, homePageFragment);
        transaction.commit();
    }

    @OnClick({R.id.rb_homePage, R.id.rb_structure, R.id.rb_project, R.id.rb_publicNumber})
    public void clickBottomButton(Button button) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (button.getId()) {
            case R.id.rb_homePage:
                transaction.replace(R.id.fl_content, homePageFragment);
                break;
            case R.id.rb_structure:
                transaction.replace(R.id.fl_content, structureFragment);

                break;
            case R.id.rb_project:
                transaction.replace(R.id.fl_content, projectFragment);

                break;
            case R.id.rb_publicNumber:
                transaction.replace(R.id.fl_content, publicNumberFragment);

                break;
        }

        transaction.commit();
    }




}
