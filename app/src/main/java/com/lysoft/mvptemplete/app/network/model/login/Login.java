package com.lysoft.mvptemplete.app.network.model.login;


import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class
Login implements Parcelable {


    public static TypeAdapter<Login> typeAdapter(Gson gson) {
        return new AutoValue_Login.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("access_token")
    public abstract String accessToken();

    @Nullable
    @SerializedName("expires_in")
    public abstract Integer expiresIn();

    @Nullable
    @SerializedName("user_id")
    public abstract String userId();


    @Nullable
    @SerializedName("email")
    public abstract String email();


    @Nullable
    @SerializedName("profilePicture")
    public abstract String profilePicture();


}
