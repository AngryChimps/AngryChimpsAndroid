package com.angrychimps.appname;

public class CompanyListing {
    private final String mCompanyId;
    private String mCompanyAdPhotoMain;
    private final String mCompanyAdTitle;
    private final String mCompanyAdServiceName;
    private final String mCompanyDescription;
    private final String mCompanyAdTimeSlotId;
    private final String mCompanyAdCategory;
    private String mCompanyAdPhotoAlbumId;
    private double mCompanyDistance;
    private final double mCompanyAdDiscountedPrice;
    private final double mCompanyAdOriginalPrice;
    private float mCompanyRating;
    private int mNumberOfReviews;
    private final int mCompanyAdMinutesRequiredForService;
    private final int mCompanyAdMinutesNoticeRequired;

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


}