package com.lysoft.mvptemplete.activities.splash.mvp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.widget.TextView;


import com.lysoft.mvptemplete.R;
import com.lysoft.mvptemplete.app.network.model.splash.VersionResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter {

    private SplashView splashView;
    private SplashModel splashModel;

    public SplashPresenter(SplashView splashView, SplashModel splashModel) {
        this.splashView = splashView;
        this.splashModel = splashModel;

    }

    public void onCreated() {
//        getRecentVersion();
    }

    @SuppressLint("CheckResult")
    public void delayOpeningDashboard() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> splashView.startLoginActivity(),
                        failure -> splashView.startLoginActivity());
    }

    private boolean connectGoogle() {
        try {
            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
            urlc.setRequestProperty("User-Agent", "Test");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(3000);
            urlc.connect();
            return (urlc.getResponseCode() == 200);
        } catch (IOException e) {
            return false;
        }
    }

    private Observable<Boolean> connectGoogleObservable() {
        return Observable.fromCallable(() -> connectGoogle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void getVersion() {
        //for testing here just navigate to login screen
        delayOpeningDashboard();
        //The actual implementation is something like below. Here application version is checked for update dialog
//        if (!splashView.isNetworkConnected()) {
//            delayOpeningDashboard();
//        } else {
//            connectGoogleObservable().subscribe(success -> {
//                if (success)
//                    getRecentVersion();
//                else
//                    delayOpeningDashboard();
//            }, failure -> {
//                delayOpeningDashboard();
//            });
//        }
    }


    public void getRecentVersion() {

        splashView.setLoadingProgress(true);
        splashModel.getRecentVersion()
                .doOnEach(__ -> splashView.setLoadingProgress(false))
                .subscribe(new DisposableObserver<VersionResponse>() {
                    @Override
                    public void onNext(VersionResponse versionResponse) {
                        if (versionResponse.isResult()) {

                            String version = versionResponse.getVersion();

                            if (Integer.parseInt(version) > splashModel.getCurrentAppCode()) {
                                splashView.showDialog(versionResponse.getForceUpdate());
                            } else {
                                splashView.startLoginActivity();
                            }

                        } else {
                            Snackbar snackbar = Snackbar.make(splashView.getParentView(), Html.fromHtml("<font color=\"#ffffff\">" + versionResponse.getMessage() + "</font>"), Snackbar.LENGTH_INDEFINITE)
                                    .setAction("Retry", view -> getRecentVersion());
                            snackbar.setActionTextColor(Color.WHITE);
                            TextView textView = snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            snackbar.getView().setBackgroundColor(ContextCompat.getColor(splashView.getParentView().getContext(), R.color.design_default_color_primary_dark));
                            snackbar.show();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (!splashView.isNetworkConnected()) {
                            delayOpeningDashboard();
                            return;
                        }

                        Snackbar snackbar = Snackbar.make(splashView.getParentView(), Html.fromHtml("<font color=\"#ffffff\">Please check your network connection</font>"), Snackbar.LENGTH_INDEFINITE)
                                .setAction("Retry", view -> getRecentVersion());
                        snackbar.setActionTextColor(Color.WHITE);
                        TextView textView = snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        snackbar.getView().setBackgroundColor(ContextCompat.getColor(splashView.getParentView().getContext(), R.color.design_default_color_primary_dark));
                        snackbar.show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onResume() {

        getRecentVersion();

    }


}
