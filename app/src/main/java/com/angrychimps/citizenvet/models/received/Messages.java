package com.angrychimps.citizenvet.models.received;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
    Used in Auth API, inside the response after login
    Used in Messages API, as a response from POST
 */
public class Messages {
    private int count;
    @SerializedName("count_unread") private int countUnread;
    @SerializedName("results") private List<MessageDetail> messages;

    public Messages() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountUnread() {
        return countUnread;
    }

    public void setCountUnread(int countUnread) {
        this.countUnread = countUnread;
    }

    public List<MessageDetail> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDetail> messages) {
        this.messages = messages;
    }
}
