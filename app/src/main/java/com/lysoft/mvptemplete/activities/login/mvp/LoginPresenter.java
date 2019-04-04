package com.lysoft.mvptemplete.activities.login.mvp;

import com.lysoft.mvptemplete.app.network.model.login.Login;
import com.lysoft.mvptemplete.ext.Constants;
import com.lysoft.mvptemplete.utils.CustomLoading;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.text.TextUtils.isEmpty;

public class LoginPresenter {

    private LoginView loginView;
    private LoginModel loginModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public LoginPresenter(LoginView loginView, LoginModel loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
    }


    public void onCreate() {
        siginButtonClick();
        signUpTextClicked();
        forgotPasswordClicked();

//        loginView.backButton.setOnClickListener(view -> loginModel.starNowShowing());

    }

    private void siginButtonClick() {
        loginView.observeMainLoginBtn().subscribe(o -> {
            if (isEmpty(loginView.getEmail())) {
                loginView.setEmailError(Constants.EMAIL_REQUIRED);
            } else if (isEmpty(loginView.getPassword())) {
                loginView.setPasswordError(Constants.PASSWORD_REQUIRED);
            } else {
                loginApiCall();
            }

        });
    }

    private void forgotPasswordClicked() {
        loginView.observeForgetPwText().subscribe(__ -> {
            //Actually do show forget password dialog here
//            loginModel.startForgotPasswordActivity();

            //This is for test
            loginView.showToast("Forget Password clicked");
        });
    }

    public void onDestroy() {
        compositeDisposable.dispose();
    }

    private void loginApiCall() {
        //This is only for test
        loginView.showDialog(true);
        loginView.startDashboardActivity();

        //Implement your actual logic here like below example.
//        DisposableObserver<Login> disposableObserver = getLoginButtonObserver();
//        getLoginObservable().subscribe(disposableObserver);
//        compositeDisposable.add(disposableObserver);
    }

    private void signUpTextClicked() {
        loginView.observeOptSignupBtn()
                .subscribe(o -> loginModel.startSignUpActivity());
    }

    private Observable<Login> getLoginObservable() {
        return loginModel.loginUser(loginView.loginParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    private DisposableObserver<Login> getLoginButtonObserver() {
        return new DisposableObserver<Login>() {
            @Override
            public void onNext(Login login) {
                loginModel.startDashboard(login);
            }

            @Override
            public void onError(Throwable throwable) {
                siginButtonClick();
            }

            @Override
            public void onComplete() {
            }
        };
    }

}
