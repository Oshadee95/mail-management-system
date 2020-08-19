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
import models.Notification;
import models.UserInfo;
import services.ActivityService;
import services.OccupationService;
import services.RoleService;
import services.UserService;
import utils.Crypto;

/**
 *
 * @author RED-HAWK
 */
@MultipartConfig
public class UserServlet extends HttpServlet {

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
                request.getSession().setAttribute("navigatedPath", "users");

                switch (request.getServletPath()) {
                    case Route.DISPLAY_USERS_ROUTE:
                        try {
                            request.setAttribute("userList", new UserService().getAll());
                            request.getRequestDispatcher("/auth/users/displayUsers.jsp").forward(request, response);
                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                            try {
                                recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 66 " + MessageConfig.USER_ERROR_2044 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2044_LOCAL, "danger", request);
                                response.sendRedirect(request.getContextPath() + Route.DISPLAY_DASHBOARD_ROUTE);
                            } catch (Exception ex) {
//                                    ex.printStackTrace();
                            }
                        }
                        break;
                    case Route.DISPLAY_USERS_FORM_ROUTE:
                        if ((request.getParameter("uid") != null)) {
                            try {
                                UserInfo uInfo = new UserInfo();
                                uInfo.setId(request.getParameter("uid"));
                                request.setAttribute("selectedUser", new UserService().get(uInfo));
                                request.setAttribute("userList", new UserService().getAll());
                                request.getRequestDispatcher("/auth/users/displayUserForm.jsp").forward(request, response);
                            } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                                try {
                                    recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 84 " + MessageConfig.USER_ERROR_2045 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2045_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_USERS_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    case Route.DISPLAY_REGISTER_USER_FORM_ROUTE:
                        try {
                            request.setAttribute("userList", new UserService().getAll());
                            request.setAttribute("roleList", new RoleService().getAll());
                            request.setAttribute("occupationList", new OccupationService().getAll());
                            request.getRequestDispatcher("/auth/users/newUserForm.jsp").forward(request, response);
                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                            try {
                                recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 103 " + MessageConfig.USER_ERROR_2046 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2046_LOCAL, "danger", request);
                                response.sendRedirect(request.getContextPath() + Route.DISPLAY_USERS_ROUTE);
                            } catch (Exception ex) {
//                                    ex.printStackTrace();
                            }
                        }
                        break;
                    case Route.REGISTER_USER_ROUTE:
                        if ((request.getParameter("uNForm") != null) || (request.getSession().getAttribute("submittedUser") != null)) {
                            try {
                                if (registerUser(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("submittedUser");
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_USERS_ROUTE);
                                } else {
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_REGISTER_USER_FORM_ROUTE);
                                }
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 122 " + MessageConfig.USER_ERROR_2035 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2035_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_USERS_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    case Route.DISPLAY_USER_UPDATE_FORM_ROUTE:
                        if ((request.getParameter("uid") != null) || (request.getSession().getAttribute("selectedUser") != null)) {
                            try {
                                UserInfo uInfo = new UserInfo();
                                uInfo.setId(request.getParameter("uid"));
                                if (request.getSession().getAttribute("selectedUser") == null) {
                                    request.getSession().setAttribute("selectedUser", new UserService().get(uInfo));
                                }
                                request.setAttribute("userList", new UserService().getAll());
                                request.setAttribute("roleList", new RoleService().getAll());
                                request.setAttribute("occupationList", new OccupationService().getAll());
                                request.getRequestDispatcher("/auth/users/updateUserForm.jsp").forward(request, response);
                            } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                                try {
                                    recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 147 " + MessageConfig.USER_ERROR_2047 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2047_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_USERS_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    case Route.UPDATE_USER_ROUTE:
                        if (request.getParameter("uid") != null) {
                            try {
                                if (updateUser(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("selectedUser");
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_USERS_ROUTE);
                                } else {
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_USER_UPDATE_FORM_ROUTE);
                                }
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 169 " + MessageConfig.USER_ERROR_2039 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2039_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_USERS_ROUTE);
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

    private boolean registerUser(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException, IOException, ServletException, Exception {
        if ((request.getParameter("nic") != null)
                && (request.getParameter("fullname") != null)
                && (request.getParameter("displayName") != null)
                && (request.getParameter("officeType") != null)
                && (request.getParameter("occupation") != null)
                && (request.getParameter("role") != null)
                && (request.getParameter("userStatus") != null)
                && (request.getPart("avatar") != null)) {

            if (!validateUploadedAvatar(request)) {
                return false;
            }

            UserInfo user = new UserInfo();
            String imageName = Crypto.generateTimeStampId() + ".png";
            user.setId(Crypto.generateUUID());
            user.setNic(request.getParameter("nic"));
            user.setFullName(request.getParameter("fullname"));
            user.setDisplayName(request.getParameter("displayName"));
            user.setPhotoURL(imageName);
            user.setOffice(request.getParameter("officeType"));
            user.setOccupationId(Integer.parseInt(request.getParameter("occupation")));
            user.setRoleId(request.getParameter("role"));
            user.setActive(request.getParameter("userStatus"));
            request.getSession().setAttribute("submittedUser", user); // Setting submitted user to session 

            if (!validateOccupation(request, user, "add")) {
                return false;
            }

            if (!validateRole(request, user, "add")) {
                return false;
            }

            validatePassword(request, "add", user, null); // default will be set to nic if password is not entered

            UserService userService = new UserService();
            if (userService.add(user)) {
                if (uploadFile(request, imageName)) {
                    recordActivity(MessageConfig.USER_OPERATION_SUCCESSFUL, MessageConfig.USER_SUCCESSFUULY_ADDED + " User id : " + user.getId(), authUser, activityService, activity, request);
                    setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_SUCCESSFUULY_ADDED_NOTIFICATION, "success", request);
                    return true;
                } else {
                    userService.remove(user);
                    recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 235 " + MessageConfig.USER_ERROR_2036, authUser, activityService, activity, request);
                    setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2036_LOCAL, "danger", request);
                    return false;
                }
            } else {
                recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 240 " + MessageConfig.USER_ERROR_2035, authUser, activityService, activity, request);
                setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2035_LOCAL, "danger", request);
                return false;
            }
        } else {
            setFieldsMissingNotification("add", request);
            return false;
        }
    }

    private boolean updateUser(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws IOException, ServletException, Exception {
        if ((request.getParameter("fullname") != null)
                && (request.getParameter("displayName") != null)
                && (request.getParameter("officeType") != null)
                && (request.getParameter("occupation") != null)
                && (request.getParameter("role") != null)
                && (request.getParameter("userStatus") != null)) {

            UserInfo dbUser = (UserInfo) request.getSession().getAttribute("selectedUser");
            UserInfo user = new UserInfo();
            user.setId(dbUser.getId());
            user.setFullName(request.getParameter("fullname"));
            user.setDisplayName(request.getParameter("displayName"));
            user.setOffice(request.getParameter("officeType"));
            user.setOccupationId(Integer.parseInt(request.getParameter("occupation")));
            user.setRoleId(request.getParameter("role"));
            user.setActive(request.getParameter("userStatus"));

            if (!validateOccupation(request, user, "update")) {
                return false;
            }

            if (!validateRole(request, user, "update")) {
                return false;
            }

            validatePassword(request, "update", user, dbUser);

            UserService userService = new UserService();
            if (userService.update(user)) {
                if (validateUploadedAvatar(request)) {
                    if (uploadFile(request, dbUser.getPhotoURL())) {
                        recordActivity(MessageConfig.USER_OPERATION_SUCCESSFUL, MessageConfig.USER_SUCCESSFUULY_UPDATED + " User id : " + user.getId(), authUser, activityService, activity, request);
                        setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_SUCCESSFUULY_UPDATED_NOTIFICATION, "success", request);
                        return true;
                    } else {
                        recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 286 " + MessageConfig.USER_ERROR_2040, authUser, activityService, activity, request);
                        setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2040_LOCAL, "danger", request);
                        return false;
                    }
                } else {
                    recordActivity(MessageConfig.USER_OPERATION_SUCCESSFUL, MessageConfig.USER_SUCCESSFUULY_UPDATED + " User id : " + user.getId(), authUser, activityService, activity, request);
                    setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_SUCCESSFUULY_UPDATED_NOTIFICATION, "success", request);
                    return true;
                }
            } else {
                recordActivity(MessageConfig.USER_OPERATION_FAILED, "Location : UserServlet.java | Line : 296 " + MessageConfig.USER_ERROR_2039, authUser, activityService, activity, request);
                setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2039_LOCAL, "danger", request);
                return false;
            }
        } else {
            setFieldsMissingNotification("update", request);
            return false;
        }
    }

    private boolean validateUploadedAvatar(HttpServletRequest request) throws IOException, ServletException {
        if (((request.getPart("avatar") != null) && (request.getPart("avatar").getContentType().equals("image/png")) || (request.getPart("avatar").getContentType().equals("image/jpeg")))) {
            return true;
        } else {
            setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2038_LOCAL, "danger", request);
            return false; // If image type is not accecpted
        }
    }

    private void validatePassword(HttpServletRequest request, String action, UserInfo user, UserInfo dbUser) throws Exception {
        if ((request.getParameter("password") != null)) {
            user.setPassword(Crypto.generateSecurePassword(request.getParameter("password")));
        } else {
            if (action.equals("add")) {
                user.setPassword(Crypto.generateSecurePassword(request.getParameter("nic")));
            } else {
                user.setPassword(Crypto.generateSecurePassword(dbUser.getPassword()));
            }
        }
    }

    private boolean validateRole(HttpServletRequest request, UserInfo user, String action) throws Exception {
        if (!(request.getParameter("role").equals("unselected"))) {
            user.setRoleId(request.getParameter("role"));
            return true;
        } else {
            setFieldsMissingNotification(action, request);
            return false;
        }
    }

    private boolean validateOccupation(HttpServletRequest request, UserInfo user, String action) throws Exception {
        if (!(request.getParameter("occupation").equals("0"))) {
            user.setOccupationId(Integer.parseInt(request.getParameter("occupation")));
            return true;
        } else {
            setFieldsMissingNotification(action, request);
            return false;
        }
    }

    private boolean uploadFile(HttpServletRequest request, String imageName) throws IOException, ServletException {
        Part filePart = request.getPart("avatar");
        // obtains input stream of the upload file
        InputStream inputStream = filePart.getInputStream();

        // Change the output path accordingly
        OutputStream output = new FileOutputStream(PathConfig.USER_PHOTO_UPLOAD_PATH + imageName);
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) > 0) {
            output.write(buffer);
        }
        File isPhotoSaved = new File(PathConfig.USER_PHOTO_UPLOAD_PATH + imageName);
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

    private void redirectUnauthorizedRequest(String route, UserInfo user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        setNotification(MessageConfig.UNAUTHORIZED_REQUEST_NOTIFICATION_TITLE, user.getDisplayName() + MessageConfig.UNAUTHORIZED_REQUEST_NOTIFICATION, "warning", request);
        switch (route) {
            case "login":
                response.sendRedirect(request.getContextPath() + Route.LOGIN_ROUTE);
                break;
            case "root":
                response.sendRedirect(request.getContextPath() + Route.DISPLAY_USERS_ROUTE);
                break;
        }
    }

    private void setFieldsMissingNotification(String action, HttpServletRequest request) {
        if (action.equals("add")) {
            setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2037_LOCAL, "danger", request);
        } else {
            setNotification(MessageConfig.USER_OPERATION_NOTIFICATION_TITLE, MessageConfig.USER_ERROR_2042_LOCAL, "danger", request);
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
