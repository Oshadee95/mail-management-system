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
        <meta name="theme-color" content="#1C2B36" />
        <title>Bird - Responsive admin dashboard template by Template Club</title>

        <link href="img/favicon.ico" rel="apple-touch-icon" type="image/png" sizes="144x144">
        <link href="img/favicon.ico" rel="apple-touch-icon" type="image/png" sizes="114x114">
        <link href="img/favicon.ico" rel="apple-touch-icon" type="image/png" sizes="72x72">
        <link href="img/favicon.ico" rel="apple-touch-icon" type="image/png">
        <link href="img/favicon.ico" rel="icon" type="image/png">
        <link href="img/favicon.ico" rel="shortcut icon">


        <link type="text/css" rel="stylesheet" href="layouts/lib/css/bootstrap.css">
        <link type="text/css" rel="stylesheet" href="layouts/lib/css/animate.min.css">
        <link type="text/css" rel="stylesheet" href="layouts/lib/css/main.css">

    </head>
    <body>
        <div class="auth-container">
            <div class="center-block">
                <div class="auth-module">
                    <div class="toggle">
                        <!--<i class="icon-user-plus"></i>-->
                        <!--<div class="tip">Click here to register</div>-->
                    </div>

                    <div class="form">
                        <h1 class="text-light">User Login</h1>
                        <form class="form-horizontal" action="<%=request.getContextPath()%>/Login/Authenticate">
                            <div class="form-group">
                                <div class="input-group m-b-15 p-t-20">
                                    <div class="input-group-addon"><i class="icon-user"></i></div>
                                    <input type="text" class="form-control" name="username" placeholder="Username">
                                </div>
                                <div class="input-group m-b-15">
                                    <div class="input-group-addon"><i class="icon-key"></i></div>
                                    <input type="password" class="form-control" name="password"  placeholder="Password">
                                </div>
<!--                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input class="styled" type="checkbox" checked="checked"> Remember me
                                    </label>
                                </div>-->
                                <button class="btn btn-info btn-block m-t-20">Sign In</button>
                            </div>
                        </form>
                    </div>


<!--                    <div class="form">
                        <h1 class="text-light">Create an account</h1>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="input-group m-b-10 p-t-20">
                                    <div class="input-group-addon"><i class="icon-user"></i></div>
                                    <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Username">
                                </div>
                                <div class="input-group m-b-10">
                                    <div class="input-group-addon"><i class="icon-key"></i></div>
                                    <input type="password" class="form-control" id="inlineFormInputGroup" placeholder="Password">
                                </div>
                                <div class="input-group m-b-10">
                                    <div class="input-group-addon"><i class="icon-user"></i></div>
                                    <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Confirm Password">
                                </div>
                                <div class="input-group m-b-15">
                                    <div class="input-group-addon"><i class="icon-envelop3"></i></div>
                                    <input type="email" class="form-control" id="inlineFormInputGroup" placeholder="Email">
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input class="styled" type="checkbox" checked="checked"> Mail me my account details
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input class="styled" type="checkbox" checked="checked"> Accept <a href="$">terms &amp; conditions</a>
                                    </label>
                                </div>
                                <button class="btn btn-info btn-block m-t-20">Create my account</button>
                            </div>
                        </form>
                    </div>-->

<!--                    <div class="forgot">
                        <a href="auth_recover.php">Forgot your password?</a>
                    </div>-->
                </div>
<!--                <div class="footer">
                    <div class="float-left">
                        © 2020 - Bird&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;&nbsp;Web app kit by <a href="https://templateclub.co.in" target="_blank">Template Club</a>. </div>
                    <div class="float-right">
                        <div class="label label-info">Version: 2.1.0</div>
                    </div>
                </div>-->
            </div>
        </div>

        <script src="lib/js/core/jquery/jquery.js"></script>
        <script src="lib/js/core/jquery/jquery.ui.js"></script>
        <script src="lib/js/core/bootstrap/bootstrap.js"></script>
        <script src="lib/js/core/hammer/hammerjs.js"></script>
        <script src="lib/js/core/hammer/jquery.hammer.js"></script>
        <script src="lib/js/core/slimscroll/jquery.slimscroll.js"></script>
        <script src="lib/js/forms/uniform.min.js"></script>
        <script src="lib/js/core/app/layouts.js"></script>
        <script src="lib/js/core/app/core.js"></script>


        <script src="lib/js/pages/auth/authentication.js"></script>

    </body>
</html>