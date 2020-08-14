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
import models.Notification;

/**
 *
 * @author RED-HAWK
 */
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

            switch (request.getServletPath()) {
                case "/Auth/Users/100":
                    try {
//                        request.setAttribute("inboxList", new InboxService().getAll());
                        request.getRequestDispatcher("/auth/users/displayUsers.jsp").forward(request, response);
                    } catch (IOException | ServletException e) {
//                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve inbox. ECODE - 1007.<br>Contact system administrator", "danger"));
//                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                    }
                    break;
//                case "/Mails/Inbox/101":
//                    if ((request.getParameter("mid") != null)) {
//                        try {
//                            InboxInfo displayInboxInfo = new InboxInfo();
//                            displayInboxInfo.setId(request.getParameter("mid"));
//                            request.setAttribute("inboxTemp", new InboxService().get(displayInboxInfo));
//                            request.getRequestDispatcher("/mails/inbox/displayInboxForm.jsp").forward(request, response);
//                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
//                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail. ECODE - 1010.<br>Contact system administrator", "danger"));
//                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
//                        }
//                    } else {
//                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Unauthorized request. User is not permitted to perform action", "danger"));
//                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
//                    }
//                    break;
//                case "/Mails/Inbox/102":
//                    try {
//                        request.setAttribute("categoryList", new CategoryService().getAll());
//                        request.setAttribute("userList", new UserService().getAllOnLowLevel());
//                        request.getRequestDispatcher("/mails/inbox/newInboxForm.jsp").forward(request, response);
//                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
//                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open inbox form. ECODE - 1006.<br>Contact system administrator", "danger"));
//                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
//                    }
//                    break;
//                case "/Mails/Inbox/103":
//                    try {
//                        request.setCharacterEncoding("UTF-8"); // to read sinhala characters
//                        request.getSession().removeAttribute("inboxTemp");
//                        if (registerMail(request)) {
//                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/102");
//                        } else { // TODO : add session to store the form values to sent it back when a error is occured
//                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/102");
//                        }
//                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
//                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1005.<br>Contact system administrator", "danger"));
//                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/102");
//                    }
//                    break;
//                case "/Mails/Inbox/104":
//                    if ((request.getParameter("mid") != null)) {
//                        try {
//                            InboxInfo iInfo = new InboxInfo();
//                            iInfo.setId(request.getParameter("mid"));
//                            request.setAttribute("inboxTemp", new InboxService().get(iInfo));
//                            request.setAttribute("categoryList", new CategoryService().getAll());
//                            request.setAttribute("userList", new UserService().getAllOnLowLevel());
//                            request.getRequestDispatcher("/mails/inbox/updateInboxForm.jsp").forward(request, response);
//                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
//                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail. ECODE - 1009.<br>Contact system administrator", "danger"));
//                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
//                        }
//                    } else {
//                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Unauthorized request. User is not permitted to perform action", "danger"));
//                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
//                    }
//                    break;
//                case "/Mails/Inbox/105":
//                    try {
//                        request.setCharacterEncoding("UTF-8"); // to read sinhala characters
//                        request.getSession().removeAttribute("inboxTemp");
//                        if (updateMail(request)) {
//                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
//                        } else { // TODO : add session to store the form values to sent it back when a error is occured
//                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
//                        }
//                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
//                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update mail. ECODE - 1011.<br>Contact system administrator", "danger"));
//                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
//                    }
//                    break;
//                default:
//                    response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
//                    break;

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
