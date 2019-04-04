package com.lysoft.mvptemplete.app.di.module;

import android.app.Application;
import android.content.Context;

import com.lysoft.mvptemplete.app.di.scope.AppScope;
import com.lysoft.mvptemplete.utils.CustomLoading;
import com.lysoft.mvptemplete.utils.CustomSnackBar;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Application application) {
        this.context = application.getApplicationContext();
    }


    @Provides
    @AppScope
    public Context context()
    {
        return context;
    }

}
