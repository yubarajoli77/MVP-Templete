package com.lysoft.mvptemplete.activities.splash;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.lysoft.mvptemplete.R;


public class UpdateDialog extends DialogFragment {

    public interface onUpdateDialogAcceptButtonListener {
        void onAcceptButtonListener(Intent playStoreIntent);

        void onCancelButtonClicked();
    }

    public onUpdateDialogAcceptButtonListener listener;

    public boolean isCancelable;

    public void setOnUpdateAcceptButtonListener(onUpdateDialogAcceptButtonListener listener, boolean isCancelable) {
        this.listener = listener;
        this.isCancelable = isCancelable;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View dialogContent = layoutInflater.inflate(R.layout.dialog_update_version, null);
        setCancelable(false);

        builder.setView(dialogContent);
        builder.setPositiveButton("Update", (dialog, which) -> {

            if (listener != null) {
                listener.onAcceptButtonListener(getPlayStoreLink());
            }

        });

        if (isCancelable) {
            builder.setNegativeButton("Cancel", ((dialogInterface, i) -> {
                if (listener != null) {
                    listener.onCancelButtonClicked();
                }
            }));
        }
        return builder.create();

    }

    public Intent getPlayStoreLink() {
        Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        return goToMarket;

    }

    public Intent getSupportLink() {
        return new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName()));
    }


}
