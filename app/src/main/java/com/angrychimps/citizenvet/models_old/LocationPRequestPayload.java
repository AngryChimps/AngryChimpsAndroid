package com.angrychimps.citizenvet.models_old;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class LocationPRequestPayload {

    @JsonField
    private String name;

    @JsonField
    private String street1;

    @JsonField
    private String street2;

    @JsonField
    private int zip;

    @JsonField
    private String company_id;

    @JsonField
    private String phone;

    @JsonField
    private boolean is_mobile;

    public LocationPRequestPayload() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getIs_mobile() {
        return is_mobile;
    }

    public void setIs_mobile(boolean is_mobile) {
        this.is_mobile = is_mobile;
    }
}
