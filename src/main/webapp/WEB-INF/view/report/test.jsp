<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=basePath%>resource/css/layui.css">
<script type="text/javascript" src="<%=basePath%>resource/js/layui.js"></script>
<!-- https://www.bejson.com/doc/layui/demo/laypage.html -->
<title>Insert title here</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>自定义主题 - 颜色随意定义</legend>
</fieldset>
 
<div id="demo2"></div>
<div id="biuuu_city_list"></div> 
<script>
layui.use(['laypage', 'layer'], function(){
var laypage = layui.laypage,layer = layui.layer;
//测试数据
var data = [
    '北京',
    '上海',
    '广州',
    '深圳',
    '杭州',
    '长沙',
    '合肥',
    '宁夏',
    '成都',
    '西安',
    '南昌',
    '上饶',
    '沈阳',
    '济南',
    '厦门',
    '福州',
    '九江',
    '宜春',
    '赣州',
    '宁波',
    '绍兴',
    '无锡',
    '苏州',
    '徐州',
    '东莞',
    '佛山',
    '中山',
    '成都',
    '武汉',
    '青岛',
    '天津',
    '重庆',
    '南京',
    '九江',
    '香港',
    '澳门',
    '台北'
];

//调用分页
laypage.render({
  elem: 'demo2'
  ,count: data.length,
  limit: 1,
   prev: '<em><</em>',
   next: '<em>></em>',
  theme: '#1E9FFF',
  layout: ['count', 'prev', 'page', 'next',  'skip'],
  jump: function(obj){
    //模拟渲染
    document.getElementById('biuuu_city_list').innerHTML = function(){
      var arr = []
      ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
      layui.each(thisData, function(index, item){
    	  var tableStr="<table>";
		    	  tableStr+="<tr>";
			    	  tableStr+="<td>";
			    	  		tableStr+=item;
			    	  tableStr+="</td>";
			    	  tableStr+="<td>";
			    	  		tableStr+=item;
			    	  tableStr+="</td>";
			    	  tableStr+="<td>";
			    	  		tableStr+=item;
			    	  tableStr+="</td>";
		    	  tableStr+="</tr>";
		    	  tableStr+="<tr>";
			    	  tableStr+="<td>";
			    	  		tableStr+=item;
			    	  tableStr+="</td>";
			    	  tableStr+="<td>";
			    	  		tableStr+=item;
			    	  tableStr+="</td>";
			    	  tableStr+="<td>";
			    	  		tableStr+=item;
			    	  tableStr+="</td>";
		    	  tableStr+="</tr>";
    	  	tableStr+="</table>"
        arr.push(tableStr);
      });
      console.log(arr)
      return arr.join('');
    }();
  }
});

});
</script>
</body>
</html>