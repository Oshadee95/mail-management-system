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
public class Route {

    // Login route
    public static final String LOGIN_ROUTE = "/Login";
    
    // Lougout route
    public static final String LOGOUT_ROUTE = "/Logout";

    // Inbox Routes
    public static final String DISPLAY_INBOX_ROUTE = "/Mails/Inbox/100";
    public static final String DISPLAY_REGISTER_INBOX_FORM_ROUTE = "/Mails/Inbox/102";
    public static final String REGISTER_INBOX_ROUTE = "/Mails/Inbox/103";
    public static final String DISPLAY_INBOX_UPDATE_FORM_ROUTE = "/Mails/Inbox/104";
    public static final String UPDATE_INBOX_ROUTE = "/Mails/Inbox/105";
    
    // Outbox Routes
    public static final String DISPLAY_OUTBOX_FORM_ROUTE = "/Mails/Outbox/101";
    public static final String DISPLAY_REGISTER_OUTBOX_FORM_ROUTE = "/Mails/Outbox/102";
    public static final String REGISTER_OUTBOX_ROUTE = "/Mails/Outbox/103";
    public static final String DISPLAY_OUTBOX_UPDATE_FORM_ROUTE = "/Mails/Outbox/104";
    public static final String UPDATE_OUTBOX_ROUTE = "/Mails/Outbox/105";

    // MyMail routes
    public static final String DISPLAY_MYMAIL_ROUTE = "/Mails/MyMail/100";

    // Dashboard routes
    public static final String DISPLAY_DASHBOARD_ROUTE = "/Dashboard/Home/100";

    // Activity routes
    public static final String DISPLAY_ACTIVITIES_ROUTES = "/Auth/Activities/100";

    // Category routes
    public static final String DISPLAY_CATEGORIES_ROUTE = "/Mails/Category/100";
    public static final String REGISTER_CATEGORY_ROUTE = "/Mails/Category/103";
    public static final String DISPLAY_CATEGORY_UPDATE_FORM_ROUTE = "/Mails/Category/104";
    public static final String UPDATE_CATEGORY_ROUTE = "/Mails/Category/105";

    // Occupation route
    public static final String DISPLAY_OCCUPATIONS_ROUTE = "/Office/Occupation/100";
    public static final String REGISTER_OCCUPATIONS_ROUTE = "/Office/Occupation/103";
    public static final String DISPLAY_OCCUPATIONS_UPDATE_FORM_ROUTE = "/Office/Occupation/104";
    public static final String UPDATE_OCCUPATIONS_ROUTE = "/Office/Occupation/105";

    // Users route
    public static final String DISPLAY_USERS_ROUTE = "/Auth/Users/100";
    public static final String DISPLAY_USERS_FORM_ROUTE = "/Auth/Users/101";
    public static final String DISPLAY_REGISTER_USER_FORM_ROUTE = "/Auth/Users/102";
    public static final String REGISTER_USER_ROUTE = "/Auth/Users/103";
    public static final String DISPLAY_USER_UPDATE_FORM_ROUTE = "/Auth/Users/104";
    public static final String UPDATE_USER_ROUTE = "/Auth/Users/105";
    
    
    // Language route
    public static final String EN = "/Auth/Language/en";
    public static final String SI = "/Auth/Language/si";

}
