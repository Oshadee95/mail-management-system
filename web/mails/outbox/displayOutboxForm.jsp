<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

<%@page import="models.InboxInfo"%>
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
                        <div class="col-md-8 col-sm-8">
                            <img class="img-fluid" src="../../resources/mails/20200813224222.png">
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <div class="card card-inverse">
                                <div class="card-header">
                                    <div class="card-title text-center">Mail Details</div>
                                    <hr>
                                </div>
                                <% InboxInfo inbox = (InboxInfo) request.getAttribute("inboxTemp");%>
                                <div class="card-block">

                                    <div class="form-group row">
                                        <label class="control-label col-lg-4">Mail ID </label>
                                        <div class="col-lg-8">
                                            <p><%=inbox.getId()%></p>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="control-label col-lg-4">Collector's name </label>
                                        <div class="col-lg-8">
                                            <p><%=inbox.getCollectorName()%></p>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="control-label col-lg-4">Letter type</label>
                                        <div class="col-lg-8">
                                            <p><%=inbox.getType()%></p>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="control-label col-lg-4">Category type</label>
                                        <div class="col-lg-7 col-11">
                                            <p><%=inbox.getCategoryName()%></p>
                                        </div>
                                    </div>


                                    <div class="form-group row">
                                        <label class="control-label col-lg-4">Recipient</label>
                                        <div class="col-lg-8">
                                            <p><%=inbox.getRecipientName()%></p>
                                        </div>
                                    </div>


                                    <div class="form-group row">
                                        <label class="control-label col-lg-4">Letter brief</label>
                                        <div class="col-lg-8">
                                            <p><%=inbox.getContent()%></p>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="control-label col-lg-4">Reply status</label>
                                        <div class="col-lg-8">
                                            <p><%=(inbox.getReplied().equals(true)) ? "Replied" : "Not replied"%></p>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="control-label col-lg-4">Submitted On </label>
                                        <div class="col-lg-8">
                                            <p><%=inbox.getRecordedAt()%></p>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </section>

            <a id="scrollTop" href="#top"><i class="icon-arrow-up12"></i></a>
        </div>
        <%@ include file="../../layouts/scripts.jsp" %>
    </body>
</html>