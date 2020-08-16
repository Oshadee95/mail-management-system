/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
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

                switch (request.getServletPath()) {
                    case "/Mails/MyMail/100":
                        if (!((authUser.getRoleId().equals("P_OPERATOR")) || (authUser.getRoleId().equals("G_OPERATOR")) || (authUser.getRoleId().equals("SYS_ADMIN")))) {
                            try {
                                request.getSession().removeAttribute("selectedOutbox");
                                request.getSession().removeAttribute("selectedInbox");
                                request.getSession().setAttribute("previousRoute", request.getContextPath()+"/Mails/MyMail/100");
                                request.setAttribute("inboxList", new InboxService().getAllByUser(authUser));
                                request.getRequestDispatcher("/mails/myMail/displayMyMail.jsp").forward(request, response);
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("ERROR");
                                    activity.setAction("Location : MyMailServlet.java | Line : 55 | Error : "+e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve mails. ECODE - 1040.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a direct request to route : /Mails/MyMail/100");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName()+" has no permission to access route", "warning"));
                                response.sendRedirect(request.getContextPath());
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                        break;
                    default:
                        try {
                            activity.setUserId(authUser.getId());
                            activity.setType("UNAUTHORIZED-REQ");
                            activity.setAction(authUser.getId() + " made a request to route : /Mails/MyMail/100");
                            activityService.add(activity);
                            request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName()+" has no permission to access route", "warning"));
                            response.sendRedirect(request.getContextPath());
                        } catch (Exception e) {
//                                ex.printStackTrace();
                        }
                        break;
                }
            }
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
