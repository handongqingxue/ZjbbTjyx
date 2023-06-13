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
var baseUrl=localhostPaht+projectName;
// var baseUrl="${pageContext.request.contextPath}";


function mouseover() {
    document.getElementById("login_button").style.backgroundColor="#58b7e8";
}
function mouseleave() {
    document.getElementById("login_button").style.backgroundColor="#5e94c8";
}

function checkUserName(){
    var userName=$("#userName").val();
    if(userName==null||userName==""||userName=="请填写用户名"){
        // layer.msg('请填写用户名', {icon: 5});
        // $("#userName").css("color","#f00");
        // $("#userName").val("请填写用户名");
    // ,offset:['55%','45%'],time:1000,area:['80px','66px']
        layer.msg('请填写用户名', {icon: 5},);
        return false;
    }
    else if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(userName)) {
        // layer.msg('用户名不能有特殊字符', {icon: 5});
        // $("#userName").css("color","#f00");
        // $("#userName").val("用户名不能有特殊字符");
        layer.msg('用户名不能有特殊字符', {icon: 5});
        return false;
    }
    else if (/(^\_)|(\__)|(\_+$)/.test(userName)) {
        // layer.msg('用户名首尾不能出现下划线\'_\'', {icon: 5});
        // $("#userName").css("color","#f00");
        // $("#userName").val("用户名首尾不能出现下划线\'_\'");
        layer.msg('用户名首尾不能出现下划线\'_\'', {icon: 5});
        return false;
    }
    else
        return true;
}

function checkPassword(){
    var password=$("#password").val();
    if(password==null||password==""||password=="请填写密码"){
        layer.msg('请填写密码',{icon: 5});
        return false;
    }
    else
        return true;
}

function checkInfo(){
    if(checkUserName()){
        if(checkPassword()){
            var userName=$("#userName").val();
            var passWord=MD5($("#password").val()).toUpperCase();
            login(userName,passWord);
        }
    }
}

function login(userName,password){
    layer.load(2);
    setTimeout(function(){
        layer.closeAll('loading');
    }, 1500);
    $.post(baseUrl + "/main/login",
        {
            userName:userName,
            Psd:password
        },
        function(json){
            if(json.status==1){
                layer.msg('登入成功', {icon: 1});
                window.location.href=baseUrl+json.url;
            }else if(json.status==0){
                layer.msg(json.msg, {icon: 5});
            }
        }
        ,"json");
}
function insertUser() {

    var pass=MD5("12345678").toUpperCase();
    $.post(baseUrl + "/main/addUser",
        {
            UserName:'lilekang1',
            Psd:pass
        },
        function(json){
            alert(json)
            // if(json.status==0){
            //     window.location.href=baseUrl+json.url;
            // }else if(json.status==1){
            //     alert(json.msg);
            // }
        }
        ,"json");
}
