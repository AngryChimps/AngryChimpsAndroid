package com.angrychimps.appname;

public class Customer {

    private String mCustomerId, mCustomerName, mCustomerPhotoMain, mCustomerPhotoAlbumId, mCustomerAddressId, mCustomerPhone, mCustomerEmail, mCustomerPassword, mCustomerBirthdate;

    public Customer(String customerName, String customerAddressId, String customerPhone, String customerEmail, String customerPassword, String customerBirthdate) {
        mCustomerName = customerName;
        mCustomerAddressId = customerAddressId;
        mCustomerPhone = customerPhone;
        mCustomerEmail = customerEmail;
        mCustomerPassword = customerPassword;
        mCustomerBirthdate = customerBirthdate;
        //set up customerId
    }

    public String getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(String customerId) {
        mCustomerId = customerId;
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        mCustomerName = customerName;
    }

    public String getCustomerPhotoMain() {
        return mCustomerPhotoMain;
    }

    public void setCustomerPhotoMain(String customerPhotoMain) {
        mCustomerPhotoMain = customerPhotoMain;
    }

    public String getCustomerPhotoAlbumId() {
        return mCustomerPhotoAlbumId;
    }

    public void setCustomerPhotoAlbumId(String customerPhotoAlbumId) {
        mCustomerPhotoAlbumId = customerPhotoAlbumId;
    }

    public String getCustomerAddressId() {
        return mCustomerAddressId;
    }

    public void setCustomerAddressId(String customerAddressId) {
        mCustomerAddressId = customerAddressId;
    }

    public String getCustomerPhone() {
        return mCustomerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        mCustomerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return mCustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        mCustomerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return mCustomerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        mCustomerPassword = customerPassword;
    }

    public String getCustomerBirthdate() {
        return mCustomerBirthdate;
    }

    public void setCustomerBirthdate(String customerBirthdate) {
        mCustomerBirthdate = customerBirthdate;
    }
}
