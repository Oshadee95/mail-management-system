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
public class DashboardServlet extends HttpServlet {

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
                request.setCharacterEncoding("UTF-8");

                switch (request.getServletPath()) {
                    case Route.DISPLAY_DASHBOARD_ROUTE:
                        resolveRequest(authUser, request, response);
                        break;
                    default:
                        resolveRequest(authUser, request, response);
                        break;
                }
            } else {
                response.sendRedirect(request.getContextPath() + Route.LOGIN_ROUTE);
            }
        }
    }

    private void resolveRequest(UserInfo user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!((user.getRoleId().equals("P_OPERATOR")) || (user.getRoleId().equals("G_OPERATOR")) || (user.getRoleId().equals("SYS_ADMIN")))) {
            response.sendRedirect(request.getContextPath() + Route.DISPLAY_MYMAIL_ROUTE);
        } else {
            response.sendRedirect(request.getContextPath() + Route.DISPLAY_DASHBOARD_ROUTE);
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
