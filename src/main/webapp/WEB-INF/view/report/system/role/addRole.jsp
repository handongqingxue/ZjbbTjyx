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
</head>
<body>
<%@include file="../../../report/inc/js.jsp"%>
    <div class="add_role_div">
        <table class="add_role_table">
            <tr>
                <td>&nbsp;用户名</td>
                <td>
                    <input class="add_role_input" type="text" placeholder="请输入角色名称" id="add_rolename">
                </td>
            </tr>
            <tr>
                <td>角色描述</td>
                <td>
                    <input class="add_role_input" type="text" placeholder="请输入角色详情" id="add_roledetail">
                </td>
            </tr>
            <tr>
                <td>分配权限</td>
                <td>
                    <div style="width: 300px">
                        <div class="layui-unselect layui-form-select treeSelect">
                            <div class="layui-select-title">
                                <span class="layui-input layui-unselect" id="treeclass">选择上级菜单</span>
                                <input type="hidden" name="selectID" class="preMenuId">
                                <i class="layui-edge" style="right: 20px;"></i>
                            </div>
                            <dl class="layui-anim layui-anim-upbit">
                                <dd>
                                    <ul id="meuntree"></ul>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <button onclick="emptyAddRolePageInput()" class="add_role_empty_button">
                        <i class="layui-icon layui-icon-refresh" style="font-size: 16px; color: #fcfdfd;"></i>
                        清空</button>
                    <button onclick="check_role_Info()" class="add_role_insert_button">
                        <i class="layui-icon layui-icon-ok-circle" style="font-size: 16px; color: #fcfdfd;"></i>
                        添加</button>
                </td>
            </tr>
        </table>
    </div>
</body>
<script>
    $(function () {
        add_role_permission();
    })
    // function add_role_permission() {
    //
    //     layui.use(['form', 'layer', 'element', 'tree','util'], function () {
    //         var form = layui.form;
    //         var layer = layui.layer;
    //         var element = layui.element;
    //         var tree = layui.tree, util = layui.util;
    //
    //         //初始化菜单树，点击某一列赋值显示到input框上
    //         $.post(path+"/web/user/role/elements", {token: sessionStorage.getItem('token')}, function(res){
    //             var treeData = recureFn(res.list);
    //             treeData.unshift({
    //                 id: 0,
    //                 title: "无上级菜单",
    //                 children: []
    //             })
    //             tree.render({
    //                 elem: "#meuntree",
    //                 data: treeData,
    //                 click: function(obj){
    //                     // layer.msg(JSON.stringify(obj.data));
    //                     var $select = $(".layui-form-select");
    //                     $select.removeClass("layui-form-selected").find(".layui-select-title span").html(obj.data.title).end().find("input:hidden[name='selectID']").val(obj.data.id);
    //                     form.render();
    //                 }
    //             })
    //         });
    //
    //         //点击显示/隐藏树形下拉框
    //         $(".treeSelect").on("click", ".layui-select-title", function (e) {
    //             $(this).parents(".treeSelect").toggleClass("layui-form-selected");
    //             layui.stope(e);
    //         }).on("click", "dl ul span.layui-tree-iconClick", function (e) {
    //             layui.stope(e);//阻止事件冒泡，即点击span小图标展开时不触发父级逻辑
    //         });
    //
    //         //递归处理树形数组
    //         function recureFn( arr ){
    //             var result = [];
    //             arr.map( item=>{
    //                 var option = {
    //                     title: item.menuName,
    //                     id: item.id,
    //                     children: recureFn( item.children )
    //                 }
    //                 // if( item.children.length!=0 ) delete option.checked; //只给最底层添加checked
    //                 result.push( option );
    //             });
    //             return result;
    //         }
    //
    //     })
    // }

</script>
</html>
