<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>永兴制胶数据报表系统</title>
</head>
<body>
<%@include file="../report/inc/js.jsp"%>
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
	   	   				mWscPcjlListDl.append("<dd><a onclick=\"showCreateArea('"+mWscPcjl.batchID+"')\">"+mWscPcjl.batchID+"</a></dd>");
					}
	   				
	   				var uWscPcjlList=result.uWscPcjlList;
	   				var uWscPcjlListDl=$("#uWscPcjlList_dl");
	   				uWscPcjlListDl.empty();
	   				for (var i = 0; i < uWscPcjlList.length; i++) {
	   					var uWscPcjl=uWscPcjlList[i];
	   	   				uWscPcjlListDl.append("<dd><a onclick=\"showCreateArea('"+uWscPcjl.batchID+"')\">"+uWscPcjl.batchID+"</a></dd>");
					}
	   				
	   				var defaultBatchID=mWscPcjlList[0].batchID;
	   				showCreateArea(defaultBatchID);
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
    	}
		$("#createM").css("display",createMDisplay);
        $("#createU").css("display",createUDisplay);
        $("#searchM").css("display",searchMDisplay);
        $("#searchU").css("display",searchUDisplay);
        
        $("#right-body-head-span2").text("报表生成页面");
        document.getElementById("right-body-head-icon").classList.replace("layui-icon-search","layui-icon-add-circle");
        
        getUnCreRepVarList(batchID);
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
		$("#createM").css("display",createMDisplay);
        $("#createU").css("display",createUDisplay);
        $("#searchM").css("display",searchMDisplay);
        $("#searchU").css("display",searchUDisplay);
        
        $("#right-body-head-span2").text("报表查询页面");
        document.getElementById("right-body-head-icon").classList.replace("layui-icon-add-circle","layui-icon-search");
        
        getPcjlListByType(recType);
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


        }else if (type=="UD"){
            $("#createM").css('display','none');
            $("#createU").css('display','none');
            $("#searchM").css('display','none');
            $("#searchU").css('display','block');
            inspect(1);
            getPcjlListByType(type);
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
                    getReportFMPageList();
                }
            }
            ,"json");
    }

</script>
</body>
</html>
