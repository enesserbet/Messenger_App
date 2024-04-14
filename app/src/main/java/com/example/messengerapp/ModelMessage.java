package com.example.messengerapp;

public class ModelMessage {
    String message, receiverUid, senderUid, timestamp, uid, chatUid;


    public ModelMessage() {

    }

    public ModelMessage(String message, String receiverUid, String senderUid, String timestamp, String uid, String chatUid) {
        this.message = message;
        this.receiverUid = receiverUid;
        this.senderUid = senderUid;
        this.timestamp = timestamp;
        this.uid = uid;
        this.chatUid = chatUid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverUId() {
        return receiverUid;
    }

    public void setReceiverUId(String receiverUId) {
        this.receiverUid = receiverUId;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
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

    public String getChatUid() {
        return chatUid;
    }

    public void setChatUid(String chatUid) {
        this.chatUid = chatUid;
    }
}
