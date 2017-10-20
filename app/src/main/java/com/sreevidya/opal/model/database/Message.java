package com.sreevidya.opal.model.database;


import java.util.Calendar;

public class Message {

    private String messageId;
    private String text;
    private String name;
    private String photoUrl;
    private String created;

    Message() {
    }

    Message(String messageId, String text, String name, String photoUrl) {
        this.messageId = messageId;
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.created = Calendar.getInstance().getTime().toString();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
