package com.lysoft.mvptemplete.activities.signUp.mvp;

import com.lysoft.mvptemplete.app.network.BaseAppNetwork;
import com.lysoft.mvptemplete.app.network.model.signup.SignUp;
import com.lysoft.mvptemplete.app.network.model.signup.SignUpParams;
import com.lysoft.mvptemplete.ext.storage.PreferencesManager;

import io.reactivex.Observable;

public class SignUpModel {
    private BaseAppNetwork baseAppNetwork;
    private PreferencesManager preferencesManager;

    public SignUpModel(BaseAppNetwork baseAppNetwork, PreferencesManager preferencesManager) {
        this.baseAppNetwork = baseAppNetwork;
        this.preferencesManager = preferencesManager;
    }

    public Observable<SignUp> singUpUser(SignUpParams signUpParams) {
        return baseAppNetwork.getSignUp(
                signUpParams.email(),
                signUpParams.password());
    }

    public void save(String key, String value) {
        preferencesManager.save(key, value);
    }

}
