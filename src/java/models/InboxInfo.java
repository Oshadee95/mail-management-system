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
    
    private String categoryName, collectorName, collectorPhotoURL, recipientName, recipientPhotoURL, allocatedOffice;

    public InboxInfo(){};

    public InboxInfo(String categoryName, String collectorName, String collectorPhotoURL, String recipientName, String recipientPhotoURL, int categoryId, String id, String type, String sender, String content, String collectorId, String recipientId, String imageURL, String replied, Timestamp recordedAt, Timestamp updatedAt, String allocatedOffice) {
        super(categoryId, id, type, sender, content, collectorId, recipientId, imageURL, replied, recordedAt, updatedAt);
        this.categoryName = categoryName;
        this.collectorName = collectorName;
        this.collectorPhotoURL = collectorPhotoURL;
        this.recipientName = recipientName;
        this.recipientPhotoURL = recipientPhotoURL;
        this.allocatedOffice = allocatedOffice;
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

    public String getAllocatedOffice() {
        return allocatedOffice;
    }

    public void setAllocatedOffice(String allocatedOffice) {
        this.allocatedOffice = allocatedOffice;
    }    
}
