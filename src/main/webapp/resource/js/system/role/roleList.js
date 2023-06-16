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

//添加角色页面
function addRolePage() {
    layer.open({
        type: 2,
        area: ['700px', '450px'],
        fixed: false, //不固定
        maxmin: true,
        title:"添加角色",
        content: path+'main/goAddRolePage'
    });
}
//清空输入框
function emptyRoleInput() {
    $("#roleName").val("");
    $("#roleDetail").val("");
}
//查询全部用户+模糊查询
function getRoleList() {
    $.post(path+"main/getRoleList",
        {
            RoleName:$("#roleName").val(),
            Detail:$("#roleDetail").val()
        },
        function(result){
            var roleList=result.data;
            initPagerRoleList(roleList);
        }
        ,"json");
}

//分页+渲染数据
function initPagerRoleList(roleList){
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage,layer = layui.layer;
        //调用分页
        laypage.render({
            elem: 'role-paging'
            ,count: roleList.length,
            limit: 12,
            prev: '<em><</em>',
            next: '<em>></em>',
            theme: '#1E9FFF',
            layout: ['count', 'prev', 'page', 'next',  'skip'],
            jump: function(obj){
                //模拟渲染
                document.getElementById('role_tbody').innerHTML = function(){
                    var arr = []
                        ,thisData = roleList.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                    layui.each(thisData, function(index, item){
                        var roleList=thisData;
                        arr=[];
                        if (roleList.length>0){
                            for (var i = 0; i < roleList.length; i++) {
                                arr.push("<tr><td style=\"width: 10%\">"+(i+1)+"</td><td style=\"width: 25%\">"+roleList[i].roleName+"</td><td style=\"width: 25%\">"+roleList[i].detail+"</td><td style=\"width: 25%\">"+roleList[i].ctime+"</td><td style=\"width: 15%\">\n" +
                                    "                                <button class=\"role_edit_button\">\n" +
                                    "                                    <i class=\"layui-icon layui-icon-edit\" style=\"font-size: 16px; color: #ffffff;\"></i>\n" +
                                    "                                    编辑\n" +
                                    "                                </button>\n" +
                                    "                                <button class=\"role_delete_button\" onclick='delRole("+roleList[i].id+")'>\n" +
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
//删除用户
function delRole(id){
    layer.prompt({title: '敏感操作,请输入口令', formType: 1}, function(pass, index){
        if (pass=="tjyx"){
            layer.close(index);
            layer.msg('验证成功!');
            $.post(path+"main/delRole",
                {id:id},
                function(result){
                    if(result.msg=="ok"){
                        layui.use('layer', function(){
                            var layer = layui.layer;
                            layer.msg('删除成功!', {icon: 1});
                        });
                        getRoleList();
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