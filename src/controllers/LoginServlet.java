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
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ActivityInfo;
import models.UserInfo;
import services.ActivityService;
import services.UserService;

/**
 *
 * @author RED-HAWK
 */
public class LoginServlet extends HttpServlet {

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

            request.setCharacterEncoding("UTF-8");
            if (request.getSession().getAttribute("authUser") == null) {

                switch (request.getServletPath()) {
                    case "/Login":
                        request.getRequestDispatcher("/login/index.jsp").forward(request, response);
                        break;
                    case "/Login/Authenticate":
                        try {
                           out.print("<img src="+"../layouts/lib/css/loader.gif"+" style="+"margin-top:25%;margin-left:50%;"+">");
                            if (request.getParameter("username") != null && request.getParameter("password") != null) {
                                UserService userService = new UserService();
                                UserInfo user = new UserInfo();
                                user.setNic(request.getParameter("username"));
                                user.setPassword(request.getParameter("password"));

                                UserInfo authUser = userService.getUserAuthenticated(user);
                                if (authUser != null) {
                                    request.getSession().removeAttribute("loginError");
                                    request.getSession().setAttribute("authUser", authUser);
                                    Instant nowUtc = Instant.now();
                                    ZoneId sriLankanStandardTime = ZoneId.of("Asia/Kolkata");
                                    ZonedDateTime sriLankantp = ZonedDateTime.ofInstant(nowUtc, sriLankanStandardTime);
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy | HH:mm:ss");
                                    request.getSession().setAttribute("language", "en");
                                    recordActivity(MessageConfig.LOGIN_OPERATION_SUCCESSFUL, authUser.getDisplayName() + " logged into the system at " + formatter.format(sriLankantp), authUser, new ActivityService(), new ActivityInfo());
                                    response.sendRedirect(request.getContextPath() + Route.DISPLAY_DASHBOARD_ROUTE);
                                } else {
                                    request.getSession().setAttribute("loginError", true);
                                    response.sendRedirect(request.getContextPath() + Route.LOGIN_ROUTE);
                                }
                            } else {
                                request.getSession().setAttribute("loginError", true);
                                response.sendRedirect(request.getContextPath() + Route.LOGIN_ROUTE);
                            }
                        } catch (Exception e) {
                            response.sendRedirect(request.getContextPath() + Route.LOGIN_ROUTE);
                        }
                        break;
                }
            } else {
                response.sendRedirect(request.getContextPath() + Route.DISPLAY_DASHBOARD_ROUTE);
            }
        }
    }

    private void recordActivity(String type, String operation, UserInfo user, ActivityService activityService, ActivityInfo activity) throws ClassNotFoundException, SQLException {
        activity.setUserId(user.getId());
        activity.setType(type);
        activity.setAction(operation);
        activityService.add(activity);
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
