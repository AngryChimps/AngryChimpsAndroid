package com.angrychimps.citizenvet.models.received;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/*
    Used in Message API, received from GET
    Used in Messages API, received after POST
 */
public class MessageDetail {
    @SerializedName("message_id") private String messageId;
    @SerializedName("location_id") private String locationId;
    @SerializedName("consumer_message_id") private String consumerMessageId;
    @SerializedName("replied_message_id") private String repliedMessageId;
    @SerializedName("sender_name") private String senderName;
    private String body;
    private String status;
    @SerializedName("created_at") private Date createdAt;

    public MessageDetail() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getConsumerMessageId() {
        return consumerMessageId;
    }

    public void setConsumerMessageId(String consumerMessageId) {
        this.consumerMessageId = consumerMessageId;
    }

    public String getRepliedMessageId() {
        return repliedMessageId;
    }

    public void setRepliedMessageId(String repliedMessageId) {
        this.repliedMessageId = repliedMessageId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
