package com.angrychimps.citizenvet.models.recieved;

import android.util.SparseArray;

import java.util.List;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecAnimalAPI {
    abstract PayloadAnimals payload();

    public SparseArray<String> animalMap(){
        int size = payload().animals().size();
        SparseArray<String> animalMap = new SparseArray<>(size);
        for(int i = 0; i < size; i++){
            PayloadAnimals.Animal animal = payload().animals().get(i);
            animalMap.put(animal.id(), animal.name());
        }
        return animalMap;
    }

    @AutoParcelGson
    static abstract class PayloadAnimals{
        abstract List<Animal> animals();

        @AutoParcelGson
        static abstract class Animal {
            abstract int id();
            abstract String name();
        }
    }
}
