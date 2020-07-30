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
public class Occupation {

    private int id;
    private String title;

    public Occupation(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Occupation() {
    }

    ;   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
