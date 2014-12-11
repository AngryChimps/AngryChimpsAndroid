package com.angrychimps.appname;

import android.graphics.Bitmap;

public class AdFlowCompanyListing {
    private Bitmap imageCompanyMain;
    private String companyTitle;
    private String companyAddress;
    private String companyTimeAvailable;

    public AdFlowCompanyListing(Bitmap imageCompanyMain, String companyTitle, String companyAddress, String companyTimeAvailable) {
        this.imageCompanyMain = imageCompanyMain;
        this.companyTitle = companyTitle;
        this.companyAddress = companyAddress;
        this.companyTimeAvailable = companyTimeAvailable;
    }

    public Bitmap getImageCompanyMain() {
        return imageCompanyMain;
    }

    public void setImageCompanyMain(Bitmap imageCompanyMain) {
        this.imageCompanyMain = imageCompanyMain;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyTimeAvailable() {
        return companyTimeAvailable;
    }

    public void setCompanyTimeAvailable(String companyTimeAvailable) {
        this.companyTimeAvailable = companyTimeAvailable;
    }


}
