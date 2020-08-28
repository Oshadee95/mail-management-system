<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

<%@page import="configurations.Language"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.InboxInfo"%>
<%@page import="java.util.List"%>
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
                                <li class="active">My Mail</li>
                            </ul>
                        </div>
                        <div class="col-md-12 col-sm-12">
                            <div class="card card-inverse">
                                <div class="card-block">
                                    <table id="mailCategorydt" class="table datatable table-striped table-responsive"  style="padding-bottom: 10vh">
                                        <thead>
                                            <tr>
                                                <th style="width: 4vw" class="d-font"><%=(language.equals("si"))? Language.si_mailId :  Language.en_mailId%></th>
                                                <th style="width: 12vw" class="d-font"><%=(language.equals("si"))? Language.si_letterType :  Language.en_letterType%></th>
                                                <th style="width: 25vw" class="d-font"><%=(language.equals("si"))? Language.si_categoryType :  Language.en_categoryType%></th>
                                                <th style="width: 35vw" class="d-font"><%=(language.equals("si"))? Language.si_brief :  Language.en_brief%></th>
                                                <th style="width: 10vw" class="d-font"><%=(language.equals("si"))? Language.si_collectedOn :  Language.en_collectedOn%></th>
                                                <th></th>
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
                                                <td class="text-center">
                                                    <ul class="icons-list">
                                                        <li class="dropdown">
                                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="<%=(i.getReplied().equals("false"))? "color:#F44336" : "" %>"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <% if (i.getReplied().equals("false")) {%>
                                                            <a href="<%=request.getContextPath()+Route.DISPLAY_REGISTER_OUTBOX_FORM_ROUTE+"?mid="+i.getId()%>" class="dropdown-item d-font"><i class="icon-reply-all2"></i> <%=(language.equals("si"))? Language.si_reply :  Language.en_reply%></a>
                                                            <% } else {%>
                                                                <a href="<%=request.getContextPath()+Route.DISPLAY_OUTBOX_UPDATE_FORM_ROUTE+"?mid="+i.getId()%>" class="dropdown-item d-font"><i class="icon-reply-all2"></i> <%=(language.equals("si"))? Language.si_editReply :  Language.en_editReply%></a>
                                                            <% }%>
                                                            <a href="<%=request.getContextPath()+Route.DISPLAY_OUTBOX_FORM_ROUTE+"?mid="+i.getId()%>" class="dropdown-item d-font"><i class="icon-eye2"></i> <%=(language.equals("si"))? Language.si_viewMail :  Language.en_viewMail%></a>
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