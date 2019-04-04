package com.lysoft.mvptemplete.activities.splash.mvp;

import com.lysoft.mvptemplete.BuildConfig;
import com.lysoft.mvptemplete.app.network.BaseAppNetwork;
import com.lysoft.mvptemplete.app.network.model.splash.VersionResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashModel {

    private BaseAppNetwork baseAppNetwork;

    public SplashModel(BaseAppNetwork baseAppNetwork) {
        this.baseAppNetwork = baseAppNetwork;
    }


    public Observable<VersionResponse> getRecentVersion(){
        return baseAppNetwork.getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public int getCurrentAppCode(){
       return BuildConfig.VERSION_CODE;
    }
}
