package com.lysoft.mvptemplete.activities.splash;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lysoft.mvptemplete.R;
import com.lysoft.mvptemplete.activities.BaseActivity;
import com.lysoft.mvptemplete.activities.splash.mvp.SplashPresenter;
import com.lysoft.mvptemplete.activities.splash.mvp.SplashView;

import javax.inject.Inject;

public class SplashAcitvity extends BaseActivity {

    @Inject
    SplashView splashView;

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent.inject(this);


        setContentView(splashView);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        splashPresenter.onCreated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashPresenter.getVersion();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
