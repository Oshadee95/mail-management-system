/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author Oshadee
 */
public class Outbox implements java.io.Serializable {

    private String mailId, senderId, replyImageURL;
    private Timestamp repliedAt, updatedAt;

    public Outbox() {};  

    public Outbox(String mailId, String senderId, String replyImageURL, Timestamp repliedAt, Timestamp updatedAt) {
        this.mailId = mailId;
        this.senderId = senderId;
        this.replyImageURL = replyImageURL;
        this.repliedAt = repliedAt;
        this.updatedAt = updatedAt;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReplyImageURL() {
        return replyImageURL;
    }

    public void setReplyImageURL(String replyImageURL) {
        this.replyImageURL = replyImageURL;
    }

    public Timestamp getRepliedAt() {
        return repliedAt;
    }

    public void setRepliedAt(Timestamp repliedAt) {
        this.repliedAt = repliedAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
