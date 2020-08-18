<%@page import="configurations.Route"%>
<%@page import="models.UserInfo"%>
<aside class="menu sidebar" style="top: 60px;">
    <div class="slimScrollDiv active" style="position: relative; overflow: hidden; width: 220px; height: 911px;"><div class="left-aside-container" style="overflow: hidden; width: 220px; height: 911px; display: block;">

            <%  UserInfo authUser = (UserInfo) request.getSession().getAttribute("authUser"); %>
            <div class="user-profile"><div class="user-profile-container">
                    <div class="user-profile clearfix">
                        <div class="admin-user-thumb">
                            <img src="../../resources/avatars/<%=authUser.getPhotoURL()%>" alt="admin" class="rounded-circle">
                        </div>
                        <div class="admin-user-info text-center">
                            <ul>
                                <li class="username"><%=authUser.getDisplayName()%></li>
                                <li><a href="<%=request.getContextPath()%>/Logout" class="tooltip-bottom" data-tooltip="Logout"><i class="icon-exit3"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>


            <div class="menu-container active">
                <ul class="sidebar-accordion" style="display: block;">
                    <li><a href="<%=request.getContextPath()+Route.DISPLAY_DASHBOARD_ROUTE%>"><i class="icon-display4"></i><span class="list-label"> Dashboard</span></a></li>
                    <li class="list-title">Apps</li>
                    <% if (!((authUser.getRoleId().equals("P_OPERATOR")) || (authUser.getRoleId().equals("G_OPERATOR")) || (authUser.getRoleId().equals("SYS_ADMIN")))) { %>
                        <li><a href="<%=request.getContextPath()+Route.DISPLAY_MYMAIL_ROUTE%>"><i class="icon-envelop"></i> <span>My Mail</span></a></li>    
                    <% } %>
                    <li><a href="<%=request.getContextPath()+Route.DISPLAY_CATEGORIES_ROUTE%>"><i class="icon-three-bars"></i> <span>Categories</span></a></li>     
                    <li><a href="<%=request.getContextPath()+Route.DISPLAY_INBOX_ROUTE %>"><i class="icon-envelop"></i> <span>Mails</span></a></li>          
                    <% if ((authUser.getRoleId().equals("GOVERNOR")) || (authUser.getRoleId().equals("P_SECRETARIAT")) || (authUser.getRoleId().equals("SYS_ADMIN"))) { %>
                        <li><a href="<%=request.getContextPath()+Route.DISPLAY_OCCUPATIONS_ROUTE%>"><i class="icon-briefcase"></i> <span>Occupations</span></a></li>
                       <li><a href="<%=request.getContextPath()+Route.DISPLAY_USERS_ROUTE%>"><i class="icon-users2"></i> <span>Users</span></a></li>      
                    <% } %>
                    <% if (authUser.getRoleId().equals("SYS_ADMIN")) { %>
                       <li><a href="<%=request.getContextPath()+Route.DISPLAY_ACTIVITIES_ROUTES%>"><i class="icon-computer-user"></i> <span>Activities</span></a></li>      
                    <% } %>
                </ul>
            </div>
        </div>
</aside>