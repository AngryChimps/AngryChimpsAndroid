package com.angrychimps.appname;

import android.graphics.Bitmap;

public class AdFlowCompanyListing {
    private Bitmap mImageCompanyMain;
    private String mCompanyTitle, mCompanyAddress, mCompanyTimeAvailable;

    public AdFlowCompanyListing(Bitmap imageCompanyMain, String companyTitle, String companyAddress, String companyTimeAvailable) {
        this.mImageCompanyMain = imageCompanyMain;
        this.mCompanyTitle = companyTitle;
        this.mCompanyAddress = companyAddress;
        this.mCompanyTimeAvailable = companyTimeAvailable;
    }

    public Bitmap getImageCompanyMain() {
        return mImageCompanyMain;
    }

    public void setImageCompanyMain(Bitmap imageCompanyMain) {
        this.mImageCompanyMain = imageCompanyMain;
    }

    public String getCompanyTitle() {
        return mCompanyTitle;
    }

    public void setCompanyTitle(String mCompanyTitle) {
        this.mCompanyTitle = mCompanyTitle;
    }

    public String getCompanyAddress() {
        return mCompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.mCompanyAddress = companyAddress;
    }

    public String getCompanyTimeAvailable() {
        return mCompanyTimeAvailable;
    }

    public void setCompanyTimeAvailable(String companyTimeAvailable) {
        this.mCompanyTimeAvailable = companyTimeAvailable;
    }


}
