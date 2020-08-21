/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import configurations.MessageConfig;
import configurations.PathConfig;
import configurations.Route;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.ActivityInfo;
import models.Category;
import models.InboxInfo;
import models.Notification;
import models.UserInfo;
import services.ActivityService;
import services.CategoryService;
import services.InboxService;
import services.UserService;
import utils.Crypto;

/**
 *
 * @author RED-HAWK
 */
@MultipartConfig
public class InboxServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getSession().getAttribute("authUser") != null) {
                UserInfo authUser = (UserInfo) request.getSession().getAttribute("authUser");
                ActivityService activityService = new ActivityService();
                ActivityInfo activity = new ActivityInfo();
                request.setCharacterEncoding("UTF-8");
                request.getSession().setAttribute("navigatedPath", "inbox");

                switch (request.getServletPath()) {
                    case Route.DISPLAY_INBOX_ROUTE:
                        try {
                            if (authUser.getRoleId().equals("G_SECRETARIAT") || authUser.getRoleId().equals("G_OPERATOR")) {
                                request.setAttribute("inboxList", new InboxService().getAllByOffice(authUser));
                            } else {
                                request.setAttribute("inboxList", new InboxService().getAll());
                            }
                            request.getSession().setAttribute("previousRoute", Route.DISPLAY_INBOX_ROUTE);
                            request.getRequestDispatcher("/mails/inbox/displayInbox.jsp").forward(request, response);
                        } catch (Exception e) {
                            try {
                                recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : InboxServlet.java | Line : 73 " + MessageConfig.INBOX_ERROR_2020 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2020_LOCAL, "danger", request);
                                response.sendRedirect(request.getContextPath() + Route.DISPLAY_DASHBOARD_ROUTE);
                            } catch (Exception ex) {
//                                    ex.printStackTrace();
                            }
                        }
                        break;
                    case Route.DISPLAY_REGISTER_INBOX_FORM_ROUTE:
                        try {
                            request.setAttribute("categoryList", new CategoryService().getAll());
                            request.setAttribute("userList", new UserService().getAllOnLowLevel());
                            request.getRequestDispatcher("/mails/inbox/newInboxForm.jsp").forward(request, response);
                        } catch (Exception e) {
                            try {
                                recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : InboxServlet.java | Line : 88 " + MessageConfig.INBOX_ERROR_2023 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2023_LOCAL, "danger", request);
                                response.sendRedirect(request.getContextPath() + Route.DISPLAY_INBOX_ROUTE);
                            } catch (Exception ex) {
//                                    ex.printStackTrace();
                            }
                        }
                        break;
                    case Route.REGISTER_INBOX_ROUTE:
                        if (((request.getParameter("nMFrom") != null && (request.getMethod().equals("POST")))|| (request.getSession().getAttribute("submittedMail") != null))) {
                            try {
                                if (registerMail(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("submittedMail");
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_INBOX_ROUTE);
                                } else {
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_REGISTER_INBOX_FORM_ROUTE);
                                }
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : InboxServlet.java | Line : 107 " + MessageConfig.INBOX_ERROR_2014 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2014_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_INBOX_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    case Route.DISPLAY_INBOX_UPDATE_FORM_ROUTE:
                        if ((request.getParameter("mid") != null) || (request.getSession().getAttribute("selectedInbox") != null)) {
                            try {
                                InboxInfo inbox = new InboxInfo();
                                inbox.setId(request.getParameter("mid"));
                                if (request.getSession().getAttribute("selectedInbox") == null) {
                                    request.getSession().setAttribute("selectedInbox", new InboxService().get(inbox));
                                }
                                request.setAttribute("categoryList", new CategoryService().getAll());
                                request.setAttribute("userList", new UserService().getAllOnLowLevel());
                                request.getRequestDispatcher("/mails/inbox/updateInboxForm.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : InboxServlet.java | Line : 131 " + MessageConfig.INBOX_ERROR_2021 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2021_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_INBOX_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    case Route.UPDATE_INBOX_ROUTE:
                        if ((request.getParameter("mid") != null) && (request.getMethod().equals("POST"))) {
                            try {
                                if (updateMail(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("selectedInbox");
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_INBOX_ROUTE);
                                } else {
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_INBOX_UPDATE_FORM_ROUTE);
                                }
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : InboxServlet.java | Line : 154 " + MessageConfig.INBOX_ERROR_2017 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2017_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_INBOX_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    default:
                        redirectUnauthorizedRequest("root", authUser, request, response);
                        break;
                }
            } else {
                redirectUnauthorizedRequest("login", null, request, response);
            }
        }
    }

    private boolean registerMail(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException, IOException, ServletException {
        if ((request.getParameter("senderName") != null)
                && (request.getParameter("mailType") != null)
                && (request.getParameter("mailCategory") != null)
                && (request.getParameter("mailRecipient") != null)
                && (request.getParameter("mailBrief") != null)
                && (request.getPart("letter") != null)) {

            Part filePart = null;
            if (request.getPart("letter") != null) {
                // obtains the upload file part in this multipart request
                filePart = request.getPart("letter");
                if (!((filePart.getContentType().equals("image/png")) || (filePart.getContentType().equals("image/jpeg")))) {
                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2022_LOCAL, "danger", request);
                    return false;
                }
            }

            InboxInfo inbox = new InboxInfo();
            String mailId = Crypto.generateTimeStampId();
            inbox.setId(mailId);
            inbox.setCollectorId(authUser.getId()); // collector id
            inbox.setSender(request.getParameter("senderName")); // Sender's name
            inbox.setType(request.getParameter("mailType")); // mail type
            inbox.setContent(request.getParameter("mailBrief")); // mail brief
            inbox.setRecipientId(request.getParameter("mailRecipient")); // mail recipient
            inbox.setCategoryId(Integer.parseInt(request.getParameter("mailCategory")));
            inbox.setImageURL(mailId + "-I.png"); // letter url
            request.getSession().setAttribute("submittedMail", inbox);

            if (!(validateMailRecipient(request, "add"))) {
                return false;
            }
            if (!(validateCategory(request, "add", authUser, inbox, activityService, activity))) {
                return false;
            }

            InboxService inboxService = new InboxService();
            if (inboxService.add(inbox)) {
                if (uploadFile(mailId, filePart)) {
                    recordActivity(MessageConfig.INBOX_OPERATION_SUCCESSFUL, MessageConfig.INBOX_SUCCESSFUULY_ADDED + " Mail id : " + inbox.getId(), authUser, activityService, activity, request);
                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_SUCCESSFUULY_ADDED_NOTIFICATION, "success", request);
                    return true;
                } else {
                    recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : InboxServlet.java | Line : 219 " + MessageConfig.INBOX_ERROR_2015, authUser, activityService, activity, request);
                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2015_LOCAL, "danger", request);
                    return false;
                }
            } else {
                inboxService.remove(inbox);
                recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : InboxServlet.java | Line : 225 " + MessageConfig.INBOX_ERROR_2014, authUser, activityService, activity, request);
                setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2014_LOCAL, "danger", request);
                return false;
            }
        } else {
            setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2016_LOCAL, "danger", request);
            return false;
        }
    }

    private boolean updateMail(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException, IOException, ServletException {
        if ((request.getParameter("mid") != null)
                && (request.getParameter("senderName") != null)
                && (request.getParameter("mailType") != null)
                && (request.getParameter("mailCategory") != null)
                && (request.getParameter("mailRecipient") != null)
                && (request.getParameter("mailBrief") != null)) {

            InboxInfo inbox = new InboxInfo();
            String mailId = request.getParameter("mid");
            inbox.setId(mailId);
            inbox.setSender(request.getParameter("senderName")); // Sender's name
            inbox.setType(request.getParameter("mailType")); // mail type
            inbox.setRecipientId(request.getParameter("mailRecipient")); // mail recipient
            inbox.setCategoryId(Integer.parseInt(request.getParameter("mailCategory")));
            inbox.setContent(request.getParameter("mailBrief")); // mail brief
            request.getSession().setAttribute("selectedInbox", inbox);

            if (!(validateMailRecipient(request, "update"))) {
                return false;
            }
            if (!(validateCategory(request, "update", authUser, inbox, activityService, activity))) {
                return false;
            }

            InboxService inboxService = new InboxService();
            if (inboxService.update(inbox)) {
                if ((request.getPart("letter") != null) && ((request.getPart("letter").getContentType().equals("image/png")) || (request.getPart("letter").getContentType().equals("image/jpeg")))) {
                    if (uploadFile(mailId, request.getPart("letter"))) {
                        recordActivity(MessageConfig.INBOX_OPERATION_SUCCESSFUL, MessageConfig.INBOX_SUCCESSFUULY_UPDATED + " Mail id : " + inbox.getId(), authUser, activityService, activity, request);
                        setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_SUCCESSFUULY_UPDATED_NOTIFICATION, "success", request);
                        return true;
                    } else {
                        recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : InboxServlet.java | Line : 268 " + MessageConfig.INBOX_ERROR_2018, authUser, activityService, activity, request);
                        setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2018_LOCAL, "danger", request);
                        return false;
                    }
                } else {
                    recordActivity(MessageConfig.INBOX_OPERATION_SUCCESSFUL, MessageConfig.INBOX_SUCCESSFUULY_UPDATED + " Mail id : " + inbox.getId(), authUser, activityService, activity, request);
                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_SUCCESSFUULY_UPDATED_NOTIFICATION, "success", request);
                    return true;
                }
            } else {
                recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : InboxServlet.java | Line : 278 " + MessageConfig.INBOX_ERROR_2017, authUser, activityService, activity, request);
                setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2017_LOCAL, "danger", request);
                return false;
            }
        } else {
            setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2019_LOCAL, "danger", request);
            return false;
        }
    }

    private boolean uploadFile(String imageName, Part filePart) throws IOException, ServletException {
        // obtains input stream of the upload file
        InputStream inputStream = filePart.getInputStream();
        // Change the output path accordingly
        OutputStream output = new FileOutputStream(PathConfig.INBOX_LETTER_UPLOAD_PATH + imageName + "-I.png");
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) > 0) {
            output.write(buffer);
        }
        File isPhotoSaved = new File(PathConfig.INBOX_LETTER_UPLOAD_PATH + imageName + "-I.png");
        return isPhotoSaved.exists();
    }

    private boolean validateCategory(HttpServletRequest request, String action, UserInfo user, InboxInfo inbox, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException {
        switch (request.getParameter("mailCategory")) {
            case "0":
                setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2016_LOCAL, "danger", request);
                return false;
            case "01":
                return createNewCategory(request, user, inbox, activityService, activity, action);
            default:
                inbox.setCategoryId(Integer.parseInt(request.getParameter("mailCategory")));
                return true;
        }
    }

    private boolean createNewCategory(HttpServletRequest request, UserInfo authUser, InboxInfo inbox, ActivityService activityService, ActivityInfo activity, String action) throws ClassNotFoundException, SQLException {
        if ((request.getParameter("newCategoryName") != null)
                && (request.getParameter("newCategoryDescription") != null)
                && (request.getParameter("newCategoryName").length() > 2)
                && (request.getParameter("newCategoryDescription").length() > 2)) {

            CategoryService categoryService = new CategoryService();
            Category category = new Category();
            category.setName(request.getParameter("newCategoryName"));
            category.setDescription(request.getParameter("newCategoryDescription"));

            if (categoryService.add(category)) {
                inbox.setCategoryId(categoryService.getLastCategory().getId()); // mail category id
                recordActivity(MessageConfig.CATEGORY_OPERATION_SUCCESSFUL, MessageConfig.CATEGORY_SUCCESSFUULY_ADDED + " Category name : " + category.getName(), authUser, activityService, activity, request);
                activityService.add(activity);
                return true;
            } else {
                recordActivity(MessageConfig.CATEGORY_OPERATION_FAILED, "Location : InboxServlet.java | Line : 330 " + MessageConfig.CATEGORY_ERROR_2000, authUser, activityService, activity, request);
                setFieldsMissingNotification(action, request);
                return false;
            }
        } else {
            setFieldsMissingNotification(action, request);
            return false;
        }
    }

    private boolean validateMailRecipient(HttpServletRequest request, String action) {
        if (request.getParameter("mailRecipient").equals("unselected")) {
            setFieldsMissingNotification(action, request);
            return false;
        } else {
            return true;
        }
    }

    private void recordActivity(String type, String operation, UserInfo user, ActivityService activityService, ActivityInfo activity, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        activity.setUserId(user.getId());
        activity.setType(type);
        activity.setAction(operation);
        activityService.add(activity);
    }

    private void setNotification(String title, String body, String className, HttpServletRequest request) {
        request.getSession().setAttribute("notification", new Notification(title, body, className));
    }

    private void redirectUnauthorizedRequest(String route, UserInfo user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(user != null) {
            setNotification(MessageConfig.UNAUTHORIZED_REQUEST_NOTIFICATION_TITLE, user.getDisplayName() + MessageConfig.UNAUTHORIZED_REQUEST_NOTIFICATION, "warning", request);
        }
        switch (route) {
            case "login":
                response.sendRedirect(request.getContextPath() + Route.LOGIN_ROUTE);
                break;
            case "root":
                response.sendRedirect(request.getContextPath() + Route.DISPLAY_INBOX_ROUTE);
                break;
        }
    }

    private void setFieldsMissingNotification(String action, HttpServletRequest request) {
        if (action.equals("add")) {
            setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2016_LOCAL, "danger", request);
        } else {
            setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.INBOX_ERROR_2019_LOCAL, "danger", request);
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
