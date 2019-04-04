package com.lysoft.mvptemplete.app.network.model.login;


import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class LoginParams implements Parcelable {

    public static TypeAdapter<LoginParams> typeAdapter(Gson gson)
    {
        return new AutoValue_LoginParams.GsonTypeAdapter(gson);
    }


    @SerializedName("email")
    public abstract String userEmail();

    @SerializedName("password")
    public abstract String password();

    @SerializedName("grant_type")
    public abstract String grantType();


    @AutoValue.Builder
    public abstract static class Builder{

        public abstract Builder userEmail(String username);

        public abstract Builder password(String password);

        public abstract Builder grantType(String grantType);

        public abstract LoginParams build();
    }

    public static Builder builder()
    {
        return new AutoValue_LoginParams.Builder();
    }
}
