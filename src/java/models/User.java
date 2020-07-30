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
public class User {

    private int id;
    private String nic, fullName, dislayName, occupationid, office, role, active, password;
    private Timestamp createdAt, updatedAt;

    public User(int id, String nic, String fullName, String dislayName, String occupationid, String office, String role, String active, String password, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.nic = nic;
        this.fullName = fullName;
        this.dislayName = dislayName;
        this.occupationid = occupationid;
        this.office = office;
        this.role = role;
        this.active = active;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDislayName() {
        return dislayName;
    }

    public void setDislayName(String dislayName) {
        this.dislayName = dislayName;
    }

    public String getOccupationid() {
        return occupationid;
    }

    public void setOccupationid(String occupationid) {
        this.occupationid = occupationid;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateAt() {
        return createdAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createdAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updatedAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updatedAt = updateAt;
    }

}
