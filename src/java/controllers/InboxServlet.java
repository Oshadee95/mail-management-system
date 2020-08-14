/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.Category;
import models.InboxInfo;
import models.Notification;
import services.CategoryService;
import services.InboxService;
import services.UserService;
import utils.Crypto;

/**
 *
 * @author RED-HAWK
 */
@MultipartConfig
public class InboxServlet extends HttpServlet {

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

            HttpSession session = request.getSession();

            switch (request.getServletPath()) {
                case "/Mails/Inbox/100":
                    try {
                        request.setAttribute("inboxList", new InboxService().getAll());
                        request.getRequestDispatcher("/mails/inbox/displayInbox.jsp").forward(request, response);
                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve inbox. ECODE - 1007.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                    }
                    break;
                case "/Mails/Inbox/101":
                    if ((request.getParameter("mid") != null)) {
                        try {
                            InboxInfo displayInboxInfo = new InboxInfo();
                            displayInboxInfo.setId(request.getParameter("mid"));
                            request.setAttribute("inboxTemp", new InboxService().get(displayInboxInfo));
                            request.getRequestDispatcher("/mails/inbox/displayInboxForm.jsp").forward(request, response);
                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail. ECODE - 1010.<br>Contact system administrator", "danger"));
                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                        }
                    } else {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Unauthorized request. User is not permitted to perform action", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                    }
                    break;
                case "/Mails/Inbox/102":
                    try {
                        request.setAttribute("categoryList", new CategoryService().getAll());
                        request.setAttribute("userList", new UserService().getAllOnLowLevel());
                        request.getRequestDispatcher("/mails/inbox/newInboxForm.jsp").forward(request, response);
                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open inbox form. ECODE - 1006.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                    }
                    break;
                case "/Mails/Inbox/103":
                    try {
                        request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                        request.getSession().removeAttribute("inboxTemp");
                        if (registerMail(request)) {
                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/102");
                        } else { // TODO : add session to store the form values to sent it back when a error is occured
                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/102");
                        }
                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1005.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/102");
                    }
                    break;
                case "/Mails/Inbox/104":
                    if ((request.getParameter("mid") != null)) {
                        try {
                            InboxInfo iInfo = new InboxInfo();
                            iInfo.setId(request.getParameter("mid"));
                            request.setAttribute("inboxTemp", new InboxService().get(iInfo));
                            request.setAttribute("categoryList", new CategoryService().getAll());
                            request.setAttribute("userList", new UserService().getAllOnLowLevel());
                            request.getRequestDispatcher("/mails/inbox/updateInboxForm.jsp").forward(request, response);
                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open mail. ECODE - 1009.<br>Contact system administrator", "danger"));
                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                        }
                    } else {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Unauthorized request. User is not permitted to perform action", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                    }
                    break;
                case "/Mails/Inbox/105":
                    try {
                        request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                        request.getSession().removeAttribute("inboxTemp");
                        if (updateMail(request)) {
                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                        } else { // TODO : add session to store the form values to sent it back when a error is occured
                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                        }
                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update mail. ECODE - 1011.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                    }
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                    break;

            }
        }
    }

