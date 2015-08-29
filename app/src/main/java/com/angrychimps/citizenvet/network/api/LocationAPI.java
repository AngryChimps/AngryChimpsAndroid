package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecLocationAPIget;
import com.angrychimps.citizenvet.models.recieved.RecLocationAPIpost;
import com.angrychimps.citizenvet.models.send.SendLocationAPI;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface LocationAPI {
    @GET("/location/{id}") Observable<RecLocationAPIget> getCompanyLocation(@Path("id") String locationId);

    @POST("/location") Observable<RecLocationAPIpost> postCompanyLocation(@Body SendLocationAPI location);

    @PATCH("/location/{id}") Observable<RecLocationAPIpost> postCompanyLocation(@Path("id") String locationId, @Body SendLocationAPI location);
}
