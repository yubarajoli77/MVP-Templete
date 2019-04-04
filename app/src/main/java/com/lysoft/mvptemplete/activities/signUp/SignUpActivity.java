package com.lysoft.mvptemplete.activities.signUp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lysoft.mvptemplete.activities.BaseActivity;
import com.lysoft.mvptemplete.activities.signUp.mvp.SignUpPresenter;
import com.lysoft.mvptemplete.activities.signUp.mvp.SignUpView;

import javax.inject.Inject;

public class SignUpActivity extends BaseActivity {

    @Inject
    public SignUpView signUpView;

    @Inject
    public SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent.inject(this);
        setContentView(signUpView);
        signUpPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signUpPresenter.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        signUpPresenter.onStop();
    }
}
