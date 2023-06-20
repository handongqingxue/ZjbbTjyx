<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>永兴制胶数据报表系统</title>
    <style>

    </style>
</head>
<body>
<%@include file="../report/inc/js.jsp"%>
<script type="text/javascript" src="<%=basePath%>resource/js/opc.js"></script>

<div class="home">
    <%--头部--%>
    <div class="home-head">
        <span class="home-head-span">永兴制胶数据报表系统</span>
        <ul class="home-head-ul">
            <li></li>
            <li>|</li>
            <li id="system-time"></li>
            <li>|</li>
            <li class="father"><span>欢迎您 !&nbsp;&nbsp;</span><a style="cursor:pointer;">
                <c:if test="${sessionScope.user.realName==null}">
                    <span>游客</span>
                </c:if>
                <c:if test="${sessionScope.user.realName!=null}">
                    ${sessionScope.user.realName}
                </c:if>
            </a>
                <c:if test="${sessionScope.user.realName!=null}">
                    <ul class="son">
                        <li>个人中心</li>
                        <li>
                            <a href="#" onclick="exit()">退出登录</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope.user.realName==null}">
                    <ul class="son2">
                        <li>
                            <a href="<%=basePath%>/main/goLogin">登录页面</a>
                        </li>
                    </ul>
                </c:if>
            </li>
        </ul>
    </div>
    <%--身体--%>
    <div class="home-body">
        <div class="home-left-body">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <%--layui-nav-itemed 自动展开--%>
                <li class="layui-nav-item">
                    <a href="javascript:;">报表生成</a>
                    <dl class="layui-nav-child" style="color: #79e6e8">
                        <li class="layui-nav-item">
                            <a href="javascript:;">M类</a>
                            <dl class="layui-nav-child" id="mWscBatchList_dl" style="color: #79e6e8">
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;">U类</a>
                            <dl class="layui-nav-child" id="uWscBatchList_dl">
                            </dl>
                        </li>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">报表查询</a>
                    <dl class="layui-nav-child">
                        <li class="layui-nav-item">
                            <a href="javascript:;">M类</a>
                            <dl class="layui-nav-child" id="mYscGlueTypeList_dl"></dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;">U类</a>
                            <dl class="layui-nav-child" id="uYscGlueTypeList_dl"></dl>
                        </li>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <c:if test="${userAllRole[0].id==1||userAllRole[0].id==2}">
                        <a href="javascript:;">系统管理</a>
                    </c:if>
                    <dl class="layui-nav-child">
                        <li class="layui-nav-item">
                            <a href="javascript:;" onclick="showUserArea('yonghu')">用户管理</a>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;" onclick="showRoleArea('role')">角色管理</a>
                        </li>
                    </dl>
                </li>
            </ul>
        </div>
        <div class="home-right-body">
            <%--右部分标题头--%>
            <div class="right-body-head">
                <span class="right-body-head-span1">
                     <i class="layui-icon" id="right-body-head-icon" style="font-size: 30px; color: #2a2a2b;"></i>
                </span>
                <span id="right-body-head-span2" class="right-body-head-span2"></span>
            </div>
            <%--右部分body--%>
            <div class="right-body-main" style="overflow: hidden">
                <div id="createM">
                    <%@include file="../report/inc/createM.jsp"%>
                </div>
                <div id="createU">
                    <%@include file="../report/inc/createU.jsp"%>
                </div>
                <div id="searchM">
                    <%@include file="../report/inc/searchM.jsp"%>
                </div>
                <div id="searchU">
                    <%@include file="../report/inc/searchU.jsp"%>
                </div>
                <div>
                    <div id="roleList">
                        <%@include file="../report/system/role/roleList.jsp"%>
                    </div>
                    <div id="userList">
                        <%@include file="../report/system/user/userList.jsp"%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
var path='<%=basePath%>';
$(function () {
	getLeftMenuData("");
})

function exit() {
    layer.confirm("是否要退出当前用户？", {
        btn: ["确定", "取消"] //按钮
    }, function (index) {
        $.post(path+"main/exit",
            function (result) {
                window.location.href=path+result;
            }
        )
        layer.close(index);
    });
}

