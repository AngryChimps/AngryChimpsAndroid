package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@SuppressWarnings("WeakerAccess") @JsonObject
public class SignupRegisterProviderAdPostResponsePayload {
    
    @JsonField
    MemberId member;

    @JsonField
    CompanyId company;

    @JsonField
    Provider_ad provider;

    @JsonField
    CalendarId calendar;

    public SignupRegisterProviderAdPostResponsePayload() {
    }

    public MemberId getMember() {
        return member;
    }

    public void setMember(MemberId member) {
        this.member = member;
    }

    public CompanyId getCompany() {
        return company;
    }

    public void setCompany(CompanyId company) {
        this.company = company;
    }

    public Provider_ad getProvider() {
        return provider;
    }

    public void setProvider(Provider_ad provider) {
        this.provider = provider;
    }

    public CalendarId getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarId calendar) {
        this.calendar = calendar;
    }
}
