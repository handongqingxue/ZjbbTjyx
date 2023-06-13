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
<%--                <button class="user_head_del_button" onclick="delUser()">--%>
<%--                    <i class="layui-icon layui-icon-delete" style="font-size: 16px; color: #ffffff;"></i>--%>
<%--                    删除--%>
<%--                </button>--%>
            </div>
        </div>
        <div class="home_right_user_div">
            <table class="user_table" id="user_table">
                <thead class="user_thead">
                    <tr>
                        <th style="width: 10%"><input id="check" onclick="choice()" type="checkbox"></th>
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
    </div>
</body>
<script>
    var path='<%=basePath%>';
    $(function () {
        getUserList();
    })

    //添加用户页面
    function addUserPage() {
        layer.open({
            type: 2,
            area: ['700px', '450px'],
            fixed: false, //不固定
            maxmin: true,
            title:"添加用户",
            content: path+'main/goAddUserPage'
        });
    }
    //清空输入框
    function emptyUserInput() {
        $("#userName").val("");
        $("#realName").val("");
    }
    //查询全部用户+模糊查询
    function getUserList() {
        $.ajax(
           {
               url:path+"main/getUserList",
               type:"post",
               data:{
                   UserName:$("#userName").val(),
                   RealName:$("#realName").val()
               },
               success:function (result) {
                   var userList=result.data;
                   initPagerUserList(userList);
               }
           }
        )
    }
    //分页+渲染数据
    function initPagerUserList(userList){
        layui.use(['laypage', 'layer'], function(){
            var laypage = layui.laypage,layer = layui.layer;
            //调用分页
            laypage.render({
                elem: 'user-paging'
                ,count: userList.length,
                limit: 12,
                prev: '<em><</em>',
                next: '<em>></em>',
                theme: '#1E9FFF',
                layout: ['count', 'prev', 'page', 'next',  'skip'],
                jump: function(obj){
                    //模拟渲染
                    document.getElementById('user_tbody').innerHTML = function(){
                        var arr = []
                            ,thisData = userList.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                        layui.each(thisData, function(index, item){
                            var userList=thisData;
                            arr=[];
                            if (userList.length>0){
                                for (var i = 0; i < userList.length; i++) {
                                    arr.push("<tr><td style=\"width: 10%\"><input class='son-check' type=\"checkbox\"></td><td style=\"width: 25%\">"+userList[i].userName+"</td><td style=\"width: 25%\">"+userList[i].realName+"</td><td style=\"width: 25%\">"+userList[i].ctime+"</td><td style=\"width: 15%\">\n" +
                                        "                                <button class=\"user_edit_button\">\n" +
                                        "                                    <i class=\"layui-icon layui-icon-edit\" style=\"font-size: 16px; color: #ffffff;\"></i>\n" +
                                        "                                    编辑\n" +
                                        "                                </button>\n" +
                                        "                                <button class=\"user_delete_button\" onclick='delUser("+userList[i].id+")'>\n" +
                                        "                                    <i class=\"layui-icon layui-icon-delete\" style=\"font-size: 16px; color: #ffffff;\"></i>\n" +
                                        "                                    删除\n" +
                                        "                                </button>\n" +
                                        "                            </td></tr>")

                                }
                            }
                        });
                        return arr.join('');
                    }();
                }
            });
        });
    }
    //复选框
    function choice() {
        var ocheck = document.getElementById("check");
        var ochecks = document.getElementsByClassName("son-check");
        for (var i = 0; i < ochecks.length; i++) {
            if (ocheck.checked == true) {
                ochecks[i].checked = true;
            } else {
                ochecks[i].checked = false;
            }
        }
    }
    //删除用户
    function delUser(id){
        layer.prompt({title: '敏感操作,请输入口令', formType: 1}, function(pass, index){
            if (pass=="tianjinyongxing"){
                layer.close(index);
                layer.msg('验证成功!');
                $.post(path+"main/delUser",
                    {id:id},
                    function(result){
                        if(result.msg=="ok"){
                            layui.use('layer', function(){
                                var layer = layui.layer;
                                layer.msg('删除成功!', {icon: 1});
                            });
                            getUserList();
                        }else {
                            layui.use('layer', function(){
                                var layer = layui.layer;
                                layer.msg('删除失败!', {icon: 2});
                            });
                        }
                    }
                    ,"json");
            }else {
                layer.msg('验证失败!');
            }
        });
    }
</script>
</html>
