package com.angrychimps.citizenvet.models_old;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@SuppressWarnings("WeakerAccess") @JsonObject
public class CompanyPostResponsePayload {

    @JsonField
    Company company;

    public CompanyPostResponsePayload() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}