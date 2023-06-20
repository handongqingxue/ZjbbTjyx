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
                <td>
                </td>
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
