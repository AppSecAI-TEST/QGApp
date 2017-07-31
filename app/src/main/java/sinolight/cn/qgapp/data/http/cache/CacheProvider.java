package sinolight.cn.qgapp.data.http.cache;




import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import sinolight.cn.qgapp.data.bean.DataBaseBean;
import sinolight.cn.qgapp.data.http.entity.ArticleEntity;
import sinolight.cn.qgapp.data.http.entity.BannerEntity;
import sinolight.cn.qgapp.data.http.entity.BookEntity;
import sinolight.cn.qgapp.data.http.entity.BookInfoEntity;
import sinolight.cn.qgapp.data.http.entity.DBResArticleEntity;
import sinolight.cn.qgapp.data.http.entity.DBResPicEntity;
import sinolight.cn.qgapp.data.http.entity.DBResTypeEntity;
import sinolight.cn.qgapp.data.http.entity.DicInfoEntity;
import sinolight.cn.qgapp.data.http.entity.MaterialEntity;
import sinolight.cn.qgapp.data.http.entity.NewBookEntity;
import sinolight.cn.qgapp.data.http.entity.PageEntity;
import sinolight.cn.qgapp.data.http.entity.RecommendEntity;
import sinolight.cn.qgapp.data.http.entity.ResStandardEntity;
import sinolight.cn.qgapp.data.http.entity.ResultEntity;
import sinolight.cn.qgapp.data.http.entity.StandardEntity;
import sinolight.cn.qgapp.data.http.entity.UserEntity;

public interface CacheProvider {

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<BannerEntity>>> getHomeBanner(
            Observable<ResultEntity<List<BannerEntity>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<BannerEntity>>> getHotPics(
            Observable<ResultEntity<List<BannerEntity>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<StandardEntity>>> getNewestStdData(
            Observable<ResultEntity<List<StandardEntity>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<RecommendEntity>>> getRecommendWords(
            Observable<ResultEntity<List<RecommendEntity>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<NewBookEntity>>> getNewsBooks(
            Observable<ResultEntity<List<NewBookEntity>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<ArticleEntity>>> getNewsArticle(
            Observable<ResultEntity<List<ArticleEntity>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<PageEntity<List<DataBaseBean>>>> getKnowledgeDataBase(
            Observable<ResultEntity<PageEntity<List<DataBaseBean>>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<DBResTypeEntity>>> getKDBResType(
            Observable<ResultEntity<List<DBResTypeEntity>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<UserEntity>> getUserInfo(
            Observable<ResultEntity<UserEntity>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<MaterialEntity>>> getHotMenu(
            Observable<ResultEntity<List<MaterialEntity>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<DBResArticleEntity>>> getHotArticle(
            Observable<ResultEntity<List<DBResArticleEntity>>> oRepos,
            EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<ResultEntity<List<DBResPicEntity>>> getHotPic(
            Observable<ResultEntity<List<DBResArticleEntity>>> oRepos,
            EvictProvider evictDynamicKey);
}