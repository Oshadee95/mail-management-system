/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configurations;

/**
 *
 * @author RED-HAWK
 */
public class DBConfig {
    
    private final String url = "jdbc:mysql://localhost/";
    private final String dbname = "mmdb";
    private final String username = "root";
    private final String password = "";

    public String getUrl() {
        return url;
    }

    public String getDbName() {
        return dbname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