function getLeftMenuData(type){
	$.post(path+"report/getLeftMenuData",
		{type:type},
		function(result){
			if(type==""){
				var mWscBatchList=result.mWscBatchList;
				var mWscBatchListDl=$("#mWscBatchList_dl");
				mWscBatchListDl.empty();
				for (var i = 0; i < mWscBatchList.length; i++) {
					var mWscBatch=mWscBatchList[i];
					var mWscBatchID=mWscBatch.batchID;
					var mWscRemark=mWscBatch.remark;
	   				mWscBatchListDl.append("<dd><a onclick=\"showCreateArea('"+mWscBatchID+"','"+mWscRemark+"')\">"+mWscBatchID+"</a></dd>");
				}
				
				var mYscGlueTypeList=result.mYscGlueTypeList;
				var mYscGlueTypeListDl=$("#mYscGlueTypeList_dl");
				mYscGlueTypeListDl.empty();
				for (var i = 0; i < mYscGlueTypeList.length; i++) {
					var mYscGlueType=mYscGlueTypeList[i];
					mYscGlueTypeListDl.append("<dd><a onclick=\"showSearchArea('"+mYscGlueType+"')\">"+mYscGlueType+"</a></dd>");
				}
                var uWscBatchList=result.uWscBatchList;
                var uWscBatchListDl=$("#uWscBatchList_dl");
                uWscBatchListDl.empty();
                for (var i = 0; i < uWscBatchList.length; i++) {
                	var uWscBatch=uWscBatchList[i];
                	var uWscBatchID=uWscBatch.batchID;
					var uWscRemark=uWscBatch.remark;
                	uWscBatchListDl.append("<dd><a onclick=\"showCreateArea('"+uWscBatchID+"','"+uWscRemark+"')\">"+uWscBatchID+"</a></dd>");
                }

                var uYscGlueTypeList=result.uYscGlueTypeList;
                var uYscGlueTypeListDl=$("#uYscGlueTypeList_dl");
                uYscGlueTypeListDl.empty();
                for (var i = 0; i < uYscGlueTypeList.length; i++) {
                    var uYscGlueType=uYscGlueTypeList[i];
                    uYscGlueTypeListDl.append("<dd><a onclick=\"showSearchArea('"+uYscGlueType+"')\">"+uYscGlueType+"</a></dd>");
                }
				var defaultBatch=mWscBatchList[0];
				var defaultBatchID=defaultBatch.batchID;
				var defaultRemark=defaultBatch.remark;
				showCreateArea(defaultBatchID,defaultRemark);
				getUnCreRepVarMList(defaultBatchID);
			}
			else if(type=="mWsc"){
				var mWscBatchList=result.mWscBatchList;
				var mWscBatchListDl=$("#mWscBatchList_dl");
				mWscBatchListDl.empty();
				for (var i = 0; i < mWscBatchList.length; i++) {
					var mWscBatch=mWscBatchList[i];
					var mWscBatchID=mWscBatch.batchID;
					var mWscRemark=mWscBatch.remark;
	   				mWscBatchListDl.append("<dd><a onclick=\"showCreateArea('"+mWscBatchID+"','"+mWscRemark+"')\">"+mWscBatchID+"</a></dd>");
				}
				
				var defaultBatch=mWscBatchList[0];
				var defaultBatchID=defaultBatch.batchID;
				getUnCreRepVarMList(defaultBatchID);
			}
			else if(type=="mYsc"){
				var mYscGlueTypeList=result.mYscGlueTypeList;
				var mYscGlueTypeListDl=$("#mYscGlueTypeList_dl");
				mYscGlueTypeListDl.empty();
				for (var i = 0; i < mYscGlueTypeList.length; i++) {
					var mYscGlueType=mYscGlueTypeList[i];
					mYscGlueTypeListDl.append("<dd><a onclick=\"showSearchArea('"+mYscGlueType+"')\">"+mYscGlueType+"</a></dd>");
				}
			}
            else if(type=="uWsc"){
                var uWscBatchList=result.uWscBatchList;
                var uWscBatchListDl=$("#uWscBatchList_dl");
                uWscBatchListDl.empty();
                for (var i = 0; i < uWscBatchList.length; i++) {
                    var uWscBatch=uWscBatchList[i];
                    var uWscBatchID=uWscBatch.batchID;
					var uWscRemark=uWscBatch.remark;
                    uWscBatchIdListDl.append("<dd><a onclick=\"showCreateArea('"+uWscBatchID+"','"+uWscRemark+"')\">"+uWscBatchID+"</a></dd>");
                }

                var defaultBatch=uWscBatchIdList[0];
                var defaultBatchID=defaultBatch.batchID;
                getUnCreRepVarUList(defaultBatchID);
            }
            else if(type=="uYsc"){
                var uYscGlueTypeList=result.uYscGlueTypeList;
                var uYscGlueTypeListDl=$("#uYscGlueTypeList_dl");
                uYscGlueTypeListDl.empty();
                for (var i = 0; i < uYscGlueTypeList.length; i++) {
                    var uYscGlueType=uYscGlueTypeList[i];
                    uYscGlueTypeListDl.append("<dd><a onclick=\"showSearchArea('"+uYscGlueType+"')\">"+uYscGlueType+"</a></dd>");
                }
            }
		}
	,"json");
}

