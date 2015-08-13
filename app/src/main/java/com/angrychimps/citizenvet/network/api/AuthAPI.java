package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.receive.UserLoginResponse;
import com.angrychimps.citizenvet.models.send.UserLogin;
import com.angrychimps.citizenvet.models.shared.UserLoginReset;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface AuthAPI {
    @POST("/auth/login") void login(@Body UserLogin userLogin, Callback<UserLoginResponse> callback);

    @GET("/auth/logout") void logout(Callback<Response> callback);

    @POST("/auth/reset-password-start") void resetPasswordStart(@Body UserLoginReset userLoginReset, Callback<UserLoginReset> callback);

    @POST("/auth/reset-password-finish") void resetPasswordFinish(@Body UserLoginReset userLoginReset, Callback<Response> callback);
}
