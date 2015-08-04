package com.angrychimps.citizenvet.models_old;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class Member {

    @JsonField
    private String id;

    @JsonField
    private String name;

    @JsonField
    private String photo;

    @JsonField
    private String email;

    @JsonField
    private String company_ids;

    public Member() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany_ids() {
        return company_ids;
    }

    public void setCompany_ids(String company_ids) {
        this.company_ids = company_ids;
    }
}
