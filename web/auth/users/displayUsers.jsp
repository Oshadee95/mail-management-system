<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>
<%@page import="configurations.Language"%>
<%@page import="models.UserInfo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ include file="../../layouts/styles.jsp" %>

    <body id="top" data-gr-c-s-loaded="true" cz-shortcut-listen="true" style="overflow-y: auto; overflow-x: hidden">

        <div id="preloader" style="display: none;">
            <div id="status" style="display: none;">
                <div class="loader"></div>
            </div>
        </div>

        <div id="body-wrapper" class="body-container" style="overflow: hidden !important;">

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
                                    <table class="table datatable table-striped table-responsive"  style="padding-bottom: 5vh">
                                        <thead>
                                            <tr>
                                                <th style="width: 2vw" class="d-font"></th>
                                                <th style="width: 35vw" class="d-font">Full Name</th>
                                                <th style="width: 10vw" class="d-font">Office</th>
                                                <th style="width: 20vw" class="d-font">Occupation</th>
                                                <th style="width: 10vw" class="d-font">Role</th>
                                                <th style="width: 5vw" class="d-font">Status</th>
                                                <th class="d-font"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<UserInfo> userList = (List<UserInfo>) request.getAttribute("userList");
                                                for (UserInfo u : userList) {
                                            %>
                                            <tr>
                                                <td style="width: 2vw" class="d-font">
                                                    <a class="example-image-link" href="../../resources/avatars/<%=u.getPhotoURL()%>" data-lightbox="example-2" data-title="<%=u.getFullName()%>">
                                                        <img src="../../resources/avatars/<%=u.getPhotoURL()%>" alt="" class="rounded-circle img-sm">
                                                    </a>
                                                </td>
                                                <td style="width: 35vw" class="d-font"><%=u.getFullName()%></td>
                                                <td style="width: 10vw" class="d-font"><%=u.getOffice()%></td>
                                                <td style="width: 20vw" class="d-font"><%=u.getOccupation()%></td>
                                                <td style="width: 10vw" class="d-font"><%=u.getRoleId()%></td>
                                                <td style="width: 5vw" class="d-font"><%=u.getActive().equals("true") ? "Active" : "Disabled"%></td>
                                                <td class="text-center">
                                                    <ul class="icons-list">
                                                        <li class="dropdown">
                                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <a href="<%=request.getContextPath()+Route.DISPLAY_USER_UPDATE_FORM_ROUTE+"?uid="+u.getId()%>" class="dropdown-item d-font"><i class="icon-editing"></i> <%=(language.equals("si"))? Language.si_editUser:  Language.en_editUser%></a>
                                                            <a href="<%=request.getContextPath()+Route.DISPLAY_USERS_FORM_ROUTE+"?uid="+u.getId()%>" class="dropdown-item d-font"><i class="icon-eye2"></i> <%=(language.equals("si"))? Language.si_viewUser:  Language.en_viewUser%></a>
                                                        </ul>
                                                        </li>
                                                    </ul>
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