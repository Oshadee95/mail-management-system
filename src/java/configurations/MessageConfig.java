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
    public static final String OCCUPATION_ERROR_2012 = "ECODE : 2012 | Failed to open route " + Route.DISPLAY_OCCUPATIONS_ROUTE;
    public static final String OCCUPATION_ERROR_2012_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2012";
    public static final String OCCUPATION_ERROR_2013 = "ECODE : 2013 |  Failed to open route " + Route.DISPLAY_OCCUPATIONS_UPDATE_FORM_ROUTE;
    public static final String OCCUPATION_ERROR_2013_LOCAL = CATEGORY_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2013";

    // Inbox messages
    public static final String INBOX_OPERATION_NOTIFICATION_TITLE = "MAIL OPERATION";
    public static final String INBOX_OPERATION_SUCCESSFUL = "IN-OP | SUCCESS";
    public static final String INBOX_OPERATION_FAILED = "IN-OP | FAILED";
    public static final String INBOX_SUCCESSFUULY_ADDED = "New mail was added.";
    public static final String INBOX_SUCCESSFUULY_ADDED_NOTIFICATION = "Mail was successfully registered";
    public static final String INBOX_SUCCESSFUULY_UPDATED = "Existing mail was updated.";
    public static final String INBOX_SUCCESSFUULY_UPDATED_NOTIFICATION = "Mail was successfully updated";

    public static final String INBOX_ADD_ERROR_LOCAL = "Failed to register mail.<br>";
    public static final String INBOX_ERROR_2014 = "ECODE : 2014 | Failed to register mail due to sql error";
    public static final String INBOX_ERROR_2014_LOCAL = CATEGORY_ADD_ERROR_LOCAL + "Contact system administrator. ECODE - 2014";
    public static final String INBOX_ERROR_2015 = "ECODE : 2015 | Failed to register mail due to image upload error";
    public static final String INBOX_ERROR_2015_LOCAL = CATEGORY_ADD_ERROR_LOCAL + "Contact system administrator. ECODE - 2015";
    public static final String INBOX_ERROR_2016 = "ECODE : 2016 | Failed to register mail due to missing values";
    public static final String INBOX_ERROR_2016_LOCAL = CATEGORY_ADD_ERROR_LOCAL + "Reqired values are missing, Please make sure all required feilds are filled";

    public static final String INBOX_UPDATE_ERROR_LOCAL = "Failed to update mail.<br>";
    public static final String INBOX_ERROR_2017 = "ECODE : 2017 | Failed to update mail due to sql error";
    public static final String INBOX_ERROR_2017_LOCAL = INBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2017";
    public static final String INBOX_ERROR_2018 = "ECODE : 2018 | Failed to update mail due to image upload error";
    public static final String INBOX_ERROR_2018_LOCAL = INBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2018";
    public static final String INBOX_ERROR_2019 = "ECODE : 2019 | Failed to update mail due to missing values";
    public static final String INBOX_ERROR_2019_LOCAL = INBOX_UPDATE_ERROR_LOCAL + "Reqired values are missing, Please make sure all required feilds are filled";

    public static final String INBOX_ROUTE_LOCAL = "Failed to open mail route.<br>";
    public static final String INBOX_ERROR_2020 = "ECODE : 2020 | Failed to open route " + Route.DISPLAY_INBOX_ROUTE;
    public static final String INBOX_ERROR_2020_LOCAL = INBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2020";
    public static final String INBOX_ERROR_2021 = "ECODE : 2021 |  Failed to open route " + Route.DISPLAY_INBOX_UPDATE_FORM_ROUTE;
    public static final String INBOX_ERROR_2021_LOCAL = INBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2021";
    public static final String INBOX_ERROR_2022_LOCAL = INBOX_UPDATE_ERROR_LOCAL + "Uploaded image type is not supported";
    public static final String INBOX_ERROR_2023 = "ECODE : 2023 |  Failed to open route " + Route.DISPLAY_REGISTER_INBOX_FORM_ROUTE;
    public static final String INBOX_ERROR_2023_LOCAL = INBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2023";

    // Outbox messages
    public static final String OUTBOX_OPERATION_NOTIFICATION_TITLE = "MAIL OPERATION";
    public static final String OUTBOX_OPERATION_SUCCESSFUL = "OU-OP | SUCCESS";
    public static final String OUTBOX_OPERATION_FAILED = "OU-OP | FAILED";
    public static final String OUTBOX_SUCCESSFUULY_ADDED = "New mail reply was added.";
    public static final String OUTBOX_SUCCESSFUULY_ADDED_NOTIFICATION = "Replied to mail successfully";
    public static final String OUTBOX_SUCCESSFUULY_UPDATED = "Mail reply was updated.";
    public static final String OUTBOX_SUCCESSFUULY_UPDATED_NOTIFICATION = "Mail reply successfully updated";

    public static final String OUTBOX_ADD_ERROR_LOCAL = "Failed to reply mail.<br>";
    public static final String OUTBOX_ERROR_2024 = "ECODE : 2024 | Failed to reply mail due to sql error";
    public static final String OUTBOX_ERROR_2024_LOCAL = OUTBOX_ADD_ERROR_LOCAL + "Contact system administrator. ECODE - 2024";
    public static final String OUTBOX_ERROR_2025 = "ECODE : 2025 | Failed to reply mail due to image upload error";
    public static final String OUTBOX_ERROR_2025_LOCAL = OUTBOX_ADD_ERROR_LOCAL + "Contact system administrator. ECODE - 2025";
    public static final String OUTBOX_ERROR_2026 = "ECODE : 2026 | Failed to reply mail due to missing values";
    public static final String OUTBOX_ERROR_2026_LOCAL = OUTBOX_ADD_ERROR_LOCAL + "Reqired values are missing, Please make sure all required feilds are filled";

    public static final String OUTBOX_UPDATE_ERROR_LOCAL = "Failed to update mail reply.<br>";
    public static final String OUTBOX_ERROR_2027 = "ECODE : 2027 | Failed to update mail  reply due to sql error";
    public static final String OUTBOX_ERROR_2027_LOCAL = OUTBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2027";
    public static final String OUTBOX_ERROR_2028 = "ECODE : 2028 | Failed to update mail reply due to image upload error";
    public static final String OUTBOX_ERROR_2028_LOCAL = OUTBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2028";
    public static final String OUTBOX_ERROR_2029 = "ECODE : 2029 | Failed to update mail reply due to missing values";
    public static final String OUTBOX_ERROR_2029_LOCAL = OUTBOX_UPDATE_ERROR_LOCAL + "Reqired values are missing, Please make sure all required feilds are filled";
    public static final String OUTBOX_ERROR_2030_LOCAL = OUTBOX_UPDATE_ERROR_LOCAL + "Uploaded image type is not supported";

    public static final String OUTBOX_ROUTE_LOCAL = "Failed to open mail route.<br>";
    public static final String OUTBOX_ERROR_2031 = "ECODE : 2032 | Failed to open route " + Route.DISPLAY_OUTBOX_FORM_ROUTE;
    public static final String OUTBOX_ERROR_2031_LOCAL = OUTBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2020";
    public static final String OUTBOX_ERROR_2032 = "ECODE : 2032 |  Failed to open route " + Route.DISPLAY_OUTBOX_UPDATE_FORM_ROUTE;
    public static final String OUTBOX_ERROR_2032_LOCAL = OUTBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2021";
    public static final String OUTBOX_ERROR_2033 = "ECODE : 2033 |  Failed to open route " + Route.DISPLAY_REGISTER_OUTBOX_FORM_ROUTE;
    public static final String OUTBOX_ERROR_2033_LOCAL = OUTBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2023";
    public static final String OUTBOX_ERROR_2034 = "ECODE : 2034 | Updated reply mail but failed to update inbox reply status due to sql error";
    public static final String OUTBOX_ERROR_2034_LOCAL = OUTBOX_UPDATE_ERROR_LOCAL + "Contact system administrator. ECODE - 2034";
}
