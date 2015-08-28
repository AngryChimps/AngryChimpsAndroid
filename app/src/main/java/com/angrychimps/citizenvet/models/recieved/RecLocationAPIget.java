package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Company;
import com.angrychimps.citizenvet.models.base.Location;
import com.angrychimps.citizenvet.models.base.Review;
import com.angrychimps.citizenvet.models.base.Staff;

import java.util.List;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecLocationAPIget {
    abstract PayloadLocation payload();

    public Location location(){
        return payload().location();
    }

    public Company company(){
        return payload().company();
    }

    public int staffCount(){
        return payload().staffList().count();
    }

    public List<Staff> staffList(){
        return payload().staffList().staff();
    }

    public int reviewCount(){
        return payload().reviews().count();
    }

    public List<Review> reviews(){
        return payload().reviews().reviews();
    }

    @AutoParcelGson
    static abstract class PayloadLocation{
        abstract Location location();
        abstract Company company();
        abstract RecStaffAPIgetAll.PayloadStaff staffList();
        abstract RecReviewAPIgetAll.PayloadReview reviews();
    }
}