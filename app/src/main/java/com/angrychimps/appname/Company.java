package com.angrychimps.appname;

public class Company {

    private String mCompanyId, mCompanyName, mCompanyAddressId, mCompanyPhoneLandLine, mCompanyPhoneMobile, mCompanyEmail, mCompanyPassword, mCompanyUserBirthDate, mCompanyPhotoAlbumId, mCompanyPhotoMain;
    private boolean mCompanyComesToCustomer;

    public Company(String companyName, String companyAddressId, String companyPhoneLandLine, String companyPhoneMobile, String companyEmail, String companyPassword, String companyUserBirthDate, String companyPhotoAlbumId, String companyPhotoMain, boolean companyComesToCustomer) {
        mCompanyName = companyName;
        mCompanyAddressId = companyAddressId;
        mCompanyPhoneLandLine = companyPhoneLandLine;
        mCompanyPhoneMobile = companyPhoneMobile;
        mCompanyEmail = companyEmail;
        mCompanyPassword = companyPassword;
        mCompanyUserBirthDate = companyUserBirthDate;
        mCompanyPhotoAlbumId = companyPhotoAlbumId;
        mCompanyPhotoMain = companyPhotoMain;
        mCompanyComesToCustomer = companyComesToCustomer;
        //set up companyId
    }

    public String getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(String mCompanyId) {
        mCompanyId = mCompanyId;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String mCompanyName) {
        mCompanyName = mCompanyName;
    }

    public String getCompanyAddressId() {
        return mCompanyAddressId;
    }

    public void setCompanyAddressId(String mCompanyAddressId) {
        mCompanyAddressId = mCompanyAddressId;
    }

    public String getCompanyPhoneLandLine() {
        return mCompanyPhoneLandLine;
    }

    public void setCompanyPhoneLandLine(String mCompanyPhoneLandLine) {
        mCompanyPhoneLandLine = mCompanyPhoneLandLine;
    }

    public String getCompanyPhoneMobile() {
        return mCompanyPhoneMobile;
    }

    public void setCompanyPhoneMobile(String mCompanyPhoneMobile) {
        mCompanyPhoneMobile = mCompanyPhoneMobile;
    }

    public String getCompanyEmail() {
        return mCompanyEmail;
    }

    public void setCompanyEmail(String mCompanyEmail) {
        mCompanyEmail = mCompanyEmail;
    }

    public String getCompanyPassword() {
        return mCompanyPassword;
    }

    public void setCompanyPassword(String mCompanyPassword) {
        mCompanyPassword = mCompanyPassword;
    }

    public String getCompanyUserBirthDate() {
        return mCompanyUserBirthDate;
    }

    public void setCompanyUserBirthDate(String mCompanyUserBirthDate) {
        mCompanyUserBirthDate = mCompanyUserBirthDate;
    }

    public String getCompanyPhotoAlbumId() {
        return mCompanyPhotoAlbumId;
    }

    public void setCompanyPhotoAlbumId(String mCompanyPhotoAlbumId) {
        mCompanyPhotoAlbumId = mCompanyPhotoAlbumId;
    }

    public String getCompanyPhotoMain() {
        return mCompanyPhotoMain;
    }

    public void setCompanyPhotoMain(String mCompanyPhotoMain) {
        mCompanyPhotoMain = mCompanyPhotoMain;
    }

    public boolean doesCompanyComeToCustomer() {
        return mCompanyComesToCustomer;
    }

    public void setCompanyComesToCustomer(boolean mCompanyComesToCustomer) {
        mCompanyComesToCustomer = mCompanyComesToCustomer;
    }
}
