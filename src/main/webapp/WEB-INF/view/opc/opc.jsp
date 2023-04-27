<%--
  Created by IntelliJ IDEA.
  User: lilekang
  Date: 2023/3/9
  Time: 9:37 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <script type="text/javascript" src="<%=basePath%>resource/js/layui.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
    <title>Title</title>
    <link rel="stylesheet" href="<%=basePath%>resource/css/layui.css">
</head>
<body>
<div>
    <div id="demo2"></div>
</div>
</body>
</html>
