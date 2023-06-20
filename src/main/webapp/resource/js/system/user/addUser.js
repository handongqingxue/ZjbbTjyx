//获取当前网址，如： http://localhost:8083/myproj/view/ahha.jsp
var curWwwPath=window.document.location.href;
//获取主机地址之后的目录，如： myproj/view/ahha.jsp
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
//获取主机地址，如： http://localhost:8080
var localhostPaht=curWwwPath.substring(0,pos);
//获取带"/"的项目名，如：/ahha
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
//得到了 服务器名称和项目名称
var path=localhostPaht+projectName;
// var baseUrl="${pageContext.request.contextPath}";
$(function () {

    roleList();
})

//检查用户名
function check_UserName() {
    var userName = $("#add_username").val();
    if(userName==null||userName==""||userName=="请填写用户名"){
        layer.msg('请填写用户名', {icon: 5},);
        return false;
    }
    else if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(userName)) {
        layer.msg('用户名不能有特殊字符', {icon: 5});
        return false;
    }
    else if (/(^\_)|(\__)|(\_+$)/.test(userName)) {
        layer.msg('用户名首尾不能出现下划线\'_\'', {icon: 5});
        return false;
    }
    else
        return true;
}
//检查密码
function check_Password() {
    var password = $("#add_password").val();
    if(password==null||password==""){
        layer.msg('请填写密码',{icon: 5});
        return false;
    }
    else
        return true;
}
//检查真实姓名
function check_RealName() {
    var realName = $("#add_realname").val();
    if(realName==null||realName==""){
        layer.msg('请填写真实姓名',{icon: 5});
        return false;
    }
    else
        return true;
}
//检查角色
function check_Role() {
    // var roles = add_user_role.getValue("value");
    var roles = $("#add_user_role").val();
    if(roles==null||roles==""){
        layer.msg('请选择角色',{icon: 5});
        return false;
    }
    else
        return true;
}
//检查信息
function check_user_Info() {
    if (check_UserName()){
        if (check_Password()){
            if (check_RealName()){
                if (check_Role()){
                    checkUserNameDoesItExist();
                }
            }
        }
    }
}
//检查用户名是否存在
function checkUserNameDoesItExist() {
    var userName=$("#add_username").val();
    $.post(path+"/main/checkUserName",
        {UserName:userName},
        function(result){
            if(result.msg=="ok"){//ok不存在,no以存在
                var userName=$("#add_username").val();
                var passWord=MD5($("#add_password").val()).toUpperCase();
                var realName = $("#add_realname").val();
                // var roles = add_user_role.getValue("value");
                var roles = $("#add_user_role").val();
                insertUser(userName,passWord,realName,roles);
            }else {
                layer.msg('该用户已存在,请重新输入',{icon: 5});
            }
        }
        ,"json");
}

//添加用户操作
function insertUser(userName,passWord,realName,role) {
    var pass=MD5(passWord).toUpperCase();
    $.post(path + "/main/addUser",
        {
            UserName:userName,
            Psd:pass,
            RealName:realName,
            role:role
        },
        function(result){
            if (result.msg=="ok"){
                emptyAddUserPageInput();//清空输入框
                // getUserList();//刷新用户列表
                layer.msg('添加成功!', {icon: 1});
            }else {
                layer.msg('添加失败,状态代码'+result.status,{icon: 5});
            }
        }
        ,"json");
}
//清空添加用户页面的input
function emptyAddUserPageInput() {
    $("#add_username").val("");
    $("#add_password").val("");
    $("#add_realname").val("");
    roleList();
}

//查询全部角色
function roleList() {
    var userRoleSelect = $("#add_user_role");
    $.post(path+"/main/getRoleList",
        {},
        function(result){
            if(result.msg=="ok"){
                var roleList=result.data;
                userRoleSelect.empty();
                userRoleSelect.append("<option value=''>请选择</option>")
                for (var i=0;i<roleList.length;i++){
                    userRoleSelect.append("<option value=\""+roleList[i].id+"\">"+roleList[i].roleName+"</option>")
                }
            }
        }
        ,"json");
}

// var add_user_role;
// function roleAll(roleList) {
//     var roleAll=[];
//     for (var i=0;i<roleList.length;i++){
//         roleAll.push({name:roleList[i].roleName,value:roleList[i].id})
//     }
//     add_user_role=xmSelect.render(
//         {
//             el: '#add_user_role',
//             toolbar: { show: true },
//             data: roleAll
//         })
// }
