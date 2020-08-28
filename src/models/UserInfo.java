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
public class UserInfo extends User {

    private String occupation;
    private int roleWeight;

    public UserInfo() {};
      
    public UserInfo(String occupation, int roleWeight, int occupationId, String id, String nic, String fullName, String displayName, String office, String roleId, String active, String password, String photoURL, Timestamp createdAt, Timestamp updatedAt) {
        super(occupationId, id, nic, fullName, displayName, office, roleId, active, password, photoURL, createdAt, updatedAt);
        this.occupation = occupation;
        this.roleWeight = roleWeight;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getRoleWeight() {
        return roleWeight;
    }

    public void setRoleWeight(int roleWeight) {
        this.roleWeight = roleWeight;
    }
}
