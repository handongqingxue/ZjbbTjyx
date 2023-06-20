<%--
  Created by IntelliJ IDEA.
  User: lilekang
  Date: 2023/6/8
  Time: 12:09 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
    <div class="home_right_div">
        <div class="home_right_head_div">
            <div class="user_head_row1_div">
                <span style="font: normal 400 16px '微软雅黑';margin-left: 25px">用户名:</span>
                <input type="text" placeholder="请输入" class="user_head_input" id="userName">
                <span style="font: normal 400 16px '微软雅黑';margin-left: 50px">真实姓名:</span>
                <input type="text" placeholder="请输入" class="user_head_input" id="realName">
                <button class="user_head_search_button" onclick="getUserList()">
                    <i class="layui-icon layui-icon-search" style="font-size: 16px; color: #ffffff;"></i>
                </button>
                <button class="user_head_empty_button" onclick="emptyUserInput()">
                    <i class="layui-icon layui-icon-refresh" style="font-size: 16px; color: #ffffff;"></i>
                </button>
            </div>
            <div class="user_head_row2_div">
                <button class="user_head_add_button" onclick="addUserPage()">
                    <i class="layui-icon layui-icon-add-1" style="font-size: 16px; color: #ffffff;"></i>
                    添加
                </button>
            </div>
        </div>
        <div class="home_right_user_div">
            <table class="user_table" id="user_table">
                <thead class="user_thead">
                    <tr>
                        <th style="width: 10%">用户编号</th>
                        <th style="width: 25%">用户名</th>
                        <th style="width: 25%">真实姓名</th>
                        <th style="width: 25%">创建时间</th>
                        <th style="width: 15%">操作</th>
                    </tr>
                </thead>
                <tbody class="user_tbody" id="user_tbody"></tbody>
            </table>
        </div>
        <div class="home_right_bottom_div">
            <div id="user-paging"></div>
        </div>
    </div>
</body>
<script>
    <%--var path='<%=basePath%>';--%>
    $(function () {
        getUserList();
    })
</script>
</html>
