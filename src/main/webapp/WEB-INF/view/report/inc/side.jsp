<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%--头部--%>
<div class="home-head">
    <span class="home-head-span">永兴制胶数据报表系统</span>
    <ul class="home-head-ul">
        <li></li>
        <li>|</li>
        <li id="system-time"></li>
        <li>|</li>
        <li class="layui-nav-item">
            <span>欢迎您!</span>
            <a href="" style="cursor:pointer;">超级管理员</a>
        </li>
        <li>|</li>
        <li>退出</li>
    </ul>
</div>

<div class="home-left-body">
    <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <%--layui-nav-itemed 自动展开--%>
        <li class="layui-nav-item">
            <a href="javascript:;" onclick="inspect(0)">M类报表生成</a>
            <dl class="layui-nav-child" style="color: #79e6e8">
                <c:forEach items="${mWscPcjlList}" var="item">
                    <dd><a href="javascript:;">${item.batchID}</a></dd>
                </c:forEach>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;" onclick="inspect(0)">U类报表生成</a>
            <dl class="layui-nav-child">
                <c:forEach items="${uWscPcjlList}" var="item">
                    <dd><a href="javascript:;">${item.batchID}</a></dd>
                </c:forEach>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;" onclick="inspect(1)">M类报表查询</a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;" onclick="typeQuery('MA')">MA胶种</a></dd>
                <dd><a href="javascript:;" onclick="typeQuery('MB')">MB胶种</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;" onclick="inspect(1)">U类报表查询</a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;" onclick="typeQuery('UD')">UD胶种</a></dd>
            </dl>
        </li>
    </ul>
</div>
</body>
</html>