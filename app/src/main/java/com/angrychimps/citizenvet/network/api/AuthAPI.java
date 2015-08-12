package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.Auth;
import com.angrychimps.citizenvet.models.Member;
import com.angrychimps.citizenvet.models.Password;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface AuthAPI {
    @POST("/auth/login") void login(@Body Auth auth, Callback<Member> callback); //This is incomplete- also contains companies

    @GET("/auth/logout") void logout(Callback callback);

    @POST("/auth/reset-password-start") void resetPasswordStart(@Body Password pw, Callback<Password> callback);

    @POST("/auth/reset-password-finish") void resetPasswordFinish(@Body Password pw, Callback callback);
}
