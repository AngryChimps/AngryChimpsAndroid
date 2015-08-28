package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecServiceAPI;

import retrofit.http.GET;
import rx.Observable;

public interface ServiceAPI {
    @GET("/service") Observable<RecServiceAPI> getServices();
}
