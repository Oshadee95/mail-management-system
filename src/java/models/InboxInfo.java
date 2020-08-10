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
public class InboxInfo extends Inbox{
    
    private String categoryName, collectorName, collectorPhotoURL, recipientName, recipientPhotoURL;

    public InboxInfo(){};

    public InboxInfo(String categoryName, String collectorName, String collectorPhotoURL, String recipientName, String recipientPhotoURL, int categoryId, String id, String sender, String content, String collectorId, String recipientId, String imageURL, Timestamp recordedAt, Timestamp updatedAt) {
        super(categoryId, id, sender, content, collectorId, recipientId, imageURL, recordedAt, updatedAt);
        this.categoryName = categoryName;
        this.collectorName = collectorName;
        this.collectorPhotoURL = collectorPhotoURL;
        this.recipientName = recipientName;
        this.recipientPhotoURL = recipientPhotoURL;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhotoURL() {
        return recipientPhotoURL;
    }

    public void setRecipientPhotoURL(String recipientPhotoURL) {
        this.recipientPhotoURL = recipientPhotoURL;
    }
    
    
}
