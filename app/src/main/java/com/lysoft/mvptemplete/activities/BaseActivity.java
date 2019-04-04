package com.lysoft.mvptemplete.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lysoft.mvptemplete.app.BaseApp;
import com.lysoft.mvptemplete.app.di.component.ActivityComponent;
import com.lysoft.mvptemplete.app.di.component.DaggerActivityComponent;
import com.lysoft.mvptemplete.app.di.module.ActivityModule;
import com.lysoft.mvptemplete.ext.Constants;
import com.lysoft.mvptemplete.utils.CustomLoading;

import static android.text.TextUtils.isEmpty;


public abstract class BaseActivity extends AppCompatActivity {
    public ActivityComponent activityComponent;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(BaseApp.get(this).appComponent())
                .activityModule(new ActivityModule(this))
                .build();


    }


    @Override
    public void onBackPressed() {
        if (!isEmpty(Constants.StorageConstants.NAME)) {
            super.onBackPressed();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click once again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        }
    }

}
