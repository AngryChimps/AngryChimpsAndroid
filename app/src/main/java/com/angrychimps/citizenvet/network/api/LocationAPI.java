package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.CompanyLocationDetail;
import com.angrychimps.citizenvet.models.shared.CompanyLocation;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface LocationAPI {
    @GET("/location/{id}") Observable<CompanyLocationDetail> getCompanyLocation(@Path("id") String locationId);

    @POST("/location") Observable<CompanyLocation> postCompanyLocation(@Body CompanyLocation companyLocation);
}
