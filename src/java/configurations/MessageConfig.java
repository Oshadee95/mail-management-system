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
public class MessageConfig {

    public static final String UNAUTHORIZED_REQUEST_NOTIFICATION_TITLE = "UNAUTHORIZED REQUEST";
    public static final String UNAUTHORIZED_REQUEST_NOTIFICATION = " is not permitted to access route";

    // Category messages
    public static final String CATEGORY_OPERATION_NOTIFICATION_TITLE = "CATEGORY OPERATION";
    public static final String CATEGORY_OPERATION_SUCCESSFUL = "CA-OP | SUCCESS";
    public static final String CATEGORY_OPERATION_FAILED = "CA-OP | FAILED";
    public static final String CATEGORY_SUCCESSFUULY_ADDED = "New mail category was added.";
    public static final String CATEGORY_SUCCESSFUULY_ADDED_NOTIFICATION = "Category was successfully registered";
    public static final String CATEGORY_SUCCESSFUULY_UPDATED = "Existing mail category was updated.";
    public static final String CATEGORY_SUCCESSFUULY_UPDATED_NOTIFICATION = "Category was successfully updated";

    public static final String CATEGORY_ADD_ERROR_LOCAL = "Failed to register category.<br>";
    public static final String CATEGORY_ERROR_2000 = "ECODE : 2000 | Failed to register category due to sql error";
    public static final String CATEGORY_ERROR_2000_LOCAL = CATEGORY_ADD_ERROR_LOCAL + "Contact system administrator. ECODE - 2000";
    public static final String CATEGORY_ERROR_2001 = "ECODE : 2001 | Failed to register category due to missing values";
    public static final String CATEGORY_ERROR_2001_LOCAL = CATEGORY_ADD_ERROR_LOCAL + "Reqired values are missing, Please make sure all required feilds are filled";

    public static final String CATEGORY_UPDATE_ERROR_LOCAL = "Failed to update category.<br>";
    public static final String CATEGORY_ERROR_2002 = "ECODE : 2002 | Failed to update category due to sql error";
    public static final String CATEGORY_ERROR_2002_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2002";
    public static final String CATEGORY_ERROR_2003 = "ECODE : 2003 | Failed to update category due to missing values";
    public static final String CATEGORY_ERROR_2003_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Reqired values are missing, Please make sure all required feilds are filled";

    public static final String CATEGORY_ROUTE_LOCAL = "Failed to open category route.<br>";
    public static final String CATEGORY_ERROR_2004 = "ECODE : 2004 | Failed to open route " + Route.DISPLAY_CATEGORIES_ROUTE;
    public static final String CATEGORY_ERROR_2004_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2004";
    public static final String CATEGORY_ERROR_2005 = "ECODE : 2005 |  Failed to open route " + Route.DISPLAY_CATEGORY_UPDATE_FORM_ROUTE;
    public static final String CATEGORY_ERROR_2005_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2005";

    // MyMail messages
    public static final String MYMAIL_OPERATION_NOTIFICATION_TITLE = "MAIL OPERATION";
    public static final String MYMAIL_OPERATION_FAILED = "MY-OP | FAILED";
    public static final String MYMAIL_ROUTE_LOCAL = "Failed to open mails route.<br>";
    public static final String MYMAIL_ERROR_2006 = "ECODE : 2006 | Failed to open route " + Route.DISPLAY_MYMAIL_ROUTE;
    public static final String MYMAIL_ERROR_2006_LOCAL = MYMAIL_ROUTE_LOCAL + "Contact system administrator. ECODE - 2006";
    
    
    // Acivities messages
    public static final String ACTIVITY_OPERATION_NOTIFICATION_TITLE = "MAIL OPERATION";
    public static final String ACTIVITY_OPERATION_FAILED = "AC-OP | FAILED";
    public static final String ACTIVITY_ROUTE_LOCAL = "Failed to open activity route.<br>";
    public static final String ACTIVITY_ERROR_2007 = "ECODE : 2007 | Failed to open route " + Route.DISPLAY_ACTIVITIES_ROUTES;
    public static final String ACTIVITY_ERROR_2007_LOCAL = MYMAIL_ROUTE_LOCAL + "Contact system administrator. ECODE - 2007";
    
    
    // Occupation messages
    public static final String OCCUPATION_OPERATION_NOTIFICATION_TITLE = "OCCUPATION OPERATION";
    public static final String OCCUPATION_OPERATION_SUCCESSFUL = "OC-OP | SUCCESS";
    public static final String OCCUPATION_OPERATION_FAILED = "OC-OP | FAILED";
    public static final String OCCUPATION_SUCCESSFUULY_ADDED = "New occupation was added.";
    public static final String OCCUPATION_SUCCESSFUULY_ADDED_NOTIFICATION = "Occupation was successfully registered";
    public static final String OCCUPATION_SUCCESSFUULY_UPDATED = "Existing occupation was updated.";
    public static final String OCCUPATION_SUCCESSFUULY_UPDATED_NOTIFICATION = "Occupation was successfully updated";

    public static final String OCCUPATION_ADD_ERROR_LOCAL = "Failed to register occupation.<br>";
    public static final String OCCUPATION_ERROR_2008 = "ECODE : 2008 | Failed to register occupation due to sql error";
    public static final String OCCUPATION_ERROR_2008_LOCAL = CATEGORY_ADD_ERROR_LOCAL + "Contact system administrator. ECODE - 2008";
    public static final String OCCUPATION_ERROR_2009 = "ECODE : 2009 | Failed to register occupation due to missing values";
    public static final String OCCUPATION_ERROR_2009_LOCAL = CATEGORY_ADD_ERROR_LOCAL + "Reqired values are missing, Please make sure all required feilds are filled";

    public static final String OCCUPATION_UPDATE_ERROR_LOCAL = "Failed to update category.<br>";
    public static final String OCCUPATION_ERROR_2010 = "ECODE : 2010 | Failed to update occupation due to sql error";
    public static final String OCCUPATION_ERROR_2010_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2010";
    public static final String OCCUPATION_ERROR_2011 = "ECODE : 2011 | Failed to update occupation due to missing values";
    public static final String OCCUPATION_ERROR_2011_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Reqired values are missing, Please make sure all required feilds are filled";

    public static final String OCCUPATION_ROUTE_LOCAL = "Failed to open occupation route.<br>";
    public static final String OCCUPATION_ERROR_2012 = "ECODE : 2012 | Failed to open route " + Route.DISPLAY_CATEGORIES_ROUTE;
    public static final String OCCUPATION_ERROR_2012_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2012";
    public static final String OCCUPATION_ERROR_2013 = "ECODE : 2013 |  Failed to open route " + Route.DISPLAY_CATEGORY_UPDATE_FORM_ROUTE;
    public static final String OCCUPATION_ERROR_2013_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2013";
}
