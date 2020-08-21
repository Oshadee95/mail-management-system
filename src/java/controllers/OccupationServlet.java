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
import models.Occupation;
import models.UserInfo;
import services.ActivityService;
import services.OccupationService;

/**
 *
 * @author RED-HAWK
 */
public class OccupationServlet extends HttpServlet {

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

                if (authUser.getRoleId().equals("SYS_ADMIN") || authUser.getRoleId().equals("GOVERNOR") || authUser.getRoleId().equals("P_SECRETARIAT")) {
                    ActivityService activityService = new ActivityService();
                    ActivityInfo activity = new ActivityInfo();
                    request.setCharacterEncoding("UTF-8");
                    request.getSession().setAttribute("navigatedPath", "occupations");

                    switch (request.getServletPath()) {
                        case Route.DISPLAY_OCCUPATIONS_ROUTE:
                            try {
                                request.setAttribute("occupationList", new OccupationService().getAll());
                                request.getRequestDispatcher("/office/occupations/displayOccupations.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.OCCUPATION_OPERATION_FAILED, "Location : OccupationServlet.java | Line : 56 " + MessageConfig.OCCUPATION_ERROR_2012 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_ERROR_2012_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_DASHBOARD_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                            break;
                        case Route.REGISTER_OCCUPATIONS_ROUTE:
                            if ((request.getParameter("oNForm") != null) && (request.getMethod().equals("POST"))) {
                                try {
                                    if (addOccupation(request, authUser, activityService, activity)) {
                                        request.getSession().removeAttribute("dbOccupation");
                                    }
                                    response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                                } catch (Exception e) {
                                    try {
                                        recordActivity(MessageConfig.OCCUPATION_OPERATION_FAILED, "Location : OccupationServlet.java | Line : 73 " + MessageConfig.OCCUPATION_ERROR_2008 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                        setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_ERROR_2008_LOCAL, "danger", request);
                                        response.sendRedirect(request.getContextPath() + Route.DISPLAY_OCCUPATIONS_ROUTE);
                                    } catch (Exception ex) {
//                                    ex.printStackTrace();
                                    }
                                }
                            } else {
                                redirectUnauthorizedRequest("root", authUser, request, response);
                            }
                        case Route.DISPLAY_OCCUPATIONS_UPDATE_FORM_ROUTE:
                            if (request.getParameter("oid") != null) {
                                try {
                                    Occupation occupation = new Occupation();
                                    occupation.setId(Integer.parseInt(request.getParameter("oid")));
                                    request.getSession().setAttribute("dbOccupation", new OccupationService().get(occupation));
                                    request.getSession().setAttribute("occupationAction", "104");
                                    response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                                } catch (Exception e) {
                                    try {
                                        recordActivity(MessageConfig.OCCUPATION_OPERATION_FAILED, "Location : OccupationServlet.java | Line : 93 " + MessageConfig.OCCUPATION_ERROR_2013 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                        setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_ERROR_2013_LOCAL, "danger", request);
                                        response.sendRedirect(request.getContextPath() + Route.DISPLAY_OCCUPATIONS_ROUTE);
                                    } catch (Exception ex) {
//                                    ex.printStackTrace();
                                    }
                                }
                            } else {
                                if (!(request.getServletPath().equals("/Office/Occupation/103"))) {
                                    redirectUnauthorizedRequest("root", authUser, request, response);
                                }
                            }
                            break;
                        case Route.UPDATE_OCCUPATIONS_ROUTE:
                            if ((request.getParameter("oid") != null) && (request.getMethod().equals("POST"))) {
                                try {
                                    if (updateOccupation(request, authUser, activityService, activity)) {
                                        request.getSession().removeAttribute("occupationAction");
                                        request.getSession().removeAttribute("dbOccupation");
                                    }
                                    response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                                } catch (Exception e) {
                                    try {
                                        recordActivity(MessageConfig.OCCUPATION_OPERATION_FAILED, "Location : OccupationServlet.java | Line : 1116 " + MessageConfig.OCCUPATION_ERROR_2011 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                        setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_ERROR_2011_LOCAL, "danger", request);
                                        response.sendRedirect(request.getContextPath() + Route.DISPLAY_OCCUPATIONS_ROUTE);
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
                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_DASHBOARD_ROUTE);
                }
            } else {
                redirectUnauthorizedRequest("login", null, request, response);
            }
        }
    }

    private boolean addOccupation(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException {
        if (request.getParameter("title") != null) {
            Occupation occupation = new Occupation();
            occupation.setTitle(request.getParameter("title"));
            request.getSession().setAttribute("dbOccupation", occupation);

            OccupationService occupationService = new OccupationService();
            if (occupationService.add(occupation)) {
                recordActivity(MessageConfig.OCCUPATION_OPERATION_SUCCESSFUL, MessageConfig.OCCUPATION_SUCCESSFUULY_ADDED + " Occupation title : " + occupation.getTitle(), authUser, activityService, activity, request);
                setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_SUCCESSFUULY_ADDED_NOTIFICATION, "success", request);
                return true;
            } else {
                recordActivity(MessageConfig.OCCUPATION_OPERATION_FAILED, "Location : OccupationServlet.java | Line : 149 " + MessageConfig.OCCUPATION_ERROR_2008, authUser, activityService, activity, request);
                setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_ERROR_2008_LOCAL, "danger", request);
                return false;
            }
        } else {
            setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_ERROR_2009_LOCAL, "danger", request);
            return false;
        }
    }

    private boolean updateOccupation(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException {
        if ((request.getParameter("oid") != null) && (request.getParameter("title") != null)) {
            Occupation occupation = new Occupation();
            occupation.setTitle(request.getParameter("title"));
            occupation.setId(Integer.parseInt((request.getParameter("oid"))));
            request.getSession().setAttribute("dbOccupation", occupation);

            OccupationService occupationService = new OccupationService();
            if (occupationService.update(occupation)) {
                recordActivity(MessageConfig.OCCUPATION_OPERATION_SUCCESSFUL, MessageConfig.OCCUPATION_SUCCESSFUULY_UPDATED + " Occupation id : " + occupation.getId(), authUser, activityService, activity, request);
                setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_SUCCESSFUULY_UPDATED_NOTIFICATION, "success", request);
                return true;
            } else {
                recordActivity(MessageConfig.OCCUPATION_OPERATION_FAILED, "Location : OccupationServlet.java | Line : 172 " + MessageConfig.OCCUPATION_ERROR_2010, authUser, activityService, activity, request);
                setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_ERROR_2010_LOCAL, "danger", request);
                return false;
            }
        } else {
            setNotification(MessageConfig.OCCUPATION_OPERATION_NOTIFICATION_TITLE, MessageConfig.OCCUPATION_ERROR_2011_LOCAL, "danger", request);
            return false;
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
                response.sendRedirect(request.getContextPath() + Route.DISPLAY_OCCUPATIONS_ROUTE);
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
