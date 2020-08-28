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
public class PathConfig {

    private static final String ROOT_PATH = "/opt/tomcat/webapps/MMS/";
    public static final String INBOX_LETTER_UPLOAD_PATH = ROOT_PATH + "mails/inbox/";
    public static final String OUTBOX_LETTER_UPLOAD_PATH = ROOT_PATH + "mails/outbox/";
    public static final String USER_PHOTO_UPLOAD_PATH = ROOT_PATH + "avatars/";
}
