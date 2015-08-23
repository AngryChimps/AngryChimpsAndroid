package com.angrychimps.citizenvet.models.received;

import android.os.Parcelable;

import com.angrychimps.citizenvet.models.shared.Company;
import com.angrychimps.citizenvet.models.shared.CompanyLocation;
import com.angrychimps.citizenvet.models.shared.StaffMember;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Received by Location API from a GET request
 */
@AutoParcelGson
public abstract class CompanyLocationDetail implements Parcelable {
    public abstract CompanyLocation location();
    public abstract Company company();
    public abstract Staff staff();
    public abstract Reviews reviews();

    CompanyLocationDetail() {
    }

    public static CompanyLocationDetail create(CompanyLocation location, Company company, Staff staff, Reviews reviews){
        return new AutoParcelGson_CompanyLocationDetail(location, company, staff, reviews);
    }

    @AutoParcelGson
    public abstract static class Staff{
        public abstract int count();
        @SerializedName("results") public abstract List<StaffMember> staffMembers();

        Staff() {
        }

        public static Staff create(int count, List<StaffMember> staffMembers){
            return new AutoParcelGson_CompanyLocationDetail_Staff(count, staffMembers);
        }
    }

    @AutoParcelGson
    public abstract static class Reviews {
        public abstract int count();
        @SerializedName("results") public abstract List<ReviewDetail> reviews();

        Reviews() {
        }

        public static Reviews create(int count, List<ReviewDetail> reviews){
            return new AutoParcelGson_CompanyLocationDetail_Reviews(count, reviews);
        }
    }
}
