package sinolight.cn.qgapp.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sinolight.cn.qgapp.R;
import sinolight.cn.qgapp.dagger.component.UserComponent;

/**
 * Created by xns on 2017/7/26.
 * 资源库视频Fragment
 */

public class DBResVideoFragment extends BaseFragment {

    public static DBResVideoFragment newInstance() {
        return new DBResVideoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_res_db_video, container, false);

        return fragmentView;
    }

    @Override
    protected UserComponent getComponent() {
        return null;
    }
}
