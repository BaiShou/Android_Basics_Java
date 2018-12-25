package com.arnold.basics.di.module;

import android.app.Application;
import android.support.annotation.Nullable;

import com.arnold.basics.BuildConfig;
import com.arnold.basics.http.converter.CustomGsonConverterFactory;
import com.arnold.basics.http.interceptor.RequestHeaderInterceptor;
import com.arnold.basics.http.log.HttpLogger;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
public class ClientModule {

    private static final int TIME_OUT = 60;

    /**
     * 提供 {@link Retrofit}
     *
     * @param builder
     * @param client
     * @param httpUrl
     * @param gson
     * @return {@link Retrofit}
     */
    @Singleton
    @Provides
    static Retrofit provideRetrofit( Retrofit.Builder builder, OkHttpClient client, HttpUrl httpUrl, Gson gson) {

        builder.baseUrl(httpUrl)//域名
                .client(client)//设置okhttp
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用 Rxjava
                .addConverterFactory(CustomGsonConverterFactory.create(gson));//使用 Gson

        return builder.build();
    }


    /**
     * 提供 [OkHttpClient]
     *
     * @param builder
     * @return [OkHttpClient]
     */
    @Singleton
    @Provides
    static OkHttpClient provideClient( OkHttpClient.Builder builder) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLogger());
//        BASIC 请求/响应行
//        HEADER 请求/响应行 + 头
//        BODY 请求/响应行 + 头 + 体
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new RequestHeaderInterceptor())
                .addNetworkInterceptor(interceptor);

        return builder.build();
    }

    @Singleton
    @Provides
    static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    static OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

    /**
     * 提供 BaseUrl,默认使用 <"https://api.github.com/">
     *
     * @return
     */
    @Singleton
    @Provides
    static HttpUrl provideBaseUrl(){

        HttpUrl httpUrl = HttpUrl.parse(BuildConfig.api_host);

        if (httpUrl!=null){
            return httpUrl;
        }

        return HttpUrl.parse("https://api.github.com/");
    }

}
