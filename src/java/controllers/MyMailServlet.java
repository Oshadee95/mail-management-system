/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import configurations.MessageConfig;
import configurations.Route;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ActivityInfo;
import models.Notification;
import models.UserInfo;
import services.ActivityService;
import services.InboxService;

/**
 *
 * @author RED-HAWK
 */
public class MyMailServlet extends HttpServlet {

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
                request.getSession().setAttribute("navigatedPath", "mymail");

                switch (request.getServletPath()) {
                    case Route.DISPLAY_MYMAIL_ROUTE:
                        if (!((authUser.getRoleId().equals("P_OPERATOR")) || (authUser.getRoleId().equals("G_OPERATOR")) || (authUser.getRoleId().equals("SYS_ADMIN")))) {
                            try {
                                request.getSession().setAttribute("previousRoute", Route.DISPLAY_MYMAIL_ROUTE);
                                request.setAttribute("inboxList", new InboxService().getAllByUser(authUser));
                                request.getRequestDispatcher("/mails/myMail/displayMyMail.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.MYMAIL_OPERATION_FAILED, "Location : MyMailServlet.java | Line : 56 " + MessageConfig.MYMAIL_ERROR_2006 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.MYMAIL_OPERATION_NOTIFICATION_TITLE, MessageConfig.MYMAIL_ERROR_2006_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_DASHBOARD_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                        break;
                    default:
                        redirectUnauthorizedRequest("login", authUser, request, response);
                        break;
                }
            } else {
                redirectUnauthorizedRequest("login", null, request, response);
            }
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
        switch (route) {
            case "login":
                response.sendRedirect(request.getContextPath() + Route.LOGIN_ROUTE);
                break;
            case "root":
                setNotification(MessageConfig.UNAUTHORIZED_REQUEST_NOTIFICATION_TITLE, user.getDisplayName() + MessageConfig.UNAUTHORIZED_REQUEST_NOTIFICATION, "warning", request);
                response.sendRedirect(request.getContextPath() + Route.DISPLAY_MYMAIL_ROUTE);
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
