package sinolight.cn.qgapp.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sinolight.cn.qgapp.R;
import sinolight.cn.qgapp.adapter.CommonTitleAdapter;
import sinolight.cn.qgapp.dagger.HasComponent;
import sinolight.cn.qgapp.dagger.component.UserComponent;
import sinolight.cn.qgapp.presenter.DBResMaterialPresenter;
import sinolight.cn.qgapp.views.view.IDBResMaterialFragmentView;

/**
 * Created by xns on 2017/7/26.
 * 热门素材Fragment
 */

public class DBResMaterialFragment extends BaseLazyLoadFragment implements IDBResMaterialFragmentView {
    private static final String TAG = "DBResMaterialFragment";
    @Inject
    DBResMaterialPresenter mPresenter;
    @BindView(R.id.rv_dbres_material)
    RecyclerView mRvDbresMaterial;
    Unbinder unbinder;
    private LinearLayoutManager mLayoutManager;

    public static DBResMaterialFragment newInstance() {
        return new DBResMaterialFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_res_db_material, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent().inject(this);
        mPresenter.bindView(this);
    }

    private void initView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRvDbresMaterial.setLayoutManager(mLayoutManager);
        mRvDbresMaterial.addItemDecoration(new LinearDivider(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.init2Show();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void lazyLoad() {
        if (!isVisible) {
            return;
        }
    }

    @Override
    protected UserComponent getComponent() {
        return ((HasComponent<UserComponent>) getActivity()).getComponent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.clear();
    }

    @Override
    public void showErrorToast(int msgId) {

    }

    @Override
    public void init2Show(CommonTitleAdapter adapter) {
        mRvDbresMaterial.setAdapter(adapter);
    }

    @Override
    public void gotoActivity(Intent callIntent) {

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
