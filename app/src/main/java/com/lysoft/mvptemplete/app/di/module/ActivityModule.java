package com.lysoft.mvptemplete.app.di.module;

import android.support.v7.app.AppCompatActivity;

import com.lysoft.mvptemplete.activities.login.mvp.LoginModel;
import com.lysoft.mvptemplete.activities.login.mvp.LoginPresenter;
import com.lysoft.mvptemplete.activities.login.mvp.LoginView;
import com.lysoft.mvptemplete.activities.main.mvp.MainModel;
import com.lysoft.mvptemplete.activities.main.mvp.MainPresenter;
import com.lysoft.mvptemplete.activities.main.mvp.MainView;
import com.lysoft.mvptemplete.activities.signUp.mvp.SignUpModel;
import com.lysoft.mvptemplete.activities.signUp.mvp.SignUpPresenter;
import com.lysoft.mvptemplete.activities.signUp.mvp.SignUpView;
import com.lysoft.mvptemplete.activities.splash.mvp.SplashModel;
import com.lysoft.mvptemplete.activities.splash.mvp.SplashPresenter;
import com.lysoft.mvptemplete.activities.splash.mvp.SplashView;
import com.lysoft.mvptemplete.app.di.scope.ActivityScope;
import com.lysoft.mvptemplete.app.di.scope.AppScope;
import com.lysoft.mvptemplete.app.network.BaseAppNetwork;
import com.lysoft.mvptemplete.ext.storage.PreferencesManager;
import com.lysoft.mvptemplete.utils.CustomLoading;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final AppCompatActivity activity;


    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;

    }


    //login
    @ActivityScope
    @Provides
    public LoginView loginView() {
        return new LoginView(activity);
    }


    @ActivityScope
    @Provides
    public LoginModel loginModel(BaseAppNetwork baseAppNetwork, PreferencesManager preferencesManager) {
        return new LoginModel(activity, baseAppNetwork, preferencesManager);
    }


    @ActivityScope
    @Provides
    public LoginPresenter loginPresenter(LoginView loginView, LoginModel loginModel) {
        return new LoginPresenter(loginView, loginModel);
    }


    //Splash Screen
    @ActivityScope
    @Provides
    public SplashView splashView() {
        return new SplashView(activity);
    }

    @Provides
    public SplashModel splashModel(BaseAppNetwork baseAppNetwork) {
        return new SplashModel(baseAppNetwork);
    }

    @Provides
    public SplashPresenter splashPresenter(SplashView splashView, SplashModel spashModel) {
        return new SplashPresenter(splashView, spashModel);
    }

    //SignUp
    @ActivityScope
    @Provides
    public SignUpView signUpView(){
        return new SignUpView(activity);
    }

    @Provides
    public SignUpModel signUpModel(BaseAppNetwork baseAppNetwork, PreferencesManager preferencesManager){
        return new SignUpModel(baseAppNetwork, preferencesManager);
    }

    @Provides
    public SignUpPresenter signUpPresenter(SignUpView signUpView, SignUpModel signUpModel){
        return  new SignUpPresenter(signUpView, signUpModel);
    }

    //Main
    @ActivityScope
    @Provides
    public MainView mainView(){
        return new MainView(activity);
    }

    @Provides
    public MainModel mainModel(BaseAppNetwork baseAppNetwork){
        return new MainModel(baseAppNetwork);
    }

    @Provides
    public MainPresenter mainPresenter(MainModel mainModel, MainView mainView){
        return new MainPresenter(mainModel, mainView);
    }


}
