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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ActivityInfo;
import models.Category;
import models.Notification;
import models.UserInfo;
import services.ActivityService;
import services.CategoryService;

/**
 *
 * @author RED-HAWK
 */
public class CategoryServlet extends HttpServlet {

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
                request.getSession().setAttribute("navigatedPath", "categories");

                switch (request.getServletPath()) {
                    case Route.DISPLAY_CATEGORIES_ROUTE:
                        try {
                            if ((request.getParameter("reset") != null) && (request.getParameter("reset").equals("true"))) {
                                request.getSession().removeAttribute("dbCategory");
                                request.getSession().removeAttribute("categoryAction");
                            }
                            request.setAttribute("categoryList", new CategoryService().getAll());
                            request.getRequestDispatcher("/mails/categories/displayCategories.jsp").forward(request, response);
                        } catch (Exception e) {
                            try {
                                recordActivity(MessageConfig.CATEGORY_OPERATION_FAILED, "Location : CategoryServlet.java | Line : 57 " + MessageConfig.CATEGORY_ERROR_2004 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_ERROR_2004_LOCAL, "danger", request);
                                response.sendRedirect(request.getContextPath() + Route.DISPLAY_DASHBOARD_ROUTE);
                            } catch (Exception ex) {
//                                    ex.printStackTrace();
                            }
                        }
                        break;
                    case Route.REGISTER_CATEGORY_ROUTE:
                        if ((request.getParameter("cNForm") != null) && (request.getMethod().equals("POST"))) {
                            try {
                                if (addCategory(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("dbCategory");
                                }
                                response.sendRedirect(request.getContextPath() + Route.DISPLAY_CATEGORIES_ROUTE);
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.CATEGORY_OPERATION_FAILED, "Location : CategoryServlet.java | Line : 74 " + MessageConfig.CATEGORY_ERROR_2000 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_ERROR_2000_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_CATEGORIES_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            redirectUnauthorizedRequest("root", authUser, request, response);
                        }
                    case Route.DISPLAY_CATEGORY_UPDATE_FORM_ROUTE:
                        if (request.getParameter("cid") != null) {
                            try {
                                Category category = new Category();
                                category.setId(Integer.parseInt(request.getParameter("cid")));
                                request.getSession().setAttribute("dbCategory", new CategoryService().get(category));
                                request.getSession().setAttribute("categoryAction", "104");
                                response.sendRedirect(request.getContextPath() + Route.DISPLAY_CATEGORIES_ROUTE);
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.CATEGORY_OPERATION_FAILED, "Location : CategoryServlet.java | Line : 94 " + MessageConfig.CATEGORY_ERROR_2005 + " | Error : " + e.getMessage(), authUser, activityService, activity, request);
                                    setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_ERROR_2005_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_CATEGORIES_ROUTE);
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            if (!(request.getServletPath().equals(Route.REGISTER_CATEGORY_ROUTE))) {
                                redirectUnauthorizedRequest("root", authUser, request, response);
                            }
                        }
                        break;
                    case Route.UPDATE_CATEGORY_ROUTE:
                        if ((request.getParameter("cid") != null) && (request.getMethod().equals("POST")) ) {
                            try {
                                if (updateCategory(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("categoryAction");
                                    request.getSession().removeAttribute("dbCategory");
                                }
                                response.sendRedirect(request.getContextPath() + Route.DISPLAY_CATEGORIES_ROUTE);
                            } catch (Exception e) {
                                try {
                                    recordActivity(MessageConfig.CATEGORY_OPERATION_FAILED, "Location : CategoryServlet.java | Line : 117 " + MessageConfig.CATEGORY_ERROR_2002, authUser, activityService, activity, request);
                                    setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_ERROR_2002_LOCAL, "danger", request);
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_CATEGORIES_ROUTE);
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

    private boolean addCategory(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        if ((request.getParameter("cName") != null) && request.getParameter("cDesc") != null) {
            Category category = new Category();
            category.setName(request.getParameter("cName"));
            category.setDescription(request.getParameter("cDesc"));
            request.getSession().setAttribute("dbCategory", category);

            CategoryService categoryService = new CategoryService();
            if (categoryService.add(category)) {
                recordActivity(MessageConfig.CATEGORY_OPERATION_SUCCESSFUL, MessageConfig.CATEGORY_SUCCESSFUULY_ADDED + " Category name : " + category.getName(), authUser, activityService, activity, request);
                setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_SUCCESSFUULY_ADDED_NOTIFICATION, "success", request);
                return true;
            } else {
                recordActivity(MessageConfig.CATEGORY_OPERATION_FAILED, "Location : CategoryServlet.java | Line : 151 " + MessageConfig.CATEGORY_ERROR_2000, authUser, activityService, activity, request);
                setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_ERROR_2000_LOCAL, "danger", request);
                return false;
            }
        } else {
            setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_ERROR_2001_LOCAL, "danger", request);
            return false;
        }
    }

    private boolean updateCategory(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException {
        if ((request.getParameter("cid") != null) && (request.getParameter("cName") != null) && (request.getParameter("cDesc") != null)) {
            Category category = new Category();
            category.setId(Integer.parseInt(request.getParameter("cid")));
            category.setName(request.getParameter("cName"));
            category.setDescription(request.getParameter("cDesc"));
            request.getSession().setAttribute("dbCategory", category);

            CategoryService categoryService = new CategoryService();
            if (categoryService.update(category)) {
                recordActivity(MessageConfig.CATEGORY_OPERATION_SUCCESSFUL, MessageConfig.CATEGORY_SUCCESSFUULY_UPDATED + " Category id : " + category.getId(), authUser, activityService, activity, request);
                setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_SUCCESSFUULY_UPDATED_NOTIFICATION, "success", request);
                return true;
            } else {
                recordActivity(MessageConfig.CATEGORY_OPERATION_FAILED, "Location : CategoryServlet.java | Line : 175 " + MessageConfig.CATEGORY_ERROR_2002, authUser, activityService, activity, request);
                setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_ERROR_2002_LOCAL, "danger", request);
                return false;
            }
        } else {
            setNotification(MessageConfig.CATEGORY_OPERATION_NOTIFICATION_TITLE, MessageConfig.CATEGORY_ERROR_2003_LOCAL, "danger", request);
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
                response.sendRedirect(request.getContextPath() + Route.DISPLAY_CATEGORIES_ROUTE);
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
