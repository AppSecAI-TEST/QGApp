package sinolight.cn.qgapp;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import sinolight.cn.qgapp.dagger.component.ApplicationComponent;
import sinolight.cn.qgapp.dagger.component.DaggerApplicationComponent;
import sinolight.cn.qgapp.dagger.module.ApplicationModule;
import sinolight.cn.qgapp.data.db.GreenDaoHelper;
import sinolight.cn.qgapp.data.http.HttpManager;

/**
 * Created by xns on 2017/6/29.
 * Application Class
 */

public class App extends Application {

    private static Context instance;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        Fresco.initialize(this);
        HttpManager.init(this);
        initDatabase();
        AppHelper.getInstance().init(instance);
    }

    private void initDatabase() {
        GreenDaoHelper.initDatabase();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getContext() {
        return instance;
    }
}
