package com.lysoft.mvptemplete.app.di.component;

import com.lysoft.mvptemplete.activities.login.LoginActivity;
import com.lysoft.mvptemplete.activities.main.MainActivity;
import com.lysoft.mvptemplete.activities.signUp.SignUpActivity;
import com.lysoft.mvptemplete.activities.splash.SplashAcitvity;
import com.lysoft.mvptemplete.app.di.module.ActivityModule;
import com.lysoft.mvptemplete.app.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);
    void inject(SignUpActivity signUpActivity);
    void inject(SplashAcitvity splashAcitvity);
    void inject(MainActivity mainActivity);
}
