<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

<%@page import="configurations.Language"%>
<%@page import="models.InboxInfo"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
                                <% 
                                    UserInfo user = (UserInfo) request.getSession().getAttribute("authUser"); 
                                    InboxInfo inbox = null;
                                    if(request.getSession().getAttribute("submittedMail") != null){
                                        inbox = (InboxInfo)request.getSession().getAttribute("submittedMail");
                                    }
                                    Date date = new Date();
                                    SimpleDateFormat dFomatter = new SimpleDateFormat("MM/dd/yyyy");
                                    SimpleDateFormat tFomatter = new SimpleDateFormat("hh:mm:ss");
                                %>
                                <div class="card-header m-b-20">
                                    <div class="card-title p-t-10">
                                       <%=(language.equals("si"))? Language.si_mailRegisterationForm :  Language.en_mailRegisterationForm%>
                                        <img alt="" src="../../resources/avatars/<%=user.getPhotoURL()%>" class="rounded-circle float-right" style="margin-top: 18px; max-height: 50px; height: 50px; width: 50px;">
                                    </div>
                                    <hr>
                                </div>                                
                                <form class="form-validate" method="POST" action="<%=request.getContextPath()+Route.REGISTER_INBOX_ROUTE%>" enctype="multipart/form-data">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_collectorId :  Language.en_collectorId%></label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control d-font" readonly value="<%=user.getId()%>">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_collector_name :  Language.en_collectorName%></label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control d-font" readonly value="<%=user.getDisplayName()%>">
                                                </div>
                                            </div>
                                                
                                            <div class="form-group row m-b-40">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_assignedTo :  Language.en_assignedTo%> <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <select style="background-color: white" name="mailRecipient" class="form-control d-font" required aria-required="true">
                                                        <option value="unselected"><%=(language.equals("si"))? Language.si_selectPerson :  Language.en_selectPerson%></option>
                                                        <optgroup label="<%=(language.equals("si"))? Language.si_private :  Language.en_private%>">
                                                            <%
                                                                List<UserInfo> uPFormList = (List<UserInfo>) request.getAttribute("userList");
                                                                for (User u : uPFormList) {
                                                                    if (u.getOffice().equals("Private") && !(u.getId().equals(user.getId()))) {
                                                            %>
                                                            <option <%=((inbox != null) && (inbox.getRecipientId().equals(u.getId()))) ? "selected" : ""%> value="<%=u.getId()%>"><%=u.getDisplayName()%></option>
                                                            <% } } %>
                                                        </optgroup>
                                                        <optgroup label="<%=(language.equals("si"))? Language.si_government :  Language.en_government%>">
                                                            <%
                                                                List<UserInfo> uGFormList = (List<UserInfo>) request.getAttribute("userList");
                                                                for (User u : uGFormList) {
                                                                     if(u.getOffice().equals("Government") && !(u.getId().equals(user.getId()))){
                                                            %>
                                                                <option value="<%=u.getId()%>" <%=((inbox != null) && (inbox.getRecipientId().equals(u.getId()))) ? "selected" : ""%>><%=u.getFullName()%></option>
                                                            <% } } %>
                                                        </optgroup>
                                                    </select>
                                                </div>
                                            </div>
                                                
                                            <hr>

                                            <div class="form-group row m-t-40">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_senderName :  Language.en_senderName%> <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="text" name="senderName" class="form-control d-font" required placeholder="<%=(language.equals("si"))? Language.si_enterSenderName :  Language.en_enterSenderName%>" aria-required="true" value="<%=(inbox != null) ? inbox.getSender() : ""%>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_letterType :  Language.en_letterType%><span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <label class="radio-inline d-font">
                                                        <input type="radio" value="registered" name="mailType" required <%=((inbox != null) && (inbox.getType().equals("registered"))) ? "checked" : ""%>>
                                                        <%=(language.equals("si"))? Language.si_registered :  Language.en_registered%>
                                                    </label>
                                                    <label class="radio-inline d-font">
                                                        <input type="radio" value="non-registered" name="mailType" required <%=((inbox != null) && (inbox.getType().equals("non-registered"))) ? "checked" : ""%>>
                                                        <%=(language.equals("si"))? Language.si_nonRegistered :  Language.en_nonRegistered%>
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_categoryType :  Language.en_categoryType%> <span class="text-danger">*</span></label>
                                                <div class="col-lg-7 col-11">
                                                    <select style="background-color: white; padding-top: 5px" name="mailCategory" class="form-control d-font" aria-required="true" required>
                                                        <option value="0"><%=(language.equals("si"))? Language.si_selectCategory :  Language.en_selectCategory%></option>
                                                        <option value="01"><%=(language.equals("si"))? Language.si_newCategory :  Language.en_newCategory%></option>
                                                        <%
                                                            List<Category> catFormList = (List<Category>) request.getAttribute("categoryList");
                                                            for (Category c : catFormList) {
                                                        %>
                                                        <option <%=((inbox != null) && (inbox.getCategoryId()== c.getId())) ? "selected" : ""%> value="<%=c.getId()%>"><%=c.getName()%></option>
                                                        <% }; %>
                                                    </select>
                                                </div>
                                                <button type="button" id="addCategory" style="padding-top: 0; padding-left: 0;" class=" col-lg-1 col-1 btn btn-link btn-lg"><i id="newCategoryIcon" class="icon-plus-circle2" style="color : #f44455; padding: 0; float: right"></i></button>
                                            </div>

                                            <div class="form-group row" id="newCategoryDiv" style="display: none">
                                                <div class="col-lg-8 offset-4">
                                                    <input type="text" style="background-color: white" name="newCategoryName" placeholder="<%=(language.equals("si"))? Language.si_enterCategoryName :  Language.en_enterCategoryName%>" class="form-control d-font" value="">
                                                </div>
                                                <div class="col-lg-8 offset-4 m-t-20">
                                                    <textarea style="background-color: white" rows="2" cols="5" name="newCategoryDescription" class="form-control d-font" placeholder="<%=(language.equals("si"))? Language.si_enterCategoryDescription :  Language.en_enteeCategoryDescription%>" aria-required="true"></textarea>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_letterImage :  Language.en_letterImage%><span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white" type="file" name="letter" class="form-control d-font" required aria-required="true" accept="image/x-png,image/jpeg">
                                                </div>
                                            </div>


                                            <div class="form-group row m-b-40">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_letterBrief :  Language.en_letterBrief%> <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <textarea style="background-color: white" rows="4" cols="5" name="mailBrief" class="form-control d-font" required="required" placeholder="<%=(language.equals("si"))? Language.si_enterBriefOfTheLetter :  Language.en_enterBriefOfTheLetter%>" aria-required="true"><%=(inbox != null) ? inbox.getContent(): ""%></textarea>
                                                </div>
                                            </div>

                                            <hr>

                                            <div class="form-group row m-t-40">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_submittedOn :  Language.en_submittedOn%> </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control d-font" readonly value="<%=dFomatter.format(date)%>">
                                                </div>
                                            </div> 
                                                
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_submittedAt :  Language.en_submittedAt%></label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control d-font" readonly value="<%=tFomatter.format(date)%>">
                                                </div>
                                            </div> 

                                        </fieldset>

                                        <div class="float-right  m-t-40 m-b-20">
                                            <button type="reset" class="btn btn-md btn-secondary d-font" id="reset"><i class="icon-reload-alt position-left"></i><%=(language.equals("si"))? Language.si_reset :  Language.en_reset%></button>&nbsp;&nbsp;
                                            <button type="submit" name="nMFrom" class="btn btn-md btn-primary d-font"><i class="icon-envelop  position-left"></i><%=(language.equals("si"))? Language.si_register :  Language.en_register%></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div class="col-md-6 col-sm-6">
                            <div class="card card-inverse">
                                <div class="card-header">
                                    <div class="card-title p-t-10">
                                        <%=(language.equals("si"))? Language.si_categories :  Language.en_categories%>
                                        </div>
                                    <hr>
                                </div>  
                                <div class="card-block" style="padding-top: 0">
                                    <table class="table datatable table-striped table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width: 30vw" class="d-font"><%=(language.equals("si"))? Language.si_categoryName :  Language.en_collectorId%></th>
                                                <th style="width: 70vw" class="d-font"><%=(language.equals("si"))? Language.si_categoryDescription :  Language.en_collectorId%></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Category> catList = (List<Category>) request.getAttribute("categoryList");
                                                for (Category c : catList) {
                                            %>
                                            <tr>
                                                <td style="width:30vw" class="d-font"><%=c.getName()%></td>
                                                <td style="width:70vw" class="d-font"><%=c.getDescription()%></td>
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