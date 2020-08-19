<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.InboxInfo"%>
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
                        <div class="col-md-12 col-sm-12">
                            <div class="card card-inverse">
                                <div class="card-block">
                                    <table id="mailCategorydt" class="table datatable table-striped table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width: 4vw" class="d-font">Mail ID</th>
                                                <th style="width: 12vw" class="d-font">Type</th>
                                                <th style="width: 20vw" class="d-font">Category</th>
                                                <th style="width: 36vw" class="d-font">Brief</th>
                                                <th style="width: 10vw" class="d-font">Collected on</th>
                                                <th style="width: 18vw" class="d-font"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<InboxInfo> inboxList = (List<InboxInfo>) request.getAttribute("inboxList");
                                                SimpleDateFormat dFomatter = new SimpleDateFormat("MM/dd/yyyy");
                                                for (InboxInfo i : inboxList) {
                                            %>
                                            <tr>
                                                <td style="width: 4vw;" class="d-font"><%=i.getId()%></td>
                                                <td style="width: 12vw;" class="d-font"><%=i.getType().toUpperCase()%></td>
                                                <td style="width: 20vw;" class="d-font"><%=(i.getCategoryName().length() > 22) ? i.getCategoryName().substring(0, 22).concat("...") : i.getCategoryName()%></td>
                                                <td style="width: 36vw;" class="d-font"><%=(i.getContent().length() > 65) ? i.getContent().substring(0, 65).concat("...") : i.getContent()%></td>
                                                <td style="width: 10vw;" class="d-font"><%=dFomatter.format(i.getRecordedAt())%></td>
                                                <td style="width: 18vw; padding: 8px 0 !important;" class="text-center">
                                                    <% if (i.getReplied().equals("false")) {%>
                                                    <form method="POST" action="<%=request.getContextPath()+Route.DISPLAY_REGISTER_OUTBOX_FORM_ROUTE%>" style="display: inline; 6vw !important">
                                                        <button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-primary btn-sm d-btn-font"><i class="icon-reply-all2"></i> &nbsp;&nbsp;Reply &nbsp;</button>
                                                    </form>
                                                    <% } else {%>
                                                    <form  method="POST" action="<%=request.getContextPath()+Route.DISPLAY_OUTBOX_UPDATE_FORM_ROUTE%>"  style="display: inline; 6vw !important">
                                                        <button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-warning btn-sm d-btn-font"><i class="icon-reply-all2"></i> &nbsp;Update </button>
                                                    </form>
                                                    <% }%>
                                                    <form method="POST" action="<%=request.getContextPath()+Route.DISPLAY_OUTBOX_FORM_ROUTE%>" style="display: inline; width: 6vw !important">
                                                        &nbsp;&nbsp;&nbsp;<button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-secondary btn-sm p-x-5 d-btn-font"><i class="icon-eye2"></i>&nbsp;&nbsp; View</button>
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