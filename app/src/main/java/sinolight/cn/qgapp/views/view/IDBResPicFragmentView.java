package sinolight.cn.qgapp.views.view;


import android.content.Intent;

import sinolight.cn.qgapp.adapter.CommonTitleAdapter;

/**
 * Created by xns on 2017/7/5.
 * 资源库图片标签页 View层接口
 */

public interface IDBResPicFragmentView {

    void showErrorToast(int msgId);

    void init2Show(CommonTitleAdapter adapter);

    void gotoActivity(Intent callIntent);

    void showRefreshing(boolean enable);
}
