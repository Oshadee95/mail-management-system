<%-- 
    Document   : index
    Created on : Aug 18, 2020, 1:12:08 AM
    Author     : RED-HAWK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>MMS | CP</title>

        <link href="layouts/img/favicon.ico" rel="apple-touch-icon" type="image/png" sizes="144x144">
        <link href="layouts/img/favicon.ico" rel="apple-touch-icon" type="image/png" sizes="114x114">
        <link href="layouts/img/favicon.ico" rel="apple-touch-icon" type="image/png" sizes="72x72">
        <link href="layoutsimg/favicon.ico" rel="apple-touch-icon" type="image/png">
        <link href="layouts/img/favicon.ico" rel="icon" type="image/png">
        <link href="layouts/img/favicon.ico" rel="shortcut icon">


        <link type="text/css" rel="stylesheet" href="layouts/lib/css/bootstrap.css">
        <link type="text/css" rel="stylesheet" href="layouts/lib/css/animate.min.css">
        <link type="text/css" rel="stylesheet" href="layouts/lib/css/main.css">

    </head>
    <body>
        <div class="auth-container">
            <div class="center-block">
                <div class="auth-module">
                    <div class="toggle" style="display: none"></div>

                    <div class="form">
                        <h1 class="text-light text-center">LOGIN</h1>
                        <form class="form-horizontal" method="POST" action="<%=request.getContextPath()%>/Login/Authenticate">
                            <% if(request.getSession().getAttribute("loginError") != null) { %>
                                <h4 class="form-control text-muted text-center text-light" style="border: 0; background-color: #fff; font-size: 15px"><i class="icon-warning text-warning x1"></i> Invalid Credentials</h4>
                            <% } %>
                            <div class="form-group">
                                <div class="input-group m-b-15 p-t-10">
                                    <div class="input-group-addon" style="background-color: #fff"><i class="icon-user" style="color: #f44455"></i></div>
                                    <input type="text" class="form-control" name="username" placeholder="Username">
                                </div>
                                <div class="input-group m-b-15">
                                    <div class="input-group-addon" style="background-color: #fff"><i class="icon-key" style="color: #f44455"></i></div>
                                    <input type="password" autocomplete class="form-control" name="password"  placeholder="Password">
                                </div>
                                <button class="btn btn-login btn-block m-t-20 m-b-15">Sign In</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
