package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@SuppressWarnings("WeakerAccess") @JsonObject
public class SignupRegisterProviderCompanyPostResponsePayload {
    
    @JsonField
    Provider_ad providerAdId;

    public SignupRegisterProviderCompanyPostResponsePayload() {
    }

    public Provider_ad getProviderAdId() {
        return providerAdId;
    }

    public void setProviderAdId(Provider_ad providerAdId) {
        this.providerAdId = providerAdId;
    }
}
