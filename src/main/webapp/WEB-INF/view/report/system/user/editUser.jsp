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
    <title>修改用户</title>
</head>
<body>
<%@include file="../../../report/inc/js.jsp"%>
<div class="edit_user_div">
    <table class="edit_user_table">
        <tr>
            <td>&nbsp;用户名</td>
            <td>
                <input type="hidden" value="${user.id}" id="userId">
                <input type="hidden" value="${user.psd}" id="requestPassWord">
                <input type="hidden" value="${user.userName}" id="requestUserName">
                <input type="hidden" value="${roleList}" id="requestRoleList">
                <input class="edit_user_input" type="text" placeholder="请输入用户名" id="edit_username" value="${user.userName}"/>
            </td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;密码</td>
            <td>
                <input class="edit_user_input" type="password" placeholder="请输入用户名" id="edit_password"  value="${user.psd}">
            </td>
        </tr>
        <tr>
            <td>真实姓名</td>
            <td>
                <input class="edit_user_input" type="text" placeholder="请输入真实姓名" id="edit_realname" value="${user.realName}">
            </td>
        </tr>
        <tr>
            <td>分配角色</td>
            <td>
<%--                <select class="edit_user_input" id="edit_user_role">--%>
<%--                    <option value="">请选择</option>--%>
<%--                    <c:forEach var="r" items="${roleList}">--%>
<%--                    <option value="${r.id}" <c:if test="${r.id==roleByUserId[0].id}">selected</c:if>>${r.roleName}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
                <div style="width: 300px">
                    <div id="edit_user_role" class="xm-select-demo"></div>
                </div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button onclick="check_edit_user_Info()" class="edit_user_edit_button">
                    <i class="layui-icon layui-icon-ok-circle" style="font-size: 16px; color: #fcfdfd;"></i>
                    修改</button>
            </td>
        </tr>
    </table>
</div>
<script>
    $(function () {
        <%--var getAllRoleByUserId = '<%=request.getAttribute("getAllRoleByUserId")%>'--%>
        var roleList = $("#requestRoleList").val();

        edit_roleAll(roleList);
    })

    var edit_user_role;

    function edit_roleAll(roleList) {
        var roleAll=[];
        for (var i=0;i<roleList.length;i++){
            // roleAll.push({name:roleList[i].roleName,value:roleList[i].id,mutex: 1, selected: true})
            roleAll.push({name:roleList[i].roleName,value:roleList[i].id})
        }
        edit_user_role=xmSelect.render(
            {
                el: '#edit_user_role',
                toolbar: { show: true },
                data: roleAll
            })
    }
</script>
</body>
</html>
