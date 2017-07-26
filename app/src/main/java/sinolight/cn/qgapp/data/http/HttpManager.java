package sinolight.cn.qgapp.data.http;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;


import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.internal.RxCache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sinolight.cn.qgapp.AppContants;
import sinolight.cn.qgapp.data.bean.DataBaseBean;
import sinolight.cn.qgapp.data.http.api.ApiService;
import sinolight.cn.qgapp.data.http.cache.CacheProvider;
import sinolight.cn.qgapp.data.http.entity.ArticleEntity;
import sinolight.cn.qgapp.data.http.entity.BannerEntity;
import sinolight.cn.qgapp.data.http.entity.BookEntity;
import sinolight.cn.qgapp.data.http.entity.BookInfoEntity;
import sinolight.cn.qgapp.data.http.entity.DBResTypeEntity;
import sinolight.cn.qgapp.data.http.entity.DicInfoEntity;
import sinolight.cn.qgapp.data.http.entity.NewBookEntity;
import sinolight.cn.qgapp.data.http.entity.PageEntity;
import sinolight.cn.qgapp.data.http.entity.RecommendEntity;
import sinolight.cn.qgapp.data.http.entity.ResArticleEntity;
import sinolight.cn.qgapp.data.http.entity.ResImgEntity;
import sinolight.cn.qgapp.data.http.entity.ResStandardEntity;
import sinolight.cn.qgapp.data.http.entity.ResWordEntity;
import sinolight.cn.qgapp.data.http.entity.ResultEntity;
import sinolight.cn.qgapp.data.http.entity.StandardEntity;
import sinolight.cn.qgapp.data.http.entity.StdInfoEntity;
import sinolight.cn.qgapp.data.http.entity.TokenEntity;
import sinolight.cn.qgapp.data.http.entity.UserEntity;
import sinolight.cn.qgapp.data.http.entity.VCodeEntity;
import sinolight.cn.qgapp.data.http.exception.ApiException;
import sinolight.cn.qgapp.data.http.parser.GsonTSpeaker;

public class HttpManager {
    public static final String TAG=HttpManager.class.getSimpleName();
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private ApiService mApiService;
    private final CacheProvider cacheProvider;
    private static Context mContext;
    private volatile static HttpManager instance;

