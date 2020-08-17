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
                                                <th style="width: 4vw">Mail ID</th>
                                                <th style="width: 12vw">Type</th>
                                                <th style="width: 20vw">Category</th>
                                                <th style="width: 30vw">Brief</th>
                                                <th style="width: 12vw">Collected By</th>
                                                <th style="width: 10vw">Collected on</th>
                                                <th style="width: 15vw"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<InboxInfo> inboxList = (List<InboxInfo>) request.getAttribute("inboxList");
                                                SimpleDateFormat dFomatter = new SimpleDateFormat("MM/dd/yyyy");
                                                for (InboxInfo i : inboxList) {
                                            %>
                                            <tr>
                                                <td style="width: 4vw; font-size: 14px"><%=i.getId()%></td>
                                                <td style="width: 12vw; font-size: 13px;"><%=i.getType().toUpperCase()%></td>
                                                <td style="width: 20vw; font-size: 14px"><%=i.getCategoryName()%></td>
                                                <td style="width: 30vw; font-size: 14px"><%=(i.getContent().length() > 65) ? i.getContent().substring(0, 65).concat("...") : i.getContent()%></td>
                                                <td style="width: 12vw; font-size: 14px"><%=i.getCollectorName()%></td>
                                                <td style="width: 10vw; font-size: 14px"><%=dFomatter.format(i.getRecordedAt())%></td>
                                                <td style="width: 18vw; font-size: 14px; padding: 8px 0 !important;" class="text-center">
                                                    <% if (i.getReplied().equals("false")) {%>
                                                    <form method="POST" action="<%=request.getContextPath()%>/Mails/Outbox/102" style="display: inline; 6vw !important">
                                                        <button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-primary btn-sm"><i class="icon-reply-all2"></i> &nbsp;&nbsp;Reply &nbsp;</button>
                                                    </form>
                                                    <% } else {%>
                                                    <form  method="POST" action="<%=request.getContextPath()%>/Mails/Outbox/104"  style="display: inline; 6vw !important">
                                                        <button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-warning btn-sm"><i class="icon-reply-all2"></i> &nbsp;Update </button>
                                                    </form>
                                                    <% }%>
                                                    <form method="POST" action="<%=request.getContextPath()%>/Mails/Outbox/101" style="display: inline; width: 6vw !important">
                                                        &nbsp;&nbsp;&nbsp;<button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-secondary btn-sm p-l-5"><i class="icon-eye2"></i>&nbsp;&nbsp; View</button>
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