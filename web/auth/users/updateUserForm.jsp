<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>
<%@page import="configurations.Language"%>
<%@page import="models.UserInfo"%>
<%@page import="models.Occupation"%>
<%@page import="models.Role"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ include file="../../layouts/styles.jsp" %>

    <body id="top" data-gr-c-s-loaded="true" cz-shortcut-listen="true" style="overflow-y: auto; overflow-x: hidden">

        <div id="body-wrapper" class="body-container"  style="overflow: hidden !important;">

            <%@ include file="../../layouts/top-navigation.jsp" %>

            <%@ include file="../../layouts/left-navigation.jsp" %>

            <section class="main-container" style="margin-top: 60px;">

                <div class="container-fluid page-content">
                    <div class="row">
                        <div class="col-lg-12 p-b-20">
                            <ul class="breadcrumb text-right">
                                <li><a href="<%=request.getContextPath()+Route.DISPLAY_DASHBOARD_ROUTE%>"> Dashboard</a></li>
                                <li><a href="<%=request.getContextPath()+Route.DISPLAY_USERS_ROUTE%>"> Users</a></li>
                                <li class="active">Update User</li>
                            </ul>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <div class="card card-inverse">
                                 <% UserInfo aUser = (UserInfo) request.getSession().getAttribute("authUser");  
                                    UserInfo user = (UserInfo) request.getSession().getAttribute("selectedUser");
                                 %>
                                <div class="card-header m-b-20">
                                    <div class="card-title p-t-10">
                                        user information
                                        <a class="example-image-link" href="../../resources/avatars/<%=user.getPhotoURL()%>" data-lightbox="example-2" data-title="<%=user.getFullName()%>">
                                            <img src="../../resources/avatars/<%=user.getPhotoURL()%>" alt="" class="rounded-circle float-right" style="margin-top: -8px; max-height: 50px; height: 50px; width: 50px;">
                                        </a>
                                    </div>
                                    <hr>
                                </div>
                                <form class="form-validate" method="POST" action="<%=request.getContextPath()+Route.UPDATE_USER_ROUTE%>" enctype="multipart/form-data">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">National identification <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="text" readonly class="form-control d-font" value="<%=user.getNic()%>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Full name<span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="text" name="fullname" class="form-control d-font" required value="<%=user.getFullName()%>" maxlength="300" aria-required="true">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Display name<span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="text" name="displayName" class="form-control d-font" required value="<%=user.getDisplayName()%>" maxlength="200" aria-required="true">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">User image</label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="file" name="avatar" class="form-control d-font" accept="image/x-png,image/jpeg">
                                                    <small class="form-text text-muted">Upload an image with 300x300 pixels</small>
                                                </div>
                                            </div>


                                            <div class="form-group row m-b-40">
                                                <label class="control-label col-lg-4 d-font">Password</label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="password" name="password" class="form-control d-font" minlength="6" placeholder="Enter password (minimum 6 characters)">
                                                    <small class="form-text text-muted">Leave blank to use old password</small>
                                                </div>
                                            </div>


                                            <hr>

                                            <div class="form-group row m-t-40">
                                                <label class="control-label col-lg-4 d-font">Office type <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <label class="radio-inline d-font">
                                                        <input type="radio" value="Government" name="officeType" <%=user.getOffice().equals("Government") ? "checked" : ""%> required>
                                                        Government
                                                    </label>
                                                    <label class="radio-inline d-font">
                                                        <input type="radio" value="Private" name="officeType" <%=user.getOffice().equals("Private") ? "checked" : ""%> required>
                                                        Private
                                                    </label>
                                                </div>
                                            </div>


                                             <% if(aUser.getRoleId().equals("SYS_ADMIN")){%>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Occupation <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <select style="background-color: white; padding-top: 5px;" name="occupation" class="form-control d-font" aria-required="true" required>
                                                        <%
                                                            List<Occupation> occupationList = (List<Occupation>) request.getAttribute("occupationList");
                                                            for (Occupation o : occupationList) {
                                                        %>
                                                            <option <%=((user != null) && (user.getOccupationId() == o.getId()))? "selected" : "" %> value="<%=o.getId()%>"><%=o.getTitle()%></option>
                                                        <% }%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Role type <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <select style="background-color: white; padding-top: 5px;" name="role" class="form-control d-font" aria-required="true" required>
                                                        <%
                                                            List<Role> roleList = (List<Role>) request.getAttribute("roleList");
                                                            for (Role r : roleList) {
                                                        %>
                                                        <option <%=((user != null) && (user.getRoleId().equals(r.getId())))? "selected" : "" %> value="<%=r.getId()%>"><%=r.getId()%></option>
                                                        <% };%>
                                                    </select>
                                                </div>
                                            </div>
                                            <% }  else { %>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Occupation <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <select style="background-color: white; padding-top: 5px;" name="occupation" class="form-control d-font" aria-required="true" required>
                                                        <option value="0">Select occupation</option>
                                                        <%
                                                            List<Occupation> occupationList = (List<Occupation>) request.getAttribute("occupationList");
                                                            for (Occupation o : occupationList) {
                                                        %>
                                                        <% if(o.getId() != 2){ %>
                                                            <option <%=((user != null) && (user.getOccupationId() == o.getId()))? "selected" : "" %> value="<%=o.getId()%>"><%=o.getTitle()%></option>
                                                        <% } } %>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Role type <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <select style="background-color: white; padding-top: 5px;" name="role" class="form-control d-font" aria-required="true" required>
                                                        <%
                                                            List<Role> roleList = (List<Role>) request.getAttribute("roleList");
                                                            for (Role r : roleList) {
                                                                if(!((r.getId().equals("SYS_ADMIN")) || (r.getId().equals("GOVERNOR")))){
                                                        %>
                                                                <option <%=((user != null) && (user.getRoleId().equals(r.getId())))? "selected" : "" %> value="<%=r.getId()%>"><%=r.getId()%></option>
                                                        <% } } %>
                                                    </select>
                                                </div>
                                            </div>
                                            <% } %>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Status</label>
                                                <div class="col-lg-8">
                                                    <label class="radio-inline d-font">
                                                        <input type="radio" value="true" name="userStatus" <%=user.getActive().equals("true") ? "checked" : ""%>>
                                                        Active
                                                    </label>
                                                    <label class="radio-inline d-font">
                                                        <input type="radio" value="false" name="userStatus" <%=user.getActive().equals("false") ? "checked" : ""%>>
                                                        Disabled
                                                    </label>
                                                </div>
                                            </div>

                                        </fieldset>
                                        <div class="float-right  m-t-40 m-b-40">
                                            <button type="reset" class="btn btn-md btn-secondary d-font" id="reset"><i class="icon-reload-alt position-left"></i><%=(language.equals("si"))? Language.si_reset :  Language.en_reset%></button>&nbsp;&nbsp;
                                            <button type="submit" name="uid" value="<%=user.getId()%>" class="btn btn-md btn-warning d-font"><i class="icon-envelop position-left"></i><%=(language.equals("si"))? Language.si_update :  Language.en_update%></button>
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
                                                <img src="../../resources/avatars/<%=u.getPhotoURL()%>" alt="">
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