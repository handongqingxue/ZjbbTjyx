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
    <script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/MD5.js"></script>
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
                    <td class="login_page_title" onclick="insertUser()">永兴制胶数据报表系统</td>
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
<script>
    var baseUrl="${pageContext.request.contextPath}";

    function mouseover() {
        document.getElementById("login_button").style.backgroundColor="#58b7e8";
    }
    function mouseleave() {
        document.getElementById("login_button").style.backgroundColor="#5e94c8";
    }

    function checkUserName(){
        var userName=$("#userName").val();
        if(userName==null||userName==""||userName=="请填写用户名"){
            $("#userName").css("color","#f00");
            $("#userName").val("请填写用户名");
            return false;
        }
        else if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(userName)) {
            $("#userName").css("color","#f00");
            $("#userName").val("用户名不能有特殊字符");
            return false;
        }
        else if (/(^\_)|(\__)|(\_+$)/.test(userName)) {
            $("#userName").css("color","#f00");
            $("#userName").val("用户名首尾不能出现下划线\'_\'");
            return false;
        }
        else
            return true;
    }

    function checkPassword(){
        var password=$("#password").val();
        if(password==null||password==""||password=="请填写密码"){
            alert("请填写密码");
            return false;
        }
        else
            return true;
    }

    function checkInfo(){
        if(checkUserName()){
            if(checkPassword()){
                var userName=$("#userName").val();
                var passWord=MD5($("#password").val()).toUpperCase();
                login(userName,passWord);
            }
        }
    }

    function login(userName,password){
        $.post(baseUrl + "/user/login",
            {userName:userName,password:password},
            function(json){
                alert(json)
                // if(json.status==0){
                //     window.location.href=baseUrl+json.url;
                // }else if(json.status==1){
                //     alert(json.msg);
                // }
            }
            ,"json");
    }

    function insertUser() {
        var pass=MD5("12345678").toUpperCase();
        $.post(baseUrl + "/user/addUser",
            {
                UserName:'lilekang',
                Psd:pass
            },
            function(json){
                alert(json)
                // if(json.status==0){
                //     window.location.href=baseUrl+json.url;
                // }else if(json.status==1){
                //     alert(json.msg);
                // }
            }
            ,"json");
    }
</script>
</body>
</html>
