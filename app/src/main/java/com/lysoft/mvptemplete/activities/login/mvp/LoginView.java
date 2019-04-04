package com.lysoft.mvptemplete.activities.login.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.lysoft.mvptemplete.R;
import com.lysoft.mvptemplete.activities.main.MainActivity;
import com.lysoft.mvptemplete.app.network.model.login.LoginParams;
import com.lysoft.mvptemplete.utils.CustomLoading;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class LoginView extends FrameLayout {

    @BindView(R.id.iv_llogo)
    ImageView ivLogo;

    @BindView(R.id.btn_left_login)
    Button btnOptionLogin;

    @BindView(R.id.btn_left_signup)
    Button btnOptionSignup;

    @BindView(R.id.iv_lfb_login_btn)
    ImageView ivFacebookLogin;

    @BindView(R.id.iv_lgoogle_btn)
    ImageView ivGoogleLogin;

    @BindView(R.id.et_lemail)
    EditText etEmail;

    @BindView(R.id.et_lpassword)
    EditText etPassword;

    @BindView(R.id.btn_llogin)
    Button btnLogin;

    @BindView(R.id.tv_lforget_pw)
    TextView tvForgetPw;

    private AppCompatActivity activity;
    private CustomLoading customLoading;

    public LoginView(@NonNull AppCompatActivity activity) {
        super(activity);
        inflate(getContext(), R.layout.activity_login, this);
        ButterKnife.bind(this);
        this.activity = activity;
        customLoading = new CustomLoading(activity);
    }

    public Observable<Object> observeOptSignupBtn(){
        return RxView.clicks(btnOptionSignup);
    }

    public Observable<Object> observeMainLoginBtn(){
        return RxView.clicks(btnLogin);
    }

    public Observable<Object> observeForgetPwText(){
        return RxView.clicks(tvForgetPw);
    }

    public String getEmail(){
        return etEmail.getText().toString().trim();
    }

    public String getPassword(){
        return etPassword.getText().toString().trim();
    }

    //For setting error in case data validation

    public void setEmailError(String errorMsg){
        etEmail.setError(errorMsg);
        etEmail.requestFocus();
    }

    public void setPasswordError(String errorMsg){
        etPassword.setError(errorMsg);
        etPassword.requestFocus();
    }

    public void showToast(String msg){
        Toast.makeText(activity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public LoginParams loginParams() {
        return LoginParams.builder()
                .userEmail(etEmail.getText().toString().trim())
                .password(etPassword.getText().toString().trim())
                .grantType("password").build();
    }


    public void startDashboardActivity() {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
    }

    public void showDialog(boolean show){
        if(show)
            customLoading.showDialog();
        else
            customLoading.dismissDialog();
    }
}
