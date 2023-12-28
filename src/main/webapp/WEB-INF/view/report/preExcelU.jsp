<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../report/inc/js.jsp"%>
    <title>Title</title>
    <style type="text/css">
        .opExcel_but_div{
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
    <script>
        var path='<%=basePath%>';

        $(function () {
            getPreExcelU();
        })

        function getPreExcelU(){
            var batchID='${param.batchID}';
            var typeExcelU=batchID.substring(1,2);
            $("#typeExcelU").html(typeExcelU);
            $.post(path+"report/getReportFUByBatchID",
                {batchID:batchID},
                function(result){
                    var repFUList=result.data;
                    var noVarRepDiv=$("#noVarRep_div_U");
                    var noVarRepTab=noVarRepDiv.find("table");
                    for (var i = 0; i < repFUList.length; i++) {
                        var repFU=repFUList[i];
                        var rowNumber=repFU.rowNumber;
                        var colNumber=repFU.colNumber;
                        var value=repFU.value;
                        if(rowNumber==3&colNumber==6||
                           rowNumber==3&colNumber==8||
                           rowNumber==8&colNumber==3||
                           rowNumber==8&colNumber==4||
                           rowNumber==11&colNumber==4||
                           rowNumber==11&colNumber==5||
                           rowNumber==12&colNumber==4||
                           rowNumber==12&colNumber==5||
                           rowNumber==16&colNumber==4||
                           rowNumber==20&colNumber==4){
                        	if(value!=null)
                        		value=value.substring(0,20);
                        }
                        noVarRepTab.find("#td"+rowNumber+"_"+colNumber).text(value);//暂时把变量添加到未显示变量的报表模版里
                    }
                }
                ,"json");
        }

        function base64 (content) {
            return window.btoa(unescape(encodeURIComponent(content)));
        }

        function exportExcel() {
            var table = $("#noVarRep_div_U");
            var excelContent = table[0].innerHTML;
            var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
            excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
            excelFile += "<body><table>";
            excelFile += excelContent;
            excelFile += "</table></body>";
            excelFile += "</html>";
            var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
            var a = document.createElement("a");
            a.download = '${param.batchID}'+".xlsx";
            a.href = link;
            a.click();
        }
    </script>
</head>
<body>
<div class="opExcel_but_div" onclick="exportExcel()">导出Excel</div>
<div id="noVarRep_div_U">
    <table border="1px" id="opcUSTable" style="border-collapse:collapse;font: normal 400 16px '微软雅黑';">
        <tr>
            <th colspan="13" style="height: 50px;">
                <input type="hidden" value="1"/>
                <span class="onetd1">U类（<span id="typeExcelU"></span>）胶生产记录</span>
                <%--<span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>--%>
            </th>
        </tr>
        <!--第二行-->
        <tr style="height: 50px;">
            <td style="text-align: center;">YSD101信息</td>
            <td style="text-align: center;background-color: #5b9bd5;"></td>
            <td style="text-align: center;">YSD102信息</td>
            <td style="text-align: center;background-color: #5b9bd5;"></td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;" colspan="2">当班操作员：</td>
            <td style="text-align: center;background-color: #c6e0b4;" colspan="2"></td>
            <td style="text-align: center;" colspan="2">接班操作员：</td>
            <td style="text-align: center;background-color: #c6e0b4;" colspan="2"></td>
        </tr>
        <!--第三行-->
        <tr style="height: 100px;">
            <td style="text-align: center;">生产编号</td>
            <td style="text-align: center;background-color: #fee699;" id="td3_2"></td>
            <td style="text-align: center;">反应釜：</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td3_4" colspan="2"></td>
            <td style="text-align: center;">开始时间</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td3_6"></td>
            <td style="text-align: center;">结束时间</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td3_8"></td>
            <td style="text-align: center;">生产工时</td>
            <td style="text-align: center;background-color: #fee699;" id="td3_10"></td>
            <td style="text-align: center;">生产日期</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td3_12"></td>
        </tr>
        <!--第四行-->
        <tr style="height: 30px;">
            <td style="text-align: center;">在用F料罐</td>
            <td style="text-align: center;background-color: #5b9bd5;">□ 1罐</td>
            <td style="text-align: center;background-color: #5b9bd5;">浓度：</td>
            <td style="text-align: center;background-color: #5b9bd5;"></td>
            <td style="text-align: center;background-color: #5b9bd5;">□ 2罐</td>
            <td style="text-align: center;background-color: #5b9bd5;">浓度：</td>
            <td style="text-align: center;background-color: #5b9bd5;"></td>
            <td></td>
            <td colspan="5" rowspan="2"></td>
        </tr>
        <!--第五行-->
        <tr style="height: 150px;">
            <td style="text-align: center;">1号罐用前重</td>
            <td style="text-align: center;background-color: #fee699;" id="td5_2"></td>
            <td style="text-align: center;">1号罐用后重</td>
            <td style="text-align: center;background-color: #fee699;" id="td5_4"></td>
            <td style="text-align: center;">2号罐用前重</td>
            <td style="text-align: center;background-color: #fee699;" id="td5_6"></td>
            <td style="text-align: center;">2号罐用后重</td>
            <td style="text-align: center;background-color: #fee699;" id="td5_8"></td>
        </tr>
        <!--第六行-->
        <tr style="height: 30px;">
            <td style="text-align: center;" rowspan="2">原辅料kg</br>kg</td>
            <td style="text-align: center;" rowspan="2" colspan="2">实际重量</br>kg</td>
            <td style="text-align: center;" colspan="2">操作时间</td>
            <td style="text-align: center;" rowspan="2">反应釜1温度温 度℃</td>
            <td style="text-align: center;" rowspan="2"> PH(25℃)</td>
            <td style="text-align: center;" rowspan="2">浑浊度检测T.T</td>
            <td style="text-align: center;" colspan="2">计量罐或反应釜重量 kg</td>
            <td style="text-align: center;" rowspan="2">重量差 kg</td>
            <td style="text-align: center;" rowspan="2">操作时间</br>min</td>
            <td style="text-align: center;" rowspan="2">备注</td>
        </tr>
        <!--第七行-->
        <tr style="height: 30px;">
            <td style="text-align: center;">动作开始</td>
            <td style="text-align: center;">动作结束</td>
            <td style="text-align: center;">加料前kg</td>
            <td style="text-align: center;">加料后kg</td>
        </tr>
        <!--第八行-->
        <tr style="height: 50px;">
            <td style="text-align: center;">YSD101</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td8_2" colspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td8_3" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td8_4" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td8_5" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td8_6" rowspan="2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td8_7" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td8_8" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td8_9" rowspan="2"></td>
            <td style="text-align: center;background-color: #fee699;" id="td8_10" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td8_11" rowspan="2"></td>
            <td style="text-align: center;" id="td8_12" rowspan="2"></td>
        </tr>
        <!--第九行-->
        <tr style="height: 70px;">
            <td style="text-align: center;">纯净水</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td9_2" colspan="2"></td>
        </tr>
        <!--第十行-->
        <tr style="height: 50px;">
            <td style="text-align: center;">YSD109</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td10_2" colspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td10_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td10_4"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td10_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td10_6"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td10_7" colspan="4"></td>
            <td style="text-align: center;background-color: #fee699;" id="td10_8"></td>
            <td style="text-align: center;" id="td10_9"></td>
        </tr>
        <!--第十一行-->
        <tr style="height: 150px;">
            <td style="text-align: center;">YSD215一次</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td11_2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td11_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td11_4"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td11_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td11_6"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td11_7" colspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td11_8"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td11_9"></td>
            <td style="text-align: center;background-color: #fee699;" id="td11_10"></td>
            <td style="text-align: center;background-color: #fee699;" id="td11_11"></td>
            <td style="text-align: center;" id="td11_12"></td>
        </tr>
        <!--第十二行-->
        <tr style="height: 150px;">
            <td style="text-align: center;">YSD215二次</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td12_2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td12_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td12_4"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td12_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td12_6"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td12_7" colspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td12_8"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td12_9"></td>
            <td style="text-align: center;background-color: #fee699;" id="td12_10"></td>
            <td style="text-align: center;background-color: #fee699;" id="td12_11"></td>
            <td style="text-align: center;" id="td12_12"></td>
        </tr>
        <!--第十三行-->
        <tr style="height: 20px;">
            <td style="text-align: center;" colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
        </tr>
        <!--第十四行-->
        <tr style="height: 50px;">
            <td style="text-align: center;">YSD103</td>
            <td style="text-align: center;background-color: #c6e0b4;">粉提1</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td14_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td14_4" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td14_5" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td14_6" rowspan="2"></td>
            <td style="text-align: center;background-color: #5b9bd5" id="td14_7" rowspan="2">录入PH</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td14_8" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td14_9" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td14_10" rowspan="2"></td>
            <td style="text-align: center;background-color: #fee699;" id="td14_11" rowspan="2"></td>
            <td style="text-align: center;background-color: #fee699;" id="td14_12" rowspan="2"></td>
            <td style="text-align: center;" id="td14_13"></td>
        </tr>
        <!--第十五行-->
        <tr style="height: 70px;">
            <td style="text-align: center;">YSD102</td>
            <td style="text-align: center;background-color: #c6e0b4">粉提2</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td15_3"></td>
            <td style="text-align: center;" id="td15_4"></td>
        </tr>
        <!--第十六行-->
        <tr style="height: 50px;">
            <td style="text-align: center;">开始升温</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td16_2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td16_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td16_4" colspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td16_5">瞬时蒸汽压力：MPa</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td16_6" colspan="2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td16_7" colspan="3" rowspan="6"></td>
            <td style="text-align: center;background-color: #fee699;" id="td16_8" rowspan="2"></td>
            <td style="text-align: center;" id="td16_9"></td>
        </tr>
        <!--第十七行-->
        <tr style="height: 150px;">
            <td style="text-align: center;">升温至高温度</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td17_2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td17_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td17_4"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td17_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td17_6"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td17_7">录入PH</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td17_8"></td>
            <td style="text-align: center;" id="td17_9"></td>
        </tr>
        <!--第十八行-->
        <tr style="height: 150px;">
            <td style="text-align: center;">一次保温10分钟测PH</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td18_2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td18_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td18_4"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td18_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td18_6"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td18_7">录入PH</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td18_8"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td18_9"></td>
            <td style="text-align: center;" id="td18_10"></td>
        </tr>
        <!--第十九行-->
        <tr style="height: 150px;">
            <td style="text-align: center;">降温开始</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td19_2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td19_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td19_4"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td19_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td19_6"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td19_7" colspan="2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td19_8"></td>
            <td style="text-align: center;" id="td19_9"></td>
        </tr>
        <!--第二十行-->
        <tr style="height: 150px;">
            <td style="text-align: center;">降温停止</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td20_2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td20_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td20_4"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td20_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td20_6"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td20_7" colspan="2"></td>
            <td style="text-align: center;background-color: #fee699;" id="td20_8">降温时间</td>
            <td style="text-align: center;" id="td20_9"></td>
        </tr>
        <!--第二十一行-->
        <tr style="height: 150px;">
            <td style="text-align: center;">加酸并计时</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td21_2"></td>
            <td style="text-align: center;background-color: #5b9bd5" id="td21_3">录入加酸量</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td21_4"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td21_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td21_6"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td21_7">录入PH</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td21_8"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td21_9"></td>
            <td style="text-align: center;" id="td21_10"></td>
        </tr>
        <!--第二十二行-->
        <tr style="height: 60px;">
            <td style="text-align: center;">冰水雾点</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td22_2" colspan="2" rowspan="3"></td>
            <td style="text-align: center;background-color: #fee699;" id="td22_3" colspan="2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td22_4"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td22_5"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td22_6"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td22_7" colspan="2"></td>
            <td style="text-align: center;">冰水时间</td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td22_9"></td>
            <td style="text-align: center;" id="td22_10"></td>
        </tr>
        <!--第二十三行-->
        <tr style="height: 60px;">
            <td style="text-align: center;">20度雾点</td>
            <td style="text-align: center;background-color: #fee699;" id="td23_2" colspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td23_3">℃</td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td23_4"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td23_5"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td23_6"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td23_7"></td>
            <td style="text-align: center;">缩聚总时间</td>
            <td style="text-align: center;background-color: #5b9bd5" id="td23_9"></td>
            <td style="text-align: center;" id="td23_10"></td>
        </tr>
        <!--第二十四行-->
        <tr style="height: 60px;">
            <td style="text-align: center;">二次降温</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td24_2">：</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td24_3"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td24_4"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td24_5"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td24_6"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td24_7" rowspan="3" colspan="3"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td24_8"></td>
            <td style="text-align: center;" id="td24_9"></td>
        </tr>
        <!--第二十五行-->
        <tr style="height: 50px;">
            <td style="text-align: center;">加碱</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td25_2"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td25_3">录入加碱量</td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td25_4"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td25_5" colspan="2" rowspan="2"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td25_6">录入PH</td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td25_7"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td25_8"></td>
            <td style="text-align: center;" id="td25_9"></td>
        </tr>
        <!--第二十六行-->
        <tr style="height: 70px;">
            <td style="text-align: center;">70度终止降温</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td26_2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td26_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td26_4"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td26_5"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td26_6"></td>
            <td style="text-align: center;background-color: #fee699;" id="td26_7"></td>
            <td style="text-align: center;" id="td26_8"></td>
        </tr>
        <!--第二十七行-->
        <tr style="height: 150px;">
            <td style="text-align: center;">二次粉料加入</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td27_2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td27_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td27_4"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td27_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td27_6"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td27_7"></td>
            <td style="text-align: center;" id="td27_8"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td27_9"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td27_10"></td>
            <td style="text-align: center;background-color: #fee699;" id="td27_11"></td>
            <td style="text-align: center;background-color: #fee699;" id="td27_12"></td>
            <td style="text-align: center;" id="td27_13"></td>
        </tr>
        <!--第二十八行-->
        <tr style="height: 30px;">
            <td style="text-align: center;">二次保温时间20</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td28_2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td28_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td28_4">：</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td28_5" colspan="2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td28_6" rowspan="4"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td28_7"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td28_8" colspan="3"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td28_9"></td>
            <td style="text-align: center;" id="td28_10"></td>
        </tr>
        <!--第二十九行-->
        <tr style="height: 100px;">
            <td style="text-align: center;">助剂6加入</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td29_2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td29_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td29_4"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td29_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td29_6" rowspan="2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td29_7"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td29_8" rowspan="2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td29_9" rowspan="2"></td>
            <td style="text-align: center;background-color: #fee699;" id="td29_10" rowspan="2"></td>
            <td style="text-align: center;background-color: #fee699;" id="td29_11" rowspan="2"></td>
            <td style="text-align: center;" id="td29_12"></td>
        </tr>
        <!--第三十行-->
        <tr style="height: 100px;">
            <td style="text-align: center;">水加入</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td30_2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td30_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td30_4"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td30_5"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td30_6"></td>
            <td style="text-align: center;" id="td30_7"></td>
        </tr>
        <!--第三十一行-->
        <tr style="height: 100px;">
            <td style="text-align: center;">冷却至结束</td>
            <td id="td31_2" colspan="2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td31_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td31_4"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td31_5"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td31_6"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td31_7"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td31_8"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td31_9"></td>
            <td style="text-align: center;background-color: #fee699;" id="td31_10"></td>
            <td style="text-align: center;" id="td31_11"></td>
        </tr>
        <!--第三十二行-->
        <tr style="height: 20px;">
            <td style="text-align: center;" rowspan="3">质量终检</td>
            <td style="text-align: center;background-color: #aeaaaa;"></td>
            <td style="text-align: center;background-color: #aeaaaa;"></td>
            <td style="text-align: center;background-color: #aeaaaa;"></td>
            <td style="text-align: center;background-color: #aeaaaa;"></td>
            <td style="text-align: center;">水数W.T</td>
            <td style="text-align: center;">PH</td>
            <td style="text-align: center;">固化时间s</td>
            <td style="text-align: center;">粘度Vis</td>
            <td style="text-align: center;">浑浊时间</td>
            <td style="text-align: center;">固含Solid</td>
            <td style="text-align: center;">外观</td>
            <td></td>
        </tr>
        <!-- 第三十三行-->
        <tr style="height: 70px;">
            <td style="text-align: center;">生产总重</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td33_2"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td33_3" rowspan="2"></td>
            <td style="text-align: center;" id="td33_4">20℃标准：</td>
            <td style="text-align: center;" id="td33_5">250-400</td>
            <td style="text-align: center;" id="td33_6">7.4-8.0</td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td33_7"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td33_8"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td33_9"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td33_10"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td33_11"></td>
            <td style="text-align: center;" id="td33_12"></td>
        </tr>
        <!-- 第三十四行-->
        <tr style="height: 50px;">
            <td style="text-align: center;background-color: #aeaaaa;" id="td34_1"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td34_2"></td>
            <td style="text-align: center;">实测：</td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td34_4"></td>
            <td style="text-align: center;background-color: #5b9bd5;" id="td34_5"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td34_6"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td34_7"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td34_8"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td34_9"></td>
            <td style="text-align: center;background-color: #aeaaaa;" id="td34_10"></td>
            <td style="text-align: center;" id="td34_11"></td>
        </tr>
        <!-- 第三十五行-->
        <tr style="height: 100px;">
            <td style="text-align: center;" rowspan="2">排 胶</td>
            <td style="text-align: center;">日期与时间：</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td35_3"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td35_4"></td>
            <td style="text-align: center;background-color: #fee699;" id="td35_5">排胶时间差</td>
            <td style="text-align: center;" id="td35_6">打入胶罐1：</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td35_7"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td35_8"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td35_9"></td>
            <td style="text-align: center;background-color: #fee699;" id="td35_10"></td>
            <td style="text-align: center;" id="td35_11"></td>
            <td style="text-align: center;background-color: #fee699;" id="td35_12" rowspan="2"></td>
            <td style="text-align: center;" id="td35_13"></td>
        </tr>
        <!-- 第三十六行-->
        <tr style="height: 100px;">
            <td style="text-align: center;">反应釜打胶前后重量</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td36_2"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td36_3"></td>
            <td style="text-align: center;background-color: #fee699;" id="td36_4">反应釜重量差</td>
            <td style="text-align: center;" id="td36_5">打入胶罐2：</td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td36_6"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td36_7"></td>
            <td style="text-align: center;background-color: #c6e0b4;" id="td36_8"></td>
            <td style="text-align: center;background-color: #fee699;" id="td36_9"></td>
            <td style="text-align: center;" id="td36_10"></td>
        </tr>
    </table>
</div>
</body>
</html>
