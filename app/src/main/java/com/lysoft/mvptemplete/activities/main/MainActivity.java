package com.lysoft.mvptemplete.activities.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lysoft.mvptemplete.R;
import com.lysoft.mvptemplete.activities.BaseActivity;
import com.lysoft.mvptemplete.activities.main.mvp.MainPresenter;
import com.lysoft.mvptemplete.activities.main.mvp.MainView;
import com.lysoft.mvptemplete.app.BaseApp;
import com.lysoft.mvptemplete.app.network.model.login.Login;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    public MainView mainView;

    @Inject
    public MainPresenter mainPresenter;

    public static void startDashboardFromLogin(Context context, Login userDetail) {
        BaseApp.getRxBus().send(userDetail);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startDashboardFromSplash(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent.inject(this);
        setContentView(mainView);
        mainPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }
}
