package com.lysoft.mvptemplete.utils;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lysoft.mvptemplete.R;


public class CustomLoading {
    private final Dialog dialog;

    public CustomLoading(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.loading_dialog, null);
        dialog = new Dialog(context, R.style.FullHeightDialog);
        dialog.setContentView(dialogView);
        dialog.setCancelable(true);
        ImageView imageView = dialog.findViewById(R.id.imageView);
        LinearLayout loaders = dialogView.findViewById(R.id.loaders);
        animateRotation(imageView);

    }


    private void animateRotation(View view) {
        ObjectAnimator animation;
        animation = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 360f);
        animation.setDuration(5000);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }

    public void showDialog() {
        if (dialog != null && !dialog.isShowing())
            dialog.show();
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

}
