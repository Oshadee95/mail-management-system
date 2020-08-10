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
public class ActivityInfo extends Activity {
    
    private String userName, userPhotoURL;

    public ActivityInfo() {}

    public ActivityInfo(String userName, String userPhotoURL, int id, String userId, String action, Timestamp occuredAt) {
        super(id, userId, action, occuredAt);
        this.userName = userName;
        this.userPhotoURL = userPhotoURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhotoURL() {
        return userPhotoURL;
    }

    public void setUserPhotoURL(String userPhotoURL) {
        this.userPhotoURL = userPhotoURL;
    }
    
    
}
