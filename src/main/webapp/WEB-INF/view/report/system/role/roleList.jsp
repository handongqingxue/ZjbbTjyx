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
    <title>角色管理</title>
</head>
<body>
    <div class="home_right_div">
        <div class="home_right_head_div">
            <div class="role_head_row1_div">
                <span style="font: normal 400 16px '微软雅黑';margin-left: 25px">角色名:</span>
                <input type="text" placeholder="请输入" class="role_head_input" id="roleName">
                <span style="font: normal 400 16px '微软雅黑';margin-left: 50px">角色描述:</span>
                <input type="text" placeholder="请输入" class="role_head_input" id="roleDetail">
                <button class="role_head_search_button" onclick="getRoleList()">
                    <i class="layui-icon layui-icon-search" style="font-size: 16px; color: #ffffff;"></i>
                </button>
                <button class="role_head_empty_button" onclick="emptyRoleInput()">
                    <i class="layui-icon layui-icon-refresh" style="font-size: 16px; color: #ffffff;"></i>
                </button>
            </div>
            <div class="role_head_row2_div">
                <button class="role_head_add_button" onclick="addRolePage()">
                    <i class="layui-icon layui-icon-add-1" style="font-size: 16px; color: #ffffff;"></i>
                    添加
                </button>
            </div>
        </div>
        <div class="home_right_role_div">
            <table class="role_table" id="role_table">
                <thead class="role_thead">
                    <tr>
                        <th style="width: 10%">序号</th>
                        <th style="width: 25%">角色名称</th>
                        <th style="width: 25%">具体描述</th>
                        <th style="width: 25%">创建时间</th>
                        <th style="width: 15%">操作</th>
                    </tr>
                </thead>
                <tbody class="role_tbody" id="role_tbody"></tbody>
            </table>
        </div>
            <div class="home_right_bottom_div">
                <div id="role-paging"></div>
            </div>
        </div>
    </div>
</body>
<script>
    <%--var path='<%=basePath%>';--%>
    $(function () {
        getRoleList();
    })
</script>
</html>
