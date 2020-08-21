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

        <div id="body-wrapper" class="body-container">

            <%@ include file="../../layouts/top-navigation.jsp" %>

            <%@ include file="../../layouts/left-navigation.jsp" %>

            <section class="main-container" style="margin-top: 60px;">

                <div class="container-fluid page-content">
                    <div class="row">
                        <div class="col-lg-12 p-b-20">
                            <ul class="breadcrumb text-right">
                                <li><a href="<%=request.getContextPath()+Route.DISPLAY_DASHBOARD_ROUTE%>"> Dashboard</a></li>
                                <li class="active">Categories</li>
                            </ul>
                        </div>
                        <%
                            if (request.getSession().getAttribute("categoryAction") != null && request.getSession().getAttribute("categoryAction").equals("104")) {
                                Category category = category = (Category) request.getSession().getAttribute("dbCategory");
                        %>
                        <div class="col-lg-4 col-md-4">
                            <div class="card card-inverse">
                                <div class="card-header m-b-5">
                                    <div class="card-title p-t-10"><%=(language.equals("si"))? Language.si_categoryUpdateForm :  Language.en_categoryUpdateForm%></div>
                                    <hr>
                                </div>    

                                <form class="form-validate m-b-20" method="POST" action="<%=request.getContextPath()+Route.UPDATE_CATEGORY_ROUTE%>">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_categoryName :  Language.en_categoryName%>  <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="cName" class="form-control d-font" value="<%=category.getName()%>" placeholder="<%=(language.equals("si"))? Language.si_enterCategoryName :  Language.en_enterCategoryName%>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_categoryDescription :  Language.en_categoryDescription%> <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="cDesc" class="form-control d-font" value="<%=(category != null) ? category.getDescription() : ""%>" placeholder="<%=(language.equals("si"))? Language.si_enterCategoryDescription :  Language.en_enteeCategoryDescription%>" >
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="float-right  m-t-40 m-b-20">
                                            <a href="<%=request.getContextPath()+Route.DISPLAY_CATEGORIES_ROUTE+"?reset=true"%>" class="btn btn-md btn-secondary d-font" id="reset"><i class="icon-reload-alt position-left"></i><%=(language.equals("si"))? Language.si_cancel :  Language.en_cancel%></a>&nbsp;&nbsp;
                                            <button type="submit" name="cid" value="<%=category.getId()%>" class="btn btn-md btn-warning d-font"><i class="icon-save  position-left"></i> <%=(language.equals("si"))? Language.si_update :  Language.en_update%></button>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                        <% } else { %>
                        <div class="col-ld-4 col-md-4">
                            <div class="card card-inverse">
                                <div class="card-header m-b-5">
                                    <div class="card-title p-t-10"><%=(language.equals("si"))? Language.si_categoryRegistrationForm :  Language.en_categoryRegistrationForm%></div>
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
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_categoryName :  Language.en_categoryName%> <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="cName" class="form-control d-font" placeholder="Enter category name" value="<%=(category != null) ? category.getName() : ""%>" placeholder="<%=(language.equals("si"))? Language.si_enterCategoryName :  Language.en_enterCategoryName%>">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font"><%=(language.equals("si"))? Language.si_categoryDescription :  Language.en_categoryDescription%> <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input type="text" name="cDesc" class="form-control d-font" placeholder="<%=(language.equals("si"))? Language.si_enterCategoryDescription :  Language.en_enteeCategoryDescription%>"  value="<%=(category != null) ? category.getDescription() : ""%>">
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="float-right  m-t-40 m-b-20">
                                            <button type="reset" class="btn btn-md btn-secondary d-font" id="reset"><i class="icon-reload-alt position-left"></i><%=(language.equals("si"))? Language.si_reset :  Language.en_reset%></button>&nbsp;&nbsp;
                                            <button type="submit" name="cNForm" class="btn btn-md btn-primary d-font"><i class="icon-save  position-left"></i> <%=(language.equals("si"))? Language.si_register :  Language.en_register%></button>
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
                                                <th style="width: 30vw" class="d-font"><%=(language.equals("si"))? Language.si_categoryName :  Language.en_categoryName%></th>
                                                <th style="width: 65vw" class="d-font"><%=(language.equals("si"))? Language.si_categoryDescription :  Language.en_categoryDescription%></th>
                                                <th></th>
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
                                                <td class="text-center">
                                                    <ul class="icons-list">
                                                        <li class="dropdown">
                                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <a href="<%=request.getContextPath()+Route.DISPLAY_CATEGORY_UPDATE_FORM_ROUTE+"?cid="+c.getId()%>" class="dropdown-item d-font"><i class="icon-editing"></i> <%=(language.equals("si"))? Language.si_editCategory:  Language.en_editCategory%></a>
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