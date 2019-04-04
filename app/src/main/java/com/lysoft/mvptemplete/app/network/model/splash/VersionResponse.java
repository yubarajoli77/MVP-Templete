package com.lysoft.mvptemplete.app.network.model.splash;

import com.google.gson.annotations.SerializedName;

public class VersionResponse {


    @SerializedName("Result")
    private boolean result;

    @SerializedName("Message")
    private String message;

    @SerializedName("Version")
    private String version;

    @SerializedName("ForceUpdate")
    private boolean forceUpdate;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }
}
