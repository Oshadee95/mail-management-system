<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

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
                                                <th>Mail ID</th>
                                                <th>Type</th>
                                                <th>Category</th>
                                                <th>Brief</th>
                                                <th>Collected By</th>
                                                <th>Recipient</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<InboxInfo> inboxList = (List<InboxInfo>) request.getAttribute("inboxList");
                                                for (InboxInfo i : inboxList) {
                                            %>
                                            <tr>
                                                <td style="width: 6vw !important"><%=i.getId()%></td>
                                                <td style="width: 8vw !important"><%=i.getType()%></td>
                                                <td style="width: 20vw !important"><%=i.getCategoryName()%></td>
                                                <td style="width: 20vw !important"><%=i.getContent()%></td>
                                                <td style="width: 12vw !important"><%=i.getCollectorName()%></td>
                                                <td style="width: 12vw !important"><%=i.getRecipientName()%></td>
                                                <td style="width: 13vw !important;">
                                                 <!--TODO : check outbox reply permission with role-->
                                                <% if (i.getCollectorId().equals("61bf4606-dbf4-4279-9a67-b9e8a878fb7a")) { %> 
                                                <% if(i.getReplied().equals("false")) { %>
                                                    <form method="POST" action="<%=request.getContextPath()%>/Mails/Outbox/102" style="display: inline">
                                                        <button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-primary btn-sm"><i class="icon-reply-all2"></i> &nbsp;&nbsp;Reply &nbsp;</button>
                                                    </form>
                                                    <button type="button" name="mid" value="<%=i.getId()%>" class="btn btn-outline-secondary disabled btn-sm float-right"><i class="icon-eye2"></i> &nbsp;View</button>
                                                <% } else { %>
                                                    <form method="POST" action="<%=request.getContextPath()%>/Mails/Outbox/104" style="display: inline">
                                                        <button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-warning btn-sm"><i class=" icon-editing"></i> &nbsp;Update </button>
                                                    </form>
                                                    <form method="POST" action="<%=request.getContextPath()%>/Mails/Outbox/101" style="display: inline">
                                                        <button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-primary btn-sm float-right"><i class="icon-eye2"></i> &nbsp;View</button>
                                                    </form>
                                                <% } %>
                                                <% } else { %>
                                                    <% if(i.getReplied().equals("false")) { %>
                                                        <button type="button" name="mid" value="<%=i.getId()%>" class="btn btn-outline-secondary disabled btn-sm float-right"><i class="icon-eye2"></i> &nbsp;View</button>
                                                <% } else { %>
                                                    <form method="POST" action="<%=request.getContextPath()%>/Mails/Outbox/101" style="display: inline">
                                                        <button type="submit" name="mid" value="<%=i.getId()%>" class="btn btn-outline-primary btn-sm float-right"><i class="icon-eye2"></i> &nbsp;View</button>
                                                    </form>
                                                <% } %>
                                                <% } %>
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