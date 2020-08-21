<%@page import="configurations.Route"%>
<% String language = (String) request.getSession().getAttribute("language"); %>
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
                <div class="dropdown-menu animated fadeIn no-p">
                <ul class="user-links">
                <li><a href="<%=request.getContextPath()+Route.SI%>"><i class="icon-font"></i> Sinhala</a></li>
                <li><a href="<%=request.getContextPath()+Route.EN%>"><i class="icon-font"></i> English</a></li>
                </ul>
                </div>
            </li>
            </ul>
            </div>
            </div>
    </div>
</header>