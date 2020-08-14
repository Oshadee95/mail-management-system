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
import javax.servlet.http.Part;
import models.Notification;
import models.UserInfo;
import services.OccupationService;
import services.RoleService;
import services.UserService;
import utils.Crypto;

/**
 *
 * @author RED-HAWK
 */
@MultipartConfig
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
                        request.setAttribute("userList", new UserService().getAll());
                        request.getRequestDispatcher("/auth/users/displayUsers.jsp").forward(request, response);
                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve users. ECODE - 1025.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                    }
                    break;
                case "/Auth/Users/101":
                    if ((request.getParameter("uid") != null)) {
                        try {
                            UserInfo uInfo = new UserInfo();
                            uInfo.setId(request.getParameter("uid"));
                            request.setAttribute("userTemp", new UserService().get(uInfo));
                            request.getRequestDispatcher("/auth/users/displayUserForm.jsp").forward(request, response);
                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve user. ECODE - 1026.<br>Contact system administrator", "danger"));
                            response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                        }
                    } else {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Unauthorized request. User is not permitted to perform action", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Auth/Users/100");
                    }
                    break;
                case "/Auth/Users/102":
                    try {
                        request.setAttribute("userList", new UserService().getAll());
                        request.setAttribute("roleList", new RoleService().getAll());
                        request.setAttribute("occupationList", new OccupationService().getAll());
                        request.getRequestDispatcher("/auth/users/newUserForm.jsp").forward(request, response);
                    } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve user form. ECODE - 1027.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Auth/Users/100");
                    }
                    break;
                case "/Auth/Users/103":
                    try {
                        request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                        if (registerUser(request)) {
                            request.getSession().removeAttribute("userTemp");
                            response.sendRedirect(request.getContextPath() + "/Auth/Users/102");
                        } else { // TODO : add session to store the form values to sent it back when a error is occured
                            response.sendRedirect(request.getContextPath() + "/Auth/Users/102");
                        }
                    } catch (Exception e) {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register user. ECODE - 1028.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Auth/Users/100");
                    }
                    break;
                case "/Auth/Users/104":
                    if ((request.getParameter("uid") != null)) {
                        try {
                            UserInfo uInfo = new UserInfo();
                            uInfo.setId(request.getParameter("uid"));
                            request.getSession().setAttribute("userTemp", new UserService().get(uInfo));
                            request.setAttribute("userList", new UserService().getAll());
                            request.setAttribute("roleList", new RoleService().getAll());
                            request.setAttribute("occupationList", new OccupationService().getAll());
                            request.getRequestDispatcher("/auth/users/updateUserForm.jsp").forward(request, response);
                        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve user. ECODE - 1029.<br>Contact system administrator", "danger"));
                            response.sendRedirect(request.getContextPath() + "/Auth/Users/100");
                        }
                    } else {
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Unauthorized request. User is not permitted to perform action", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Auth/Users/100");
                    }
                    break;
                case "/Auth/Users/105":
                    try {
                        request.setCharacterEncoding("UTF-8"); // to read sinhala characters
                        if (updateUser(request)) {
                            request.getSession().removeAttribute("userTemp");
                            response.sendRedirect(request.getContextPath() + "/Auth/Users/100");
                        } else { // TODO : add session to store the form values to sent it back when a error is occured
                            response.sendRedirect(request.getContextPath() + "/Auth/Users/100");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update user. ECODE - 1030.<br>Contact system administrator", "danger"));
                        response.sendRedirect(request.getContextPath() + "/Auth/Users/100");
                    }
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/Auth/Users/100");
                    break;

            }
        }
    }

    private boolean registerUser(HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException, ServletException, Exception {
        if ((request.getParameter("nic") != null)
                && (request.getParameter("fullname") != null)
                && (request.getParameter("displayName") != null)
                && (request.getParameter("officeType") != null)
                && (request.getParameter("occupation") != null)
                && (request.getParameter("role") != null)
                && (request.getParameter("userStatus") != null)
                && (request.getPart("avatar") != null)) {

            // obtains the upload file part in this multipart request
            Part filePart = request.getPart("avatar");

            if (!((filePart.getContentType().equals("image/png")) || (filePart.getContentType().equals("image/jpeg")))) {
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register user. ECODE - 1031.<br>Uploaded image type not supported", "danger"));
                return false; // If image type is not accecpted
            }

            UserInfo user = new UserInfo();
            String imageName = Crypto.generateTimeStampId() + ".png";
            user.setId(Crypto.generateUUID());
            user.setNic(request.getParameter("nic"));
            user.setFullName(request.getParameter("fullname"));
            user.setDisplayName(request.getParameter("displayName"));
            user.setPhotoURL(imageName);
            user.setOffice(request.getParameter("officeType"));
            user.setOccupationId(Integer.parseInt(request.getParameter("occupation")));
            user.setRoleId(request.getParameter("role"));
            user.setActive(request.getParameter("userStatus"));

            if ((request.getParameter("password") != null) && request.getParameter("password").length() > 6) {
                user.setPassword(Crypto.generateSecurePassword(request.getParameter("password")));
            } else {
                user.setPassword(Crypto.generateSecurePassword(request.getParameter("nic")));
            }

            UserService us = new UserService();

            if (us.add(user)) {
                uploadFile(request, imageName, filePart);
                request.getSession().setAttribute("notification", new Notification("Success Notification", "User successfully registered", "success"));
                return true;
            } else {
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register user. ECODE - 1032.<br>Contact system administrator", "danger"));
                return false; // If new category was selected but values are missing 
            }

        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to register user. ECODE - 1033.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
            return false; // if required form values are missing
        }
    }

    private boolean updateUser(HttpServletRequest request) throws IOException, ServletException, Exception {
        if ((request.getParameter("fullname") != null)
                && (request.getParameter("displayName") != null)
                && (request.getParameter("officeType") != null)
                && (request.getParameter("occupation") != null)
                && (request.getParameter("role") != null)
                && (request.getParameter("userStatus") != null)) {

            UserInfo tempUserInfo = (UserInfo) request.getSession().getAttribute("userTemp");
            UserInfo user = new UserInfo();
            user.setId(tempUserInfo.getId());
            user.setFullName(request.getParameter("fullname"));
            user.setDisplayName(request.getParameter("displayName"));
            user.setOffice(request.getParameter("officeType"));
            user.setOccupationId(Integer.parseInt(request.getParameter("occupation")));
            user.setRoleId(request.getParameter("role"));
            user.setActive(request.getParameter("userStatus"));

            if ((request.getParameter("password") != null) && request.getParameter("password").length() > 6) {
                user.setPassword(Crypto.generateSecurePassword(request.getParameter("password")));
            } else {
                user.setPassword(tempUserInfo.getPassword());
            }

            UserService us = new UserService();

            if (us.update(user)) {
                if ((request.getPart("avatar") != null) && (request.getPart("avatar").getContentType().equals("image/png") || request.getPart("avatar").getContentType().equals("image/jpeg"))) {
                    uploadFile(request, tempUserInfo.getPhotoURL(), request.getPart("avatar"));
                }
                request.getSession().setAttribute("notification", new Notification("Success Notification", "User successfully updated", "success"));
                return true;
            } else {
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update user. ECODE - 1035.<br>Contact system administrator", "danger"));
                return false; // If new category was selected but values are missing 
            }

        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update user. ECODE - 1036.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
            return false; // if required form values are missing
        }
    }

    private void uploadFile(HttpServletRequest request, String imageName, Part filePart) throws IOException, ServletException {
        // obtains input stream of the upload file
        InputStream inputStream = filePart.getInputStream();

        // Change the output path accordingly
        OutputStream output = new FileOutputStream("C:\\Users\\RED-HAWK\\Documents\\GitHub Projects\\mail-management-system\\web\\resources\\avatars\\" + imageName);
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) > 0) {
            output.write(buffer);
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
