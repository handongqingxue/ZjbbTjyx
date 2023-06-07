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
            <li class="father"><span>欢迎您 !&nbsp;&nbsp;</span><a style="cursor:pointer;">${sessionScope.user.userName}</a>
                <ul class="son">
                    <li>个人中心</li>
                    <li>
                        <a href="#" onclick="exit()">退出登录</a>
                    </li>
                </ul>
            </li>
<%--            <li>|</li>--%>
<%--            <li><a href="#" onclick="exit()">退出</a></li>--%>
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
                            <dl class="layui-nav-child" id="mWscBatchIdList_dl" style="color: #79e6e8">
                                <c:forEach items="${mWscBatchIdList}" var="item">
                                    <dd><a href="javascript:;">${item.batchID}</a></dd>
                                </c:forEach>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;">U类</a>
                            <dl class="layui-nav-child" id="uWscBatchIdList_dl">
                                <c:forEach items="${uWscBatchIdList}" var="item">
                                    <dd><a href="javascript:;">${item.batchID}</a></dd>
                                </c:forEach>
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
                    <a href="javascript:;">系统管理</a>
                    <dl class="layui-nav-child">
                        <li class="layui-nav-item">
                            <a href="javascript:;">用户管理</a>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;">角色管理</a>
                        </li>
                    </dl>
                </li>
            </ul>

<%--之前的菜单栏--%>
<%--            <ul class="layui-nav layui-nav-tree" lay-filter="test">--%>
<%--                &lt;%&ndash;layui-nav-itemed 自动展开&ndash;%&gt;--%>
<%--                <li class="layui-nav-item">--%>
<%--                    <a href="javascript:;">M类报表生成</a>--%>
<%--                    <dl class="layui-nav-child" id="mWscBatchIdList_dl" style="color: #79e6e8">--%>
<%--                        <c:forEach items="${mWscBatchIdList}" var="item">--%>
<%--                            <dd><a href="javascript:;">${item.batchID}</a></dd>--%>
<%--                        </c:forEach>--%>
<%--                    </dl>--%>
<%--                </li>--%>
<%--                <li class="layui-nav-item">--%>
<%--                    <a href="javascript:;">U类报表生成</a>--%>
<%--                    <dl class="layui-nav-child" id="uWscBatchIdList_dl">--%>
<%--                        <c:forEach items="${uWscBatchIdList}" var="item">--%>
<%--                            <dd><a href="javascript:;">${item.batchID}</a></dd>--%>
<%--                        </c:forEach>--%>
<%--                    </dl>--%>
<%--                </li>--%>
<%--                <li class="layui-nav-item">--%>
<%--                    <a href="javascript:;">M类报表查询</a>--%>
<%--                    <dl class="layui-nav-child" id="mYscGlueTypeList_dl"></dl>--%>
<%--                </li>--%>
<%--                <li class="layui-nav-item">--%>
<%--                    <a href="javascript:;">U类报表查询</a>--%>
<%--                    <dl class="layui-nav-child" id="uYscGlueTypeList_dl"></dl>--%>
<%--                </li>--%>
<%--            </ul>--%>
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
    if (confirm('是否要退出当前用户？')){
        $.post(path+"main/exit",
            function (result) {
                window.location.href=path+result;
            }
        )
    }
}

