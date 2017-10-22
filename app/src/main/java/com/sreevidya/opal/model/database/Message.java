package com.sreevidya.opal.model.database;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Message {

    private String text;
    private String photoUrl;
    private String created;
    private boolean isUser;

    Message() {

    }

    public Message(String text, String photoUrl, boolean isUser) {
        this.text = text;
        this.photoUrl = photoUrl;
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.UK);
        this.created = timeFormat.format(Calendar.getInstance().getTime());
        this.isUser = isUser;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
