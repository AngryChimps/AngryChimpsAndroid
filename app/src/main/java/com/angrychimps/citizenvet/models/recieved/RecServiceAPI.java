package com.angrychimps.citizenvet.models.recieved;

import android.util.SparseArray;

import java.util.List;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecServiceAPI {
    abstract PayloadService payload();

    public SparseArray<String> servicesMap(){
        int size = payload().services().size();
        SparseArray<String> servicesMap = new SparseArray<>(size);
        for(int i = 0; i < size; i++){
            PayloadService.Service service = payload().services().get(i);
            servicesMap.put(service.id(), service.name());
        }
        return servicesMap;
    }

    @AutoParcelGson
    static abstract class PayloadService{
        abstract List<Service> services();

        @AutoParcelGson
        static abstract class Service {
            abstract int id();
            abstract String name();
        }
    }
}
