package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@SuppressWarnings("WeakerAccess") @JsonObject
public class CategoriesGetResponsePayload {

    @JsonField
    Categories categories;

    public CategoriesGetResponsePayload() {
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}
