package com.lysoft.mvptemplete.app.di.module;

import android.app.Activity;

import com.lysoft.mvptemplete.app.di.scope.AppScope;
import com.lysoft.mvptemplete.utils.CustomLoading;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {

    @AppScope
    @Provides
    public CustomLoading customLoading(Activity activity){
        return new CustomLoading(activity);
    }
}
