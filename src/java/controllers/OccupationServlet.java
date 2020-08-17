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
import models.Occupation;
import models.UserInfo;
import services.ActivityService;
import services.CategoryService;
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
                ActivityService activityService = new ActivityService();
                ActivityInfo activity = new ActivityInfo();

                switch (request.getServletPath()) {
                    case "/Office/Occupation/100":
                        try {
                            request.setAttribute("occupationList", new OccupationService().getAll());
                            request.getRequestDispatcher("/office/occupations/displayOccupations.jsp").forward(request, response);
                        } catch (Exception e) {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("ERROR");
                                activity.setAction("Location : OccupationServlet.java | Line : 55 | Code : 1090 | Error : " + e.getMessage());
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve occupations. ECODE - 1090.<br>Contact system administrator", "danger"));
                                response.sendRedirect(request.getContextPath() + "/Mails/Inbox/100");
                            } catch (Exception ex) {
//                                    ex.printStackTrace();
                            }
                        }
                        break;
                    case "/Office/Occupation/103":
                        if (request.getParameter("oNForm") != null) {
                            try {
                                if (addOccupation(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("dbOccupation");
                                }
                                response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("ERROR");
                                    activity.setAction("Location : OccupationServlet.java | Line : 91 | Code : 1092 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to add occupation. ECODE - 1092.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a request to route : /Office/Occupation/103");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(request.getContextPath());
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                    case "/Office/Occupation/104":
                        if (request.getParameter("oid") != null) {
                            try {
                                Occupation occupation = new Occupation();
                                occupation.setId(Integer.parseInt(request.getParameter("oid")));
                                request.getSession().setAttribute("dbOccupation", new OccupationService().get(occupation));
                                request.getSession().setAttribute("occupationAction", "104");
                                response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("ERROR");
                                    activity.setAction("Location : OccupationServlet.java | Line : 123 | Code : 1097 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to open update occupation form. ECODE - 1097.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            if (!(request.getServletPath().equals("/Office/Occupation/103"))) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("UNAUTHORIZED-REQ");
                                    activity.setAction(authUser.getId() + " made a request to route : /Office/Occupation/105");
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                    response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                                } catch (Exception e) {
//                                ex.printStackTrace();
                                }
                            }
                        }
                        break;
                    case "/Office/Occupation/105":
                        if (request.getParameter("oid") != null) {
                            try {
                                if (updateOccupation(request, authUser, activityService, activity)) {
                                    request.getSession().removeAttribute("occupationAction");
                                    request.getSession().removeAttribute("dbOccupation");
                                }
                                response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                            } catch (Exception e) {
                                try {
                                    activity.setUserId(authUser.getId());
                                    activity.setType("ERROR");
                                    activity.setAction("Location : OccupationServlet.java | Line : 156 | Code : 1093 | Error : " + e.getMessage());
                                    activityService.add(activity);
                                    request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to retrieve occupation. ECODE - 1093.<br>Contact system administrator", "danger"));
                                    response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                                } catch (Exception ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                activity.setUserId(authUser.getId());
                                activity.setType("UNAUTHORIZED-REQ");
                                activity.setAction(authUser.getId() + " made a request to route : /Office/Occupation/105");
                                activityService.add(activity);
                                request.getSession().setAttribute("notification", new Notification("Unauthorized Request", authUser.getDisplayName() + " has no permission to access route", "warning"));
                                response.sendRedirect(request.getContextPath());
                            } catch (Exception e) {
//                                ex.printStackTrace();
                            }
                        }
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/Office/Occupation/100");
                        break;
                }
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
                activity.setUserId(authUser.getId());
                activity.setType("OS-SUCCESSFUL");
                activity.setAction(authUser.getDisplayName() + " added new occupation : " + occupation.getTitle());
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Success Notification", "Occupation successfully added", "success"));
                return true;
            } else {
                activity.setUserId(authUser.getId());
                activity.setType("OS-ERROR");
                activity.setAction("Location : OccupationServlet.java | Line : 190 | Code : 1094 | Error : Fail to add occupation");
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to add occupation. ECODE - 1094.<br>Contact system administrator", "danger"));
                return false;
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to add occupation. ECODE - 1095.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
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
                activity.setUserId(authUser.getId());
                activity.setType("OS-SUCCESSFUL");
                activity.setAction(authUser.getDisplayName() + " updated occupation : " + occupation.getId());
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Success Notification", "Occupation successfully updated", "success"));
                return true;
            } else {
                activity.setUserId(authUser.getId());
                activity.setType("OS-ERROR");
                activity.setAction("Location : OccupationServlet.java | Line : 190 | Code : 1094 | Error : Fail to update category");
                activityService.add(activity);
                request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update occupation. ECODE - 1094.<br>Contact system administrator", "danger"));
                return false;
            }
        } else {
            request.getSession().setAttribute("notification", new Notification("Error Notification", "Failed to update occupation. ECODE - 1095.<br>Reqired values are missing, Please make sure all required vales are filled", "danger"));
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
