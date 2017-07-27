package sinolight.cn.qgapp.views.holder;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sinolight.cn.qgapp.R;
import sinolight.cn.qgapp.data.bean.KDBResData;
import sinolight.cn.qgapp.data.http.entity.DBResArticleEntity;
import sinolight.cn.qgapp.utils.L;

/**
 * Created by xns on 2017/7/17.
 * 资源库热门文章holder
 */

public class DBResHotArticleHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DBResHotArticleHolder";

    @BindView(R.id.tv_db_res_hot_article_title)
    TextView mTvDbResHotArticleTitle;
    @BindView(R.id.tv_db_res_hot_article_content)
    TextView mTvDbResHotArticleContent;
    @BindView(R.id.tv_db_res_hot_article_author)
    TextView mTvDbResHotArticleAuthor;
    @BindView(R.id.tv_db_res_hot_article_source)
    TextView mTvDbResHotArticleSource;
    @BindView(R.id.root_db_res_hot_article)
    ConstraintLayout mRootDbResHotArticle;
    private DBResArticleEntity mData;

    public DBResHotArticleHolder(View layout) {
        super(layout);
        ButterKnife.bind(this, layout);
    }

    public void setData(KDBResData<DBResArticleEntity> data) {
        if (data != null) {
            mData = data.getData();
            bindData();
        }
    }

    private void bindData() {
        mTvDbResHotArticleTitle.setText(mData.getName());
        mTvDbResHotArticleContent.setText(mData.getRemark());
        mTvDbResHotArticleAuthor.setText(mData.getAuthor());
        mTvDbResHotArticleSource.setText(mData.getSource());
    }

    @OnClick({R.id.root_db_res_hot_article})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.root_db_res_hot_article:
                L.d(TAG, "onClick:" + mData.getName());
                break;
        }
    }
}
