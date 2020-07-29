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
public class Outbox {
    private int mailId;
    private String senderId,content;
     private Timestamp repliedAt;

    public Outbox(int mailId, String senderId, String content, Timestamp repliedAt) {
        this.mailId = mailId;
        this.senderId = senderId;
        this.content = content;
        this.repliedAt = repliedAt;
    }

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getRepliedAt() {
        return repliedAt;
    }

    public void setRepliedAt(Timestamp repliedAt) {
        this.repliedAt = repliedAt;
    }

}

