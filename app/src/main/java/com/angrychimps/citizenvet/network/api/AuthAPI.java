package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.UserLoginResponse;
import com.angrychimps.citizenvet.models.send.UserLogin;
import com.angrychimps.citizenvet.models.shared.UserLoginReset;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

public interface AuthAPI {
    @POST("/auth/login") Observable<UserLoginResponse> login(@Body UserLogin userLogin);

    @GET("/auth/logout") Observable<Response> logout();

    @POST("/auth/reset-password-start") Observable<UserLoginReset> resetPasswordStart(@Body UserLoginReset userLoginReset);

    @POST("/auth/reset-password-finish") Observable<Response> resetPasswordFinish(@Body UserLoginReset userLoginReset);
}
