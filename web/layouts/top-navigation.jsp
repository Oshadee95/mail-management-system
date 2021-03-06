<%@page import="models.UserInfo"%>
<%@page import="configurations.Language"%>
<%@page import="configurations.Route"%>
<% String language = (String) request.getSession().getAttribute("language");%>
<header class="main-nav clearfix">
    <div class="main-nav-wrapper">
        <div class="navbar-left float-left">
            <div class="clearfix">
                <ul class="left-branding float-left">
                    <li class="visible-handheld"><span class="left-toggle-switch" style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><i class="icon-menu7"></i></span></li>
                    <li>
                        <!--<a href="/"><div class="logo"></div></a>-->
                    </li>
                </ul>
            </div>
        </div>
        <div class="navbar float-right navbar-toggleable-sm">
            <div class="clearfix">
                <ul class="float-right top-icons">
                    <li class="dropdown user-dropdown">
                        <a href="#" class="btn-user dropdown-toggle hidden-xs-down" data-toggle="dropdown" aria-expanded="false"><icon class="icon-earth x4 p-t-5 text-primary"></icon></a>
                        <a href="#" class="dropdown-toggle hidden-sm-up" data-toggle="dropdown"><i class="icon-more"></i></a>
                        <div class="dropdown-menu animated fadeIn no-p" style="width:10px">
                            <ul class="user-links">
                                <li><a class="<%=(language.equals("si")) ? "active" : ""%>" href="<%=request.getContextPath() + Route.SI%>"><%=Language.si_sinhala%></a></li>
                                <li><a class="<%=(language.equals("en")) ? "active" : ""%>" href="<%=request.getContextPath() + Route.EN%>">English</a></li>
                            </ul>
                        </div>
                    </li>
                    <%  UserInfo lUser = (UserInfo) request.getSession().getAttribute("authUser"); %>
                    <li class="dropdown user-dropdown">
                        <a href="#" class="btn-user dropdown-toggle hidden-xs-down" data-toggle="dropdown" aria-expanded="false"><img src="../../resources/avatars/<%=lUser.getPhotoURL()%>" class="rounded-circle user"></a>
                        <a href="#" class="dropdown-toggle hidden-sm-up" data-toggle="dropdown"><i class="icon-more"></i></a>
                        <div class="dropdown-menu animated fadeIn no-p" style="width:10px">
                            <ul class="user-links">
                                <li><a href="<%=request.getContextPath() + Route.LOGOUT_ROUTE%>"><i class="icon-logout"></i> Logout</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>