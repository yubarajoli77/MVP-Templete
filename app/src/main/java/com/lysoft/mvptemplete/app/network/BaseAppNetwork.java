package com.lysoft.mvptemplete.app.network;


import com.lysoft.mvptemplete.app.network.model.login.Login;
import com.lysoft.mvptemplete.app.network.model.signup.SignUp;
import com.lysoft.mvptemplete.app.network.model.splash.VersionResponse;
import com.lysoft.mvptemplete.app.network.model.updateuser.PhotoUpdateResponse;
import com.lysoft.mvptemplete.ext.Constants;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface BaseAppNetwork {


    @GET(Constants.ServerConstants.APP_VERSION)
    Observable<VersionResponse> getVersion();

    @FormUrlEncoded
    @POST(Constants.ServerConstants.LOGIN)
    Observable<Login> getAccessToken(
            @Field("email") String username,
            @Field("password") String password,
            @Field("grant_type") String grantType);


    @Multipart
    @POST(Constants.ServerConstants.CHANGE_PROFILE_PIC)
    Call<PhotoUpdateResponse> postImage(
            @Header("Authorization") String authToken,
            @Part MultipartBody.Part image,
            @Part("image") okhttp3.RequestBody name
    );


    @GET(Constants.ServerConstants.FORGOT_PASSWORD)
    Observable<Boolean> getForgotPassword(@Query("email") String email);


    @FormUrlEncoded
    @POST(Constants.ServerConstants.REGISTER)
    Observable<SignUp> getSignUp(@Field("Email") String email,
                                 @Field("Password") String password);

    @GET(Constants.ServerConstants.DELETE_PROFILE_PIC)
    Observable<String> getDeleteProfilePicObservable(@Header("Authorization") String authToken);

}
