/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author RED-HAWK
 */
public class Crypto {

    public static String generateUUID() {
        return UUID.randomUUID().toString(); // returns a 36 digit UUID
    }

    public static String generateTimeStampId() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Timestamp(new Date().getTime())); // returns a 14 digit timestamp as an id
    }

    public static String generateSecurePassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        //convert the byte to hex format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString(); // returns a 64 digit secure password
    }
}
