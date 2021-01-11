package me.hhs.wanandroid.ui;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.RadioButton;
import butterknife.BindView;
import butterknife.OnClick;
import me.hhs.wanandroid.fragment.HomePageFragment;
import me.hhs.wanandroid.fragment.MeFragment;
import me.hhs.wanandroid.fragment.ProjectFragment;
import me.hhs.wanandroid.fragment.PublicNumberFragment;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.fragment.StructureFragment;
import me.hhs.wanandroid.utils.onDoubleClickListener;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rb_homePage)
    RadioButton rbHomePage;
    @BindView(R.id.rb_structure)
    RadioButton rbStructure;
    @BindView(R.id.rb_project)
    RadioButton rbProject;
    @BindView(R.id.rb_publicNumber)
    RadioButton rbPublicNumber;
    @BindView(R.id.rb_me)
    RadioButton rbMe;

    private HomePageFragment homePageFragment;
    private StructureFragment structureFragment;
    private ProjectFragment projectFragment;
    private PublicNumberFragment publicNumberFragment;
    private MeFragment meFragment;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {
        super.initData();

        homePageFragment = new HomePageFragment();
        structureFragment = new StructureFragment();
        projectFragment = new ProjectFragment();
        publicNumberFragment = new PublicNumberFragment();
        meFragment = new MeFragment();
        setDefultFragment();
        //会和点击事件冲突
      /*  rbHomePage.setOnTouchListener(new onDoubleClickListener(new onDoubleClickListener.DoubleClickCallback() {
            @Override
            public void onDoubleClick() {
               homePageFragment.onRefreshArticle();
            }
        })); */
    }

    private void setDefultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content, homePageFragment);
        transaction.commit();
    }

    @OnClick({R.id.rb_homePage, R.id.rb_structure, R.id.rb_project, R.id.rb_publicNumber, R.id.rb_me})
    public void clickBottomButton(Button button) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (button.getId()) {
            case R.id.rb_homePage:
                transaction.replace(R.id.fl_content, homePageFragment);
                homePageFragment.onRefreshArticle();
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
            case R.id.rb_me:
                transaction.replace(R.id.fl_content, meFragment);
                break;
        }

        transaction.commit();
    }


}
