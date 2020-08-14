<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>
<%@page import="models.ActivityInfo"%>
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
                                <div class="card-block m-t-40">
                                    <table class="table datatable table-striped table-responsive">
                                        <thead>
                                            <tr>
                                                <th style="width: 6vw !important">Activity ID</th>
                                                <th style="width: 10vw !important">User Name</th>
                                                <th style="width: 74vw !important">Activity</th>
                                                <th style="width: 10vw !important">Occurred At</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<ActivityInfo> activityList = (List<ActivityInfo>) request.getAttribute("activityList");
                                                for (ActivityInfo i : activityList) {
                                            %>
                                            <tr>
                                                <td style="width: 6vw !important"><%=i.getId()%></td>
                                                <td style="width: 10vw !important"><%=i.getUserName()%></td>
                                                <td style="width: 74vw !important"><%=i.getAction()%></td>
                                                <td style="width: 10vw !important"><%=i.getOccuredAt()%></td>
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