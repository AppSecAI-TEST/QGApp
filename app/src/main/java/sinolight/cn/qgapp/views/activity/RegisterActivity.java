package sinolight.cn.qgapp.views.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sinolight.cn.qgapp.R;
import sinolight.cn.qgapp.dagger.component.DaggerRegisterActivityComponent;
import sinolight.cn.qgapp.dagger.module.RegisterActivityModule;
import sinolight.cn.qgapp.presenter.RegisterActivityPresenter;
import sinolight.cn.qgapp.utils.VCodeUtil;
import sinolight.cn.qgapp.views.view.IRegisterActivityView;

/**
 * Created by xns on 2017/6/30.
 * 注册界面
 */

public class RegisterActivity extends BaseActivity implements IRegisterActivityView {
    @Inject
    Context mContext;
    @Inject
    RegisterActivityPresenter mPresenter;
    @BindView(R.id.et_register_user)
    EditText mEtRegisterUser;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_pw)
    EditText mEtPw;
    @BindView(R.id.et_repw)
    EditText mEtRepw;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.iv_register_code)
    ImageView mIvRegisterCode;


    public static Intent getCallIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
    }

    private void initializeInjector() {
        DaggerRegisterActivityComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .registerActivityModule(new RegisterActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        mPresenter.getVCode();
        Bitmap codeBitmap = VCodeUtil.createSecurityCodeBitmap(120, 35, 16, 1, "ab12");
        mIvRegisterCode.setImageBitmap(codeBitmap);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
    }

    @OnClick(R.id.btn_register_reg)
    public void onViewClicked() {
    }
}
