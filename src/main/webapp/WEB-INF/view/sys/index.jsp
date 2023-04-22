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
    <link rel="stylesheet" href="<%=basePath%>resource/css/form_style.css">
    <script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>

    <link rel="stylesheet" href="<%=basePath%>resource/css/system_style.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/layui.css">
    <script type="text/javascript" src="<%=basePath%>resource/js/layui.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/opc.js"></script>
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
            <li class="layui-nav-item">
                <span>欢迎您!</span>
                <a href="" style="cursor:pointer;">超级管理员</a>
            </li>
            <li>|</li>
            <li>退出</li>
        </ul>
    </div>
    <%--身体--%>
    <div class="home-body">
        <div class="home-left-body">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <%--layui-nav-itemed 自动展开--%>
                <li class="layui-nav-item">
                    <a href="javascript:;" onclick="inspect(0)">M类报表生成</a>
                    <dl class="layui-nav-child" style="color: #79e6e8">
                        <dd><a href="javascript:;">demo1</a></dd>
                        <dd><a href="javascript:;">demo2</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" onclick="inspect(0)">U类报表生成</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">demo1</a></dd>
                        <dd><a href="javascript:;">demo2</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" onclick="inspect(1)">M类报表查询</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">demo1</a></dd>
                        <dd><a href="javascript:;">demo2</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" onclick="inspect(1)">U类报表查询</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">demo1</a></dd>
                        <dd><a href="javascript:;">demo2</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                        <dd><a href="javascript:;">demo3</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
        <div class="home-right-body">
            <%--右部分标题头--%>
            <div class="right-body-head">
                <span class="right-body-head-span1">
                     <i class="layui-icon" id="right-body-head-icon" style="font-size: 30px; color: #2a2a2b;"></i>
                </span>
                <span id="right-body-head-span2" class="right-body-head-span2"></span>
            </div>
            <%--右部分body--%>
            <div class="right-body-main" style="overflow: hidden">
<%--                <iframe style="width: 100%; height: 500px;" src="../opc/demo.jsp"></iframe>--%>
                <%@include file="../opc/opcm.jsp"%>
            </div>
        </div>
    </div>
</div>
<script>
    window.load(init(1));

    function init(id) {
        document.getElementById("right-body-head-icon").classList.add("layui-icon-search");
        document.getElementById("right-body-head-span2").innerHTML="报表查询页面";
    }

    function inspect(id){
        if (id==0){
            document.getElementById("right-body-head-span2").innerHTML="报表生成页面";
            document.getElementById("right-body-head-icon").classList.replace("layui-icon-search","layui-icon-add-circle");
        }else if(id==1){
            document.getElementById("right-body-head-span2").innerHTML="报表查询页面";
            document.getElementById("right-body-head-icon").classList.replace("layui-icon-add-circle","layui-icon-search");
        }
    }

</script>
</body>
</html>
