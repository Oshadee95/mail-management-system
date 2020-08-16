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
                String previousRoute = (String) request.getSession().getAttribute("previousRoute");

                switch (request.getServletPath()) {
                    case "/Mails/Inbox/100":
                        try {
                            if (authUser.getRoleId().equals("G_SECRETARIAT") || authUser.getRoleId().equals("G_OPERATOR")) {
                                request.setAttribute("inboxList", new InboxService().getAllByOffice(authUser));
                            } else {
                                request.setAttribute("inboxList", new InboxService().getAll());
                            }
                            request.getRequestDispatcher("/mails/inbox/displayInbox.jsp").forward(request, response);
                        } catch (Exception e) {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("IS-ERROR");
                                activity.setAction("Location : InboxServlet.java | Line : 70 | Code : 1070 | Error : " + e.getMessage());
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to inbox. ECODE - 1070.<br>Contact system administrator", "danger"));
                                response.sendRedirect(request.getContextPath());
                            } catch (Exception ex) {
//                                    ex.printStackTrace();
                            }
                        }
                        break;

                    case "/Mails/Inbox/101":
                        if ((request.getParameter("mid") != null)) {
                            try {
                                InboxInfo displayInboxInfo = new InboxInfo();
                                displayInboxInfo.setId(request.getParameter("mid"));
                                request.setAttribute("inboxTemp", new InboxService().get(displayInboxInfo));
                                request.getRequestDispatcher("/mails/inbox/displayInboxForm.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("IS-ERROR");
                                    activity.setAction("Location : InboxServlet.java | Line : 91 | Code : 1071 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail. ECODE - 1071.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a direct request to route : /Mails/Inbox/101");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(request.getContextPath());
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                        break;
                    case "/Mails/Inbox/102":
                        try {
                            request.setAttribute("categoryList", new CategoryService().getAll());
                            request.setAttribute("userList", new UserService().getAllOnLowLevel());
                            request.getSession().setAttribute("previousRoute", request.getContextPath() + "/Mails/Inbox/102");
                            request.getRequestDispatcher("/mails/inbox/newInboxForm.jsp").forward(request, response);
                        } catch (Exception e) {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("IS-ERROR");
                                activity.setAction("Location : InboxServlet.java | Line : 120 | Code : 1072 | Error : " + e.getMessage());
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail form. ECODE - 1072.<br>Contact system administrator", "danger"));
                                response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                            } catch (Exception ex) {
//                                    ex.printStackTrace();
                            }
                        }
                        break;
                    case "/Mails/Inbox/103":
                        if (request.getParameter("nMFrom") != null) {
                            try {
                                request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                                if (registerMail(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("submittedMail");
                                    response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                                } else {
                                    response.sendRedirect(previousRoute);
                                }
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("IS-ERROR");
                                    activity.setAction("Location : InboxServlet.java | Line : 144 | Code : 1073 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1073.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(previousRoute);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a direct request to route : /Mails/Inbox/103");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                        break;
                    case "/Mails/Inbox/104":
                        if ((request.getParameter("mid") != null) || (request.getSession().getAttribute("selectedMail") != null)) {
                            try {
                                InboxInfo inbox = new InboxInfo();
                                inbox.setId(request.getParameter("mid"));
                                if (request.getSession().getAttribute("selectedMail") == null) {
                                    request.getSession().setAttribute("selectedMail", new InboxService().get(inbox));
                                }
                                request.setAttribute("categoryList", new CategoryService().getAll());
                                request.setAttribute("userList", new UserService().getAllOnLowLevel());
                                request.getSession().setAttribute("previousRoute", request.getContextPath() + "/Mails/Inbox/104");
                                request.getRequestDispatcher("/mails/inbox/updateInboxForm.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("IS-ERROR");
                                    activity.setAction("Location : InboxServlet.java | Line : 179 | Code : 1074 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open update mail form. ECODE - 1074.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a direct request to route : /Mails/Inbox/104");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                        break;
                    case "/Mails/Inbox/105":
                        if (request.getParameter("mid") != null) {
                            try {
                                request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                                if (updateMail(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("selectedMail");
                                    response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                                } else {
                                    response.sendRedirect(previousRoute);
                                }
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("IS-ERROR");
                                    activity.setAction("Location : InboxServlet.java | Line : 214 | Code : 1075 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update mail. ECODE - 1075.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(previousRoute);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a direct request to route : /Mails/Inbox/105");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                        break;

                }
            }
        }
    }

    private boolean registerMail(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException, IOException, ServletException {
        if ((request.getParameter("senderName") != null)
                && (request.getParameter("mailType") != null)
                && (request.getParameter("mailCategory") != null)
                && (request.getParameter("mailRecipient") != null)
                && (request.getParameter("mailBrief") != null
                && (request.getPart("letter") != null))) {

            Part filePart = null;
            if (request.getPart("letter") != null) {
                // obtains the upload file part in this multipart request
                filePart = request.getPart("letter");
                if (!((filePart.getContentType().equals("image/png")) || (filePart.getContentType().equals("image/jpeg")))) {
                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1076.<br>Uploaded image type not supported", "danger"));
                    return false; // If image type is not accecpted
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
            inbox.setImageURL(mailId + "-I.png"); // letter url
            String mailCategory = request.getParameter("mailCategory");

            switch (mailCategory) {
                case "00":
                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1078.<br>Please select a category", "danger"));
                    return false;
                case "01":
                    if (createNewCategory(request, mailCategory, authUser, activityService, activity, "register") != null) {
                        inbox.setCategoryId(createNewCategory(request, mailCategory, authUser, activityService, activity, "register").getCategoryId());
                    } else {
                        return false;
                    }
                    break;
                default:
                    inbox.setCategoryId(Integer.parseInt(mailCategory));
                    break;
            }

            InboxService inboxService = new InboxService();
            if (inboxService.add(inbox)) {
                if (uploadFile(request, mailId, filePart)) {
                    activity.setUserId(authUser.getId());
                    activity.setType("IS-SUCCESSFUL");
                    activity.setAction(authUser.getDisplayName() + " registered mail : " + inbox.getId());
                    activityService.add(activity);
                    request.getSession().setAttribute("notification", new Notification("Success Notification", "Mail successfully registered", "success"));
                    return true;
                } else {
                    inboxService.remove(inbox);
                    activity.setUserId(authUser.getId());
                    activity.setType("IS-ERROR");
                    activity.setAction("Location : InboxServlet.java | Line : 302 | Code : 1079 | Error : Failed to save mail image on the server for mail : " + inbox.getId());
                    activityService.add(activity);
                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register reply. ECODE - 1079.<br>Contact system administrator", "danger"));
                    return false;
                }
            } else {
                inboxService.remove(inbox);
                activity.setUserId(authUser.getId());
                activity.setType("IS-ERROR");
                activity.setAction("Location : InboxServlet.java | Line : 311 | Code : 1080 | Error : Failed to register mail");
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1080.<br>Contact system administrator", "danger"));
                return false;
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1077.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
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
            inbox.setContent(request.getParameter("mailBrief")); // mail brief
            inbox.setRecipientId(request.getParameter("mailRecipient")); // mail recipient
            String mailCategory = request.getParameter("mailCategory");

            switch (mailCategory) {
                case "00":
                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update mail. ECODE - 1078.<br>Please select a category", "danger"));
                    return false;
                case "01":
                    if (createNewCategory(request, mailCategory, authUser, activityService, activity, "update") != null) {
                        inbox.setCategoryId(createNewCategory(request, mailCategory, authUser, activityService, activity, "update").getCategoryId());
                    } else {
                        return false;
                    }
                    break;
                default:
                    inbox.setCategoryId(Integer.parseInt(mailCategory));
                    break;
            }

            InboxService inboxService = new InboxService();
            if (inboxService.update(inbox)) {
                if ((request.getPart("letter") != null) && ((request.getPart("letter").getContentType().equals("image/png")) || (request.getPart("letter").getContentType().equals("image/jpeg")))) {
                    if (uploadFile(request, mailId, request.getPart("letter"))) {
                        activity.setUserId(authUser.getId());
                        activity.setType("IS-SUCCESSFUL");
                        activity.setAction(authUser.getDisplayName() + " updated mail : " + inbox.getId());
                        activityService.add(activity);
                        request.getSession().setAttribute("notification", new Notification("Success Notification", "Mail successfully updated", "success"));
                        return true;
                    } else {
                        activity.setUserId(authUser.getId());
                        activity.setType("IS-ERROR");
                        activity.setAction("Location : InboxServlet.java | Line : 378 | Code : 1079 | Error : Failed to save updated mail image on the server for mail : " + inbox.getId());
                        activityService.add(activity);
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update mail. ECODE - 1079.<br>Contact system administrator", "danger"));
                        return false;
                    }
                } else {
                    activity.setUserId(authUser.getId());
                    activity.setType("IS-SUCCESSFUL");
                    activity.setAction(authUser.getDisplayName() + " updated mail : " + inbox.getId());
                    activityService.add(activity);
                    request.getSession().setAttribute("notification", new Notification("Success Notification", "Mail successfully updated", "success"));
                    return true;
                }
            } else {
                activity.setUserId(authUser.getId());
                activity.setType("IS-ERROR");
                activity.setAction("Location : InboxServlet.java | Line : 367 | Code : 394 | Error : Failed to update mail");
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update mail. ECODE - 1080.<br>Contact system administrator", "danger"));
                return false;
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update mail. ECODE - 1077.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
            return false;
        }
    }

    private boolean uploadFile(HttpServletRequest request, String imageName, Part filePart) throws IOException, ServletException {
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

    private InboxInfo createNewCategory(HttpServletRequest request, String mailCategory, UserInfo authUser, ActivityService activityService, ActivityInfo activity, String action) throws ClassNotFoundException, SQLException {
        if ((request.getParameter("newCategoryName") != null)
                && (request.getParameter("newCategoryDescription") != null)
                && (request.getParameter("newCategoryName").length() > 2)
                && (request.getParameter("newCategoryDescription").length() > 2)) {

            CategoryService cs = new CategoryService();
            Category c = new Category();
            c.setName(request.getParameter("newCategoryName"));
            c.setDescription(request.getParameter("newCategoryDescription"));

            InboxInfo inbox = new InboxInfo();
            if (cs.add(c)) {
                inbox.setCategoryId(cs.getLastCategory().getId()); // mail category id
                activity.setUserId(authUser.getId());
                activity.setType("IS-SUCCESSFUL");
                activity.setAction(authUser.getDisplayName() + " added a new category : " + inbox.getCategoryId());
                activityService.add(activity);
                return inbox;
            } else {
                activity.setUserId(authUser.getId());
                activity.setType("IS-ERROR");
                activity.setAction("Location : InboxServlet.java | Line : 439 | Code : 1081 | Error : Failed to add category");
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to " + action + " mail. ECODE - 1081.<br>Contact system administrator", "danger"));
                return null; // if query execution failed while adding new category
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to " + action + " mail. ECODE - 1002.<br>New category name or description is missing", "danger"));
            return null;
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
