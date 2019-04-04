package com.lysoft.mvptemplete.app.network.model.error;



import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
/*
    Auto value helps us to access the values directly with object.property();
 */
@AutoValue
public abstract class ErrorResponse {

    @SerializedName("ErrorStatusCode")
    public abstract Integer getErrorStatusCode();

    @Nullable
    @SerializedName("Message")
    public abstract String getMessage();

    public static TypeAdapter<ErrorResponse> typeAdapter(Gson gson) {
        return new AutoValue_ErrorResponse.GsonTypeAdapter(gson);
    }

}