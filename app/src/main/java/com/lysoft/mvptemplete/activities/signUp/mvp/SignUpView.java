package com.lysoft.mvptemplete.activities.signUp.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jakewharton.rxbinding2.view.RxView;
import com.lysoft.mvptemplete.R;
import com.lysoft.mvptemplete.activities.login.LoginActivity;
import com.lysoft.mvptemplete.app.network.model.signup.SignUpParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class SignUpView extends FrameLayout {
    private AppCompatActivity activity;

    @BindView(R.id.btn_login)
    Button btnOptLogin;

    @BindView(R.id.btn_signup)
    Button btnOptSignUp;

    @BindView(R.id.btn_register)
    Button btnRegister;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.et_confirm_pw)
    EditText etConfirmPw;

    @BindView(R.id.iv_logo)
    ImageView ivLogo;

    @BindView(R.id.iv_fb_login)
    ImageView ivFbLogin;

    @BindView(R.id.iv_google_btn)
    ImageView ivGoogleLogin;

    @BindView(R.id.cl_signup_root_layout)
    ConstraintLayout clRootLayout;

    public SignUpView(@NonNull AppCompatActivity activity) {
        super(activity);
        inflate(getContext(), R.layout.activity_sign_up, this);
        ButterKnife.bind(this);
        this.activity = activity;
    }

    public Observable<Object> observeOptLoginBtn(){
        return RxView.clicks(btnOptLogin);
    }

    public Observable<Object> observeRegisterBtn(){
        return RxView.clicks(btnRegister);
    }

    public String getEmail(){
        return etEmail.getText().toString().trim();
    }

    public String getPassword(){
        return etPassword.getText().toString().trim();
    }

    public String getComfirmPassword(){
        return etConfirmPw.getText().toString().trim();
    }

    public void setEmailError(String error){
        etEmail.setError(error);
        etEmail.requestFocus();
    }

    public void setPasswordError(String error){
        etPassword.setError(error);
        etPassword.requestFocus();
    }

    public void setConfirmPwError(String error){
        etConfirmPw.setError(error);
        etConfirmPw.requestFocus();
    }

    public void startLoginActivity() {
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }

    public SignUpParams signUpParams() {
        return SignUpParams.builder()
                .email(etEmail.getText().toString().trim())
                .password(etPassword.getText().toString().trim())
                .build();
    }

    public View getRootLayout() {
        return clRootLayout;
    }
}
