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
                            <% UserInfo user = (UserInfo) request.getSession().getAttribute("authUser"); %>
                            <div class="card card-inverse">
                                <div class="card-header">
                                    <div class="elements p-t-30">
                                        <% if (!(user.getRoleId().equals("G_SECRETARIAT") || user.getRoleId().equals("G_OPERATOR"))) {%>
                                        <ul class="icons-list">
                                            <li><a href="<%=request.getContextPath()+Route.DISPLAY_REGISTER_INBOX_FORM_ROUTE%>" class="btn btn-link btn-md"><i class="icon-new-tab x3" style="color :#24ab8f!important"></i></a></li>
                                        </ul>
                                        <% } %>
                                    </div>
                                </div>
                                <div class="card-block m-t-40">
                                    <table class="table datatable table-striped table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width: 4vw" class="d-font"><%=(language.equals("si"))? Language.si_mailid :  Language.en_mailid%></th>
                                                <th style="width: 12vw" class="d-font"><%=(language.equals("si"))? Language.si_letterType :  Language.en_letterType%></th>
                                                <th style="width: 15vw" class="d-font"><%=(language.equals("si"))? Language.si_categoryType :  Language.en_categoryType%></th>
                                                <th style="width: 20vw" class="d-font"><%=(language.equals("si"))? Language.si_brief :  Language.en_brief%></th>
                                                <th style="width: 12vw" class="d-font"><%=(language.equals("si"))? Language.si_recipientName :  Language.en_recipientName%></th>
                                                <th style="width: 10vw" class="d-font"><%=(language.equals("si"))? Language.si_submittedOn :  Language.en_submittedOn%></th>
                                                <th style="width: 10vw" class="d-font"></th>
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
                                                        <li><form method="POST" action="<%=request.getContextPath()+Route.DISPLAY_OUTBOX_FORM_ROUTE%>" style="display: inline;">
                                                                <button type="submit" name="mid" value="<%=inbox.getId()%>" class="btn btn-link btn-md d-btn-font"><i class="icon-eye2"></i></button>
                                                            </form></li>
                                                        <li class="dropdown">
                                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"></a>
                                                            <ul class="dropdown-menu dropdown-menu-right">
                                                                <% if (inbox.getReplied().equals("false")) {%>
                                                                <form method="POST" action="<%=request.getContextPath()+Route.DISPLAY_REGISTER_OUTBOX_FORM_ROUTE%>">
                                                                    <button type="submit" name="mid" value="<%=inbox.getId()%>" class="float-left btn btn-link dropdown-item d-btn-font"><i class="icon-reply-all2"></i>&nbsp;&nbsp;&nbsp; <%=(language.equals("si"))? Language.si_reply :  Language.en_reply%> &nbsp;</button>
                                                                </form>
                                                                <% } else {%>
                                                                <form  method="POST" action="<%=request.getContextPath()+Route.DISPLAY_OUTBOX_UPDATE_FORM_ROUTE%>">
                                                                    <button type="submit" name="mid" value="<%=inbox.getId()%>" class="float-left btn btn-link dropdown-item d-btn-font"><i class="icon-reply-all2"></i>&nbsp;&nbsp;&nbsp; <%=(language.equals("si"))? Language.si_updateReply :  Language.en_updateReply%></button>
                                                                </form>
                                                                <% } %>
                                                                <% if (inbox.getCollectorId().equals(user.getId())) {%>
                                                                <form method="POST" action="<%=request.getContextPath()+Route.DISPLAY_INBOX_UPDATE_FORM_ROUTE%>">
                                                                    <button type="submit" name="mid" value="<%=inbox.getId()%>" class="float-left btn btn-link dropdown-item d-btn-font"><i class=" icon-editing"></i> &nbsp;<%=(language.equals("si"))? Language.si_updateMail :  Language.en_updateMail%></button>
                                                                </form>
                                                                <% }%>
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