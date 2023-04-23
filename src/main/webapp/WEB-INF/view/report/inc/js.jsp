<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<link rel="stylesheet" href="<%=basePath%>resource/css/form_style.css">
<script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>

<link rel="stylesheet" href="<%=basePath%>resource/css/system_style.css">
<link rel="stylesheet" href="<%=basePath%>resource/css/layui.css">
<script type="text/javascript" src="<%=basePath%>resource/js/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/opc.js"></script>