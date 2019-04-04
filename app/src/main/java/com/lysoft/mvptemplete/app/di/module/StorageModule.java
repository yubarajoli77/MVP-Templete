package com.lysoft.mvptemplete.app.di.module;


import android.content.Context;

import com.lysoft.mvptemplete.app.di.scope.AppScope;
import com.lysoft.mvptemplete.ext.storage.PreferencesManager;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @AppScope
    @Provides
    public PreferencesManager preferencesManager(Context context)
    {
        return new PreferencesManager(context);
    }

}
