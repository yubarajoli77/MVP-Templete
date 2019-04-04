package com.lysoft.mvptemplete.app.di.component;

import android.content.Context;

import com.lysoft.mvptemplete.app.di.module.AppModule;
import com.lysoft.mvptemplete.app.di.module.GsonModule;
import com.lysoft.mvptemplete.app.di.module.NetworkModule;
import com.lysoft.mvptemplete.app.di.module.StorageModule;
import com.lysoft.mvptemplete.app.di.scope.AppScope;
import com.lysoft.mvptemplete.app.network.BaseAppNetwork;
import com.lysoft.mvptemplete.ext.storage.PreferencesManager;
import com.squareup.picasso.Picasso;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@AppScope
@Component(modules = {AppModule.class, GsonModule.class, NetworkModule.class, StorageModule.class})
public interface AppComponent {
    Context context();
    OkHttpClient okHttpClient();

    BaseAppNetwork baseAppNetwork();

    PreferencesManager preferencesManager();

    Picasso picasso();

    Retrofit retrofit();

}
