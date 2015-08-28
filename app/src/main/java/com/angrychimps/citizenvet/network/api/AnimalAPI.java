package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecAnimalAPI;

import retrofit.http.GET;
import rx.Observable;

public interface AnimalAPI {
    @GET("/animal") Observable<RecAnimalAPI> getAnimals();
}
