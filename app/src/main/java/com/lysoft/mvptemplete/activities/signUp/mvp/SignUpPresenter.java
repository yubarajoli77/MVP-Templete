package com.lysoft.mvptemplete.activities.signUp.mvp;

import android.support.annotation.MainThread;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;

import com.lysoft.mvptemplete.app.network.model.signup.SignUp;
import com.lysoft.mvptemplete.ext.Constants;
import com.lysoft.mvptemplete.utils.CustomSnackBar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SignUpPresenter {
    private SignUpView signUpView;
    private SignUpModel signUpModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SignUpPresenter(SignUpView signUpView, SignUpModel signUpModel) {
        this.signUpView = signUpView;
        this.signUpModel = signUpModel;
    }

    public void onCreate() {
        onRegisterBtnClicked();
        onOptLoginBtnClicked();
    }

    private void onRegisterBtnClicked() {
        signUpView.observeRegisterBtn()
                .subscribe(o -> {
                    if (TextUtils.isEmpty(signUpView.getEmail())) {
                        signUpView.setEmailError(Constants.EMAIL_REQUIRED);
                    } else if (TextUtils.isEmpty(signUpView.getPassword())) {
                        signUpView.setPasswordError(Constants.PASSWORD_REQUIRED);
                    } else if (TextUtils.isEmpty(signUpView.getComfirmPassword())) {
                        signUpView.setConfirmPwError(Constants.CONFIRM_PASSWORD_REQUIRED);
                    } else if (!signUpView.getPassword().equals(signUpView.getComfirmPassword())) {
                        signUpView.setConfirmPwError(Constants.CONFIRM_PASSWORD_NOT_MATCHED);
                    } else {
                        //Here we can call server for user register
//                        signupApiCall();

                        //But I just started the login activity here for test purpose
                        signUpView.startLoginActivity();

                    }
                });
    }

    private void onOptLoginBtnClicked() {
        signUpView.observeOptLoginBtn().subscribe(o -> signUpView.startLoginActivity());
    }

    private void signupApiCall() {
        DisposableObserver<SignUp> disposableObserver = getSignUpButtonObserver();
        signupObservable().subscribe(disposableObserver);
    }

    private DisposableObserver<SignUp> getSignUpButtonObserver() {
        return new DisposableObserver<SignUp>() {
            @Override
            public void onNext(SignUp signUp) {

                //Do your logic here after successful response from server. Here I save some details in shared prefereces.
                // Here you can verify the email

                signUpModel.save(Constants.PASSWORD, signUpView.getPassword());
                signUpModel.save(Constants.EMAIL, signUpView.getEmail());
                signUpView.startLoginActivity();
            }

            @Override
            public void onError(Throwable throwable) {

                CustomSnackBar.showError(throwable.getMessage(), Snackbar.LENGTH_SHORT, signUpView.getRootLayout()).show();

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private Observable<SignUp> signupObservable() {
        return signUpModel.singUpUser(signUpView.signUpParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void onDestroy() {
        compositeDisposable.dispose();
    }

    public void onStop() {
        compositeDisposable.dispose();
        if (CustomSnackBar.getSnackbar() != null)
            CustomSnackBar.getSnackbar().dismiss();
    }
}
