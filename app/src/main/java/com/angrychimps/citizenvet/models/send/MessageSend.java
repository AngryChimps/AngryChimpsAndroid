package com.angrychimps.citizenvet.models.send;

/*
    Used in Message API, sent from POST
 */
public class MessageSend {
    private String locationId;
    private String sendingMemberId;
    private String repliedMemberId;
    private String body;

    public MessageSend() {
    }

    public MessageSend(String locationId, String sendingMemberId, String repliedMemberId, String body) {
        this.locationId = locationId;
        this.sendingMemberId = sendingMemberId;
        this.repliedMemberId = repliedMemberId;
        this.body = body;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getSendingMemberId() {
        return sendingMemberId;
    }

    public void setSendingMemberId(String sendingMemberId) {
        this.sendingMemberId = sendingMemberId;
    }

    public String getRepliedMemberId() {
        return repliedMemberId;
    }

    public void setRepliedMemberId(String repliedMemberId) {
        this.repliedMemberId = repliedMemberId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
