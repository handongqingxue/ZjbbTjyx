<%--
  Created by IntelliJ IDEA.
  User: lilekang
  Date: 2023/4/19
  Time: 9:15 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <title>永兴制胶数据报表系统</title>
    <link rel="stylesheet" href="<%=basePath%>resource/css/form_style.css">
    <script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="<%=basePath%>resource/css/system_style.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/layui.css">
    <script type="text/javascript" src="<%=basePath%>resource/js/layui.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/opc.js"></script>
</head>
<body>
<div class="home">
    <%--头部--%>
    <div class="home-head">
        <span class="home-head-span">永兴制胶数据报表系统</span>
        <ul class="home-head-ul">
            <li></li>
            <li>|</li>
            <li id="system-time"></li>
            <li>|</li>
            <li class="layui-nav-item">
                <span>欢迎您!</span>
                <a style="cursor:pointer;">超级管理员</a>
            </li>
            <li>|</li>
            <li><a href="<%=basePath%>main/exit">退出</a></li>
        </ul>
    </div>
    <%--身体--%>
    <div class="home-body">
        <div class="home-left-body">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <%--layui-nav-itemed 自动展开--%>
                <li class="layui-nav-item">
                    <a href="javascript:;">M类报表生成</a>
                    <dl class="layui-nav-child" id="mWscPcjlList_dl" style="color: #79e6e8">
                    	<!-- 
                        <c:forEach items="${mWscPcjlList}" var="item">
                            <dd><a href="javascript:;" onclick="typeQuery('M')">${item.batchID}</a></dd>
                        </c:forEach>
                         -->
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">U类报表生成</a>
                    <dl class="layui-nav-child" id="uWscPcjlList_dl">
                    	<!-- 
                        <c:forEach items="${uWscPcjlList}" var="item">
                            <dd><a href="javascript:;" onclick="typeQuery('U')">${item.batchID}</a></dd>
                        </c:forEach>
                         -->
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">M类报表查询</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="typeQuery('MA')">MA胶种</a></dd>
                        <dd><a href="javascript:;" onclick="typeQuery('MB')">MB胶种</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">U类报表查询</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="typeQuery('UD')">UD胶种</a></dd>
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
            </div>
        </div>
    </div>
</div>
<script>
var path='<%=basePath%>';
    $(function () {
    	getLeftMenuData("");
    })
    
    function getLeftMenuData(type){
    	$.post(path+"report/getLeftMenuData",
   			{type:type},
   			function(result){
   				if(type==""){
	   				var mWscPcjlList=result.mWscPcjlList;
	   				var mWscPcjlListDl=$("#mWscPcjlList_dl");
	   				mWscPcjlListDl.empty();
	   				for (var i = 0; i < mWscPcjlList.length; i++) {
	   					var mWscPcjl=mWscPcjlList[i];
	   	   				mWscPcjlListDl.append("<dd><a onclick=\"showCreateArea('M','"+mWscPcjl.batchID+"')\">"+mWscPcjl.batchID+"</a></dd>");
					}
	   				
	   				var uWscPcjlList=result.uWscPcjlList;
	   				var uWscPcjlListDl=$("#uWscPcjlList_dl");
	   				uWscPcjlListDl.empty();
	   				for (var i = 0; i < uWscPcjlList.length; i++) {
	   					var uWscPcjl=uWscPcjlList[i];
	   	   				uWscPcjlListDl.append("<dd><a onclick=\"showCreateArea('U','"+mWscPcjl.batchID+"')\">"+uWscPcjl.batchID+"</a></dd>");
					}
	   				
	   				var defaultBatchID=mWscPcjlList[0].batchID;
	   				showCreateArea("M",defaultBatchID);
	   				getUnCreRepVarList(defaultBatchID);
   				}
   				else if(type=="mWsc"){
   					var mWscPcjlList=result.mWscPcjlList;
	   				var mWscPcjlListDl=$("#mWscPcjlList_dl");
	   				mWscPcjlListDl.empty();
	   				for (var i = 0; i < mWscPcjlList.length; i++) {
	   					var mWscPcjl=mWscPcjlList[i];
	   	   				mWscPcjlListDl.append("<dd><a onclick=\"typeQuery('M')\">"+mWscPcjl.batchID+"</a></dd>");
					}
	   				
	   				var defaultBatchID=mWscPcjlList[0].batchID;
	   				getUnCreRepVarList(defaultBatchID);
   				}
    		}
    	,"json");
    }

    function inspect(id){
        if (id==0){
            document.getElementById("right-body-head-span2").innerHTML="报表生成页面";
            document.getElementById("right-body-head-icon").classList.replace("layui-icon-search","layui-icon-add-circle");
        }else if(id==1){
            document.getElementById("right-body-head-span2").innerHTML="报表查询页面";
            document.getElementById("right-body-head-icon").classList.replace("layui-icon-add-circle","layui-icon-search");
        }
    }
    
    function showCreateArea(recType,batchID){
    	// alert(recType+","+batchID);
    	var createMDisplay;
    	var createUDisplay;
    	var searchMDisplay;
    	var searchUDisplay;
    	if (recType=="M"){
    		createMDisplay="block";
    		createUDisplay="none";
    		searchMDisplay="none";
    		searchUDisplay="none"
    	}
		$("#createM").css("display",createMDisplay);
        $("#createU").css("display",createUDisplay);
        $("#searchM").css("display",searchMDisplay);
        $("#searchU").css("display",searchUDisplay);
        
        $("#right-body-head-span2").text("报表生成页面");
        
        getUnCreRepVarList(batchID);
    }

    function typeQuery(type) {
        if (type=="M"){
            $("#createM").css('display','block');
            $("#createU").css('display','none');
            $("#searchM").css('display','none');
            $("#searchU").css('display','none');
            inspect(0);
        }else if(type=="U"){
            $("#createM").css('display','none');
            $("#createU").css('display','block');
            $("#searchM").css('display','none');
            $("#searchU").css('display','none');
            inspect(0);
        } else if (type=="MA"||type=="MB"){
            $("#createM").css('display','none');
            $("#createU").css('display','none');
            $("#searchM").css('display','block');
            $("#searchU").css('display','none');
            inspect(1);
            getPcjlListByType(type);
            getReportFMList(0,type);

        }else if (type=="UD"){
            $("#createM").css('display','none');
            $("#createU").css('display','none');
            $("#searchM").css('display','none');
            $("#searchU").css('display','block');
            inspect(1);
            getPcjlListByType(type);
            getReportFMList(0,type);
        }
    }

    function getPcjlListByType(type) {
        $.post("<%=basePath%>report/getPcjlListByType",
            {type:type},
            function(result){
                if(result.msg=="ok"){
                    var typeSelect=$("#typeSelect");
                    var glue=$("#glue");
                    glue.text(type)
                    typeSelect.empty();
                    var list=result.data;
                    //完整功能
                    typeSelect.append("<option value=''>请选择</option>")
                    for (var i=0;i<list.length;i++){
                        typeSelect.append("<option value=\""+list[i].batchID+"\">"+list[i].batchID+"</option>")
                    }
                }
            }
            ,"json");
    }

</script>
</body>
</html>
