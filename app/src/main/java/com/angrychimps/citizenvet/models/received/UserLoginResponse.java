package com.angrychimps.citizenvet.models.received;

import android.os.Parcelable;

import com.angrychimps.citizenvet.models.shared.Company;
import com.angrychimps.citizenvet.models.shared.Member;

import java.util.List;

import auto.parcelgson.AutoParcelGson;

/*
    Used in Auth API, received after login
 */
@AutoParcelGson
public abstract class UserLoginResponse implements Parcelable {
    public abstract Member member();
    public abstract List<Company> companies();

    UserLoginResponse() {
    }

    public static UserLoginResponse create(Member member, List<Company> companies){
        return new AutoParcelGson_UserLoginResponse(member, companies);
    }
}
