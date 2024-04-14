package com.example.messengerapp;

public class ModelUsers {
    String email, name, profilImage, timestamp, uid, userTye;

    public ModelUsers() {
    }

    public ModelUsers(String email, String name, String profilImage, String timestamp, String uid, String userTye) {
        this.email = email;
        this.name = name;
        this.profilImage = profilImage;
        this.timestamp = timestamp;
        this.uid = uid;
        this.userTye = userTye;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilImage() {
        return profilImage;
    }

    public void setProfilImage(String profilImage) {
        this.profilImage = profilImage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserTye() {
        return userTye;
    }

    public void setUserTye(String userTye) {
        this.userTye = userTye;
    }
}
