<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>
<%@page import="models.UserInfo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ include file="../../layouts/styles.jsp" %>

    <body id="top" data-gr-c-s-loaded="true" cz-shortcut-listen="true" style="overflow: visible;">

        <div id="preloader" style="display: none;">
            <div id="status" style="display: none;">
                <div class="loader"></div>
            </div>
        </div>

        <div id="body-wrapper" class="body-container">

            <%@ include file="../../layouts/top-navigation.jsp" %>

            <%@ include file="../../layouts/left-navigation.jsp" %>

            <section class="main-container" style="margin-top: 60px;">

                <div class="container-fluid page-content">
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="card card-inverse">
                                <div class="card-header">
                                    <div class="elements p-t-30">
                                        <ul class="icons-list">
                                            <li><a href="<%=request.getContextPath()+Route.DISPLAY_REGISTER_USER_FORM_ROUTE%>" class="btn btn-link btn-md"><i class="icon-user-plus x3" style="color :#f44455 !important"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="card-block m-t-40">
                                    <table class="table datatable table-striped table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width: 2vw" class="d-font"></th>
                                                <th style="width: 25vw" class="d-font">Display Name</th>
                                                <th style="width: 10vw" class="d-font">Office</th>
                                                <th style="width: 20vw" class="d-font">Occupation</th>
                                                <th style="width: 10vw" class="d-font">Role</th>
                                                <th style="width: 5vw" class="d-font">Status</th>
                                                <th style="width: 15vw" class="d-font"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<UserInfo> userList = (List<UserInfo>) request.getAttribute("userList");
                                                for (UserInfo u : userList) {
                                            %>
                                            <tr>
                                                <td style="width: 2vw" class="d-font"><img alt="" src="../../resources/avatars/<%=u.getPhotoURL()%>" class="rounded-circle img-sm"></td>
                                                <td style="width: 25vw" class="d-font"><%=u.getDisplayName()%></td>
                                                <td style="width: 10vw" class="d-font"><%=u.getOffice()%></td>
                                                <td style="width: 20vw" class="d-font"><%=u.getOccupation()%></td>
                                                <td style="width: 10vw" class="d-font"><%=u.getRoleId()%></td>
                                                <td style="width: 5vw" class="d-font"><%=u.getActive().equals("true") ? "Active" : "Disabled"%></td>
                                                <td style="width: 15vw; padding: 8px 0;" class="text-center">
                                                    <form method="POST" action="<%=request.getContextPath()+Route.DISPLAY_USER_UPDATE_FORM_ROUTE%>" style="display: inline; 6vw">
                                                        <button type="submit" name="uid" value="<%=u.getId()%>" class="btn btn-outline-warning btn-sm d-btn-font"><i class=" icon-editing"></i> &nbsp;Update</button>
                                                    </form>
                                                    <form method="POST" action="<%=request.getContextPath()+Route.DISPLAY_USERS_FORM_ROUTE%>" style="display: inline; 6vw">
                                                         &nbsp;&nbsp;&nbsp;<button type="submit" name="uid" value="<%=u.getId()%>" class="btn btn-outline-primary btn-sm d-btn-font"><i class="icon-eye2"></i> &nbsp;Open</button>
                                                    </form>
                                                </td>
                                            </tr>
                                            <% }%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </section>

            <a id="scrollTop" href="#top"><i class="icon-arrow-up12"></i></a>
        </div>
        <%@ include file="../../layouts/scripts.jsp" %>
        <script src="../../layouts/lib/js/plugins/tables/datatables/datatables.min.js"></script>
        <script src="../../layouts/lib/js/pages/tables/datatable_basic.js"></script>
    </body>
</html>