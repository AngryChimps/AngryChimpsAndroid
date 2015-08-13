package com.angrychimps.citizenvet.models.send;

import com.google.gson.annotations.SerializedName;

/*
    Used in Messages API to request a list of messages
 */
public class MessagesRequest {
    @SerializedName("location_id") private String locationId;
    @SerializedName("member_id") private String memberId;
    @SerializedName("company_id") private String companyId;
    @SerializedName("include_archived") private boolean includeArchived;
    private int limit;
    private int offset;

    public MessagesRequest() {
    }

    //Convenience method to get messages for a member
    public MessagesRequest member(String memberId, boolean includeArchived, int limit, int offset){
        this.memberId = memberId;
        this.includeArchived = includeArchived;
        this.limit = limit;
        this.offset = offset;
        return MessagesRequest.this;
    }

    //Convenience method to get messages for a company
    public MessagesRequest company(String companyId, boolean includeArchived, int limit, int offset){
        this.companyId = companyId;
        this.includeArchived = includeArchived;
        this.limit = limit;
        this.offset = offset;
        return MessagesRequest.this;
    }

    //Convenience method to get messages for a location
    public MessagesRequest location(String locationId, boolean includeArchived, int limit, int offset){
        this.locationId = locationId;
        this.includeArchived = includeArchived;
        this.limit = limit;
        this.offset = offset;
        return MessagesRequest.this;
    }

    //Convenience method to get messages between a location and a member
    public MessagesRequest between(String locationId, String memberId, int limit, int offset){
        this.locationId = locationId;
        this.memberId = memberId;
        this.limit = limit;
        this.offset = offset;
        return MessagesRequest.this;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public boolean isIncludeArchived() {
        return includeArchived;
    }

    public void setIncludeArchived(boolean includeArchived) {
        this.includeArchived = includeArchived;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
