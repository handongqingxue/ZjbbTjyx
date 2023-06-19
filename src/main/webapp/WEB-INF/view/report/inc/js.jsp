<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<link rel="stylesheet" href="<%=basePath%>resource/css/form_style.css">
<script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="<%=basePath%>resource/css/system_style.css">
<script type="text/javascript" src="<%=basePath%>resource/js/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/xm-select.js"></script>
<link rel="stylesheet" href="<%=basePath%>resource/css/layui.css">
<script type="text/javascript" src="<%=basePath%>resource/js/pdf/jspdf.debug.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/pdf/html2canvas.min.js"></script>
<%--<script type="text/javascript" src="<%=basePath%>resource/js/calendar/calendar.js"></script>--%>
<script type="text/javascript" src="<%=basePath%>resource/js/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/login.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/MD5.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/system/user/userList.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/system/user/addUser.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/system/user/editUser.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/system/role/roleList.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/system/role/addRole.js"></script>