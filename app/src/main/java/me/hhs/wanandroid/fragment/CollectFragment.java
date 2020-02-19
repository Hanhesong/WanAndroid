package me.hhs.wanandroid.fragment;

import android.widget.TextView;

import butterknife.BindView;
import me.hhs.wanandroid.R;
import me.hhs.wanandroid.Serve;
import me.hhs.wanandroid.entity.ArticleDataBean;
import me.hhs.wanandroid.presenter.collect.GetCollectPresenterImpl;
import me.hhs.wanandroid.presenter.collect.IGetCollectPrecenter;
import me.hhs.wanandroid.ui.view.IGetCollectView;


public class CollectFragment extends BaseFragment implements Serve, IGetCollectView {
    @BindView(R.id.test)
    TextView textView;

    private IGetCollectPrecenter getCollectPrecenter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initData() {
        super.initData();
        getCollectPrecenter = new GetCollectPresenterImpl(this);
        getCollectPrecenter.getCollect(getContext(), 0);
    }


    @Override
    public void getCollectSuccess(ArticleDataBean articleDataBean) {
        if (articleDataBean != null) {
            textView.setText(articleDataBean.toString());

        }

    }

    @Override
    public void getCollectFailure(String errorCode, String errorMsg) {
        textView.setText("errorCode: " + errorCode + " " + errorMsg);
    }

    @Override
    public void getCollectError(Throwable throwable) {

    }
}
