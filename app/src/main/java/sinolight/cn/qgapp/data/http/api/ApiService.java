package sinolight.cn.qgapp.data.http.api;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import sinolight.cn.qgapp.data.http.entity.BannerEntity;
import sinolight.cn.qgapp.data.http.entity.RecommendEntity;
import sinolight.cn.qgapp.data.http.entity.ResultEntity;
import sinolight.cn.qgapp.data.http.entity.StandardEntity;
import sinolight.cn.qgapp.data.http.entity.TokenEntity;
import sinolight.cn.qgapp.data.http.entity.VCodeEntity;


public interface ApiService {
    /**
     * 获取验证码
     * @param time
     * @return
     */
    @FormUrlEncoded
    @POST("account/doVCode")
    Observable<ResultEntity<VCodeEntity>> getCode(
            @Field("time") String time
    );

    /**
     * 注册接口
     * @param name
     * @param email
     * @param pwd
     * @param repwd
     * @param vcode
     * @return
     */
    @FormUrlEncoded
    @POST("account/regist")
    Observable<ResultEntity<TokenEntity>> register(
            @Field("username") String name,
            @Field("email") String email,
            @Field("pwd") String pwd,
            @Field("repwd") String repwd,
            @Field("vcode") String vcode
    );

    /**
     * 登录接口
     * @param name
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("account/logOn")
    Observable<ResultEntity<TokenEntity>> login(
            @Field("username") String name,
            @Field("pwd") String pwd
    );

    /**
     * 首页轮播图片
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("home/doRotationChart")
    Observable<ResultEntity<List<BannerEntity>>> getHomeBanner(
            @Field("token") String token
    );

    /**
     * 首页-热门图集
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("home/doHotPics")
    Observable<ResultEntity<List<BannerEntity>>> getHotPics(
            @Field("token") String token
    );

    /**
     * 首页-最新标准
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("home/doNewestStdData")
    Observable<ResultEntity<List<StandardEntity>>> getNewestStdData(
            @Field("token") String token
    );

    /**
     * 首页-推荐词条
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("home/doRecommendEntry")
    Observable<ResultEntity<List<RecommendEntity>>> getRecommendWords(
            @Field("token") String token
    );
}