    private HttpManager() {
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("HttpManager",message);
            }
        });
        loggingInterceptor.setLevel(level);
        //拦截请求和响应日志并输出，其实有很多封装好的日志拦截插件，大家也可以根据个人喜好选择。
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor);

        OkHttpClient okHttpClient = builder.build();

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AppContants.BASE_URL)
                .client(okHttpClient)
                .build();

        cacheProvider = new RxCache.Builder()
                .persistence(mContext.getFilesDir(), new GsonTSpeaker())
                .using(CacheProvider.class);

        mApiService = mRetrofit.create(ApiService.class);
    }

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    public static void init(Context context){
        mContext=context;
    }

    private <T> void toSubscribe(Observable<ResultEntity<T>> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())
                .map(new Function<ResultEntity<T>, T>() {
                    @Override
                    public T apply(@NonNull ResultEntity<T> response) throws Exception {
                        int code= response.getCode();
                        if (code != AppContants.SUCCESS_CODE) {
                            throw new ApiException(code, response.getMessage());
                        } else {
                            return response.getResult();
                        }
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public void getHomeBannerWithCache(Observer<List<BannerEntity>> subscriber, String token, boolean update) {
        toSubscribe(cacheProvider.getHomeBanner(mApiService.getHomeBanner(token),new EvictProvider(update)), subscriber);
    }

    public void getHotPicsWithCache(Observer<List<BannerEntity>> subscriber, String token, boolean update) {
        toSubscribe(cacheProvider.getHotPics(mApiService.getHotPics(token),new EvictProvider(update)), subscriber);
    }

    public void getNewestStdDataWithCache(Observer<List<StandardEntity>> subscriber, String token, boolean update) {
        toSubscribe(cacheProvider.getNewestStdData(mApiService.getNewestStdData(token),new EvictProvider(update)), subscriber);
    }

    public void getRecommendWordsWithCache(Observer<List<RecommendEntity>> subscriber, String token, boolean update) {
        toSubscribe(cacheProvider.getRecommendWords(mApiService.getRecommendWords(token),new EvictProvider(update)), subscriber);
    }

    public void getNewsBooksWithCache(Observer<List<NewBookEntity>> subscriber, String token, boolean update) {
        toSubscribe(cacheProvider.getNewsBooks(mApiService.getNewsBooks(token),new EvictProvider(update)), subscriber);
    }

    public void getNewsArticleWithCache(Observer<List<ArticleEntity>> subscriber, String token, boolean update) {
        toSubscribe(cacheProvider.getNewsArticle(mApiService.getNewsArticle(token),new EvictProvider(update)), subscriber);
    }

    public void getKDBWithCache(Observer<PageEntity<List<DataBaseBean>>> subscriber, String token, int page, int size, boolean update) {
        toSubscribe(cacheProvider.getKnowledgeDataBase(
                mApiService.getKnowledgeDataBase(token,page,size),
                new EvictProvider(update)), subscriber);
    }

    public void getKDBResTypeWithCache(Observer<List<DBResTypeEntity>> subscriber, String token, String type, boolean update) {
        toSubscribe(cacheProvider.getKDBResType(
                mApiService.getKDBResType(token,type),
                new EvictProvider(update)), subscriber);
    }

    public void getKDBBookListNoCache(Observer<PageEntity<List<BookEntity>>> subscriber, String token,
                                        @Nullable String dbId, @Nullable String type, @Nullable String key,
                                        int page, int size) {
        toSubscribe(mApiService.getKDBBookList(token,dbId,type,key, page, size), subscriber);
    }

    public void getKDBStdListNoCache(Observer<PageEntity<List<ResStandardEntity>>> subscriber, String token,
                                       @Nullable String dbId, @Nullable String type, @Nullable String key,
                                       int page, int size) {
        toSubscribe(mApiService.getKDBStdList(token,dbId,type,key, page, size), subscriber);
    }

    public void getKDBIndustryAnalysisListNoCache(Observer<PageEntity<List<ResArticleEntity>>> subscriber,
                                                  String token, @Nullable String dbId, @Nullable String themeType,
                                                  @Nullable String key, int type, int page, int size) {
        toSubscribe(mApiService.getKDBIndustryAnalysisList(token,dbId,themeType,key, type, page, size), subscriber);
    }

    public void getKDBdoPicListNoCache(Observer<PageEntity<List<ResImgEntity>>> subscriber, String token,
                                       @Nullable String key, int page, int size) {
        toSubscribe(mApiService.getKDBPicList(token, key, page, size), subscriber);
    }

    public void getKDBdoPicInfoNoCache(Observer<PageEntity<List<ResImgEntity>>> subscriber, String token,
                                       @Nullable String dbId, @Nullable String themeType,
                                       @Nullable String key, int page, int size) {
        toSubscribe(mApiService.getKDBPicInfo(token,dbId,themeType,key, page, size), subscriber);
    }

    public void getKDBWordListNoCache(Observer<PageEntity<List<ResWordEntity>>> subscriber, String token,
                                       @Nullable String dbId, int type, @Nullable String key, int page, int size) {
        toSubscribe(mApiService.getKDBWordList(token,"5f5f7c1e-2d7f-4570-a639-a5c4bae75378",key,type, page, size), subscriber);
    }

    public void getKDBBookInfoNoCache(Observer<BookInfoEntity> subscriber, String token, String id) {
        toSubscribe(mApiService.getKDBBookInfo(token, id), subscriber);
    }

    public void getKDBStdInfoNoCache(Observer<StdInfoEntity> subscriber, String token, String id) {
        toSubscribe(mApiService.getKDBStdInfo(token, id), subscriber);
    }

    public void getKDBEntryInfoNoCache(Observer<DicInfoEntity> subscriber, String token, String id) {
        toSubscribe(mApiService.getKDBEntryInfo(token, id), subscriber);
    }

    public void getUserInfoWithCache(Observer<UserEntity> subscriber, String token, String user, boolean update) {
        toSubscribe(cacheProvider.getUserInfo(
                mApiService.getUserInfo(token,user),
                new EvictProvider(update)), subscriber);
    }

    public void changePwdNoCache(Observer<Object> subscriber, String token, String oldPwd, String newPwd,
                                 String reNewPwd, String user) {
        toSubscribe(mApiService.changePwd(token, oldPwd, newPwd, reNewPwd, user), subscriber);
    }

    public void getCode(Observer<VCodeEntity> subscriber, String time) {
        toSubscribe(mApiService.getCode(time), subscriber);
    }

    public void register(Observer<TokenEntity> subscriber, String name, String email, String pwd, String repwd, String vcode) {
        toSubscribe(mApiService.register(name, email, pwd, repwd, vcode), subscriber);
    }

    public void login(Observer<TokenEntity> subscriber, String name, String pwd) {
        toSubscribe(mApiService.login(name, pwd), subscriber);
    }
}
