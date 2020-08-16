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
                                    <div class="card-title p-t-10">user registration form</div>
                                    <hr>
                                </div>
                                <form class="form-validate" method="POST" action="<%=request.getContextPath()%>/Auth/Users/103" enctype="multipart/form-data">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">National identification <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="text" name="nic" class="form-control" required placeholder="Enter NIC" maxlength="20" aria-required="true">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Full name<span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="text" name="fullname" class="form-control" required placeholder="Enter full name" maxlength="300" aria-required="true">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Display name<span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="text" name="displayName" class="form-control" required placeholder="Enter first and last name" maxlength="200" aria-required="true">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">User image<span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="file" name="avatar" class="form-control" required aria-required="true" accept="image/x-png,image/jpeg">
                                                    <small class="form-text text-muted">Upload an image with 300x300 pixels</small>
                                                </div>
                                            </div>


                                            <div class="form-group row m-b-40">
                                                <label class="control-label col-lg-4">Password</label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="password" name="password" class="form-control" placeholder="Enter password (minimum 6 characters)">
                                                </div>
                                            </div>


                                            <hr>

                                            <div class="form-group row m-t-40">
                                                <label class="control-label col-lg-4">Office type <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <label class="radio-inline">
                                                        <input type="radio" value="Government" name="officeType" required>
                                                        Govenment
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" value="Private" name="officeType" required>
                                                        Private
                                                    </label>
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Occupation <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <select style="background-color: white" name="occupation" class="form-control" aria-required="true" required>
                                                        <option value="0">Select occupation</option>
                                                        <%
                                                            List<Occupation> occupationList = (List<Occupation>) request.getAttribute("occupationList");
                                                            for (Occupation o : occupationList) {
                                                        %>
                                                        <option value="<%=o.getId()%>"><%=o.getTitle()%></option>
                                                        <% }; %>
                                                    </select>
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Role type <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <select style="background-color: white" name="role" class="form-control" aria-required="true" required>
                                                        <option value="0">Select role</option>
                                                        <%
                                                            List<Role> roleList = (List<Role>) request.getAttribute("roleList");
                                                            for (Role r : roleList) {
                                                        %>
                                                        <option value="<%=r.getId()%>"><%=r.getId()%></option>
                                                        <% };%>
                                                    </select>
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Status</label>
                                                <div class="col-lg-8">
                                                    <label class="radio-inline">
                                                        <input type="radio" value="true" name="userStatus">
                                                        Active
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" value="false" name="userStatus">
                                                        Disabled
                                                    </label>
                                                </div>
                                            </div>

                                        </fieldset>

                                        <div class="float-right  m-t-50 m-b-20">
                                            <button type="reset" class="btn btn-lg btn-secondary" id="reset"><i class="icon-reload-alt position-left"></i>Reset</button>&nbsp;
                                            <button type="submit" class="btn btn-lg btn-success"><i class="icon-arrow-right14"></i>Submit</button>
                                        </div>
                                    </div>
                                </form>
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
                                        <% } %>
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