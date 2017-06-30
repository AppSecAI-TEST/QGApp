package sinolight.cn.qgapp.dagger.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import sinolight.cn.qgapp.dagger.module.ApplicationModule;
import sinolight.cn.qgapp.data.db.DaoSession;
import sinolight.cn.qgapp.views.activity.BaseActivity;

/**
 * Created by xns on 2017/6/1.
 * 全局组件
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context context();

    DaoSession daoSeesion();
}
