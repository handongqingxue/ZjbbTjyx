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

//检查用户名是否存在
function checkEditUserNameDoesItExist() {
    var userName=$("#edit_username").val();
    $.post(path+"/report/checkUserName",
        {UserName:userName},
        function(result){
            var requestUserName=$("#requestUserName").val();
            var userId=$("#userId").val();
            var userName=$("#edit_username").val();
            var passWord=$("#edit_password").val();
            var realName = $("#edit_realname").val();
            var role = $("#edit_user_role").val();
            if (requestUserName==userName){
                editUser(userId,userName,passWord,realName,role);
            }else {
                if(result.msg=="ok"){//ok不存在,no以存在
                    editUser(userId,userName,passWord,realName,role);
                }else {
                    layer.msg('该用户已存在,请重新输入',{icon: 5});
                }
            }
        }
        ,"json");
}

//检查信息
function check_edit_user_Info() {
    if (check_Edit_UserName()){
        if (check_Edit_Password()){
            if (check_Edit_RealName()){
                if (check_Edit_Role()){
                    checkEditUserNameDoesItExist();
                }
            }
        }
    }
}

//检查角色
function check_Edit_Role() {
    var role = $("#edit_user_role").val();
    if(role==null||role==""){
        layer.msg('请选择角色',{icon: 5});
        return false;
    }
    else
        return true;
}
//检查真实姓名
function check_Edit_RealName() {
    var realName = $("#edit_realname").val();
    if(realName==null||realName==""){
        layer.msg('请填写真实姓名',{icon: 5});
        return false;
    }
    else
        return true;
}
//检查用户名
function check_Edit_UserName() {
    var userName = $("#edit_username").val();
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
function check_Edit_Password() {
    var password = $("#edit_password").val();
    if(password==null||password==""){
        layer.msg('请填写密码',{icon: 5});
        return false;
    }
    else
        return true;
}
//修改用户信息
function editUser(userId,userName,passWord,realName,role) {
    var pass;
    var requestPassWord = $("#requestPassWord").val();
    if (requestPassWord==passWord){
        pass=passWord;
    }else {
        pass=MD5(passWord).toUpperCase();
    }
     $.post(path+"/report/editUser",
         	{
         	    Id:userId,
         	    UserName:userName,
                Psd:pass,
                RealName:realName,
                role:role.toString()
            },
         	function(result){
                if (result.msg=="ok"){
                    layer.msg('修改成功!',{icon: 1});
                }else{
                    layer.msg('修改失败!',{icon: 5});
                }
         	}
         ,"json");
}