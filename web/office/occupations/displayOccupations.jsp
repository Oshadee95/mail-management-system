<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

<%@page import="models.Occupation"%>
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
                            if(request.getSession().getAttribute("occupationAction") != null && request.getSession().getAttribute("occupationAction").equals("104")){
                            Occupation occupation = (Occupation) request.getSession().getAttribute("dbOccupation");
                        %>
                        <div class="col-md-4 col-sm-4">
                            <div class="card card-inverse">
                                <div class="card-header m-b-5">
                                    <div class="card-title p-t-10"> occupation update form</div>
                                    <hr>
                                </div>    
                               
                                <form class="form-validate m-b-20" method="POST" action="<%=request.getContextPath()%>/Office/Occupation/105">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Category title </label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="title" class="form-control" value="<%=occupation.getTitle()%>">
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="float-right  m-t-40 m-b-20">
                                            <button type="reset" class="btn btn-md btn-secondary" id="reset"><i class="icon-reload-alt position-left"></i>Reset</button>&nbsp;&nbsp;
                                            <button type="submit" name="oid" value="<%=occupation.getId()%>" class="btn btn-md btn-warning"><i class="icon-briefcase  position-left"></i> Update</button>
                                        </div>
                                    </div>
                                </form>
                               
                            </div>
                        </div>
                        <% }  else { %>
                        <div class="col-md-4 col-sm-4">
                            <div class="card card-inverse">
                                <div class="card-header m-b-5">
                                    <div class="card-title p-t-10"> occupation registration form</div>
                                    <hr>
                                </div>    
                                <% 
                                    Occupation occupation = null;
                                    if(request.getSession().getAttribute("dbOccupation") != null){
                                        occupation = (Occupation) request.getSession().getAttribute("dbOccupation");
                                    }
                                %>
                                <form class="form-validate m-b-20" method="POST" action="<%=request.getContextPath()%>/Office/Occupation/103">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Category name </label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="title" class="form-control" placeholder="Enter category name" value="<%=(occupation != null)? occupation.getTitle(): "" %>">
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="float-right  m-t-40 m-b-20">
                                            <button type="reset" class="btn btn-md btn-secondary" id="reset"><i class="icon-reload-alt position-left"></i>Reset</button>&nbsp;&nbsp;
                                            <button type="submit" name="oNForm" class="btn btn-md btn-primary"><i class="icon-briefcase  position-left"></i> register</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <% } %>
                        
                        <div class="col-md-8 col-sm-">
                            <div class="card card-inverse">
                                <div class="card-block">
                                    <table class="table datatable table-striped table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width: 95vw">Name</th>
                                                <th style="width: 5vw"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Occupation> occupationList = (List<Occupation>) request.getAttribute("occupationList");
                                                for (Occupation oc : occupationList) {
                                            %>
                                            <tr>
                                                <td style="width:95vw"><%=oc.getTitle()%></td>
                                                <td style="width:5vw">
                                                    <form method="POST" action="<%=request.getContextPath()%>/Office/Occupation/104" style="display: inline">
                                                        <button type="submit" name="oid" value="<%=oc.getId()%>" class="btn btn-outline-warning btn-sm"><i class=" icon-editing"></i> &nbsp;Update </button>
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