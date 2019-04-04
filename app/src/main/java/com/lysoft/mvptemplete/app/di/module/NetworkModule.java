package com.lysoft.mvptemplete.app.di.module;


import android.content.Context;

import com.google.gson.Gson;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lysoft.mvptemplete.app.di.scope.AppScope;
import com.lysoft.mvptemplete.app.network.BaseAppNetwork;
import com.lysoft.mvptemplete.ext.Constants;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule  {

    @AppScope
    @Provides
    public Cache cache(Context context) {
        return new Cache(new File(context.getCacheDir(), Constants.NetworkConstants.HTTP_DIR_CACHE), Constants.NetworkConstants.CACHE_SIZE);
    }


    @AppScope
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    @AppScope
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(Constants.NetworkConstants.TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.NetworkConstants.TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    @AppScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder().client(okHttpClient)
                .baseUrl(Constants.ServerConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


    }

    @AppScope
    @Provides
    public BaseAppNetwork baseAppNetwork(Retrofit retrofit) {
        return retrofit.create(BaseAppNetwork.class);
    }

    @AppScope
    @Provides
    public Picasso picasso(Context context, OkHttpClient okHttpClient)
    {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }

}
