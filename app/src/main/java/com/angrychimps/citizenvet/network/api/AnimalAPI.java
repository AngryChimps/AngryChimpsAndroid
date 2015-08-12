package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.Animal;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface AnimalAPI {
    @GET("/animal") void get(Callback<List<Animal>> callback);
}
