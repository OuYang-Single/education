package com.example.qiming.mvp.model.di.module;

import android.os.Handler;
import com.jess.arms.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import static com.example.qiming.mvp.model.api.Api.APP_DOMAIN;

@Module
public abstract class DownloadFileModule {

  @Provides
    public static OkHttpClient getOkHttpClient() {
       return        new OkHttpClient.Builder()
               .connectTimeout(20, TimeUnit.SECONDS)
               .readTimeout(20, TimeUnit.SECONDS)
               .writeTimeout(20, TimeUnit.SECONDS)
               //允许失败重试
               .retryOnConnectionFailure(true)
               .build();



    }
    @Provides
    public static Retrofit getRetrofit(OkHttpClient okHttpClient ) {
        return new Retrofit.Builder()
                //设置基站地址(基站地址+描述网络请求的接口上面注释的Post地址,就是要上传文件到服务器的地址,
                // 这只是一种设置地址的方法,还有其他方式,不在赘述)
                .baseUrl(APP_DOMAIN)
                //设置委托,使用OKHttp联网,也可以设置其他的;
                .client(okHttpClient)
                //设置支持rxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build();

    }
    @ActivityScope
    @Provides
    public static Handler getHandler() {
        return new Handler();
    }
}
