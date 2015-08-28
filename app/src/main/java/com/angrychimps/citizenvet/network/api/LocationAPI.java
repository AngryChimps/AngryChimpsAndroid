package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.base.Location;
import com.angrychimps.citizenvet.models.recieved.RecLocationAPIget;
import com.angrychimps.citizenvet.models.recieved.RecLocationAPIpost;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface LocationAPI {
    @GET("/location/{id}") Observable<RecLocationAPIget> getCompanyLocation(@Path("id") String locationId);

    @POST("/location") Observable<RecLocationAPIpost> postCompanyLocation(@Body Location companyLocation);
}
