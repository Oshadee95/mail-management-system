/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Oshadee
 */
public class Roles {

    private int weight;
    private String role;

    public Roles(int weight, String role) {
        this.weight = weight;
        this.role = role;
    }

    public Roles() {
    };   
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
