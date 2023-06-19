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
    permissionList();
})
//检查角色名
function check_RoleName() {
    var roleName = $("#add_rolename").val();
    if(roleName==null||roleName==""||roleName=="请填写用户名"){
        layer.msg('请填写角色名称', {icon: 5},);
        return false;
    }
    else if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(roleName)) {
        layer.msg('角色名不能有特殊字符', {icon: 5});
        return false;
    }
    else if (/(^\_)|(\__)|(\_+$)/.test(roleName)) {
        layer.msg('角色名首尾不能出现下划线\'_\'', {icon: 5});
        return false;
    }
    else
        return true;
}

//检查权限
function check_Permission() {
    var permission = $("#add_role_permission").val();
    if(permission==null||permission==""){
        layer.msg('请选择权限',{icon: 5});
        return false;
    }
    else
        return true;
}
//检查信息
function check_role_Info() {
    if (check_RoleName()){
        if (check_Permission()){
            checkRoleNameDoesItExist();
        }
    }
}
//检查用户名是否存在
function checkRoleNameDoesItExist() {
    var roleName=$("#add_rolename").val();
    $.post(path+"/main/checkRoleName",
        {RoleName:roleName},
        function(result){
            if(result.msg=="ok"){//ok不存在,no以存在
                var roleName=$("#add_rolename").val();
                var roleDetail=$("#add_roledetail").val();
                var permission = $("#add_role_permission").val();
                insertRole(roleName,roleDetail,permission);
            }else {
                layer.msg('该用户已存在,请重新输入',{icon: 5});
            }
        }
        ,"json");
}

//添加角色操作
function insertRole(roleName,roleDetail,permission) {
    $.post(path + "/main/addRole",
        {
            RoleName:roleName,
            Detail:roleDetail,
            permissionId:permission,
        },
        function(result){
            if (result.msg=="ok"){
                emptyAddRolePageInput();//清空输入框
                // getUserList();//刷新用户列表
                layer.msg('添加成功!', {icon: 1});
            }else {
                layer.msg('添加失败,状态代码'+result.status,{icon: 5});
            }
        }
        ,"json");
}
//清空添加角色页面的input
function emptyAddRolePageInput() {
    $("#add_rolename").val("");
    $("#add_detail").val("");
    permissionList();
}

//查询全部权限
function permissionList() {
    var rolePermissionSelect = $("#add_role_permission");
    $.post(path+"/main/getPermissionList",
        {},
        function(result){
            if(result.msg=="ok"){
                var permissionList=result.data;
                rolePermissionSelect.empty();
                rolePermissionSelect.append("<option value=\"\">请选择</option>")
                for (var p of permissionList) {
                    rolePermissionSelect.append("<option value=\""+p.id+"\">"+p.permName+"</option>")
                }
            }
        }
        ,"json");
}

