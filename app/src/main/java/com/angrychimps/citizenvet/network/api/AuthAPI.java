package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecAuthAPIlogin;
import com.angrychimps.citizenvet.models.recieved.RecAuthAPIreset;
import com.angrychimps.citizenvet.models.send.SendAuthAPIlogin;
import com.angrychimps.citizenvet.models.send.SendAuthAPIreset;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

public interface AuthAPI {
    @POST("/auth/login") Observable<RecAuthAPIlogin> login(@Body SendAuthAPIlogin userLogin);

    @GET("/auth/logout") Observable<Response> logout();

    @POST("/auth/reset-password-start") Observable<RecAuthAPIreset> resetPasswordStart(@Body SendAuthAPIreset userLoginReset);

    @POST("/auth/reset-password-finish") Observable<Response> resetPasswordFinish(@Body SendAuthAPIreset userLoginReset);
}
