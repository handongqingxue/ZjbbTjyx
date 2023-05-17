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
    <table border="1px" id="opcUSTable" style="width:100%;border-collapse:collapse;text-align:center;font: normal 400 16px '微软雅黑';">
        <tr>
            <th colspan="13" style="height: 50px;">
                <input type="hidden" value="1"/>
                <span class="onetd1">U类（<span id="typeExcelU"></span>）胶生产记录</span>
                <%--<span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>--%>
            </th>
        </tr>
        <!--第二行-->
        <tr style="height: 50px;">
            <td style="width:5%">YSD101信息</td>
            <td style="width:5%;background-color: #5b9bd5"></td>
            <td style="width:5%">YSD102信息</td>
            <td style="width:5%;background-color: #5b9bd5"></td>
            <td style="width:5%"></td>
            <td colspan="2" style="width:13%">当班操作员：</td>
            <td colspan="2" style="width:13%;background-color: #c6e0b4"></td>
            <td colspan="2" style="width:13%;">接班操作员：</td>
            <td colspan="2" style="width:13%;background-color: #c6e0b4"></td>
        </tr>
        <!--第三行-->
        <tr style="height: 100px;">
            <td>生产编号</td>
            <td id="td3_2" style="background-color: #fee699;"></td>
            <td>反应釜：</td>
            <td id="td3_4" colspan="2" style="background-color: #c6e0b4;"></td>
            <td>开始时间</td>
            <td id="td3_6" style="background-color: #c6e0b4;"></td>
            <td>结束时间</td>
            <td id="td3_8" style="background-color: #c6e0b4;"></td>
            <td>生产工时</td>
            <td id="td3_10" style="background-color: #fee699;"></td>
            <td>生产日期</td>
            <td id="td3_12" style="width:7%;background-color: #c6e0b4;"></td>
        </tr>
        <!--第四行-->
        <tr style="height: 30px;">
            <td>在用F料罐</td>
            <td style="background-color: #5b9bd5">□ 1罐</td>
            <td style="background-color: #5b9bd5">浓度：</td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5">□ 2罐</td>
            <td style="background-color: #5b9bd5">浓度：</td>
            <td style="background-color: #5b9bd5"></td>
            <td></td>
            <td colspan="5" rowspan="2"></td>
        </tr>
        <!--第五行-->
        <tr style="height: 150px;">
            <td>1号罐用前重</td>
            <td id="td5_2" style="background-color: #fee699;"></td>
            <td>1号罐用后重</td>
            <td id="td5_4" style="background-color: #fee699;"></td>
            <td>2号罐用前重</td>
            <td id="td5_6" style="background-color: #fee699;"></td>
            <td>2号罐用后重</td>
            <td id="td5_8" style="background-color: #fee699;"></td>
        </tr>
        <!--第六行-->
        <tr style="height: 30px;">
            <td rowspan="2">原辅料kg</br>kg</td>
            <td rowspan="2" colspan="2">实际重量</br>kg</td>
            <td colspan="2">操作时间</td>
            <td rowspan="2">反应釜1温度温 度℃</td>
            <td rowspan="2"> PH(25℃)</td>
            <td rowspan="2">浑浊度检测T.T</td>
            <td colspan="2">计量罐或反应釜重量 kg</td>
            <td rowspan="2">重量差 kg</td>
            <td rowspan="2">操作时间</br>min</td>
            <td rowspan="2">备注</td>
        </tr>
        <!--第七行-->
        <tr style="height: 30px;">
            <td>动作开始</td>
            <td>动作结束</td>
            <td>加料前kg</td>
            <td>加料后kg</td>
        </tr>
        <!--第八行-->
        <tr style="height: 50px;">
            <td>YSD101</td>
            <td id="td8_2" style="background-color: #c6e0b4;" colspan="2"></td>
            <td id="td8_3" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td8_4" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td8_5" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td8_6" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td8_7" style="background-color: #aeaaaa;" rowspan="2"></td>
            <td id="td8_8" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td8_9" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td8_10" style="background-color: #fee699;" rowspan="2"></td>
            <td id="td8_11" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td8_12" rowspan="2"></td>
        </tr>
        <!--第九行-->
        <tr style="height: 70px;">
            <td>纯净水</td>
            <td id="td9_2" style="background-color: #c6e0b4;" colspan="2"></td>
        </tr>
        <!--第十行-->
        <tr style="height: 50px;">
            <td>YSD109</td>
            <td id="td10_2" style="background-color: #c6e0b4;" colspan="2"></td>
            <td id="td10_3" style="background-color: #c6e0b4;"></td>
            <td id="td10_4" style="background-color: #c6e0b4;"></td>
            <td id="td10_5" style="background-color: #c6e0b4;"></td>
            <td id="td10_6" style="background-color: #c6e0b4;"></td>
            <td id="td10_7" style="background-color: #aeaaaa;" colspan="4"></td>
            <td id="td10_8" style="background-color: #fee699;"></td>
            <td id="td10_9"></td>
        </tr>
        <!--第十一行-->
        <tr style="height: 150px;">
            <td>YSD215一次</td>
            <td id="td11_2" style="background-color: #c6e0b4;"></td>
            <td id="td11_3" style="background-color: #c6e0b4;"></td>
            <td id="td11_4" style="background-color: #c6e0b4;"></td>
            <td id="td11_5" style="background-color: #c6e0b4;"></td>
            <td id="td11_6" style="background-color: #c6e0b4;"></td>
            <td id="td11_7" style="background-color: #aeaaaa;" colspan="2"></td>
            <td id="td11_8" style="background-color: #c6e0b4;"></td>
            <td id="td11_9" style="background-color: #c6e0b4;"></td>
            <td id="td11_10" style="background-color: #fee699;"></td>
            <td id="td11_11" style="background-color: #fee699;"></td>
            <td id="td11_12"></td>
        </tr>
        <!--第十二行-->
        <tr style="height: 150px;">
            <td>YSD215二次</td>
            <td id="td12_2" style="background-color: #c6e0b4;"></td>
            <td id="td12_3" style="background-color: #c6e0b4;"></td>
            <td id="td12_4" style="background-color: #c6e0b4;"></td>
            <td id="td12_5" style="background-color: #c6e0b4;"></td>
            <td id="td12_6" style="background-color: #c6e0b4;"></td>
            <td id="td12_7" style="background-color: #aeaaaa;" colspan="2"></td>
            <td id="td12_8" style="background-color: #c6e0b4;"></td>
            <td id="td12_9" style="background-color: #c6e0b4;"></td>
            <td id="td12_10" style="background-color: #fee699;"></td>
            <td id="td12_11" style="background-color: #fee699;"></td>
            <td id="td12_12"></td>
        </tr>
        <!--第十三行-->
        <tr style="height: 20px;">
            <td colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
        </tr>
        <!--第十四行-->
        <tr style="height: 50px;">
            <td>YSD103</td>
            <td style="background-color: #c6e0b4;">粉提1</td>
            <td id="td14_3" style="background-color: #c6e0b4;"></td>
            <td id="td14_4" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td14_5" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td14_6" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td14_7" rowspan="2" style="background-color: #5b9bd5">录入PH</td>
            <td id="td14_8" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td14_9" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td14_10" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td14_11" style="background-color: #fee699;" rowspan="2"></td>
            <td id="td14_12" style="background-color: #fee699;" rowspan="2"></td>
            <td id="td14_13"></td>
        </tr>
        <!--第十五行-->
        <tr style="height: 70px;">
            <td>YSD102</td>
            <td style="background-color: #c6e0b4">粉提2</td>
            <td id="td15_3" style="background-color: #c6e0b4;"></td>
            <td id="td15_4"></td>
        </tr>
        <!--第十六行-->
        <tr style="height: 50px;">
            <td>开始升温</td>
            <td id="td16_2" style="background-color: #c6e0b4;"></td>
            <td id="td16_3" style="background-color: #aeaaaa;"></td>
            <td id="td16_4" style="background-color: #c6e0b4;" colspan="2"></td>
            <td id="td16_5" style="background-color: #c6e0b4;">瞬时蒸汽压力：MPa</td>
            <td id="td16_6" style="background-color: #c6e0b4;" colspan="2"></td>
            <td id="td16_7" style="background-color: #aeaaaa;" colspan="3" rowspan="6"></td>
            <td id="td16_8" style="background-color: #fee699;" rowspan="2"></td>
            <td id="td16_9"></td>
        </tr>
        <!--第十七行-->
        <tr style="height: 150px;">
            <td>升温至高温度</td>
            <td id="td17_2" style="background-color: #aeaaaa;"></td>
            <td id="td17_3" style="background-color: #aeaaaa;"></td>
            <td id="td17_4" style="background-color: #c6e0b4;"></td>
            <td id="td17_5" style="background-color: #aeaaaa;"></td>
            <td id="td17_6" style="background-color: #c6e0b4;"></td>
            <td id="td17_7" style="background-color: #5b9bd5">录入PH</td>
            <td id="td17_8" style="background-color: #aeaaaa;"></td>
            <td id="td17_9"></td>
        </tr>
        <!--第十八行-->
        <tr style="height: 150px;">
            <td>一次保温10分钟测PH</td>
            <td id="td18_2" style="background-color: #aeaaaa;"></td>
            <td id="td18_3" style="background-color: #aeaaaa;"></td>
            <td id="td18_4" style="background-color: #c6e0b4;"></td>
            <td id="td18_5" style="background-color: #aeaaaa;"></td>
            <td id="td18_6" style="background-color: #c6e0b4;"></td>
            <td id="td18_7" style="background-color: #5b9bd5">录入PH</td>
            <td id="td18_8" style="background-color: #aeaaaa;"></td>
            <td id="td18_9" style="background-color: #c6e0b4;"></td>
            <td id="td18_10"></td>
        </tr>
        <!--第十九行-->
        <tr style="height: 150px;">
            <td>降温开始</td>
            <td id="td19_2" style="background-color: #aeaaaa;"></td>
            <td id="td19_3" style="background-color: #aeaaaa;"></td>
            <td id="td19_4" style="background-color: #c6e0b4;"></td>
            <td id="td19_5" style="background-color: #aeaaaa;"></td>
            <td id="td19_6" style="background-color: #c6e0b4;"></td>
            <td id="td19_7" style="background-color: #aeaaaa;" colspan="2"></td>
            <td id="td19_8" style="background-color: #aeaaaa;"></td>
            <td id="td19_9"></td>
        </tr>
        <!--第二十行-->
        <tr style="height: 150px;">
            <td>降温停止</td>
            <td id="td20_2" style="background-color: #aeaaaa;"></td>
            <td id="td20_3" style="background-color: #aeaaaa;"></td>
            <td id="td20_4" style="background-color: #c6e0b4;"></td>
            <td id="td20_5" style="background-color: #aeaaaa;"></td>
            <td id="td20_6" style="background-color: #c6e0b4;"></td>
            <td id="td20_7" style="background-color: #aeaaaa;" colspan="2"></td>
            <td id="td20_8" style="background-color: #fee699;">降温时间</td>
            <td id="td20_9"></td>
        </tr>
        <!--第二十一行-->
        <tr style="height: 150px;">
            <td>加酸并计时</td>
            <td id="td21_2" style="background-color: #c6e0b4;"></td>
            <td id="td21_3" style="background-color: #5b9bd5">录入加酸量</td>
            <td id="td21_4" style="background-color: #c6e0b4;"></td>
            <td id="td21_5" style="background-color: #c6e0b4;"></td>
            <td id="td21_6" style="background-color: #c6e0b4;"></td>
            <td id="td21_7" style="background-color: #5b9bd5">录入PH</td>
            <td id="td21_8" style="background-color: #c6e0b4;"></td>
            <td id="td21_9" style="background-color: #aeaaaa;"></td>
            <td id="td21_10"></td>
        </tr>
        <!--第二十二行-->
        <tr style="height: 60px;">
            <td>冰水雾点</td>
            <td id="td22_2" style="background-color: #aeaaaa;" colspan="2" rowspan="3"></td>
            <td id="td22_3" style="background-color: #fee699;" colspan="2"></td>
            <td id="td22_4" style="background-color: #aeaaaa;"></td>
            <td id="td22_5" style="background-color: #5b9bd5"></td>
            <td id="td22_6" style="background-color: #5b9bd5"></td>
            <td id="td22_7" style="background-color: #aeaaaa;" colspan="2"></td>
            <td>冰水时间</td>
            <td id="td22_9" style="background-color: #5b9bd5"></td>
            <td id="td22_10"></td>
        </tr>
        <!--第二十三行-->
        <tr style="height: 60px;">
            <td>20度雾点</td>
            <td id="td23_2" style="background-color: #fee699;" colspan="2"></td>
            <td id="td23_3" style="background-color: #c6e0b4;">℃</td>
            <td id="td23_4" style="background-color: #5b9bd5"></td>
            <td id="td23_5" style="background-color: #5b9bd5"></td>
            <td id="td23_6" style="background-color: #aeaaaa;"></td>
            <td id="td23_7" style="background-color: #aeaaaa;"></td>
            <td>缩聚总时间</td>
            <td id="td23_9" style="background-color: #5b9bd5"></td>
            <td id="td23_10"></td>
        </tr>
        <!--第二十四行-->
        <tr style="height: 60px;">
            <td>二次降温</td>
            <td id="td24_2" style="background-color: #c6e0b4;">：</td>
            <td id="td24_3" style="background-color: #aeaaaa;"></td>
            <td id="td24_4" style="background-color: #aeaaaa;"></td>
            <td id="td24_5" style="background-color: #5b9bd5"></td>
            <td id="td24_6" style="background-color: #5b9bd5"></td>
            <td id="td24_7" style="background-color: #aeaaaa;" rowspan="3" colspan="3"></td>
            <td id="td24_8" style="background-color: #aeaaaa;"></td>
            <td id="td24_9"></td>
        </tr>
        <!--第二十五行-->
        <tr style="height: 50px;">
            <td>加碱</td>
            <td id="td25_2" style="background-color: #c6e0b4;"></td>
            <td id="td25_3" style="background-color: #5b9bd5">录入加碱量</td>
            <td id="td25_4" style="background-color: #5b9bd5"></td>
            <td id="td25_5" style="background-color: #aeaaaa;" colspan="2" rowspan="2"></td>
            <td id="td25_6" style="background-color: #5b9bd5">录入PH</td>
            <td id="td25_7" style="background-color: #5b9bd5"></td>
            <td id="td25_8" style="background-color: #aeaaaa;"></td>
            <td id="td25_9"></td>
        </tr>
        <!--第二十六行-->
        <tr style="height: 70px;">
            <td>70度终止降温</td>
            <td id="td26_2" style="background-color: #aeaaaa;"></td>
            <td id="td26_3" style="background-color: #aeaaaa;"></td>
            <td id="td26_4" style="background-color: #c6e0b4;"></td>
            <td id="td26_5" style="background-color: #aeaaaa;"></td>
            <td id="td26_6" style="background-color: #aeaaaa;"></td>
            <td id="td26_7" style="background-color: #fee699;"></td>
            <td id="td26_8"></td>
        </tr>
        <!--第二十七行-->
        <tr style="height: 150px;">
            <td>二次粉料加入</td>
            <td id="td27_2" style="background-color: #c6e0b4;"></td>
            <td id="td27_3" style="background-color: #c6e0b4;"></td>
            <td id="td27_4" style="background-color: #c6e0b4;"></td>
            <td id="td27_5" style="background-color: #c6e0b4;"></td>
            <td id="td27_6" style="background-color: #c6e0b4;"></td>
            <td id="td27_7" style="background-color: #5b9bd5"></td>
            <td id="td27_8"></td>
            <td id="td27_9" style="background-color: #c6e0b4;"></td>
            <td id="td27_10" style="background-color: #c6e0b4;"></td>
            <td id="td27_11" style="background-color: #fee699;"></td>
            <td id="td27_12" style="background-color: #fee699;"></td>
            <td id="td27_13"></td>
        </tr>
        <!--第二十八行-->
        <tr style="height: 30px;">
            <td>二次保温时间20</td>
            <td id="td28_2" style="background-color: #aeaaaa;"></td>
            <td id="td28_3" style="background-color: #aeaaaa;"></td>
            <td id="td28_4" style="background-color: #c6e0b4;">：</td>
            <td id="td28_5" style="background-color: #aeaaaa;" colspan="2"></td>
            <td id="td28_6" style="background-color: #aeaaaa;" rowspan="4"></td>
            <td id="td28_7" style="background-color: #aeaaaa;"  ></td>
            <td id="td28_8" style="background-color: #aeaaaa;" colspan="3"></td>
            <td id="td28_9" style="background-color: #aeaaaa;"></td>
            <td id="td28_10"></td>
        </tr>
        <!--第二十九行-->
        <tr style="height: 100px;">
            <td>助剂6加入</td>
            <td id="td29_2" style="background-color: #c6e0b4;"></td>
            <td id="td29_3" style="background-color: #c6e0b4;"></td>
            <td id="td29_4" style="background-color: #c6e0b4;"></td>
            <td id="td29_5" style="background-color: #c6e0b4;"></td>
            <td id="td29_6" style="background-color: #c6e0b4;" rowspan="2"></td>
            <td id="td29_7" style="background-color: #aeaaaa;"></td>
            <td id="td29_8" rowspan="2" style="background-color: #c6e0b4;"></td>
            <td id="td29_9" rowspan="2" style="background-color: #c6e0b4;"></td>
            <td id="td29_10" style="background-color: #fee699;" rowspan="2"></td>
            <td id="td29_11" style="background-color: #fee699;" rowspan="2"></td>
            <td id="td29_12"></td>
        </tr>
        <!--第三十行-->
        <tr style="height: 100px;">
            <td>水加入</td>
            <td id="td30_2" style="background-color: #c6e0b4;"></td>
            <td id="td30_3" style="background-color: #c6e0b4;"></td>
            <td id="td30_4" style="background-color: #c6e0b4;"></td>
            <td id="td30_5" style="background-color: #c6e0b4;"></td>
            <td id="td30_6" style="background-color: #aeaaaa;"></td>
            <td id="td30_7"></td>
        </tr>
        <!--第三十一行-->
        <tr style="height: 100px;">
            <td>冷却至结束</td>
            <td id="td31_2" colspan="2"></td>
            <td id="td31_3" style="background-color: #aeaaaa;"></td>
            <td id="td31_4" style="background-color: #c6e0b4;"></td>
            <td id="td31_5" style="background-color: #c6e0b4;"></td>
            <td id="td31_6" style="background-color: #c6e0b4;"></td>
            <td id="td31_7" style="background-color: #c6e0b4;"></td>
            <td id="td31_8" style="background-color: #c6e0b4;"></td>
            <td id="td31_9" style="background-color: #aeaaaa;"></td>
            <td id="td31_10" style="background-color: #fee699;"></td>
            <td id="td31_11"></td>
        </tr>
        <!--第三十二行-->
        <tr style="height: 20px;">
            <td rowspan="3">质量终检</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td>水数W.T</td>
            <td>PH</td>
            <td>固化时间s</td>
            <td>粘度Vis</td>
            <td>浑浊时间</td>
            <td>固含Solid</td>
            <td>外观</td>
            <td></td>
        </tr>
        <!-- 第三十三-->
        <tr style="height: 70px;">
            <td>生产总重</td>
            <td id="td33_2" style="background-color: #c6e0b4;"></td>
            <td id="td33_3" style="background-color: #aeaaaa;" rowspan="2"></td>
            <td id="td33_4">20℃标准：</td>
            <td id="td33_5">250-400</td>
            <td id="td33_6">7.4-8.0</td>
            <td id="td33_7" style="background-color: #aeaaaa;"></td>
            <td id="td33_8" style="background-color: #aeaaaa;"></td>
            <td id="td33_9" style="background-color: #aeaaaa;"></td>
            <td id="td33_10" style="background-color: #aeaaaa;"></td>
            <td id="td33_11" style="background-color: #aeaaaa;"></td>
            <td id="td33_12"></td>
        </tr>
        <!-- 第三十四-->
        <tr style="height: 50px;">
            <td id="td34_1" style="background-color: #aeaaaa;"></td>
            <td id="td34_2" style="background-color: #aeaaaa;"></td>
            <td>实测：</td>
            <td id="td34_4" style="background-color: #5b9bd5"></td>
            <td id="td34_5" style="background-color: #5b9bd5"></td>
            <td id="td34_6" style="background-color: #aeaaaa;"></td>
            <td id="td34_7" style="background-color: #aeaaaa;"></td>
            <td id="td34_8" style="background-color: #aeaaaa;"></td>
            <td id="td34_9" style="background-color: #aeaaaa;"></td>
            <td id="td34_10" style="background-color: #aeaaaa;"></td>
            <td id="td34_11"></td>
        </tr>
        <!-- 第三十五-->
        <tr style="height: 100px;">
            <td rowspan="2">排 胶</td>
            <td>日期与时间：</td>
            <td id="td35_3" style="background-color: #c6e0b4;"></td>
            <td id="td35_4" style="background-color: #c6e0b4;"></td>
            <td id="td35_5" style="background-color: #fee699;">排胶时间差</td>
            <td id="td35_6">打入胶罐1：</td>
            <td id="td35_7" style="background-color: #c6e0b4;"></td>
            <td id="td35_8" style="background-color: #c6e0b4;"></td>
            <td id="td35_9" style="background-color: #c6e0b4;"></td>
            <td id="td35_10" style="background-color: #fee699;"></td>
            <td id="td35_11"></td>
            <td id="td35_12" style="background-color: #fee699;" rowspan="2"></td>
            <td id="td35_13"></td>
        </tr>
        <!-- 第三十六-->
        <tr style="height: 100px;">
            <td>反应釜打胶前后重量</td>
            <td id="td36_2" style="background-color: #c6e0b4;"></td>
            <td id="td36_3" style="background-color: #c6e0b4;"></td>
            <td id="td36_4" style="background-color: #fee699;">反应釜重量差</td>
            <td id="td36_5">打入胶罐2：</td>
            <td id="td36_6" style="background-color: #c6e0b4;"></td>
            <td id="td36_7" style="background-color: #c6e0b4;"></td>
            <td id="td36_8" style="background-color: #c6e0b4;"></td>
            <td id="td36_9" style="background-color: #fee699;"></td>
            <td id="td36_10"></td>
        </tr>
    </table>
</div>
</body>
</html>
