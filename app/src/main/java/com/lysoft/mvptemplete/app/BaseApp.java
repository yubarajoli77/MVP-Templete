package com.lysoft.mvptemplete.app;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lysoft.mvptemplete.app.di.component.AppComponent;
import com.lysoft.mvptemplete.app.di.component.DaggerAppComponent;
import com.lysoft.mvptemplete.app.di.module.AppModule;
import com.lysoft.mvptemplete.ext.rxBus.RxBus;

public class BaseApp extends MultiDexApplication {

    private static RxBus rxBus;
    private AppComponent appComponent;
    private Activity activity;

    public Activity getActivityInstance() {
        return activity;
    }

    public void setActivityInstance(Activity activity) {
        this.activity = activity;
    }

    public static Fragment getFragmentInstance() {
        return fragment;
    }

    public void setFragmentInstance(Fragment fragment) {
        BaseApp.fragment = fragment;
    }

    private static Fragment fragment;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        MultiDex.install(this);

        rxBus = new RxBus();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static RxBus getRxBus() {
        return rxBus;
    }

    public static BaseApp get(Activity activity) {
        return (BaseApp) activity.getApplication();
    }

    public static BaseApp get(Service service) {
        return (BaseApp) service.getApplication();
    }

    public AppComponent appComponent() {
        return appComponent;
    }

}
