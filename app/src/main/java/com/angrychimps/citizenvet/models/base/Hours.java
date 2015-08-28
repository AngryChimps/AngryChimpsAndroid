package com.angrychimps.citizenvet.models.base;

import android.os.Parcelable;

import java.util.Date;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class Hours implements Parcelable {
    public abstract Day sunday();
    public abstract Day monday();
    public abstract Day tuesday();
    public abstract Day wednesday();
    public abstract Day thursday();
    public abstract Day friday();
    public abstract Day saturday();

    Hours() {
    }

    public static Hours create(Day sunday, Day monday, Day tuesday, Day wednesday, Day thursday, Day friday, Day saturday){
        return new AutoParcelGson_Hours(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
    }

    @AutoParcelGson
    public abstract static class Day {
        public abstract Date start();
        public abstract Date end();

        Day() {
        }

        public static Day create(Date start, Date end){
            return new AutoParcelGson_Hours_Day(start, end);
        }
    }
}
