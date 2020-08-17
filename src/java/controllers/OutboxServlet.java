/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
                String previousRoute = (String) request.getSession().getAttribute("previousRoute");

                switch (request.getServletPath()) {
                    case "/Mails/Outbox/101":
                        if ((request.getParameter("mid") != null)) {
                            try {
                                InboxInfo inbox = new InboxInfo();
                                inbox.setId(request.getParameter("mid"));
                                request.setAttribute("selectedInbox", new InboxService().get(inbox));
                                OutboxInfo outbox = new OutboxInfo();
                                outbox.setMailId(inbox.getId());
                                request.setAttribute("selectedOutbox", new OutboxService().get(outbox));
//                                request.getSession().setAttribute("previousRoute", request.getContextPath() + "/Mails/Outbox/101");
                                request.getRequestDispatcher("/mails/outbox/displayOutboxForm.jsp").forward(request, response);
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("OS-ERROR");
                                    activity.setAction("Location : OutboxServlet.java | Line : 71 | Code : 1050 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail. ECODE - 1050.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(getInboxRoute(request, authUser));
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a direct request to route : /Mails/Outbox/101");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(getInboxRoute(request, authUser));
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                        break;
                    case "/Mails/Outbox/102":
                        if ((request.getParameter("mid") != null) || (request.getSession().getAttribute("selectedInbox") != null)) {
                            try {
                                InboxInfo inbox = new InboxInfo();
                                if (request.getSession().getAttribute("selectedInbox") == null) {
                                    inbox.setId(request.getParameter("mid"));
                                    request.getSession().setAttribute("selectedInbox", new InboxService().get(inbox));
                                }
                                request.getSession().setAttribute("previousRoute", request.getContextPath() + "/Mails/Outbox/102");
                                request.getRequestDispatcher("/mails/outbox/newOutboxForm.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("OS-ERROR");
                                    activity.setAction("Location : OutboxServlet.java | Line : 106 | Code : 1051 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open reply form. ECODE - 1051.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(getInboxRoute(request, authUser));
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getDisplayName() + " made a direct request to route : /Mails/Outbox/102");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(getInboxRoute(request, authUser));
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                        break;
                    case "/Mails/Outbox/103":
                        if (request.getParameter("rForm") != null) {
                            try {
                                request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                                replyMail(request, authUser, activityService, activity);
                                response.sendRedirect(getInboxRoute(request, authUser));
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("OS-ERROR");
                                    activity.setAction("Location : OutboxServlet.java | Line : 137 | Code : 1052 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1052.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(previousRoute);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getDisplayName() + " made a direct request to route : /Mails/Outbox/103");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(getInboxRoute(request, authUser));
                            } catch (Exception e) {
//                                e.printStackTrace();
                            }
                        }
                        break;
                    case "/Mails/Outbox/104":
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
                                request.getSession().setAttribute("previousRoute", request.getContextPath() + "/Mails/Outbox/104");
                                request.getRequestDispatcher("/mails/outbox/updateOutboxForm.jsp").forward(request, response);
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("OS-ERROR");
                                    activity.setAction("Location : OutboxServlet.java | Line : 180 | Code : 1053 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open update reply form. ECODE - 1053.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(getInboxRoute(request, authUser));
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getDisplayName() + " made a direct request to route : /Mails/Outbox/104");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(getInboxRoute(request, authUser));
                            } catch (Exception e) {
//                                e.printStackTrace();
                            }
                        }
                        break;
                    case "/Mails/Outbox/105":
                        if (request.getParameter("rUFrom") != null) {
                            try {
                                request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                                updateReplyMail(request, authUser, activityService, activity);
                                response.sendRedirect(getInboxRoute(request, authUser));
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("OS-ERROR");
                                    activity.setAction("Location : OutboxServlet.java | Line : 211 | Code : 1054 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update reply. ECODE - 1054.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(previousRoute);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getDisplayName() + " made a direct request to route : /Mails/Outbox/105");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(getInboxRoute(request, authUser));
                            } catch (Exception e) {
//                                e.printStackTrace();
                            }
                        }
                        break;
                    default:
                        response.sendRedirect(getInboxRoute(request, authUser));
                        break;

                }
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
                            activity.setUserId(authUser.getId());
                            activity.setType("OS-SUCCESSFUL");
                            activity.setAction(authUser.getDisplayName() + " replied to mail : " + outbox.getMailId());
                            activityService.add(activity);
                            request.getSession().setAttribute("notification", new Notification("Success Notification", "Replied to mail successfully", "success"));
                            return true;
                        } else {
                            updateMailReplyStatus(outbox, "false");
                            outboxService.remove(outbox);
                            activity.setUserId(authUser.getId());
                            activity.setType("OS-ERROR");
                            activity.setAction("Location : OutboxServlet.java | Line : 261 | Code : 1055 | Error : Failed to save reply image on the server for mail : " + outbox.getMailId());
                            activityService.add(activity);
                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1055.<br>Contact system administrator", "danger"));
                            return false;
                        }
                    } else {
                        outboxService.remove(outbox);
                        activity.setUserId(authUser.getId());
                        activity.setType("OS-ERROR");
                        activity.setAction("Location : OutboxServlet.java | Line : 270 | Code : 1056 | Error : Failed to update reply status on inbox mail : " + outbox.getMailId());
                        activityService.add(activity);
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1056.<br>Contact system administrator", "danger"));
                        return false;
                    }
                } else {
                    activity.setUserId(authUser.getId());
                    activity.setType("OS-ERROR");
                    activity.setAction("Location : OutboxServlet.java | Line : 278 | Code : 1057 | Error : Failed to add reply for mail : " + outbox.getMailId());
                    activityService.add(activity);
                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1057.<br>Contact system administrator", "danger"));
                    return false;
                }
            } else {
                activity.setUserId(authUser.getId());
                activity.setType("OS-ERROR");
                activity.setAction("Location : OutboxServlet.java | Line : 286 | Code : 1058 | Error : Failed to locate outbox object to reply mail");
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1058.<br>Contact system administrator", "danger"));
                return false;
            }
        } else {
            activity.setUserId(authUser.getId());
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1059.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
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
                        activity.setUserId(authUser.getId());
                        activity.setType("OS-SUCCESSFUL");
                        activity.setAction(authUser.getDisplayName() + " replied to mail : " + outbox.getMailId());
                        activityService.add(activity);
                        request.getSession().setAttribute("notification", new Notification("Success Notification", "Replied to mail successfully", "success"));
                        return true;
                    } else {
                        updateMailReplyStatus(outbox, "false");
                        outboxService.remove(outbox);
                        activity.setUserId(authUser.getId());
                        activity.setType("OS-ERROR");
                        activity.setAction("Location : OutboxServlet.java | Line : 311 | Code : 316 | Error : Failed to save reply image on the server for mail : " + outbox.getMailId());
                        activityService.add(activity);
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update reply. ECODE - 1055.<br>Contact system administrator", "danger"));
                        return false;
                    }
                } else {
                    activity.setUserId(authUser.getId());
                    activity.setType("OS-ERROR");
                    activity.setAction("Location : OutboxServlet.java | Line : 278 | Code : 324 | Error : Failed to update reply for mail : " + outbox.getMailId());
                    activityService.add(activity);
                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1057.<br>Contact system administrator", "danger"));
                    return false;
                }
            } else {
                activity.setUserId(authUser.getId());
                activity.setType("OS-ERROR");
                activity.setAction("Location : OutboxServlet.java | Line : 322 | Code : 1058 | Error : Failed to locate outbox object to reply mail");
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1058.<br>Contact system administrator", "danger"));
                return false;
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1059.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
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
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1060.<br>Uploaded image type is not supported", "danger"));
            return false; // If image type is not accecpted
        }
        // obtains input stream of the upload file
        InputStream inputStream = filePart.getInputStream();
        // Change the output path accordingly
        OutputStream output = new FileOutputStream("C:\\Users\\RED-HAWK\\Documents\\GitHub Projects\\mail-management-system\\web\\resources\\mails\\" + imageName);
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) > 0) {
            output.write(buffer);
        }
        return true;
    }

    private String getInboxRoute(HttpServletRequest request, UserInfo authUser) {
        if (!((authUser.getRoleId().equals("P_OPERATOR")) || (authUser.getRoleId().equals("G_OPERATOR")) || (authUser.getRoleId().equals("SYS_ADMIN")))) {
            return request.getContextPath() + "/Mails/MyMail/100";
        } else {
            return request.getContextPath() + "/Mails/Inbox/100";
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
