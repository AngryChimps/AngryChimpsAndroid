package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.Service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface ServiceAPI {
    @GET("/service") void get(Callback<List<Service>> callback);
}
