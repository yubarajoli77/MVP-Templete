package com.lysoft.mvptemplete.activities.main.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.lysoft.mvptemplete.R;

import butterknife.ButterKnife;

public class MainView extends FrameLayout {

    private AppCompatActivity activity;

    public MainView(AppCompatActivity activity) {
        super(activity);
        inflate(getContext(), R.layout.activity_main,this);
        ButterKnife.bind(this);
        this.activity = activity;

    }
}
