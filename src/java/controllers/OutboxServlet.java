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
import models.InboxInfo;
import models.Notification;
import models.OutboxInfo;
import services.InboxService;
import services.OutboxService;

/**
 *
 * @author RED-HAWK
 */
public class OutboxServlet extends HttpServlet {

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
                case "/Mails/Outbox/100":
                    try {
                        request.setAttribute("inboxList", new InboxService().getAll());
                        request.getRequestDispatcher("/mails/outbox/displayOutbox.jsp").forward(request, response);
                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve inbox. ECODE - 1013.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                    }
                    break;
                case "/Mails/Outbox/101":
                    if ((request.getParameter("mid") != null)) {
                        try {
                            InboxInfo displayInboxInfo = new InboxInfo();
                            displayInboxInfo.setId(request.getParameter("mid"));
                            request.setAttribute("inboxTemp", new InboxService().get(displayInboxInfo));
                            request.getRequestDispatcher("/mails/outbox/displayOutboxForm.jsp").forward(request, response);
                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail. ECODE - 1014.<br>Contact system administrator", "danger"));
                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                        }
                    } else {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Unauthorized request. User is not permitted to perform action", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                    }
                    break;
                case "/Mails/Outbox/102":
                    if ((request.getParameter("mid") != null)) {
                        try {
                            InboxInfo displayInboxInfo = new InboxInfo();
                            displayInboxInfo.setId(request.getParameter("mid"));
                            request.setAttribute("inboxTemp", new InboxService().get(displayInboxInfo));
                            request.getRequestDispatcher("/mails/outbox/newOutboxForm.jsp").forward(request, response);
                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail form. ECODE - 1015.<br>Contact system administrator", "danger"));
                            response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                        }
                    } else {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Unauthorized request. User is not permitted to perform action", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                    }
                    break;
                case "/Mails/Outbox/103":
                    try {
                        request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                        if (replyMail(request)) {
                            request.getSession().removeAttribute("inboxTemp");
                            response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                        } else { // TODO : add session to store the form values to sent it back when a error is occured
                            response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                        }
                    } catch (IOException | ClassNotFoundException | SQLException e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1016.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                    }
                    break;
                case "/Mails/Outbox/104":
                    if ((request.getParameter("mid") != null)) {
                        try {
                            InboxInfo iInfo = new InboxInfo();
                            iInfo.setId(request.getParameter("mid"));
                            request.setAttribute("inboxTemp", new InboxService().get(iInfo));
                            
                            OutboxInfo oInfo = new OutboxInfo();
                            oInfo.setMailId(request.getParameter("mid"));
                            request.setAttribute("outboxTemp", new OutboxService().get(oInfo));
                            
                            request.getRequestDispatcher("/mails/outbox/updateOutboxForm.jsp").forward(request, response);
                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail. ECODE - 1017.<br>Contact system administrator", "danger"));
                            response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                        }
                    } else {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Unauthorized request. User is not permitted to perform action", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                    }
                    break;
                case "/Mails/Outbox/105":
                    try {
                        request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                        if (updateReplyMail(request)) {
                            request.getSession().removeAttribute("inboxTemp");
                            request.getSession().removeAttribute("outboxTemp");
                            response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                        } else { // TODO : add session to store the form values to sent it back when a error is occured
                            response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                        }
                    } catch (IOException | ClassNotFoundException | SQLException e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update mail reply. ECODE - 1018.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                    }
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/Mails/Outbox/100");
                    break;

            }
        }

    }

    private boolean replyMail(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        if ((request.getParameter("mid") != null) && (request.getParameter("reply") != null)) {
            OutboxInfo outboxInfo = new OutboxInfo();
            outboxInfo.setSenderId("61bf4606-dbf4-4279-9a67-b9e8a878fb7a");
            outboxInfo.setMailId(request.getParameter("mid"));
            outboxInfo.setContent(request.getParameter("reply"));

            OutboxService ous = new OutboxService();
            if (ous.add(outboxInfo)) {
                InboxService ins = new InboxService();
                InboxInfo inboxInfo = new InboxInfo();
                inboxInfo.setId(outboxInfo.getMailId());
                inboxInfo.setReplied("true");
                ins.updateStatus(inboxInfo);
                request.getSession().setAttribute("notification", new Notification("Success Notification", "Replied to mail successfully", "success"));
                return true;
            } else {
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1019.<br>Contact system administrator", "danger"));
                return false; // If new category was selected but values are missing 
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to reply mail. ECODE - 1020.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
            return false; // if required form values are missing
        }
    }

    private boolean updateReplyMail(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        if ((request.getParameter("mid") != null) && (request.getParameter("reply") != null)) {
            OutboxInfo outboxInfo = new OutboxInfo();
            outboxInfo.setSenderId("61bf4606-dbf4-4279-9a67-b9e8a878fb7a");
            outboxInfo.setMailId(request.getParameter("mid"));
            outboxInfo.setContent(request.getParameter("reply"));

            OutboxService ous = new OutboxService();
            if (ous.update(outboxInfo)) {
                request.getSession().setAttribute("notification", new Notification("Success Notification", "Updated reply successfully", "success"));
                return true;
            } else {
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update reply. ECODE - 1021.<br>Contact system administrator", "danger"));
                return false; // If new category was selected but values are missing 
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update reply. ECODE - 1022.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
            return false; // if required form values are missing
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
