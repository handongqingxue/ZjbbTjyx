<%--
  Created by IntelliJ IDEA.
  User: lilekang
  Date: 2023/4/21
  Time: 9:48 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <title>登陆</title>
    <link rel="stylesheet" href="<%=basePath%>resource/css/system_style.css">
</head>
<body>
    <div class="login">
        <video
                class="fullscreenVideo"
                id="bgVid"
                playsinline=""
                autoplay=""
                muted=""
                loop=""
                width="100%"
        >
            <source src="<%=basePath%>resource/video/login_background.mp4" type="video/mp4" />
        </video>
        <div class="login_box">
            <table>
                <tr>
                    <td class="login_page_title">永兴制胶数据报表系统</td>
                </tr>
                <tr>
                    <td>
                        <input type="text" class="login_input" placeholder="请输入账号">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" class="login_input" placeholder="请输入密码">
                    </td>
                </tr>
                <tr>
                    <td>
                        <button @click="login()" class="login_button">登陆</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
