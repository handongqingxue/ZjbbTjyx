<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <%@include file="report/inc/js.jsp"%>
</head>
<body>
    <div class="login">
        <div class="login_box">
            <table>
                <tr>
                    <td class="login_page_title">永兴制胶数据报表系统</td>
                </tr>
                <tr>
                    <td>
                        <input type="text" id="userName" class="login_input" placeholder="请输入账号">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" id="password" class="login_input" placeholder="请输入密码">
                    </td>
                </tr>
                <tr>
                    <td>
                        <button onclick="checkInfo()" id="login_button" onmouseover="mouseover()" onmouseleave="mouseleave()" class="login_button">登陆</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
