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
import models.InboxInfo;
import models.Notification;
import models.OutboxInfo;
import models.UserInfo;
import services.ActivityService;
import services.InboxService;
import services.OutboxService;

/**
 *
 * @author RED-HAWK
 */
@MultipartConfig
public class OutboxServlet extends HttpServlet {

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

                switch (request.getServletPath()) {
                    case Route.DISPLAY_OUTBOX_FORM_ROUTE:
                        if ((request.getParameter("mid") != null)) {
                            try {
                                InboxInfo inbox = new InboxInfo();
                                inbox.setId(request.getParameter("mid"));
                                request.setAttribute("selectedInbox", new InboxService().get(inbox));
                                OutboxInfo outbox = new OutboxInfo();
                                outbox.setMailId(inbox.getId());
                                request.setAttribute("selectedOutbox", new OutboxService().get(outbox));
                                request.getRequestDispatcher("/mails/outbox/displayOutboxForm.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 154 " + MessageConfig.OUTBOX_ERROR_2031 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2031_LOCAL, "danger", request);
                                    redirectToRoot(request, response);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    case Route.DISPLAY_REGISTER_OUTBOX_FORM_ROUTE:
                        if ((request.getParameter("mid") != null) || (request.getSession().getAttribute("selectedInbox") != null)) {
                            try {
                                InboxInfo inbox = new InboxInfo();
                                if (request.getSession().getAttribute("selectedInbox") == null) {
                                    inbox.setId(request.getParameter("mid"));
                                    request.getSession().setAttribute("selectedInbox", new InboxService().get(inbox));
                                }
                                request.getRequestDispatcher("/mails/outbox/newOutboxForm.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 154 " + MessageConfig.OUTBOX_ERROR_2033 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2033_LOCAL, "danger", request);
                                    redirectToRoot(request, response);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    case Route.REGISTER_OUTBOX_ROUTE:
                        if ((request.getParameter("rForm") != null) && (request.getMethod().equals("POST"))) {
                            try {
                                if (replyMail(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("selectedInbox");
                                    redirectToRoot(request, response);
                                } else {
                                    response.sendRedirect(request.getContextPath() + Route.REGISTER_OUTBOX_ROUTE);
                                }
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 154 " + MessageConfig.OUTBOX_ERROR_2027 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2027_LOCAL, "danger", request);
                                    redirectToRoot(request, response);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    case Route.DISPLAY_OUTBOX_UPDATE_FORM_ROUTE:
                        if ((request.getParameter("mid") != null) || (request.getSession().getAttribute("selectedInbox") != null)) {
                            try {
                                InboxInfo inbox = new InboxInfo();
                                if (request.getSession().getAttribute("selectedInbox") != null) {
                                    inbox = (InboxInfo) request.getSession().getAttribute("selectedInbox");
                                } else {
                                    inbox.setId(request.getParameter("mid"));
                                    request.getSession().setAttribute("selectedInbox", new InboxService().get(inbox));
                                }

                                OutboxInfo outbox = new OutboxInfo();
                                if (request.getSession().getAttribute("selectedOutbox") == null) {
                                    outbox.setMailId(inbox.getId());
                                    request.getSession().setAttribute("selectedOutbox", new OutboxService().get(outbox));
                                }
                                request.getRequestDispatcher("/mails/outbox/updateOutboxForm.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 154 " + MessageConfig.OUTBOX_ERROR_2032 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2032_LOCAL, "danger", request);
                                    redirectToRoot(request, response);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    case Route.UPDATE_OUTBOX_ROUTE:
                        if ((request.getParameter("rUFrom") != null) && (request.getMethod().equals("POST"))) {
                            try {
                                if (updateReplyMail(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("selectedInbox");
                                    redirectToRoot(request, response);
                                } else {
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_OUTBOX_UPDATE_FORM_ROUTE);
                                }
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.INBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 154 " + MessageConfig.OUTBOX_ERROR_2027 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2027_LOCAL, "danger", request);
                                    redirectToRoot(request, response);
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

    private boolean replyMail(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException, IOException, ServletException {
        if (request.getPart("reply") != null) {
            OutboxService outboxService = new OutboxService();
            if (getUpdatedReplyValues(request) != null) {
                OutboxInfo outbox = getUpdatedReplyValues(request);
                if (outboxService.add(outbox)) {
                    if (updateMailReplyStatus(outbox, "true")) {
                        if (uploadFile(request, outbox.getReplyImageURL())) {
                            recordActivity(MessageConfig.OUTBOX_OPERATION_SUCCESSFUL, MessageConfig.OUTBOX_SUCCESSFUULY_ADDED + " Reply mail id : " + outbox.getMailId(), authUser, activityService, activity, request);
                            setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_SUCCESSFUULY_ADDED_NOTIFICATION, "success", request);
                            return true;
                        } else {
                            recordActivity(MessageConfig.OUTBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 219 " + MessageConfig.OUTBOX_ERROR_2028, authUser, activityService, activity, request);
                            setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2028_LOCAL, "danger", request);
                            return false;
                        }
                    } else {
                        outboxService.remove(outbox);
                        recordActivity(MessageConfig.OUTBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 219 " + MessageConfig.OUTBOX_ERROR_2034, authUser, activityService, activity, request);
                        setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2034_LOCAL, "danger", request);
                        return false;
                    }
                } else {
                    recordActivity(MessageConfig.OUTBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 219 " + MessageConfig.OUTBOX_ERROR_2024, authUser, activityService, activity, request);
                    setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2024_LOCAL, "danger", request);
                    return false;
                }
            } else {
                setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2026_LOCAL, "danger", request);
                return false;
            }
        } else {
            setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2026_LOCAL, "danger", request);
            return false;
        }
    }

    private boolean updateReplyMail(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException, IOException, ServletException {
        if (request.getPart("reply") != null) {
            OutboxService outboxService = new OutboxService();
            if (getUpdatedReplyValues(request) != null) {
                OutboxInfo outbox = getUpdatedReplyValues(request);
                if (outboxService.update(outbox)) {
                    if (uploadFile(request, outbox.getReplyImageURL())) {
                        recordActivity(MessageConfig.OUTBOX_OPERATION_SUCCESSFUL, MessageConfig.OUTBOX_SUCCESSFUULY_UPDATED + " Reply mail id : " + outbox.getMailId(), authUser, activityService, activity, request);
                        setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_SUCCESSFUULY_UPDATED_NOTIFICATION, "success", request);
                        return true;
                    } else {
                        recordActivity(MessageConfig.OUTBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 219 " + MessageConfig.OUTBOX_ERROR_2028, authUser, activityService, activity, request);
                        setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2028_LOCAL, "danger", request);
                        return false;
                    }
                } else {
                    recordActivity(MessageConfig.OUTBOX_OPERATION_FAILED, "Location : OutboxServlet.java | Line : 219 " + MessageConfig.OUTBOX_ERROR_2027, authUser, activityService, activity, request);
                    setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2027_LOCAL, "danger", request);
                    return false;
                }
            } else {
                setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2029_LOCAL, "danger", request);
                return false;
            }
        } else {
            setNotification(MessageConfig.OUTBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2029_LOCAL, "danger", request);
            return false;
        }
    }

    private OutboxInfo getUpdatedReplyValues(HttpServletRequest request) throws IOException, ServletException {
        InboxInfo inbox = (InboxInfo) request.getSession().getAttribute("selectedInbox");
        UserInfo user = (UserInfo) request.getSession().getAttribute("authUser");
        OutboxInfo outbox = new OutboxInfo();
        outbox.setSenderId(user.getId());
        outbox.setMailId(inbox.getId());
        outbox.setReplyImageURL(inbox.getId() + "-O.png");
        return outbox;
    }

    public boolean updateMailReplyStatus(OutboxInfo outbox, String status) throws ClassNotFoundException, SQLException {
        InboxService inboxService = new InboxService();
        InboxInfo inbox = new InboxInfo();
        inbox.setId(outbox.getMailId());
        inbox.setReplied(status);
        return inboxService.updateStatus(inbox);
    }

    private boolean uploadFile(HttpServletRequest request, String imageName) throws IOException, ServletException {
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("reply");
        if (!((filePart.getContentType().equals("image/png")) || (filePart.getContentType().equals("image/jpeg")))) {
            setNotification(MessageConfig.INBOX_OPERATION_NOTIFICATION_TITLE, MessageConfig.OUTBOX_ERROR_2030_LOCAL, "danger", request);
            return false;
        }
        // obtains input stream of the upload file
        InputStream inputStream = filePart.getInputStream();
        // Change the output path accordingly
        OutputStream output = new FileOutputStream(PathConfig.OUTBOX_LETTER_UPLOAD_PATH + imageName);
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) > 0) {
            output.write(buffer);
        }
        File isPhotoSaved = new File(PathConfig.OUTBOX_LETTER_UPLOAD_PATH + imageName);
        return isPhotoSaved.exists();
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

    private void redirectToRoot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("previousRoute") != null) {
            response.sendRedirect(request.getContextPath() + request.getSession().getAttribute("previousRoute"));
        } else {
            response.sendRedirect(request.getContextPath() + Route.DISPLAY_INBOX_ROUTE);
        }
    }

    private void redirectUnauthorizedRequest(String route, UserInfo user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        setNotification(MessageConfig.UNAUTHORIZED_REQUEST_NOTIFICATION_TITLE, user.getDisplayName() + MessageConfig.UNAUTHORIZED_REQUEST_NOTIFICATION, "warning", request);
        switch (route) {
            case "login":
                response.sendRedirect(request.getContextPath() + Route.LOGIN_ROUTE);
                break;
            case "root":
                redirectToRoot(request, response);
                break;
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
