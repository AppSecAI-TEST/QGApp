package sinolight.cn.qgapp.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sinolight.cn.qgapp.R;
import sinolight.cn.qgapp.adapter.CookAdapter;
import sinolight.cn.qgapp.dagger.component.DaggerMaterialInfoActivityComponent;
import sinolight.cn.qgapp.dagger.module.MaterialInfoActivityModule;
import sinolight.cn.qgapp.presenter.MaterialDetailActivityPresenter;
import sinolight.cn.qgapp.views.view.IMaterialDetailActivityView;

/**
 * Created by xns on 2017/8/4.
 * 菜谱界面
 */

public class MaterialDetailActivity extends BaseActivity implements IMaterialDetailActivityView {
    private static final String TAG = "MaterialDetailActivity";

    @Inject
    Context mContext;
    @Inject
    MaterialDetailActivityPresenter mPresenter;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tb_material_info)
    Toolbar mTbMaterialInfo;
    @BindView(R.id.rv_book_info)
    RecyclerView mRvBookInfo;
    @BindView(R.id.loading_root)
    RelativeLayout mLoadingRoot;

    private LinearLayoutManager mLayoutManager;

    public static Intent getCallIntent(Context context) {
        return new Intent(context, MaterialDetailActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        mPresenter.checkoutIntent(getIntent());
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_material_detail;
    }

    @Override
    protected void initViews() {
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        mRvBookInfo.setLayoutManager(mLayoutManager);
        mRvBookInfo.setHasFixedSize(true);
        mRvBookInfo.addItemDecoration(new LinearDivider(mContext));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initializeInjector() {
        DaggerMaterialInfoActivityComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .materialInfoActivityModule(new MaterialInfoActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showRefreshView(boolean enable) {
        if (enable) {
            mLoadingRoot.setVisibility(View.VISIBLE);
        } else {
            mLoadingRoot.setVisibility(View.GONE);
        }
    }

    @Override
    public void showToast(int msgId) {
        String msg = getString(msgId);
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showListView(CookAdapter adapter) {
        if (mRvBookInfo != null && mRvBookInfo.getAdapter() == null) {
            mRvBookInfo.setAdapter(adapter);
        }
    }

    @Override
    public void showTitle(String title) {
        mTvTitle.setText(title);

    }

    @OnClick({R.id.im_back_arrow, R.id.iv_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back_arrow:
                finish();
                break;
            case R.id.iv_collect:
                break;
        }
    }

    private class LinearDivider extends Y_DividerItemDecoration {
        private Context context;

        public LinearDivider(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        public Y_Divider getDivider(int itemPosition) {
            Y_Divider divider = null;
            divider = new Y_DividerBuilder()
                    .setLeftSideLine(false, ContextCompat.getColor(context, R.color.color_transparent_all), 0.5f, 0, 0)
                    .setBottomSideLine(true, ContextCompat.getColor(context, R.color.color_bottom_divider), 0.5f, 0, 0)
                    .setRightSideLine(false, ContextCompat.getColor(context, R.color.color_transparent_all), 0.5f, 0, 0)
                    .setTopSideLine(false, ContextCompat.getColor(context, R.color.color_transparent_all), 0.5f, 0, 0)
                    .create();
            return divider;
        }
    }
}
