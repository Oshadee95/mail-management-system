<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.User"%>
<%@page import="models.UserInfo"%>
<%@page import="models.Category"%>
<%@page import="java.util.List"%>
<%@page import="models.InboxInfo"%>
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
                                <%
                                    InboxInfo inbox = (InboxInfo) request.getSession().getAttribute("selectedInbox");
                                    Date date = new Date();
                                    SimpleDateFormat dFomatter = new SimpleDateFormat("MM/dd/yyyy");
                                     SimpleDateFormat tFomatter = new SimpleDateFormat("hh:mm:ss");
                                %>
                                <div class="card-header m-b-20">
                                    <div class="card-title p-t-10">
                                        mail registration form
                                        <img alt="" src="../../resources/avatars/<%=inbox.getCollectorPhotoURL()%>" class="rounded-circle float-right" style="margin-top: 18px; max-height: 50px; height: 50px; width: 50px;">
                                    </div>
                                    <hr>
                                </div>   
                                <form class="form-validate" method="POST" action="<%=request.getContextPath()%>/Mails/Inbox/105" enctype="multipart/form-data">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Collector's ID </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=inbox.getCollectorId()%>">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Collector's name </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=inbox.getCollectorName()%>">
                                                </div>
                                            </div>
                                                
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Submitted On </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=dFomatter.format(inbox.getRecordedAt())%>">
                                                </div>
                                            </div>  
                                                
                                            <div class="form-group row m-b-40">
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
                                                           <option <%=(inbox.getRecipientId().equals(u.getId())) ? "selected" : ""%> value="<%=u.getId()%>"><%=u.getDisplayName()%></option>
                                                           <% }
                                                           } %>
                                                       </optgroup>
                                                       <optgroup label="Government">
                                                           <%
                                                               List<UserInfo> uGFormList = (List<UserInfo>) request.getAttribute("userList");
                                                               for (User u : uGFormList) {
                                                                   if (u.getOffice().equals("Government")) {
                                                           %>
                                                           <option <%=(inbox.getRecipientId().equals(u.getId())) ? "selected" : ""%> value="<%=u.getId()%>"><%=u.getDisplayName()%></option>
                                                           <% }
                                                           }%>
                                                       </optgroup>
                                                   </select>
                                               </div>
                                            </div>

                                            <hr>

                                            <div class="form-group row m-t-40">
                                                <label class="control-label col-lg-4">Sender's name <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="text" name="senderName" class="form-control" required value="<%=inbox.getSender()%>" aria-required="true">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Letter type <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <label class="radio-inline">
                                                        <input type="radio" value="registered" name="mailType" required <%=(inbox.getType().equals("registered")) ? "checked" : ""%>>
                                                        Registered
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" value="non-registered" name="mailType" required <%=(inbox.getType().equals("non-registered")) ? "checked" : ""%>>
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
                                                        <option <%=(inbox.getCategoryId() == c.getId()) ? "selected" : ""%> value="<%=c.getId()%>"><%=c.getName()%></option>
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
                                                <label class="control-label col-lg-4">Letter image</label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="file" name="letter" class="form-control" accept="image/x-png,image/jpeg">
                                                </div>
                                            </div>


                                            <div class="form-group row m-b-40">
                                                <label class="control-label col-lg-4">Letter brief <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <textarea style="background-color: white" rows="4" cols="5" name="mailBrief" class="form-control" required="required" aria-required="true"><%=inbox.getContent()%></textarea>
                                                </div>
                                            </div>

                                            <hr>

                                            <div class="form-group row m-t-40">
                                                <label class="control-label col-lg-4">Updated On </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=dFomatter.format(date)%>">
                                                </div>
                                            </div> 
                                                
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Updated At </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=tFomatter.format(date)%>">
                                                </div>
                                            </div> 

                                        </fieldset>

                                        <div class="float-right  m-t-40 m-b-20">
                                            <button type="reset" class="btn btn-md btn-secondary" id="reset"><i class="icon-reload-alt position-left"></i>Reset</button>&nbsp;&nbsp;
                                            <button type="submit" name="mid" value="<%=inbox.getId()%>" class="btn btn-md btn-warning"><i class="icon-envelop position-left"></i>update</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                         <div class="col-md-6 col-sm-6">
                            <div class="card card-inverse">
                                <div class="card-header">
                                    <div class="card-title p-t-10">
                                        categories
                                        </div>
                                    <hr>
                                </div>  
                                <div class="card-block" style="padding-top: 0">
                                    <table class="table datatable table-striped table-responsive">
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
                                <div class="card-header">
                                    <div class="card-title p-t-10">
                                        users
                                    </div>
                                    <hr>
                                </div> 
                                <div class="card-block" style="padding-top: 0">
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