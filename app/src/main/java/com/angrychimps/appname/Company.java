package com.angrychimps.appname;

class Company {

    private String mCompanyId;
    private final String mCompanyName;
    private final String mCompanyAddressId;
    private final String mCompanyPhoneLandLine;
    private final String mCompanyPhoneMobile;
    private final String mCompanyEmail;
    private final String mCompanyPassword;
    private final String mCompanyUserBirthDate;
    private final String mCompanyPhotoAlbumId;
    private final String mCompanyPhotoMain;
    private final boolean mCompanyComesToCustomer;

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


}
