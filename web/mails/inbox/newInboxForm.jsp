<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

<%@page import="models.User"%>
<%@page import="models.UserInfo"%>
<%@page import="models.Category"%>
<%@page import="java.util.List"%>
<%@page import="services.CategoryService"%>
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
                                <div class="card-header">
                                    <div class="card-title">Advanced inputs</div>
                                </div>
                                <form class="form-validate" method="POST" action="<%=request.getContextPath()%>/Mails/Inbox/103" enctype="multipart/form-data">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Collector's ID </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Collector's name </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Sender's name <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="text" name="senderName" class="form-control" required placeholder="Enter sender's name" aria-required="true">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Letter type <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <label class="radio-inline">
                                                        <input type="radio" value="registered" name="mailType" required>
                                                        Registered
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" value="non-registered" name="mailType" required>
                                                        Non-registered
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Category type <span class="text-danger">*</span></label>
                                                <div class="col-lg-7 col-11">
                                                    <select style="background-color: white" name="mailCategory" class="form-control" aria-required="true" required>
                                                        <option value="0">Select category</option>
                                                        <option value="01">New Category</option>
                                                        <%
                                                            List<Category> catFormList = (List<Category>) request.getAttribute("categoryList");
                                                            for (Category c : catFormList) {
                                                        %>
                                                        <option value="<%=c.getId()%>"><%=c.getName()%></option>
                                                        <% }; %>
                                                    </select>
                                                </div>
                                                <button type="button" id="addCategory" style="padding-top: 0; padding-left: 0;" class=" col-lg-1 col-1 btn btn-link btn-lg"><i id="newCategoryIcon" class="icon-plus-circle2" style="color : #6887ff; padding: 0; float: right"></i></button>
                                            </div>

                                            <div class="form-group row" id="newCategoryDiv" style="display: none">
                                                <div class="col-lg-8 offset-4">
                                                    <input type="text" style="background-color: white" name="newCategoryName" placeholder="Enter category name" class="form-control" value="">
                                                </div>
                                                <div class="col-lg-8 offset-4 m-t-20">
                                                    <textarea style="background-color: white" rows="2" cols="5" name="newCategoryDescription" class="form-control" placeholder="Enter category description" aria-required="true"></textarea>
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Assigned to <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <select style="background-color: white" name="mailRecipient" class="form-control" required aria-required="true">
                                                        <option value="unselected">Select person</option>
                                                        <optgroup label="Private">
                                                            <%
                                                                List<UserInfo> uPFormList = (List<UserInfo>) request.getAttribute("userList");
                                                                for (User u : uPFormList) {
                                                                    if (u.getOffice().equals("Private")) {
                                                            %>
                                                            <option value="<%=u.getId()%>"><%=u.getDisplayName()%></option>
                                                            <% } } %>
                                                        </optgroup>
                                                        <optgroup label="Government">
                                                            <%
                                                                List<UserInfo> uGFormList = (List<UserInfo>) request.getAttribute("userList");
                                                                for (User u : uGFormList) {
                                                                     if(u.getOffice().equals("Government")){
                                                            %>
                                                            <option value="<%=u.getId()%>"><%=u.getDisplayName()%></option>
                                                            <% } } %>
                                                        </optgroup>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Letter image<span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="file" name="letter" class="form-control" required aria-required="true" accept="image/x-png,image/jpeg">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Letter brief <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <textarea style="background-color: white" rows="4" cols="5" name="mailBrief" class="form-control" required="required" placeholder="Enter brief of the letter content" aria-required="true"></textarea>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Reply status </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="Not replied">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Submitted At </label>
                                                <div class="col-lg-8">
                                                    <span id="submittedAt"></span>
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
                            <div class="card card-inverse">
                                <div class="card-block">
                                    <table id="mailCategorydt" class="table datatable table-striped table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width: 30vw !important">Name</th>
                                                <th style="width: 70vw !important">Description</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Category> catList = (List<Category>) request.getAttribute("categoryList");
                                                for (Category c : catList) {
                                            %>
                                            <tr>
                                                <td style="width:30vw !important"><%=c.getName()%></td>
                                                <td style="width:70vw !important"><%=c.getDescription()%></td>
                                            </tr>
                                            <% } %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="card card-inverse">
                                <div class="card-block">
                                    <table class="table datatable table-striped table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width: 70vw !important">Employee</th>
                                                <th style="width: 30vw !important">Office</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<UserInfo> uList = (List<UserInfo>) request.getAttribute("userList");
                                                for (User u : uList) {
                                            %>
                                            <tr>
                                                <td style="width:70vw !important"><%=u.getDisplayName()%></td>
                                                <td style="width:30vw !important"><%=u.getOffice()%></td>
                                            </tr>
                                            <% } %>
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
        <script src="../../layouts/lib/js/forms/jquery.validate.js"></script>
        <!--<script src="../../layouts/lib/js/pages/forms/form_validations.js"></script>-->
        <script src="../../layouts/lib/js/plugins/tables/datatables/datatables.min.js"></script>
        <script src="../../layouts/lib/js/pages/tables/datatable_basic.js"></script>
        <script>
            var currentDate = new Date();
            $('#submittedAt').text(currentDate.getDate() + "-" + (currentDate.getMonth() + 1) + "-" + currentDate.getFullYear() + " | " + currentDate.getHours() + ":" + currentDate.getMinutes() + ":" + currentDate.getSeconds())

            $("#addCategory").click(function () {
                $("#newCategoryDiv").toggle();
                var className = $("#newCategoryIcon").attr('class');
                if (className === 'icon-plus-circle2') {
                    $("#newCategoryIcon").attr('class', 'icon-minus-circle2')
                } else {
                    $("#newCategoryIcon").attr('class', 'icon-plus-circle2')
                }
            });
        </script>
    </body>
</html>