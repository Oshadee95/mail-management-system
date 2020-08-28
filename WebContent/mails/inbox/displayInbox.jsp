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
                                <li class="active">Mails</li>
                            </ul>
                        </div>
                        <div class="col-md-12 col-sm-12">
                            <% UserInfo user = (UserInfo) request.getSession().getAttribute("authUser"); %>
                            <div class="card card-inverse">
                                <div class="card-header">
                                    <div class="elements p-t-30">
                                        <% if (!(user.getRoleId().equals("G_SECRETARIAT") || user.getRoleId().equals("G_OPERATOR"))) {%>
                                        <ul class="icons-list">
                                            <li><a href="<%=request.getContextPath()+Route.DISPLAY_REGISTER_INBOX_FORM_ROUTE%>" class="btn btn-link btn-md"><i class="icon-new-tab x3" style="color :#f44455 !important"></i></a></li>
                                        </ul>
                                        <% } %>
                                    </div>
                                </div>
                                <div class="card-block m-t-40">
                                    <table class="table datatable table-striped table-responsive" style="padding-bottom: 10vh">
                                        <thead>
                                            <tr>
                                                <th style="width: 4vw" class="d-font"><%=(language.equals("si"))? Language.si_mailId :  Language.en_mailId%></th>
                                                <th style="width: 12vw" class="d-font"><%=(language.equals("si"))? Language.si_letterType :  Language.en_letterType%></th>
                                                <th style="width: 15vw" class="d-font"><%=(language.equals("si"))? Language.si_categoryType :  Language.en_categoryType%></th>
                                                <th style="width: 25vw" class="d-font"><%=(language.equals("si"))? Language.si_brief :  Language.en_brief%></th>
                                                <th style="width: 12vw" class="d-font"><%=(language.equals("si"))? Language.si_recipientName :  Language.en_recipientName%></th>
                                                <th style="width: 10vw" class="d-font"><%=(language.equals("si"))? Language.si_submittedOn :  Language.en_submittedOn%></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<InboxInfo> inboxList = (List<InboxInfo>) request.getAttribute("inboxList");
                                                SimpleDateFormat dFomatter = new SimpleDateFormat("MM/dd/yyyy");
                                                for (InboxInfo inbox : inboxList) {
                                            %>
                                            <tr>
                                                <td style="width: 4vw;" class="d-font"><%=inbox.getId()%></td>
                                                <td style="width: 12vw;" class="d-font"><%=inbox.getType().toUpperCase()%></td>
                                                <td style="width: 15vw;" class="d-font"><%=(inbox.getCategoryName().length() > 15) ? inbox.getCategoryName().substring(0, 15) + "..." : inbox.getCategoryName()%></td>
                                                <td style="width: 20vw;" class="d-font"><%=(inbox.getContent().length() > 40) ? inbox.getContent().substring(0, 40) + "..." : inbox.getContent()%></td>
                                                <td style="width: 12vw;" class="d-font"><%=inbox.getRecipientName()%></td>
                                                <td style="width: 10vw;" class="d-font"><%=dFomatter.format(inbox.getRecordedAt())%></td>
                                                <td class="text-center">
                                                    <ul class="icons-list">
                                                        <li class="dropdown">
                                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="<%=((inbox.getCollectorId().equals(user.getId())) || ((authUser.getRoleId().equals("SYS_ADMIN") || authUser.getRoleId().equals("GOVERNOR") || authUser.getRoleId().equals("P_SECRETARIAT"))))? "color:#F44336" : "" %>"></a>
                                                        <ul class="dropdown-menu dropdown-menu-right">
                                                            <% if (inbox.getReplied().equals("false")) {%>
                                                                <a href="<%=request.getContextPath()+Route.DISPLAY_REGISTER_OUTBOX_FORM_ROUTE+"?mid="+inbox.getId()%>" class="dropdown-item d-font"><i class="icon-reply-all2"></i> <%=(language.equals("si"))? Language.si_reply :  Language.en_reply%></a>
                                                            <% } else {%>
                                                                <a href="<%=request.getContextPath()+Route.DISPLAY_OUTBOX_UPDATE_FORM_ROUTE+"?mid="+inbox.getId()%>" class="dropdown-item d-font"><i class="icon-reply-all2"></i> <%=(language.equals("si"))? Language.si_updateReply :  Language.en_updateReply%></a>
                                                            <% } %>
                                                            <% if (inbox.getCollectorId().equals(user.getId()) || (user.getRoleId().equals("SYS_ADMIN") || user.getRoleId().equals("GOVERNOR") || user.getRoleId().equals("P_SECRETARIAT"))) {%>
                                                                <a href="<%=request.getContextPath()+Route.DISPLAY_INBOX_UPDATE_FORM_ROUTE+"?mid="+inbox.getId()%>" class="dropdown-item d-font"><i class="icon-editing"></i> <%=(language.equals("si"))? Language.en_editMail :  Language.en_editMail%></a>
                                                            <% }%>
                                                            <a href="<%=request.getContextPath()+Route.DISPLAY_OUTBOX_FORM_ROUTE+"?mid="+inbox.getId()%>" class="dropdown-item d-font"><i class="icon-eye2"></i> <%=(language.equals("si"))? Language.si_viewMail :  Language.en_viewMail%></a>
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