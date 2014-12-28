package com.angrychimps.appname;

public class CustomerListing {

    private String mCustomerId, mCustomerAdTitle, mCustomerAdDescription, mCustomerAdPhotoMain, mCustomerAddressId, mServiceCategory, mCustomerAdTimeSlotId, mCustomerAdPhotoAlbumId;
    private boolean mServiceProviderComesToMe;
    private int mCustomerCommunicationsPreference;

    public CustomerListing(String customerId, String customerAdTitle, String customerAdDescription, String serviceCategory, String customerAdTimeSlotId, boolean serviceProviderComesToMe) {
        mCustomerId = customerId;
        mCustomerAdTitle = customerAdTitle;
        mCustomerAdDescription = customerAdDescription;
        mServiceCategory = serviceCategory;
        mCustomerAdTimeSlotId = customerAdTimeSlotId;
        mServiceProviderComesToMe = serviceProviderComesToMe;
    }

    public String getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(String customerId) {
        mCustomerId = customerId;
    }

    public String getCustomerAdTitle() {
        return mCustomerAdTitle;
    }

    public void setCustomerAdTitle(String customerAdTitle) {
        mCustomerAdTitle = customerAdTitle;
    }

    public String getCustomerAdDescription() {
        return mCustomerAdDescription;
    }

    public void setCustomerAdDescription(String customerAdDescription) {
        mCustomerAdDescription = customerAdDescription;
    }

    public String getCustomerAdPhotoMain() {
        return mCustomerAdPhotoMain;
    }

    public void setCustomerAdPhotoMain(String customerAdPhotoMain) {
        mCustomerAdPhotoMain = customerAdPhotoMain;
    }

    public String getCustomerAddressId() {
        return mCustomerAddressId;
    }

    public void setCustomerAddressId(String customerAddressId) {
        mCustomerAddressId = customerAddressId;
    }

    public String getServiceCategory() {
        return mServiceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        mServiceCategory = serviceCategory;
    }

    public String getCustomerAdTimeSlotId() {
        return mCustomerAdTimeSlotId;
    }

    public void setCustomerAdTimeSlotId(String customerAdTimeSlotId) {
        mCustomerAdTimeSlotId = customerAdTimeSlotId;
    }

    public String getCustomerAdPhotoAlbumId() {
        return mCustomerAdPhotoAlbumId;
    }

    public void setCustomerAdPhotoAlbumId(String customerAdPhotoAlbumId) {
        mCustomerAdPhotoAlbumId = customerAdPhotoAlbumId;
    }

    public boolean isServiceProviderComesToMe() {
        return mServiceProviderComesToMe;
    }

    public void setServiceProviderComesToMe(boolean serviceProviderComesToMe) {
        mServiceProviderComesToMe = serviceProviderComesToMe;
    }

    public int getCustomerCommunicationsPreference() {
        return mCustomerCommunicationsPreference;
    }

    public void setCustomerCommunicationsPreference(int customerCommunicationsPreference) {
        mCustomerCommunicationsPreference = customerCommunicationsPreference;
    }
}
