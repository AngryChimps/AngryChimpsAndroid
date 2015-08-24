package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.Service;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface ServiceAPI {
    @GET("/service") Observable<List<Service>> getServices();
}
