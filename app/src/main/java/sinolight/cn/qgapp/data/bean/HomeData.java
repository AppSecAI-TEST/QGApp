package sinolight.cn.qgapp.data.bean;

import java.util.List;

import sinolight.cn.qgapp.AppContants;

/**
 * Created by admin on 2017/7/4.
 * 首页数据的包装类
 */

public class HomeData<T> {
    private String id;
    private String title;
    private String url;
    private int resId;
    /**
     * 首页Store的类型
     */
    private AppContants.HomeStore.Type homeType;
    /**
     * 是否为本地数据
     */
    private boolean isLocal;
    /**
     * Adapter Item类型
     */
    private int itemType;
    /**
     * Span Line
     */
    private boolean isSpan;
    // 标准号
    private String stdno;
    // 实施时间
    private String imdate;
    // 作者
    private String author;
    // 来源
    private String source;
    // 简介
    private String remark;
    // 封装的具体数据
    private List<T> datas;

    public HomeData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public boolean isSpan() {
        return isSpan;
    }

    public void setSpan(boolean span) {
        isSpan = span;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public String getStdno() {
        return stdno;
    }

    public void setStdno(String stdno) {
        this.stdno = stdno;
    }

    public String getImdate() {
        return imdate;
    }

    public void setImdate(String imdate) {
        this.imdate = imdate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AppContants.HomeStore.Type getHomeType() {
        return homeType;
    }

    public void setHomeType(AppContants.HomeStore.Type homeType) {
        this.homeType = homeType;
    }
}
