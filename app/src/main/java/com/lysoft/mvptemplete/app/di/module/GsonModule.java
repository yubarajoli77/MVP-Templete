package com.lysoft.mvptemplete.app.di.module;


import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lysoft.mvptemplete.app.di.scope.AppScope;
import com.lysoft.mvptemplete.ext.BaseTypeAdapterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {

    @Provides
    @AppScope
    public Gson gson()
    {
        return Converters.registerAll(new GsonBuilder())
                .registerTypeAdapterFactory(BaseTypeAdapterFactory.create())
                .create();
    }
}
