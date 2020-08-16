<%@page import="models.Notification"%>
<script src="../../layouts/lib/js/core/jquery/jquery.js"></script>
<script src="../../layouts/lib/js/core/jquery/jquery.ui.js"></script>
<script src="../../layouts/lib.js/core/jquery/jquery.magnific-popup.min"></script>
<script src="../../layouts/lib/js/core/tether.min.js"></script>
<script src="../../layouts/lib/js/core/bootstrap/bootstrap.js"></script>
<script src="../../layouts/lib/js/core/bootstrap/jasny_bootstrap.min.js"></script>
<script src="../../layouts/lib/js/core/hammer/hammerjs.js"></script>
<script src="../../layouts/lib/js/core/hammer/jquery.hammer.js"></script>
<script src="../../layouts/lib/js/core/slimscroll/jquery.slimscroll.js"></script>
<script src="../../layouts/lib/js/extensions/smart-resize.js"></script>
<script src="../../layouts/lib/js/extensions/blockui.min.js"></script>
<script src="../../layouts/lib/js/forms/uniform.min.js"></script>
<script src="../../layouts/lib/js/forms/switchery.js"></script>
<script src="../../layouts/lib/js/forms/select2.min.js"></script>
<!--<script src="../../layouts/lib/js/plugins/ekko-lightbox.min.js"></script>-->
<script src="../../layouts/lib/js/plugins/lightbox/lightbox-plus-jquery.min.js"></script>

<script src="../../layouts/lib/js/plugins/notifications/pnotify.min.js"></script>
<script src="../../layouts/lib/js/pages/extensions/extension_pnotify.js"></script>

<script src="../../layouts/lib/js/core/app/layouts.js"></script>
<script src="../../layouts/lib/js/core/app/core.js"></script>

<script>
    <%
        if (request.getSession().getAttribute("notification") != null) {
            Notification n = (Notification) request.getSession().getAttribute("notification");
    %>
    new PNotify({title: '<%=n.getStatus()%>', text: '<%=n.getOutcomeDescription()%>', addclass: 'bg-<%=n.getClassName()%>'});
    <%
            request.getSession().removeAttribute("notification");
        }
    %>
</script>
