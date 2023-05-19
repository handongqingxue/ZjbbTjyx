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
    <script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/pdf/jspdf.debug.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/pdf/html2canvas.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
#outputPdf_div{
    background-color: #00FF00;
    width: 500px;
    height: 300px;
}
</style>
</head>
<body>

<script>
var path='<%=basePath%>';
$(function(){
	initJOpcTV();
	//readOpcProVarList();
});

function initJOpcTV(){
	$.post(path+"opc/initJOpcTV",
		function(result){
			if(result.status=="ok")
				setInterval("syncTriggerVar()",3000);
		}
	,"json");
}

function syncTriggerVar(){
	$.post(path+"opc/syncTriggerVar",
		function(){
		
		}
	,"json");
}

function readOpcProVarList(){
	$.post(path+"opc/readOpcProVarList",
		function(){
		
		}
	,"json");
}

// layui.use(['laypage', 'layer'], function(){
// 	var laypage = layui.laypage,layer = layui.layer;
// 	//测试数据
// 	var data = [
// 	    '北京',
// 	    '上海',
// 	    '广州',
// 	    '深圳',
// 	    '杭州',
// 	    '长沙',
// 	    '合肥',
// 	    '宁夏',
// 	    '成都',
// 	    '西安',
// 	    '南昌',
// 	    '上饶',
// 	    '沈阳',
// 	    '济南',
// 	    '厦门',
// 	    '福州',
// 	    '九江',
// 	    '宜春',
// 	    '赣州',
// 	    '宁波',
// 	    '绍兴',
// 	    '无锡',
// 	    '苏州',
// 	    '徐州',
// 	    '东莞',
// 	    '佛山',
// 	    '中山',
// 	    '成都',
// 	    '武汉',
// 	    '青岛',
// 	    '天津',
// 	    '重庆',
// 	    '南京',
// 	    '九江',
// 	    '香港',
// 	    '澳门',
// 	    '台北'
// 	];
//
// 	//调用分页
// 	laypage.render({
// 	  elem: 'demo2'
// 	  ,count: data.length,
// 	  limit: 1,
// 	   prev: '<em><</em>',
// 	   next: '<em>></em>',
// 	  theme: '#1E9FFF',
// 	  layout: ['count', 'prev', 'page', 'next',  'skip'],
// 	  jump: function(obj){
// 	    //模拟渲染
// 	    document.getElementById('biuuu_city_list').innerHTML = function(){
// 	      var arr = []
// 	      ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
// 	      layui.each(thisData, function(index, item){
// 	    	  var tableStr="<table>";
// 			    	  tableStr+="<tr>";
// 				    	  tableStr+="<td>";
// 				    	  		tableStr+=item;
// 				    	  tableStr+="</td>";
// 				    	  tableStr+="<td>";
// 				    	  		tableStr+=item;
// 				    	  tableStr+="</td>";
// 				    	  tableStr+="<td>";
// 				    	  		tableStr+=item;
// 				    	  tableStr+="</td>";
// 			    	  tableStr+="</tr>";
// 			    	  tableStr+="<tr>";
// 				    	  tableStr+="<td>";
// 				    	  		tableStr+=item;
// 				    	  tableStr+="</td>";
// 				    	  tableStr+="<td>";
// 				    	  		tableStr+=item;
// 				    	  tableStr+="</td>";
// 				    	  tableStr+="<td>";
// 				    	  		tableStr+=item;
// 				    	  tableStr+="</td>";
// 			    	  tableStr+="</tr>";
// 	    	  	tableStr+="</table>"
// 	        arr.push(tableStr);
// 	      });
// 	      console.log(arr)
// 	      return arr.join('');
// 	    }();
// 	  }
// 	});
// });

function outputPdf1(){
    html2canvas(
        //document.getElementById("outputPdf_div"),
        $("#outputPdf_div"),
        {
            scale: '5',
            dpi: '300',//导出pdf清晰度
            //dpi: '172',//导出pdf清晰度
            onrendered: function (canvas) {
                var contentWidth = canvas.width;
                var contentHeight = canvas.height;
                //一页pdf显示html页面生成的canvas高度;
                var pageHeight = contentWidth / 592.28 * 841.89;
                //未生成pdf的html页面高度
                var leftHeight = contentHeight;
                //pdf页面偏移
                var position = 0;
                //html页面生成的canvas在pdf中图片的宽高（a4纸的尺寸[595.28,841.89]）
                var imgWidth = 595.28;
                var imgHeight = 592.28 / contentWidth * contentHeight;
                var pageData = canvas.toDataURL('image/jpeg', 1.0);
                var pdf = new jsPDF('', 'pt', 'a4');
                //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
                //当内容未超过pdf一页显示的范围，无需分页
                if (leftHeight < pageHeight) {
                    pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
                } else {
                    while (leftHeight > 0) {
                        pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
                        leftHeight -= pageHeight;
                        position -= 841.89;
                        //避免添加空白页
                        if (leftHeight > 0) {
                            pdf.addPage();
                        }
                    }
                }
                var qpbh=$("#outputPdf_div #qpbh_span").text();
                // var zzrqY=$("#outputPdf_div #zzrqY_span").text();
                // var zzrqM=$("#outputPdf_div #zzrqM_span").text();
                pdf.save(qpbh+'.pdf');
                // $("#pdf_div").css("border-color","#000");

                $("#outputPdf_div").empty();
                resizeOutputPdfDiv(0);
            },
            //背景设为白色（默认为黑色）
            background: "#fff"
        }
    )
}
</script>
<%--    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
<%--        <legend>自定义主题 - 颜色随意定义</legend>--%>
<%--    </fieldset>--%>
<%--    <div id="demo2"></div>--%>
<%--    <div id="biuuu_city_list"></div>--%>
    <div id="outputPdf_div">
        <span id="qpbh_span">aaaa</span>
    </div>
    <button onclick="outputPdf1()">打印pdf</button>
</body>
</html>