package com.lysoft.mvptemplete.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

public class CustomSnackBar {
    public static Snackbar snackbar;

    public static Snackbar showError(String message, int type, View parent) {
        snackbar = Snackbar.make(parent, message, type);
        snackbar.getView().setBackgroundColor(Color.parseColor("#2364AE"));
        return snackbar;
    }

    public static Snackbar getSnackbar() {
        return snackbar;
    }
}
