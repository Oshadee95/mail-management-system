/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author RED-HAWK
 */
public class OutboxInfo extends Outbox {
    
    private String type, imageURL, collectorId, collectorName, collectorPhotoURL, senderName, senderPhotoURL;

    public OutboxInfo(){};
    
    public OutboxInfo(String type, String imageURL, String collectorId, String collectorName, String collectorPhotoURL, String senderName, String senderPhotoURL, String mailId, String senderId, String content, Timestamp repliedAt, Timestamp updatedAt) {
        super(mailId, senderId, content, repliedAt, updatedAt);
        this.type = type;
        this.imageURL = imageURL;
        this.collectorId = collectorId;
        this.collectorName = collectorName;
        this.collectorPhotoURL = collectorPhotoURL;
        this.senderName = senderName;
        this.senderPhotoURL = senderPhotoURL;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getCollectorPhotoURL() {
        return collectorPhotoURL;
    }

    public void setCollectorPhotoURL(String collectorPhotoURL) {
        this.collectorPhotoURL = collectorPhotoURL;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhotoURL() {
        return senderPhotoURL;
    }

    public void setSenderPhotoURL(String senderPhotoURL) {
        this.senderPhotoURL = senderPhotoURL;
    }
}