    private boolean registerMail(HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException, ServletException {
        if ((request.getParameter("senderName") != null)
                && (request.getParameter("mailType") != null)
                && (request.getParameter("mailCategory") != null)
                && (request.getParameter("mailRecipient") != null)
                && (request.getParameter("mailBrief") != null
                && (request.getPart("letter") != null))) {

            // obtains the upload file part in this multipart request
            Part filePart = request.getPart("letter");

            if (!((filePart.getContentType().equals("image/png")) || (filePart.getContentType().equals("image/jpeg")))) {
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1008.<br>Uploaded image type not supported", "danger"));
                return false; // If image type is not accecpted
            }

            InboxInfo i = new InboxInfo();
            String mailId = Crypto.generateTimeStampId();
            i.setId(mailId);
            i.setCollectorId("61bf4606-dbf4-4279-9a67-b9e8a878fb7a"); // collector id
            i.setSender(request.getParameter("senderName")); // Sender's name
            i.setType(request.getParameter("mailType")); // mail type
            i.setContent(request.getParameter("mailBrief")); // mail brief
            i.setRecipientId(request.getParameter("mailRecipient")); // mail recipient
            i.setImageURL(mailId + ".png"); // letter url
            String mailCategory = request.getParameter("mailCategory");
            i.setCategoryId(Integer.parseInt(mailCategory)); // mail category id

            createCategory(request, i, mailCategory);

            InboxService ins = new InboxService();

            if (ins.add(i)) {
                uploadFile(request, mailId, filePart);
                request.getSession().setAttribute("notification", new Notification("Success Notification", "Mail successfully registered", "success"));
                return true;
            } else {
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1004.<br>Contact system administrator", "danger"));
                return false; // If new category was selected but values are missing 
            }

        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1001.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
            return false; // if required form values are missing
        }
    }

    private boolean updateMail(HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException, ServletException {
        if ((request.getParameter("mid") != null)
                && (request.getParameter("senderName") != null)
                && (request.getParameter("mailType") != null)
                && (request.getParameter("mailCategory") != null)
                && (request.getParameter("mailRecipient") != null)
                && (request.getParameter("mailBrief") != null)) {

            InboxInfo i = new InboxInfo();
            String mailId = request.getParameter("mid");
            i.setId(mailId);
            i.setSender(request.getParameter("senderName")); // Sender's name
            i.setType(request.getParameter("mailType")); // mail type
            i.setContent(request.getParameter("mailBrief")); // mail brief
            i.setRecipientId(request.getParameter("mailRecipient")); // mail recipient
            String mailCategory = request.getParameter("mailCategory");
            i.setCategoryId(Integer.parseInt(mailCategory)); // mail category id

            createCategory(request, i, mailCategory);

            InboxService ins = new InboxService();

            if (ins.update(i)) {
                if ((request.getPart("letter") != null) && (request.getPart("letter").getContentType().equals("image/png") || request.getPart("letter").getContentType().equals("image/jpeg"))) {
                    uploadFile(request, mailId, request.getPart("letter"));
                } 
                request.getSession().setAttribute("notification", new Notification("Success Notification", "Mail successfully updated", "success"));
                return true;
            } else {
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1004.<br>Contact system administrator", "danger"));
                return false; // If new category was selected but values are missing 
            }

        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1001.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
            return false; // if required form values are missing
        }
    }

    private void uploadFile(HttpServletRequest request, String mailId, Part filePart) throws IOException, ServletException {
        // obtains input stream of the upload file
        InputStream inputStream = filePart.getInputStream();

        // Change the output path accordingly
        OutputStream output = new FileOutputStream("localFilePath/" + mailId + ".png");
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) > 0) {
            output.write(buffer);
        }
    }

    private boolean createCategory(HttpServletRequest request, InboxInfo i, String mailCategory) throws ClassNotFoundException, SQLException {
        if (mailCategory.equals("01")) { // Adding new category before registering new mail
            if ((request.getParameter("newCategoryName") != null)
                    && (request.getParameter("newCategoryDescription") != null)
                    && (request.getParameter("newCategoryName").length() > 2)
                    && (request.getParameter("newCategoryDescription").length() > 2)) {

                CategoryService cs = new CategoryService();
                Category c = new Category();
                c.setName(request.getParameter("newCategoryName"));
                c.setDescription(request.getParameter("newCategoryDescription"));

                if (cs.add(c)) {
                    i.setCategoryId(cs.getLastCategory().getId()); // mail category id
                    return true;
                } else {
                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1003.<br>Contact system administrator", "danger"));
                    return false; // if query execution failed while adding new category
                }
            } else {
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register mail. ECODE - 1002.<br>New category name or description is missing", "danger"));
                return false; // If new category was selected but values are missing 
            }
        }
        return true;
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
