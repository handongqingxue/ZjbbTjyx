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
            <div class="user_head_div">
                <span style="font: normal 400 16px '微软雅黑';margin-left: 50px">用户名:</span>
                <input type="text" style="height: 300px;margin-left: 15px" placeholder="请输入">
                <span style="font: normal 400 16px '微软雅黑';margin-left: 50px">真实姓名:</span>
                <input type="text" style="height: 25px;margin-left: 15px" placeholder="请输入">
                <button class="user_head_add_button">
                    <i class="layui-icon layui-icon-add-1" style="font-size: 16px; color: #ffffff;"></i>
                    添加用户
                </button>
                <button class="user_head_search_button" onclick="getUserList()">
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
                <tbody class="user_tbody" id="user_tbody">
<%--                        <tr id="">--%>
<%--                            <td style="width: 10%"><input type="checkbox"></td>--%>
<%--                            <td style="width: 25%"></td>--%>
<%--                            <td style="width: 25%"></td>--%>
<%--                            <td style="width: 25%"></td>--%>
<%--                            <td style="width: 15%">--%>
<%--                                <button class="user_edit_button">--%>
<%--                                    <i class="layui-icon layui-icon-edit" style="font-size: 16px; color: #ffffff;"></i>--%>
<%--                                    编辑--%>
<%--                                </button>--%>
<%--                                <button class="user_delete_button">--%>
<%--                                    <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>--%>
<%--                                    删除--%>
<%--                                </button>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
                </tbody>
            </table>

            </div>
        <div class="home_right_bottom_div">
            <div id="demo3"></div>
<%--            <div id="paging_m" class="home_right_bottom_paging"></div>--%>
<%--            <button>上一页</button>--%>
<%--            <span>当前在第1页</span>--%>
<%--            <button>下一页</button>--%>
        </div>
        </div>
    </div>
</body>
<script>
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer;

        //自定义首页、尾页、上一页、下一页文本
        laypage.render({
            elem: 'demo3'
            ,count: 100
            ,first: '首页'
            ,last: '尾页'
            ,prev: '<em>←</em>'
            ,next: '<em>→</em>'
        });
        //调用分页
        laypage.render({
            elem: 'demo20'
            ,count: data.length
            ,jump: function(obj){
                //模拟渲染
                document.getElementById('biuuu_city_list').innerHTML = function(){
                    var arr = []
                        ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                    layui.each(thisData, function(index, item){
                        arr.push('<li>'+ item +'</li>');
                    });
                    return arr.join('');
                }();
            }
        });

    });

    var userList;

    $(function () {
        getUserList();
    })

    function getUserList() {
        $.post(path+"main/getUserList",
            // {batchID:batchID},
            function(result){
                var user_tbody=$("#user_tbody");
                user_tbody.empty();
                var userList=result.data;
                for (var i = 0; i < userList.length; i++) {
                    user_tbody.append("<tr><td style=\"width: 10%\"><input type=\"checkbox\"></td><td style=\"width: 25%\">"+userList[i].userName+"</td><td style=\"width: 25%\">"+userList[i].userName+"</td><td style=\"width: 25%\">"+userList[i].ctime+"</td><td style=\"width: 15%\">\n" +
                        "                                <button class=\"user_edit_button\">\n" +
                        "                                    <i class=\"layui-icon layui-icon-edit\" style=\"font-size: 16px; color: #ffffff;\"></i>\n" +
                        "                                    编辑\n" +
                        "                                </button>\n" +
                        "                                <button class=\"user_delete_button\">\n" +
                        "                                    <i class=\"layui-icon layui-icon-delete\" style=\"font-size: 16px; color: #ffffff;\"></i>\n" +
                        "                                    删除\n" +
                        "                                </button>\n" +
                        "                            </td></tr>")

                }
            }
            ,"json");
    }
</script>
</html>
