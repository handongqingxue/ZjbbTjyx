<%--
  Created by IntelliJ IDEA.
  User: lilekang
  Date: 2023/6/13
  Time: 4:20 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<%@include file="../../../report/inc/js.jsp"%>
    <div class="add_role_div">
        <table class="add_role_table">
            <tr>
                <td>&nbsp;用户名</td>
                <td>
                    <input class="add_role_input" type="text" placeholder="请输入角色名称" id="add_rolename">
                </td>
            </tr>
            <tr>
                <td>角色描述</td>
                <td>
                    <input class="add_role_input" type="text" placeholder="请输入角色详情" id="add_roledetail">
                </td>
            </tr>
            <tr>
                <td>分配权限</td>
                <td>
                    <select class="add_role_input" id="add_role_permission"></select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button onclick="emptyAddRolePageInput()" class="add_role_empty_button">
                        <i class="layui-icon layui-icon-refresh" style="font-size: 16px; color: #fcfdfd;"></i>
                        清空</button>
                    <button onclick="check_role_Info()" class="add_role_insert_button">
                        <i class="layui-icon layui-icon-ok-circle" style="font-size: 16px; color: #fcfdfd;"></i>
                        添加</button>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