function getLeftMenuData(type){
	$.post(path+"report/getLeftMenuData",
		{type:type},
		function(result){
			if(type==""){
				var mWscBatchIdList=result.mWscBatchIdList;
				var mWscBatchIdListDl=$("#mWscBatchIdList_dl");
				mWscBatchIdListDl.empty();
				for (var i = 0; i < mWscBatchIdList.length; i++) {
					var mWscBatchId=mWscBatchIdList[i];
	   				mWscBatchIdListDl.append("<dd><a onclick=\"showCreateArea('"+mWscBatchId+"')\">"+mWscBatchId+"</a></dd>");
				}
				
				var mYscGlueTypeList=result.mYscGlueTypeList;
				var mYscGlueTypeListDl=$("#mYscGlueTypeList_dl");
				mYscGlueTypeListDl.empty();
				for (var i = 0; i < mYscGlueTypeList.length; i++) {
					var mYscGlueType=mYscGlueTypeList[i];
					mYscGlueTypeListDl.append("<dd><a onclick=\"showSearchArea('"+mYscGlueType+"')\">"+mYscGlueType+"</a></dd>");
				}
                var uWscBatchIdList=result.uWscBatchIdList;
                var uWscBatchIdListDl=$("#uWscBatchIdList_dl");
                uWscBatchIdListDl.empty();
                for (var i = 0; i < uWscBatchIdList.length; i++) {
                	var uWscBatchId=uWscBatchIdList[i];
                	uWscBatchIdListDl.append("<dd><a onclick=\"showCreateArea('"+uWscBatchId+"')\">"+uWscBatchId+"</a></dd>");
                }

                var uYscGlueTypeList=result.uYscGlueTypeList;
                var uYscGlueTypeListDl=$("#uYscGlueTypeList_dl");
                uYscGlueTypeListDl.empty();
                for (var i = 0; i < uYscGlueTypeList.length; i++) {
                    var uYscGlueType=uYscGlueTypeList[i];
                    uYscGlueTypeListDl.append("<dd><a onclick=\"showSearchArea('"+uYscGlueType+"')\">"+uYscGlueType+"</a></dd>");
                }
				var defaultBatchID=mWscBatchIdList[0];
				showCreateArea(defaultBatchID);
				getUnCreRepVarMList(defaultBatchID);
			}
			else if(type=="mWsc"){
				var mWscBatchIdList=result.mWscBatchIdList;
				var mWscBatchIdListDl=$("#mWscBatchIdList_dl");
				mWscBatchIdListDl.empty();
				for (var i = 0; i < mWscBatchIdList.length; i++) {
					var mWscBatchId=mWscBatchIdList[i];
	   				mWscBatchIdListDl.append("<dd><a onclick=\"showCreateArea('"+mWscBatchId+"')\">"+mWscBatchId+"</a></dd>");
				}
				
				var defaultBatchID=mWscBatchIdList[0];
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
                var uWscBatchIdList=result.uWscBatchIdList;
                var uWscBatchIdListDl=$("#uWscBatchIdList_dl");
                uWscBatchIdListDl.empty();
                for (var i = 0; i < uWscBatchIdList.length; i++) {
                    var uWscBatchId=uWscBatchIdList[i];
                    uWscBatchIdListDl.append("<dd><a onclick=\"showCreateArea('"+uWscBatchId+"')\">"+uWscBatchId+"</a></dd>");
                }

                var defaultBatchID=uWscBatchIdList[0];
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

function showCreateArea(batchID){
  	var createMDisplay;
  	var createUDisplay;
  	var searchMDisplay;
  	var searchUDisplay;
  	
  	var recType=batchID.substring(0,1);
  	if (recType=="M"){
  		createMDisplay="block";
  		createUDisplay="none";
  		searchMDisplay="none";
  		searchUDisplay="none"
        getUnCreRepVarMList(batchID);

    }
    if (recType=="U"){
        createMDisplay="none";
        createUDisplay="block";
        searchMDisplay="none";
        searchUDisplay="none"
        getUnCreRepVarUList(batchID);

    }
	$("#createM").css("display",createMDisplay);
    $("#createU").css("display",createUDisplay);
    $("#searchM").css("display",searchMDisplay);
    $("#searchU").css("display",searchUDisplay);
      
    $("#right-body-head-span2").text("报表生成页面");
    document.getElementById("right-body-head-icon").classList.add("layui-icon-add-circle");
}
    
function showSearchArea(glueType){
  	var createMDisplay;
  	var createUDisplay;
  	var searchMDisplay;
  	var searchUDisplay;
  	
  	var recType=glueType.substring(0,1);
  	if (recType=="M"){
  		createMDisplay="none";
  		createUDisplay="none";
  		searchMDisplay="block";
  		searchUDisplay="none"
  	}
    if (recType=="U"){
        createMDisplay="none";
        createUDisplay="none";
        searchMDisplay="none";
        searchUDisplay="block"
    }
	$("#createM").css("display",createMDisplay);
    $("#createU").css("display",createUDisplay);
    $("#searchM").css("display",searchMDisplay);
    $("#searchU").css("display",searchUDisplay);
      
    $("#right-body-head-span2").text("报表查询页面");
    document.getElementById("right-body-head-icon").classList.replace("layui-icon-add-circle","layui-icon-search");
      
    getPcjlListByType(glueType);
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
