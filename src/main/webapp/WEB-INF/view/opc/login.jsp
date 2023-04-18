<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />
<html lang="en"
	class="app js no-touch no-android chrome no-firefox no-iemobile no-ie no-ie10 no-ie11 no-ios no-ios7 ipad"/>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1"/>
<meta name="renderer" content="webkit"/>
<title>登录－C&A建设项目数字化交付系统</title>
<link rel="icon" href="favicon.ico" type="image/ico">
<meta name="keywords" content="LightYear,LightYearAdmin,光年,后台模板,后台管理系统,光年HTML模板">
<meta name="description" content="Light Year Admin V4是一个后台管理系统的HTML模板，基于Bootstrap v4.4.1。">
<meta name="author" content="yinqi">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/materialdesignicons.min.css" rel="stylesheet">
<link href="css/style.min.css" rel="stylesheet">
<link href="css/login.min.css" rel="stylesheet">

</head>
  
<body class="center-vh" style="background-image: url(images/login/login-bg-2.jpg); background-size: cover;">
<div class="login-box p-5 w-420 mb-0 mr-2 ml-2">
  <div class="text-center mb-3">
<%--    <a href="#"> <img alt="light year admin" src="images/logo-index.png"> </a>--%>
  </div>
  <form action="#!" method="post" class="login-form" autocomplete="on">
    <div class="form-group has-feedback">
      <span class="mdi mdi-account" aria-hidden="true"></span>
      <input type="text" class="form-control" id="username" placeholder="用户名">
    </div>

    <div class="form-group has-feedback">
      <span class="mdi mdi-lock" aria-hidden="true"></span>
      <input type="password" class="form-control" id="password" placeholder="密码" autocomplete="off">
    </div>
    
    <!-- <div class="form-group has-feedback row">
      <div class="col-7">
        <span class="mdi mdi-check-all form-control-feedback" aria-hidden="true"></span>
        <input type="text" name="captcha" class="form-control" placeholder="验证码">
      </div>
      <div class="col-5 text-right">
        <img src="images/captcha.png" class="pull-right" id="captcha" style="cursor: pointer;" onclick="this.src=this.src+'?d='+Math.random();" title="点击刷新" alt="captcha">
      </div>
    </div>

    <div class="form-group">
      <div class="custom-control custom-checkbox">
        <input type="checkbox" class="custom-control-input" id="rememberme">
        <label class="custom-control-label not-user-select text-white" for="rememberme">5天内自动登录</label>
      </div>
    </div>
 	-->
    <div class="form-group" id="div_submit">
      <span class="btn btn-block btn-primary" type="submit">立即登录</span>
    </div>
  </form>
  
  <!-- <p class="text-center text-white">Copyright © 2021 . All right reserved</p> -->
</div>
<script type="text/javascript" src="resource/jquery.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</body>
</html>