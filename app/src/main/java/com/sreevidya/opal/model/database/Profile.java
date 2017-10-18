package com.sreevidya.opal.model.database;


public class Profile {
    private String uid;
    private String email;
    private String photoURL;
    private String name;

    /**
     * Empty constructor is needed by firebase
     */
    public Profile() {
    }

    public Profile(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.photoURL = "";
    }

    public Profile(String uid, String name, String email, String photoURL) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.photoURL = photoURL;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
