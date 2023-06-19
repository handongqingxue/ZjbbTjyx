<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<%@include file="../../../report/inc/js.jsp"%>
    <div class="add_user_div">
        <table class="add_user_table">
            <tr>
                <td>&nbsp;用户名</td>
                <td>
                    <input class="add_user_input" type="text" placeholder="请输入用户名" id="add_username">
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;密码</td>
                <td>
                    <input class="add_user_input" type="password" placeholder="请输入用户名" id="add_password">
                </td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td>
                    <input class="add_user_input" type="text" placeholder="请输入真实姓名" id="add_realname">
                </td>
            </tr>
            <tr>
                <td>分配角色</td>
                <td>
                    <div style="width: 300px">
                        <div id="add_user_role" class="xm-select-demo"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button onclick="emptyAddUserPageInput()" class="add_user_empty_button">
                        <i class="layui-icon layui-icon-refresh" style="font-size: 16px; color: #fcfdfd;"></i>
                        清空</button>
                    <button onclick="check_user_Info()" class="add_user_insert_button">
                        <i class="layui-icon layui-icon-ok-circle" style="font-size: 16px; color: #fcfdfd;"></i>
                        添加</button>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
