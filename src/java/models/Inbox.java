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
public class Inbox {

    private int id, categryId;
    private String name, sender, collector, assingedTo, imageURL, content;
    private Timestamp registeredAt, updatedAt;

    public Inbox() {
    }

    ;

    public Inbox(int id, int categryId, String name, String sender, String collector, String assingedTo, String imageURL, String content, Timestamp registeredAt, Timestamp updatedAt) {
        this.id = id;
        this.categryId = categryId;
        this.name = name;
        this.sender = sender;
        this.collector = collector;
        this.assingedTo = assingedTo;
        this.imageURL = imageURL;
        this.content = content;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategryId() {
        return categryId;
    }

    public void setCategryId(int categryId) {
        this.categryId = categryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getAssingedTo() {
        return assingedTo;
    }

    public void setAssingedTo(String assingedTo) {
        this.assingedTo = assingedTo;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
