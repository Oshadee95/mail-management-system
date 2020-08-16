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
                        <div class="col-md-6 col-sm-6">
                            <div class="card card-inverse">
                                <div class="card-header m-b-20">
                                    <div class="card-title p-t-10">user info update form</div>
                                    <hr>
                                </div>
                                <% UserInfo user = (UserInfo) request.getAttribute("userTemp");%>
                                <div class="card-block">
                                    <fieldset>
                                        <div class="form-group row">
                                            <label class="control-label col-lg-4">National identification <span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <input style="background-color: white" type="text" readonly class="form-control" value="<%=user.getNic()%>">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="control-label col-lg-4">Full name<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <input style="background-color: white" type="text" name="fullname" class="form-control" required value="<%=user.getFullName()%>" maxlength="300" aria-required="true">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="control-label col-lg-4">Display name<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <input style="background-color: white" type="text" name="displayName" class="form-control" required value="<%=user.getDisplayName()%>" maxlength="200" aria-required="true">
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label class="control-label col-lg-4">User image<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <input style="background-color: white" type="file" name="avatar" class="form-control" accept="image/x-png,image/jpeg">
                                                <small class="form-text text-muted">Upload an image with 300x300 pixels</small>
                                            </div>
                                        </div>


                                        <div class="form-group row m-b-40">
                                            <label class="control-label col-lg-4">Password</label>
                                            <div class="col-lg-8">
                                                <input style="background-color: white" type="password" name="password" class="form-control" minlength="6" placeholder="Enter password (minimum 6 characters)">
                                                <small class="form-text text-muted">Leave blank to use old password</small>
                                            </div>
                                        </div>


                                        <hr>

                                        <div class="form-group row m-t-40">
                                            <label class="control-label col-lg-4">Office type <span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <label class="radio-inline">
                                                    <input type="radio" value="Government" name="officeType" <%=user.getOffice().equals("Government") ? "checked" : ""%> required>
                                                    Government
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" value="Private" name="officeType" <%=user.getOffice().equals("Private") ? "checked" : ""%> required>
                                                    Private
                                                </label>
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label class="control-label col-lg-4">Occupation <span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" value="<%=user.getOccupation()%>">
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label class="control-label col-lg-4">Role type <span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <input type="text" class="form-control" value="<%=user.getRoleId()%>">
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label class="control-label col-lg-4">Status</label>
                                            <div class="col-lg-8">
                                                <label class="radio-inline">
                                                    <input type="radio" disabled <%=user.getActive().equals("true") ? "checked" : ""%>>
                                                    Active
                                                </label>
                                                <label class="radio-inline">
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
                            <div class="card card-inverse card-flat" style="height: 75vh; overflow-y: auto">
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
                                                <img src="../../resources/avatars/<%=u.getPhotoURL()%>" alt="<%=u.getDisplayName()%>">
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