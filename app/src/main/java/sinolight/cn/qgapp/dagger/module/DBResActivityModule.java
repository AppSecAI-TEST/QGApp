package sinolight.cn.qgapp.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sinolight.cn.qgapp.dagger.PerActivity;
import sinolight.cn.qgapp.presenter.DBResActivityPresenter;
import sinolight.cn.qgapp.views.view.IDBResActivityView;

/**
 * Created by xns on 2017/6/29.
 * DBResActivity的Module
 */
@Module
public class DBResActivityModule {
    private IDBResActivityView view;

    public DBResActivityModule(IDBResActivityView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    IDBResActivityView provideIDBResActivityView() {
        return this.view;
    }

    @Provides
    @PerActivity
    DBResActivityPresenter provideITMDetailPresenter(IDBResActivityView view, Context context) {
        return new DBResActivityPresenter(view, context);
    }

}
