package sinolight.cn.qgapp.views.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sinolight.cn.qgapp.R;
import sinolight.cn.qgapp.data.http.entity.BookInfoEntity;

import static android.text.Html.FROM_HTML_MODE_COMPACT;

/**
 * Created by xns on 2017/7/24.
 * 百科数据库界面
 */

public class BaiKeFragment extends ResBaseFragment {
    private static final String TYPE_KEY = "sinolight.cn.qgapp.views.fragment.BaiKeFragment_TYPE";
    public static final int TYPE_BAIKE_ANALYSIS = 0;
    public static final int TYPE_BAIKE_WORD = 1;
    /**
     * BookInfo View
     */
    private TextView mTvBookFragmentEditionNum;
    private TextView mTvBookFragmentPrintTime;
    private TextView mTvBookFragmentPrintNum;
    private TextView mTvBookFragmentIsn;
    private TextView mTvBookFragmentClassificationInfo;
    private TextView mTvBookFragmentPageNum;
    private TextView mTvBookFragmentPageSize;
    private TextView mTvBookFragmentPackage;
    /**
     * BookIntroduction View
     */
    private TextView mTvBookIntroduction;
    // 目录
    private TextView mTvTableOfContent;

    private int mType;
    private BookInfoEntity mBookData;


    public static BaiKeFragment newInstance(int type) {
        BaiKeFragment fragment = new BaiKeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE_KEY, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(TYPE_KEY, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = 0;
        switch (mType) {
            case TYPE_BAIKE_ANALYSIS:
                layoutId = R.layout.fragment_book_info;
                break;
            case TYPE_BAIKE_WORD:
                layoutId = R.layout.fragment_book_introduction;
                break;
        }

        return inflater.inflate(layoutId, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        switch (mType) {
//            case TYPE_BOOK_INFO:
//                bindBookInfo(view);
//                break;
//            case TYPE_BOOK_INTRODUCTION:
//                bindBookIntroduction(view);
//                break;
//            case TYPE_BOOK_TABLE_OF_CONTENTS:
//                bindBookTableOfContent(view);
//                break;
//        }

    }

    private void bindBookTableOfContent(View view) {
        mTvTableOfContent = view.findViewById(R.id.tv_table_of_content);
        // Fix issue ViewPager and TextView Scroll
//        mTvTableOfContent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                mTvTableOfContent.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
//        mTvTableOfContent.setMovementMethod(new ScrollingMovementMethod());
        if (Build.VERSION.SDK_INT >= 24) {
            mTvTableOfContent.setText(Html.fromHtml(mBookData.getCatalog(), FROM_HTML_MODE_COMPACT));
        } else {
            mTvTableOfContent.setText(Html.fromHtml(mBookData.getCatalog())); // or for older api
        }

    }

    /**
     * 绑定图书信息
     *
     * @param view
     */
    private void bindBookInfo(View view) {
        mTvBookFragmentEditionNum = view.findViewById(R.id.tv_book_fragment_edition_num);
        mTvBookFragmentPrintTime = view.findViewById(R.id.tv_book_fragment_print_time);
        mTvBookFragmentPrintNum = view.findViewById(R.id.tv_book_fragment_print_num);
        mTvBookFragmentIsn = view.findViewById(R.id.tv_book_fragment_isn);
        mTvBookFragmentClassificationInfo = view.findViewById(R.id.tv_book_fragment_classification_info);
        mTvBookFragmentPageNum = view.findViewById(R.id.tv_book_fragment_page_num);
        mTvBookFragmentPageSize = view.findViewById(R.id.tv_book_fragment_page_size);
        mTvBookFragmentPackage = view.findViewById(R.id.tv_book_fragment_package);

        mTvBookFragmentEditionNum.setText(formatStr(R.string.text_edition_num_format, getVersionNum()));
        mTvBookFragmentPrintTime.setText(formatStr(R.string.text_print_time_format, mBookData.getPrinttime()));
        mTvBookFragmentPrintNum.setText(formatStr(R.string.text_print_num_format, getPrintNum()));
        mTvBookFragmentIsn.setText(formatStr(R.string.text_isn_format, mBookData.getIsbn()));
        mTvBookFragmentClassificationInfo.setText(mBookData.getClassification());
        mTvBookFragmentPageNum.setText(formatStr(R.string.text_page_num_format, String.valueOf(mBookData.getPage())));
        mTvBookFragmentPageSize.setText(formatStr(R.string.text_page_size_format, mBookData.getFormat()));
        mTvBookFragmentPackage.setText(formatStr(R.string.text_package_style_format, mBookData.getZzformat()));
    }

    private String formatStr(int baseStrId, String child) {
        String local = getString(baseStrId);
        return String.format(local, child);
    }

    private String getVersionNum() {
        String str = mBookData.getVersionprint();
        String[] arr = str.split(";");
        if (arr.length > 1) {
            if (arr[1] != null) {
                return arr[1];
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private String getPrintNum() {
        String str = mBookData.getVersionprint();
        String[] arr = str.split(";");
        if (arr.length > 1) {
            if (arr[1] != null) {
                return arr[1];
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 绑定图书简介
     *
     * @param view
     */
    private void bindBookIntroduction(View view) {
        mTvBookIntroduction = view.findViewById(R.id.tv_book_introduction);
        // Fix issue ViewPager and TextView Scroll
//        mTvBookIntroduction.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                mTvBookIntroduction.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
//        mTvBookIntroduction.setMovementMethod(new ScrollingMovementMethod());
        mTvBookIntroduction.setText(formatStr(R.string.text_two_empty_format, mBookData.getAbs()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
