package com.lysoft.mvptemplete.app.network.model.signup;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.lysoft.mvptemplete.app.network.model.login.LoginParams;

@AutoValue
public abstract class SignUpParams implements Parcelable {

    public static TypeAdapter<SignUpParams> typeAdapter(Gson gson) {
        return new AutoValue_SignUpParams.GsonTypeAdapter(gson);
    }

    @SerializedName("Email")
    public abstract String email();

    @SerializedName("Password")
    public abstract String password();


    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder email(String email);

        public abstract Builder password(String password);

        public abstract SignUpParams build();

    }

    public static Builder builder() {
        return new AutoValue_SignUpParams.Builder();
    }

}
