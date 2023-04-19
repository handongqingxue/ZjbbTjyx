<%--
  Created by IntelliJ IDEA.
  User: lilekang
  Date: 2023/4/19
  Time: 9:15 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <title>永兴制胶数据报表系统</title>
    <link rel="stylesheet" href="<%=basePath%>resource/css/m_ui.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/layui.css">
</head>

<body>
    <div class="home">
        <%--头部--%>
        <div class="home-head">
            <span class="home-head-span">永兴制胶数据报表系统</span>
            <ul class="home-head-ul">
                <li></li>
                <li>|</li>
                <li id="system-time">2023年4月19日10:30:30</li>
                <li>|</li>
                <li>欢迎您!超级管理员</li>
            </ul>
        </div>
        <div class="home-left-body">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">默认展开</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">选项1</a></dd>
                        <dd><a href="javascript:;">选项2</a></dd>
                        <dd><a href="">跳转</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">移动模块</a></dd>
                        <dd><a href="">后台模版</a></dd>
                        <dd><a href="">电商平台</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">产品</a></li>
                <li class="layui-nav-item"><a href="">大数据</a></li>
            </ul>

        </div>
    </div>
<script>
    var i=0;
    function myDate(){
        var now=new Date();
        var year=now.getFullYear();
        var month=now.getMonth()+1;
        var day=now.getDate();
        var hours=now.getHours();
        var minutes=now.getMinutes();
        var seconds=now.getSeconds();
        document.getElementById("system-time").innerHTML=year+"年"+month+"月"+day+"日"+"&nbsp;"+hours+":"+minutes+":"+seconds;
    }
    setInterval(myDate,1000);

</script>
</body>
</html>
