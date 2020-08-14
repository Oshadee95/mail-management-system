<aside class="menu sidebar" style="top: 60px;">
    <div class="slimScrollDiv active" style="position: relative; overflow: hidden; width: 220px; height: 911px;"><div class="left-aside-container" style="overflow: hidden; width: 220px; height: 911px; display: block;">

            <div class="user-profile"><div class="user-profile-container">
                    <div class="user-profile clearfix">
                        <div class="admin-user-thumb">
                            <img src="../../layouts/img/demo/img1.jpg" alt="admin" class="rounded-circle">
                        </div>
                        <div class="admin-user-info text-center">
                            <ul>
                                <li class="username">Jane Doe</li>
                                <li><a href="#" class="tooltip-bottom" data-tooltip="Logout"><i class="icon-exit3"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>


            <div class="menu-container active">
                <ul class="sidebar-accordion" style="display: block;">
                    <li><a href="new"><i class="icon-display4"></i><span class="list-label"> Dashboard</span></a></li>
                    <li class="list-title">Apps</li>
                    <li><a href="<%=request.getContextPath()%>/Categories"><i class="icon-three-bars"></i> <span>Categories</span></a></li>       
                    <li><a href="<%=request.getContextPath()%>/Mails/Inbox/100"><i class="icon-envelop"></i> <span>Received Mails</span></a></li>       
                    <li><a href="<%=request.getContextPath()%>/Mails/Outbox"><i class="icon-mail-read"></i> <span>Replied Mails</span></a></li>       
                    <li><a href="<%=request.getContextPath()%>/Occupations"><i class="icon-briefcase"></i> <span>Occupations</span></a></li>
                    <li><a href="<%=request.getContextPath()%>/Roles"><i class="icon-database-settings"></i> <span>Roles</span></a></li>       
                    <li><a href="<%=request.getContextPath()%>/Users"><i class="icon-users2"></i> <span>Users</span></a></li>  
                    <li><a href="<%=request.getContextPath()%>/Auth/Activities/100"><i class="icon-computer-user"></i> <span>Activities</span></a></li>       
                </ul>
            </div>
        </div>
</aside>