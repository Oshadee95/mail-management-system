/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
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

                switch (request.getServletPath()) {
                    case "/Mails/Category/100":
                        try {
                            request.setAttribute("categoryList", new CategoryService().getAll());
                            request.getRequestDispatcher("/mails/categories/displayCategories.jsp").forward(request, response);
                        } catch (Exception e) {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("ERROR");
                                activity.setAction("Location : CategoryServlet.java | Line : 55 | Code : 1090 | Error : " + e.getMessage());
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve categories. ECODE - 1090.<br>Contact system administrator", "danger"));
                                response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                            } catch (Exception ex) {
//                                    ex.printStackTrace();
                            }
                        }
                        break;
                    case "/Mails/Category/103":
                        if (request.getParameter("cNForm") != null) {
                            try {
                                if (addCategory(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("dbCategory");
                                }
                                response.sendRedirect(request.getContextPath() + "/Mails/Category/100");
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("ERROR");
                                    activity.setAction("Location : CategoryServlet.java | Line : 91 | Code : 1092 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to add category. ECODE - 1092.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(request.getContextPath() + "/Mails/Category/100");
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a request to route : /Mails/Category/103");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(request.getContextPath());
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                    case "/Mails/Category/104":
                        if (request.getParameter("cid") != null) {
                            try {
                                Category category = new Category();
                                category.setId(Integer.parseInt(request.getParameter("cid")));
                                request.getSession().setAttribute("dbCategory", new CategoryService().get(category));
                                request.getSession().setAttribute("categoryAction", "104");
                                response.sendRedirect(request.getContextPath() + "/Mails/Category/100");
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("ERROR");
                                    activity.setAction("Location : CategoryServlet.java | Line : 123 | Code : 1097 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open update category form. ECODE - 1097.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(request.getContextPath() + "/Mails/Category/100");
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            if (!(request.getServletPath().equals("/Mails/Category/103"))) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("UNAUTHORIZED-REQ");
                                    activity.setAction(authUser.getId() + " made a request to route : /Mails/Category/105");
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                    response.sendRedirect(request.getContextPath() + "/Mails/Category/100");
                                } catch (Exception e) {
//                                ex.printStackTrace();
                                }
                            }
                        }
                        break;
                    case "/Mails/Category/105":
                        if (request.getParameter("cid") != null) {
                            try {
                                if (updateCategory(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("categoryAction");
                                    request.getSession().removeAttribute("dbCategory");
                                }
                                response.sendRedirect(request.getContextPath() + "/Mails/Category/100");
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("ERROR");
                                    activity.setAction("Location : CategoryServlet.java | Line : 156 | Code : 1093 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve category. ECODE - 1093.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(request.getContextPath() + "/Mails/Category/100");
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a request to route : /Mails/Category/105");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(request.getContextPath());
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/Mails/Category/100");
                        break;
                }
            }
        }
    }

    private boolean addCategory(HttpServletRequest request, UserInfo authUser, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException {
        if ((request.getParameter("cName") != null) && request.getParameter("cDesc") != null) {
            Category category = new Category();
            category.setName(request.getParameter("cName"));
            category.setDescription(request.getParameter("cDesc"));
            request.getSession().setAttribute("dbCategory", category);

            CategoryService categoryService = new CategoryService();
            if (categoryService.add(category)) {
                activity.setUserId(authUser.getId());
                activity.setType("CS-SUCCESSFUL");
                activity.setAction(authUser.getDisplayName() + " added new category : " + category.getName());
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Success Notification", "Category successfully added", "success"));
                return true;
            } else {
                activity.setUserId(authUser.getId());
                activity.setType("CS-ERROR");
                activity.setAction("Location : CategoryServlet.java | Line : 190 | Code : 1094 | Error : Fail to add category");
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to add category. ECODE - 1094.<br>Contact system administrator", "danger"));
                return false;
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to add category. ECODE - 1095.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
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
                activity.setUserId(authUser.getId());
                activity.setType("CS-SUCCESSFUL");
                activity.setAction(authUser.getDisplayName() + " updated category : " + category.getId());
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Success Notification", "Category successfully updated", "success"));
                return true;
            } else {
                activity.setUserId(authUser.getId());
                activity.setType("CS-ERROR");
                activity.setAction("Location : CategoryServlet.java | Line : 190 | Code : 1094 | Error : Fail to update category");
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update category. ECODE - 1094.<br>Contact system administrator", "danger"));
                return false;
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update category. ECODE - 1095.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
            return false;
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
