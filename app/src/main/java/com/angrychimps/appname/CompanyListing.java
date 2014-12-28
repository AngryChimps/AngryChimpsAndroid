package com.angrychimps.appname;

public class CompanyListing {
    private String mCompanyId, mCompanyAdPhotoMain, mCompanyAdTitle, mCompanyAdServiceName, mCompanyDescription, mCompanyAdTimeSlotId, mCompanyAdCategory, mCompanyAdPhotoAlbumId;
    private double mCompanyDistance, mCompanyAdDiscountedPrice, mCompanyAdOriginalPrice;
    private float mCompanyRating;
    private int mNumberOfReviews, mCompanyAdMinutesRequiredForService, mCompanyAdMinutesNoticeRequired;

    public CompanyListing(String companyId, String companyAdTitle, String companyAdServiceName, String companyDescription, String companyAdTimeSlotId, String companyAdCategory, double companyAdDiscountedPrice, double companyAdOriginalPrice, int companyAdMinutesRequiredForService, int companyAdMinutesNoticeRequired) {
        mCompanyId = companyId;
        mCompanyAdTitle = companyAdTitle;
        mCompanyAdServiceName = companyAdServiceName;
        mCompanyDescription = companyDescription;
        mCompanyAdTimeSlotId = companyAdTimeSlotId;
        mCompanyAdCategory = companyAdCategory;
        mCompanyAdDiscountedPrice = companyAdDiscountedPrice;
        mCompanyAdOriginalPrice = companyAdOriginalPrice;
        mCompanyAdMinutesRequiredForService = companyAdMinutesRequiredForService;
        mCompanyAdMinutesNoticeRequired = companyAdMinutesNoticeRequired;
    }

    public String getCompanyId() {
        return mCompanyId;
    }

    public void setCompanyId(String mCompanyId) {
        mCompanyId = mCompanyId;
    }

    public String getCompanyAdPhotoMain() {
        return mCompanyAdPhotoMain;
    }

    public void setCompanyAdPhotoMain(String mCompanyAdPhotoMain) {
        mCompanyAdPhotoMain = mCompanyAdPhotoMain;
    }

    public String getCompanyAdTitle() {
        return mCompanyAdTitle;
    }

    public void setCompanyAdTitle(String mCompanyAdTitle) {
        mCompanyAdTitle = mCompanyAdTitle;
    }

    public String getCompanyAdServiceName() {
        return mCompanyAdServiceName;
    }

    public void setCompanyAdServiceName(String mCompanyAdServiceName) {
        mCompanyAdServiceName = mCompanyAdServiceName;
    }

    public String getCompanyDescription() {
        return mCompanyDescription;
    }

    public void setCompanyDescription(String mCompanyDescription) {
        mCompanyDescription = mCompanyDescription;
    }

    public String getCompanyAdTimeSlotId() {
        return mCompanyAdTimeSlotId;
    }

    public void setCompanyAdTimeSlotId(String mCompanyAdTimeSlotId) {
        mCompanyAdTimeSlotId = mCompanyAdTimeSlotId;
    }

    public String getCompanyAdCategory() {
        return mCompanyAdCategory;
    }

    public void setCompanyAdCategory(String mCompanyAdCategory) {
        mCompanyAdCategory = mCompanyAdCategory;
    }

    public String getCompanyAdPhotoAlbumId() {
        return mCompanyAdPhotoAlbumId;
    }

    public void setCompanyAdPhotoAlbumId(String mCompanyAdPhotoAlbumId) {
        mCompanyAdPhotoAlbumId = mCompanyAdPhotoAlbumId;
    }

    public double getCompanyDistance() {
        return mCompanyDistance;
    }

    public void setCompanyDistance(double mCompanyDistance) {
        mCompanyDistance = mCompanyDistance;
    }

    public double getCompanyAdDiscountedPrice() {
        return mCompanyAdDiscountedPrice;
    }

    public void setCompanyAdDiscountedPrice(double mCompanyAdDiscountedPrice) {
        mCompanyAdDiscountedPrice = mCompanyAdDiscountedPrice;
    }

    public double getCompanyAdOriginalPrice() {
        return mCompanyAdOriginalPrice;
    }

    public void setCompanyAdOriginalPrice(double mCompanyAdOriginalPrice) {
        mCompanyAdOriginalPrice = mCompanyAdOriginalPrice;
    }

    public float getCompanyRating() {
        return mCompanyRating;
    }

    public void setCompanyRating(float mCompanyRating) {
        mCompanyRating = mCompanyRating;
    }

    public int getNumberOfReviews() {
        return mNumberOfReviews;
    }

    public void setNumberOfReviews(int mNumberOfReviews) {
        mNumberOfReviews = mNumberOfReviews;
    }

    public int getCompanyAdDiscountPercent() {
        return (int) (mCompanyAdDiscountedPrice/mCompanyAdOriginalPrice);
    }

    public int getCompanyAdMinutesRequiredForService() {
        return mCompanyAdMinutesRequiredForService;
    }

    public void setCompanyAdMinutesRequiredForService(int mCompanyAdMinutesRequiredForService) {
        mCompanyAdMinutesRequiredForService = mCompanyAdMinutesRequiredForService;
    }

    public int getCompanyAdMinutesNoticeRequired() {
        return mCompanyAdMinutesNoticeRequired;
    }

    public void setCompanyAdMinutesNoticeRequired(int mCompanyAdMinutesNoticeRequired) {
        mCompanyAdMinutesNoticeRequired = mCompanyAdMinutesNoticeRequired;
    }
}