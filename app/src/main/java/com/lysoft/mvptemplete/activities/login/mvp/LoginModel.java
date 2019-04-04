package com.lysoft.mvptemplete.activities.login.mvp;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.lysoft.mvptemplete.activities.main.MainActivity;
import com.lysoft.mvptemplete.activities.signUp.SignUpActivity;
import com.lysoft.mvptemplete.app.BaseApp;
import com.lysoft.mvptemplete.app.network.BaseAppNetwork;
import com.lysoft.mvptemplete.app.network.model.login.Login;
import com.lysoft.mvptemplete.app.network.model.login.LoginParams;
import com.lysoft.mvptemplete.ext.Constants;
import com.lysoft.mvptemplete.ext.storage.PreferencesManager;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginModel {

    public final Activity activity;
    private final BaseAppNetwork baseAppNetwork;
    private final PreferencesManager preferencesManager;

    public LoginModel(Activity activity, BaseAppNetwork baseAppNetwork, PreferencesManager preferencesManager) {
        this.activity = activity;
        this.baseAppNetwork = baseAppNetwork;
        this.preferencesManager = preferencesManager;
    }

    public void startSignUpActivity() {
        activity.startActivity(new Intent(activity.getApplicationContext(), SignUpActivity.class));
        activity.finish();
    }

    public Observable<Login> loginUser(LoginParams loginParams) {
        return baseAppNetwork.getAccessToken(
                loginParams.userEmail(),
                loginParams.password(),
                loginParams.grantType()
        );
    }

    public void startDashboard(Login login) {
        storeUser(login);
        MainActivity.startDashboardFromLogin(activity, login);
        activity.finish();
    }

    private void storeUser(Login login) {
        preferencesManager.save(Constants.StorageConstants.ACCESS_TOKEN, "Bearer " + login.accessToken());
        preferencesManager.save(Constants.StorageConstants.PROFILE_PIC, login.profilePicture());
        preferencesManager.save(Constants.StorageConstants.EMAIL, login.email());

    }
}
