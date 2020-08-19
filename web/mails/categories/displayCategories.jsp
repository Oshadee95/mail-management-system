<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

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

                        <%
                            if (request.getSession().getAttribute("categoryAction") != null && request.getSession().getAttribute("categoryAction").equals("104")) {
                                Category category = category = (Category) request.getSession().getAttribute("dbCategory");
                        %>
                        <div class="col-md-4 col-sm-4">
                            <div class="card card-inverse">
                                <div class="card-header m-b-5">
                                    <div class="card-title p-t-10"> category update form</div>
                                    <hr>
                                </div>    

                                <form class="form-validate m-b-20" method="POST" action="<%=request.getContextPath()+Route.UPDATE_CATEGORY_ROUTE%>">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Category name </label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="cName" class="form-control d-font" value="<%=category.getName()%>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Category description </label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="cDesc" class="form-control d-font" value="<%=(category != null) ? category.getDescription() : ""%>">
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="float-right  m-t-40 m-b-20">
                                            <button type="reset" class="btn btn-md btn-secondary d-font" id="reset"><i class="icon-reload-alt position-left"></i>Reset</button>&nbsp;&nbsp;
                                            <button type="submit" name="cid" value="<%=category.getId()%>" class="btn btn-md btn-warning d-font"><i class="icon-save  position-left"></i> Update</button>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                        <% } else { %>
                        <div class="col-md-4 col-sm-4">
                            <div class="card card-inverse">
                                <div class="card-header m-b-5">
                                    <div class="card-title p-t-10"> category registration form</div>
                                    <hr>
                                </div>    
                                <%
                                    Category category = null;
                                    if (request.getSession().getAttribute("dbCategory") != null) {
                                        category = (Category) request.getSession().getAttribute("dbCategory");
                                    }
                                %>
                                <form class="form-validate m-b-20" method="POST" action="<%=request.getContextPath()+Route.REGISTER_CATEGORY_ROUTE%>">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Category name </label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="cName" class="form-control d-font" placeholder="Enter category name" value="<%=(category != null) ? category.getName() : ""%>">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Category description </label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="cDesc" class="form-control d-font" placeholder="Enter category description" value="<%=(category != null) ? category.getDescription() : ""%>">
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="float-right  m-t-40 m-b-20">
                                            <button type="reset" class="btn btn-md btn-secondary d-font" id="reset"><i class="icon-reload-alt position-left"></i>Reset</button>&nbsp;&nbsp;
                                            <button type="submit" name="cNForm" class="btn btn-md btn-primary d-font"><i class="icon-save  position-left"></i> register</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <% } %>

                        <div class="col-md-8">
                            <div class="card card-inverse">
                                <div class="card-block">
                                    <table id="datatable" class="table datatable table-striped table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width: 30vw" class="d-font">Name</th>
                                                <th style="width: 65vw" class="d-font">Description</th>
                                                <th style="width: 5vw"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Category> catList = (List<Category>) request.getAttribute("categoryList");
                                                for (Category c : catList) {
                                            %>
                                            <tr>
                                                <td style="width:30vw" class="d-font"><%=c.getName()%></td>
                                                <td style="width:65vw" class="d-font"><%=c.getDescription()%></td>
                                                <td style="width:5vw">
                                                    <form method="POST" action="<%=request.getContextPath()+Route.DISPLAY_CATEGORY_UPDATE_FORM_ROUTE%>" style="display: inline">
                                                        <button type="submit" name="cid" value="<%=c.getId()%>" class="btn btn-outline-warning btn-sm d-btn-font"><i class=" icon-editing"></i> &nbsp;Update </button>
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