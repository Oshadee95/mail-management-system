<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>
<%@page import="models.UserInfo"%>
<%@page import="models.Occupation"%>
<%@page import="models.Role"%>
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
                        <% UserInfo user = (UserInfo) request.getAttribute("selectedUser");%>
                        <div class="col-md-6 col-sm-6">
                            <div class="card card-inverse">
                                <div class="card-header m-b-20">
                                    <div class="card-title p-t-10">
                                        user information
                                        <a class="example-image-link" href="../../resources/avatars/<%=user.getPhotoURL()%>" data-lightbox="example-2" data-title="<%=user.getFullName()%>">
                                            <img src="../../resources/avatars/<%=user.getPhotoURL()%>" alt="" class="rounded-circle float-right" style="margin-top: -8px; max-height: 50px; height: 50px; width: 50px;">
                                        </a>
                                    </div>
                                    <hr>
                                </div>
                                <div class="card-block">
                                    <fieldset>
                                        <div class="form-group row">
                                            <label class="control-label col-lg-4 d-font">National identification</label>
                                            <div class="col-lg-8">
                                                <input type="text" readonly class="form-control d-font" value="<%=user.getNic()%>">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="control-label col-lg-4 d-font">Full name</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control d-font" readonly value="<%=user.getFullName()%>">
                                            </div>
                                        </div>

                                        <div class="form-group row m-b-40">
                                            <label class="control-label col-lg-4 d-font">Display name</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control d-font" readonly value="<%=user.getDisplayName()%>">
                                            </div>
                                        </div>

                                        <hr>

                                        <div class="form-group row m-t-40">
                                            <label class="control-label col-lg-4 d-font">Office </label>
                                            <div class="col-lg-8">
                                                <label class="radio-inline d-font">
                                                    <input type="radio" value="Government" <%=user.getOffice().equals("Government") ? "checked" : ""%> disabled>
                                                    Government
                                                </label>
                                                <label class="radio-inline d-font">
                                                    <input type="radio" value="Private" <%=user.getOffice().equals("Private") ? "checked" : ""%> disabled>
                                                    Private
                                                </label>
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label class="control-label col-lg-4 d-font">Occupation</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control d-font" value="<%=user.getOccupation()%>" readonly>
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label class="control-label col-lg-4 d-font">Role type</label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control d-font" value="<%=user.getRoleId()%>" readonly>
                                            </div>
                                        </div>


                                        <div class="form-group row p-b-40">
                                            <label class="control-label col-lg-4 d-font">Status</label>
                                            <div class="col-lg-8">
                                                <label class="radio-inline d-font">
                                                    <input type="radio" disabled <%=user.getActive().equals("true") ? "checked" : ""%>>
                                                    Active
                                                </label>
                                                <label class="radio-inline d-font">
                                                    <input type="radio" disabled <%=user.getActive().equals("false") ? "checked" : ""%>>
                                                    Disabled
                                                </label>
                                            </div>
                                        </div>

                                    </fieldset>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6 col-sm-6">
                            <div class="card card-inverse card-flat" style="height: 69vh; overflow-y: auto">
                                <div class="card-header m-b-20">
                                    <div class="card-title p-t-10">Employees</div>
                                    <hr>
                                </div>
                                <div class="card-block p-l-40 p-r-40">
                                    <div class="row">
                                        <%
                                            List<UserInfo> userList = (List<UserInfo>) request.getAttribute("userList");
                                            for (UserInfo u : userList) {
                                        %>
                                        <div class="col-md-2 col-sm-6" title="<%=u.getDisplayName()%>" style="padding: 5px 5px">
                                            <div class="our-team3">
                                                <img class="img-fluid" src="../../resources/avatars/<%=u.getPhotoURL()%>" alt="">
                                            </div>
                                        </div>
                                        <% }%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </section>

            <a id="scrollTop" href="#top"><i class="icon-arrow-up12"></i></a>
        </div>
        <%@ include file="../../layouts/scripts.jsp" %>
    </body>
</html>