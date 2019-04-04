package com.lysoft.mvptemplete.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lysoft.mvptemplete.activities.BaseActivity;
import com.lysoft.mvptemplete.activities.login.mvp.LoginPresenter;
import com.lysoft.mvptemplete.activities.login.mvp.LoginView;
import com.lysoft.mvptemplete.activities.main.MainActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity {

    @Inject
    LoginView loginView;
    @Inject
    LoginPresenter loginPresenter;

    public static void startLoginActivityFromSplash(AppCompatActivity context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent.inject(this);
        setContentView(loginView);
        loginPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }


}
