<%-- 
    Document   : inboxMailForm
    Created on : Aug 12, 2020, 4:34:15 PM
    Author     : RED-HAWK
--%>

<%@page import="models.OutboxInfo"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.InboxInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ include file="../../layouts/styles.jsp" %>

    <body id="top" data-gr-c-s-loaded="true" cz-shortcut-listen="true" style="overflow-y: auto; overflow-x: hidden">
        
        <div id="body-wrapper" class="body-container" style="overflow: hidden !important;">

            <%@ include file="../../layouts/top-navigation.jsp" %>

            <%@ include file="../../layouts/left-navigation.jsp" %>

            <section class="main-container" style="margin-top: 60px;">

                <div class="container-fluid page-content">
                    <div class="row">
                         <div class="col-lg-12 p-b-20">
                            <ul class="breadcrumb text-right">
                                <li><a href="<%=request.getContextPath()+Route.DISPLAY_DASHBOARD_ROUTE%>"> Dashboard</a></li>
                                <li><a href="<%=request.getContextPath()+Route.DISPLAY_INBOX_ROUTE %>"> Mails</a></li>
                                <li class="active">Update Reply</li>
                            </ul>
                        </div>
                        <%
                            InboxInfo inbox = (InboxInfo) request.getSession().getAttribute("selectedInbox");
                            OutboxInfo outbox = (OutboxInfo) request.getSession().getAttribute("selectedOutbox");
                            Date date = new Date();
                            SimpleDateFormat dFomatter = new SimpleDateFormat("MM/dd/yyyy");
                            SimpleDateFormat tFomatter = new SimpleDateFormat("HH:mm:ss");
                        %>
                        <div class="col-md-5 col-sm-5">
                            <div class="card card-inverse">
                                <div class="card-header m-b-20">
                                    <div class="card-title p-t-10">
                                        mail information
                                        <img alt="" src="../../resources/avatars/<%=inbox.getCollectorPhotoURL()%>" class="rounded-circle float-right" style="margin-top: 18px; max-height: 50px; height: 50px; width: 50px;">
                                    </div>
                                    <hr>
                                </div>
                                <div class="card-block">
                                    <fieldset>
                                        <div class="form-group row m-b-40">
                                            <label class="control-label col-lg-4 d-font">Mail identification </label>
                                            <div class="col-lg-8">
                                                <input style="color: #848484;" type="text" class="form-control d-font" readonly value="<%=inbox.getId()%>">
                                            </div>
                                        </div>

                                        <hr>

                                        <div class="form-group row m-t-40">
                                            <label class="control-label col-lg-4 d-font">Collector's name </label>
                                            <div class="col-lg-8">
                                                <input style="color: #848484;" type="text" class="form-control d-font" readonly value="<%=inbox.getCollectorName()%>">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="control-label col-lg-4 d-font">Registered date </label>
                                            <div class="col-lg-8">
                                                <input style="color: #848484;" type="text" class="form-control d-font" readonly value="<%=dFomatter.format(inbox.getRecordedAt())%>">
                                            </div>
                                        </div>

                                        <div class="form-group row m-b-40">
                                            <label class="control-label col-lg-4 d-font">Updated date </label>
                                            <div class="col-lg-8">
                                                <input style="color: #848484;" type="text" class="form-control d-font" readonly value="<%=dFomatter.format(inbox.getUpdatedAt())%>">
                                            </div>
                                        </div>

                                        <hr>

                                        <div class="form-group row m-t-40">
                                            <label class="control-label col-lg-4 d-font">recipient's office</label>
                                            <div class="col-lg-8">
                                                <label class="radio-inline d-font">
                                                    <input style="color: #848484;" type="radio" <%=(inbox.getAllocatedOffice().equals("Government")) ? "checked" : ""%> disabled>
                                                    Government
                                                </label>
                                                <label class="radio-inline d-font">
                                                    <input style="color: #848484;" type="radio" <%=(inbox.getAllocatedOffice().equals("Private")) ? "checked" : ""%> disabled>
                                                    Private
                                                </label>
                                            </div>
                                        </div>

                                        <div class="form-group row m-b-40">
                                            <label class="control-label col-lg-4 d-font">recipient's name </label>
                                            <div class="col-lg-8">
                                                <input style="color: #848484;" type="text" class="form-control d-font" readonly value="<%=inbox.getRecipientName()%>">
                                            </div>
                                        </div>

                                        <hr>

                                        <div class="form-group row m-t-40">
                                            <label class="control-label col-lg-4 d-font">Replied date </label>
                                            <div class="col-lg-8">
                                                <input style="color: #848484;" type="text" class="form-control d-font" value="<%=dFomatter.format(outbox.getRepliedAt())%>" readonly>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="control-label col-lg-4 d-font">Replied updated date </label>
                                            <div class="col-lg-8">
                                                <input style="color: #848484;" type="text" class="form-control d-font" value="<%=dFomatter.format(outbox.getUpdatedAt())%>" readonly>
                                            </div>
                                        </div>
                                            
                                        <div class="form-group row m-b-40">
                                            <label class="control-label col-lg-4 d-font">replied by </label>
                                            <div class="col-lg-8">
                                                <input style="color: #848484;" type="text" class="form-control d-font" readonly value="<%=outbox.getSenderName()%>">
                                            </div>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-5 col-sm-5">
                            <div class="card card-inverse">
                                <div class="card-header m-b-20">
                                    <div class="card-title p-t-10">
                                        mail update reply form
                                        <img alt="" src="../../resources/avatars/<%=inbox.getRecipientPhotoURL()%>" class="rounded-circle float-right" style="margin-top: 18px; max-height: 50px; height: 50px; width: 50px;">
                                    </div>
                                    <hr>
                                </div>
                                <div class="card-block">
                                    <form class="form-validate" method="POST" action="<%=request.getContextPath()+Route.UPDATE_OUTBOX_ROUTE%>" enctype="multipart/form-data">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Letter type </label>
                                                <div class="col-lg-8">
                                                    <label class="radio-inline d-font">
                                                        <input type="radio" <%=(inbox.getType().equals("registered")) ? "checked" : ""%> disabled>
                                                        Registered
                                                    </label>
                                                    <label class="radio-inline d-font">
                                                        <input type="radio" <%=(inbox.getType().equals("non-registered")) ? "checked" : ""%> disabled>
                                                        Non-registered
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Category </label>
                                                <div class="col-lg-8">
                                                    <input style="color: #848484;" type="text" class="form-control d-font" value="<%=inbox.getCategoryName()%>" readonly>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Sender's name </label>
                                                <div class="col-lg-8">
                                                    <input style="color: #848484;" type="text" class="form-control d-font" value="<%=inbox.getSender()%>" readonly>
                                                </div>
                                            </div>

                                            <div class="form-group row m-b-40">
                                                <label class="control-label col-lg-4 d-font">Letter brief</label>
                                                <div class="col-lg-8">
                                                    <textarea style="color: #848484;" rows="4" cols="5" class="form-control d-font" readonly><%=inbox.getContent()%></textarea>
                                                </div>
                                            </div>

                                            <hr>

                                            <div class="form-group row m-t-40">
                                                <label class="control-label col-lg-4 d-font">Reply letter<span class="text-danger">*</span></label>
                                                <div class="col-lg-8">
                                                    <input style="background-color: white;" type="file" name="reply" required class="form-control d-font" accept="image/x-png,image/jpeg">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Replied updated date </label>
                                                <div class="col-lg-8">
                                                    <input style="color: #848484;" type="text" class="form-control d-font" value="<%=dFomatter.format(date)%>" readonly>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="control-label col-lg-4 d-font">Replied updated time </label>
                                                <div class="col-lg-8">
                                                    <input style="color: #848484;" type="text" class="form-control d-font" value="<%=tFomatter.format(date)%>" readonly>
                                                </div>
                                            </div>

                                            <div class="float-right  m-t-40 m-b-20">
                                                <button type="reset" class="btn btn-md btn-secondary d-font" id="reset"><i class="icon-reload-alt position-left"></i>Reset</button>&nbsp;&nbsp;
                                                <button type="submit" name="rUFrom" class="btn btn-md btn-warning d-font"><i class="icon-reply  position-left"></i>reply</button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-2">
                            <div class="row">
                                <div class="col-lg-12">
                                    <a class="example-image-link" href="../../resources/mails/inbox/<%=inbox.getImageURL()%>" data-lightbox="example-2" data-title="MAIL ID <%=inbox.getId()%>">
                                        <img class="img-fluid example-image" src="../../resources/mails/inbox/<%=inbox.getImageURL()%>" alt="<%=inbox.getImageURL()%>">
                                    </a>
                                    <a href="../../resources/mails/inbox/<%=inbox.getImageURL()%>" download="<%=inbox.getId()%>-I" class="btn btn-sm float-right p-t-5" style="color : #fff; border-color: #F44336; border-radius: 0; margin-top: -34px; background-color: #F44336; font-size: 14px">INBOX &nbsp;&nbsp;<i style="color : #fff" class="icon-download4"></i></a>
                                </div>
                                <div class="col-lg-12 m-t-10">
                                    <a class="example-image-link" href="../../resources/mails/outbox/<%=outbox.getReplyImageURL()%>" data-lightbox="example-2" data-title="REPLY - MAIL ID <%=inbox.getId()%>">
                                        <img class="img-fluid example-image" src="../../resources/mails/outbox/<%=outbox.getReplyImageURL()%>" alt="<%=outbox.getReplyImageURL()%>">
                                    </a>
                                    <a href="../../resources/mails/outbox/<%=outbox.getReplyImageURL()%>" download="<%=inbox.getId()%>-O" class="btn btn-sm float-right p-t-5" style="color : #fff; border-color: #F44336; border-radius: 0; margin-top: -34px; background-color: #F44336; font-size: 14px">SENT &nbsp;&nbsp;<i style="color : #fff" class="icon-download4"></i></a>
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