package me.hhs.wanandroid.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.RecyclerViewAdapter;
import me.hhs.wanandroid.entity.ArticleBean;
import me.hhs.wanandroid.fragment.BaseFragment;

/**
 * Created by KevinSong on 2019/7/18
 */
public class HomePageFragment extends BaseFragment {

    private RecyclerViewAdapter adapter;
    private List<ArticleBean> list;
    private RecyclerView.LayoutManager layoutManager;

   @BindView(R.id.home_rv)
   RecyclerView recyclerView;



    @Override
    public int getLayoutResId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initViews() {
        super.initViews();
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list,getContext());
        layoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    protected void initData() {
        super.initData();
        ((LinearLayoutManager)layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
