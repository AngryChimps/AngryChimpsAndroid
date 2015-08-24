package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.Animal;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface AnimalAPI {
    @GET("/animal") Observable<List<Animal>> getAnimals();
}
