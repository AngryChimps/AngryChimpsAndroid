package com.angrychimps.citizenvet.models.send;

/*
    Used in Message API, sent from PATCH to change message status
 */
public class MessageStatus {
    private String messageId;
    private String status;

    public MessageStatus() {
    }

    public MessageStatus(String messageId, String status) {
        this.messageId = messageId;
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
