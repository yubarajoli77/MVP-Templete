package com.lysoft.mvptemplete.app.network.model.signup;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class SignUp implements Parcelable {

    @Nullable
    @SerializedName("UserRegistrationId")
    public abstract String userRegistrationId();

    @Nullable
    @SerializedName("Result")
    public abstract String result();

    @Nullable
    @SerializedName("VerificationCode")
    public abstract Integer verificationCode();

    @Nullable
    @SerializedName("Message")
    public abstract String message();

    public static TypeAdapter<SignUp> typeAdapter(Gson gson) {
        return new AutoValue_SignUp.GsonTypeAdapter(gson);
    }
}
