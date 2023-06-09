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
    <title>Title</title>
</head>
<body>
    <div class="home_right_div">
        <div class="home_right_head_div">
            <div class="user_head_div">
                <span style="font: normal 400 16px '微软雅黑';margin-left: 50px">用户名:</span>
                <input type="text" style="height: 30px;margin-left: 15px" placeholder="请输入">
                <span style="font: normal 400 16px '微软雅黑';margin-left: 50px">真实姓名:</span>
                <input type="text" style="height: 30px;margin-left: 15px" placeholder="请输入">
                <button class="user_head_add_button">
                    <i class="layui-icon layui-icon-add-1" style="font-size: 16px; color: #ffffff;"></i>
                    添加用户
                </button>
                <button class="user_head_search_button">
                    <i class="layui-icon layui-icon-search" style="font-size: 16px; color: #ffffff;"></i>
                    查询
                </button>
            </div>
        </div>
        <div class="home_right_user_div">
            <table class="user_table">
                <thead class="user_thead">
                    <tr>
                        <th style="width: 10%"><input type="checkbox"></th>
                        <th style="width: 25%">用户名</th>
                        <th style="width: 25%">真实姓名</th>
                        <th style="width: 25%">创建时间</th>
                        <th style="width: 15%">操作</th>
                    </tr>
                </thead>
                <tbody class="user_tbody">
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 10%"><input type="checkbox"></td>
                        <td style="width: 25%">admin</td>
                        <td style="width: 25%">超级管理员</td>
                        <td style="width: 25%">2023-06.08</td>
                        <td style="width: 15%">
                            <button class="user_edit_button">
                                <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>
                                编辑
                            </button>
                            <button class="user_delete_button">
                                <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>
                                删除
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>

            </div>
        <div class="home_right_bottom_div">
<%--            <div id="paging_m" class="home_right_bottom_paging"></div>--%>
            <button>上一页</button>
            <span>当前在第1页</span>
            <button>下一页</button>
        </div>
        </div>
    </div>
</body>
</html>
