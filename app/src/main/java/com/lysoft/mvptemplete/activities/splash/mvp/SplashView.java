package com.lysoft.mvptemplete.activities.splash.mvp;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.lysoft.mvptemplete.R;
import com.lysoft.mvptemplete.activities.login.LoginActivity;
import com.lysoft.mvptemplete.activities.main.MainActivity;
import com.lysoft.mvptemplete.activities.splash.UpdateDialog;


import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ViewConstructor")
public class SplashView extends FrameLayout implements UpdateDialog.onUpdateDialogAcceptButtonListener {

    @BindView(R.id.loadingProgress)
    ProgressBar loadingProgress;

    @BindView(R.id.parentLayout)
    RelativeLayout parentLayout;

    private AppCompatActivity context;


    UpdateDialog updateDialog;

    @BindView(R.id.logo)
    ImageView logo;


    public SplashView(@NonNull AppCompatActivity context) {
        super(context);
        this.context = context;
        inflate(context, R.layout.activity_splash, this);
        ButterKnife.bind(this);

//        setLayoutHeight(logo, getScreenHeight(context));


        logo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.translate));


    }


    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }

        return false;
    }

    public void setLoadingProgress(boolean shouldLoad) {
        if (shouldLoad) {
            loadingProgress.getIndeterminateDrawable().setColorFilter(0xFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);
            loadingProgress.setVisibility(VISIBLE);
        } else
            loadingProgress.setVisibility(GONE);
    }

    public void showDialog(boolean isForceUpdate) {

        if (updateDialog != null)
            updateDialog.dismiss();

        updateDialog = new UpdateDialog();
        updateDialog.setOnUpdateAcceptButtonListener(this, !isForceUpdate);
        updateDialog.show(context.getSupportFragmentManager(), UpdateDialog.class.getSimpleName());
    }

    @Override
    public void onAcceptButtonListener(Intent playStoreIntent) {
        try {
            context.startActivity(updateDialog.getPlayStoreLink());
        } catch (ActivityNotFoundException exception) {
            context.startActivity(updateDialog.getSupportLink());
        }
        updateDialog.dismiss();
    }

    @Override
    public void onCancelButtonClicked() {
        startLoginActivity();
        updateDialog.dismiss();
    }


    public void startLoginActivity() {
        LoginActivity.startLoginActivityFromSplash(context);
        context.finish();
    }

    public View getParentView() {
        return parentLayout.getRootView();
    }

    public static void setLayoutHeight(ImageView view, int splashHeight) {

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) (splashHeight * 0.6));
        view.setLayoutParams(params);

    }

    public static int getScreenHeight(AppCompatActivity context) {

        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;

    }
}
