<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

<%@page import="models.OutboxInfo"%>
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
                        <% InboxInfo inbox = (InboxInfo)request.getAttribute("inboxTemp"); %>
                        <% OutboxInfo outbox = (OutboxInfo)request.getAttribute("outboxTemp"); %>
                        <div class="col-md-8 col-sm-8">
                            <img class="img-fluid" src="../../resources/mails/20200813224222.png">
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <div class="card card-inverse">
                                <div class="card-header">
                                    <div class="card-title">Advanced inputs</div>
                                </div>
                                <form class="form-validate" method="POST" action="<%=request.getContextPath()%>/Mails/Outbox/105">
                                    <div class="card-block">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Collector's name </label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=inbox.getCollectorName()%>">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Sender's name <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=inbox.getSender()%>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Letter type <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=inbox.getType()%>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Category type <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=inbox.getSender()%>">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Assigned to <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input type="text" class="form-control" readonly value="<%=inbox.getRecipientName()%>">
                                                </div>
                                            </div>


                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Letter brief <span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <textarea rows="4" cols="5" class="form-control" readonly><%=inbox.getContent()%></textarea>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4">Reply </label>
                                                <div class="col-lg-8">
                                                    <textarea style="background-color: white" rows="4" cols="5" class="form-control" name="reply"><%=outbox.getContent()%></textarea>
                                                </div>
                                            </div>
                                        </fieldset>

                                        <div class="float-right  m-t-50 m-b-20">
                                            <button type="reset" class="btn btn-lg btn-secondary" id="reset"><i class="icon-reload-alt position-left"></i>Reset</button>&nbsp;
                                            <button type="submit" name="mid" value="<%=inbox.getId()%>" class="btn btn-lg btn-success"><i class="icon-arrow-right14"></i>Submit</button>
                                        </div>
                                    </div>
                                </form>
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