function showCreateArea(batchID,remark){
	//alert($("#opcMCTable").length)
  	var createMDisplay;
	var mWscText;
	
  	var createUDisplay;
	var uWscText;
	
  	var searchMDisplay;
  	var searchUDisplay;
    var userListDisplay;
    var roleListDisplay;
  	var recType=batchID.substring(0,1);
  	if (recType=="M"){
  		createMDisplay="block";
  		mWscText=remark=="0"?"未生成":"已生成";
  		
  		createUDisplay="none";
        uWscText="";
        
  		searchMDisplay="none";
  		searchUDisplay="none"
        userListDisplay="none";
        roleListDisplay="none";
        getUnCreRepVarMList(batchID);
    }
    if (recType=="U"){//
        createMDisplay="none";
        mWscText="";
    
        createUDisplay="block";
        uWscText=remark=="0"?"未生成":"已生成";
        
        searchMDisplay="none";
        searchUDisplay="none"
        userListDisplay="none";
        roleListDisplay="none";
        getUnCreRepVarUList(batchID);
    }
	$("#createM").css("display",createMDisplay);
    $("#m_wsc_span").text(mWscText);
    
    $("#createU").css("display",createUDisplay);
    $("#u_wsc_span").text(uWscText);
    
    $("#searchM").css("display",searchMDisplay);
    $("#searchU").css("display",searchUDisplay);
    $("#userList").css("display",userListDisplay);
    $("#roleList").css("display",roleListDisplay);
    $("#right-body-head-span2").text("报表生成页面");
    document.getElementById("right-body-head-icon").classList.add("layui-icon-add-circle");
}
    
function showSearchArea(glueType){
  	var createMDisplay;
  	var createUDisplay;
  	var searchMDisplay;
  	var searchUDisplay;
    var userListDisplay;
    var roleListDisplay;

  	var recType=glueType.substring(0,1);
  	if (recType=="M"){
  		createMDisplay="none";
  		createUDisplay="none";
  		searchMDisplay="block";
  		searchUDisplay="none";
        userListDisplay="none";
        roleListDisplay="none";

    }
    if (recType=="U"){
        createMDisplay="none";
        createUDisplay="none";
        searchMDisplay="none";
        searchUDisplay="block"
        userListDisplay="none";
        roleListDisplay="none";

    }
	$("#createM").css("display",createMDisplay);
    $("#createU").css("display",createUDisplay);
    $("#searchM").css("display",searchMDisplay);
    $("#searchU").css("display",searchUDisplay);
    $("#userList").css("display",userListDisplay);
    $("#roleList").css("display",roleListDisplay);

    $("#right-body-head-span2").text("报表查询页面");
    document.getElementById("right-body-head-icon").classList.replace("layui-icon-add-circle","layui-icon-search");
      
    getPcjlListByType(glueType);
}

function showUserArea(data){
    var createMDisplay;
    var createUDisplay;
    var searchMDisplay;
    var searchUDisplay;
    var userListDisplay;
    var roleListDisplay;

    var type=data.substring(0,1);
    if (type=="y"){
        createMDisplay="none";
        createUDisplay="none";
        searchMDisplay="none";
        searchUDisplay="none"
        userListDisplay="block";
        roleListDisplay="none";
    }
    $("#createM").css("display",createMDisplay);
    $("#createU").css("display",createUDisplay);
    $("#searchM").css("display",searchMDisplay);
    $("#searchU").css("display",searchUDisplay);
    $("#userList").css("display",userListDisplay);
    $("#roleList").css("display",roleListDisplay);

    $("#right-body-head-span2").text("用户管理页面");
    // document.getElementById("right-body-head-icon").classList.replace("layui-icon-add-circle","layui-icon-username");
}
function showRoleArea(data) {
    var createMDisplay;
    var createUDisplay;
    var searchMDisplay;
    var searchUDisplay;
    var userListDisplay;
    var roleListDisplay;

    var type=data.substring(0,1);
    if (type=="r"){
        createMDisplay="none";
        createUDisplay="none";
        searchMDisplay="none";
        searchUDisplay="none"
        userListDisplay="none";
        roleListDisplay="block";
    }

    $("#createM").css("display",createMDisplay);
    $("#createU").css("display",createUDisplay);
    $("#searchM").css("display",searchMDisplay);
    $("#searchU").css("display",searchUDisplay);
    $("#userList").css("display",userListDisplay);
    $("#roleList").css("display",roleListDisplay);

    $("#right-body-head-span2").text("角色管理页面");
    // document.getElementById("right-body-head-icon").classList.replace("layui-icon-add-circle","layui-icon-username");
}

function getPcjlListByType(type) {
    $.post("<%=basePath%>report/getPcjlListByType",
        {type:type},
        function(result){
            if(result.msg=="ok"){
                var list=result.data;
                //完整功能
                var recType=type.substring(0,1);
                if (recType=="M"){
                    var current_glue_m=$("#current_glue_m");
                    current_glue_m.text(type)
                    var typeSelect=$("#typeSelectM");
                    typeSelect.empty();
                    typeSelect.append("<option value=''>请选择</option>")
                    for (var i=0;i<list.length;i++){
                        typeSelect.append("<option value=\""+list[i].batchID+"\">"+list[i].batchID+"</option>")
                    }
                    getReportFMPageList();
                }else if(recType=="U"){
                    var current_glue_u=$("#current_glue_u");
                    current_glue_u.text(type)
                    var typeSelect=$("#typeSelectU");
                    typeSelect.empty();
                    typeSelect.append("<option value=''>请选择</option>")
                    for (var i=0;i<list.length;i++){
                        typeSelect.append("<option value=\""+list[i].batchID+"\">"+list[i].batchID+"</option>")
                    }
                    getReportFUPageList();
                }
            }
        }
     ,"json");
}
</script>
</body>
</html>
