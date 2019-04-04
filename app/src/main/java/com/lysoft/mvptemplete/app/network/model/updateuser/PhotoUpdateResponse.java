package com.lysoft.mvptemplete.app.network.model.updateuser;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoUpdateResponse {

    @SerializedName("Photo")
    @Expose
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}