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
    <style>
        .add_user_div{
            margin: 0px auto;
        }
    </style>
</head>
<body>
<%@include file="../../../report/inc/js.jsp"%>
    <div class="add_user_div">
        <table>
            <tr>
                <td>用户名</td>
                <td>
                    <input type="text" placeholder="请输入用户名" id="add_username">
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input type="password" placeholder="请输入用户名">
                </td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td>
                    <input type="password" placeholder="请输入真实姓名">
                </td>
            </tr>
            <tr>
                <td>分配角色</td>
                <td>
                    <select id="user_role">
                        <option>请选择</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <button>清空</button>
                </td>
                <td>
                    <button onclick="a()">添加</button>
                </td>
            </tr>
        </table>
    </div>
</body>
<script>
    function a() {
        var user = $("#add_username").val();
        console.log(user+";")
    }

</script>
</html>
