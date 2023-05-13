<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style type="text/css">
        .opPdf_but_div{
            width: 90px;
            height: 30px;
            line-height: 30px;
            margin:20px auto;
            color: #fff;
            text-align:center;
            background-color: #5ac980;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
    <%@include file="../report/inc/js.jsp"%>
    <title>Insert title here</title>
    <script type="text/javascript">
        var path='<%=basePath%>';
        $(function(){
            getPrePdfJsonByUuid();
        });

        function getPrePdfJsonByUuid(){
            var uuid='${param.uuid}';
            $.post(path+"report/getPrePdfJsonByUuid",
                {uuid:uuid},
                function(result){
                    var repHtmlStr=result.data;
                    var repDiv=$("#rep_div");
                    repDiv.empty();
                    repDiv.append(repHtmlStr);
                }
                ,"json");
        }

        function outputPdf(){
            html2canvas(
                $("#rep_div"),
                {
                    scale: '5',
                    dpi: '172',//导出pdf清晰度
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

                        var batchID=$("#rep_div table #batchID_hid").val();
                        pdf.save(batchID+'.pdf');
                    },
                    //背景设为白色（默认为黑色）
                    background: "#fff"
                }
            )
        }
    </script>
</head>
<body>
<div class="opPdf_but_div" onclick="outputPdf()">导出pdf</div>
<div id="rep_div">
</div>
</body>
</html>