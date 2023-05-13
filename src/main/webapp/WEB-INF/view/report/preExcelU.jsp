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
            $.post(path+"report/getReportFMByBatchID",
                {batchID:batchID},
                function(result){
                    var repFMList=result.data;
                    var noVarRepDiv=$("#noVarRep_div");
                    var noVarRepTab=noVarRepDiv.find("table");
                    for (var i = 0; i < repFMList.length; i++) {
                        var repFM=repFMList[i];
                        var rowNumber=repFM.rowNumber;
                        var colNumber=repFM.colNumber;
                        var value=repFM.value;
                        //console.log(repFM)
                        noVarRepTab.find("#td"+rowNumber+"_"+colNumber).text(value);//暂时把变量添加到未显示变量的报表模版里
                    }
                }
                ,"json");
        }

        function base64 (content) {
            return window.btoa(unescape(encodeURIComponent(content)));
        }

        function exportExcel() {
            var table = $("#noVarRep_div");
            var excelContent = table[0].innerHTML;
            var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
            excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
            excelFile += "<body><table>";
            excelFile += excelContent;
            excelFile += "</table></body>";
            excelFile += "</html>";
            var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
            var a = document.createElement("a");
            var batchID=$("#batchID_hid").val();
            a.download = '${param.batchID}'+".xlsx";
            a.href = link;
            a.click();
        }
    </script>
</head>
<body>
<div class="opExcel_but_div" onclick="exportExcel()">导出Excel</div>
<div id="noVarRep_div">
    <table border="1px" id="opcUSTable" style="width:100%;border-collapse:collapse;text-align:center;font: normal 400 16px '微软雅黑';">
        <tr>
            <th colspan="13" style="height: 50px;">
                <input type="hidden" id="batchID_hid" value="1"/>
                <span class="onetd1">U类（）胶生产记录</span>
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
            <td style="background-color: #fee699;"></td>
            <td>反应釜：</td>
            <td colspan="2" style="background-color: #c6e0b4;"></td>
            <td>开始时间</td>
            <td style="background-color: #c6e0b4;"></td>
            <td>结束时间</td>
            <td style="background-color: #c6e0b4;"></td>
            <td>生产工时</td>
            <td style="background-color: #fee699;"></td>
            <td>生产日期</td>
            <td style="width:7%;background-color: #c6e0b4;"></td>
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
            <td style="background-color: #fee699;"></td>
            <td>1号罐用后重</td>
            <td style="background-color: #fee699;"></td>
            <td>2号罐用前重</td>
            <td style="background-color: #fee699;"></td>
            <td>2号罐用后重</td>
            <td style="background-color: #fee699;"></td>
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
            <td style="background-color: #c6e0b4;" colspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #aeaaaa;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #fee699;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td rowspan="2"></td>
        </tr>
        <!--第九行-->
        <tr style="height: 70px;">
            <td>纯净水</td>
            <td style="background-color: #c6e0b4;" colspan="2"></td>
        </tr>
        <!--第十行-->
        <tr style="height: 50px;">
            <td>YSD109</td>
            <td style="background-color: #c6e0b4;" colspan="2"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;" colspan="4"></td>
            <td style="background-color: #fee699;"></td>
            <td></td>
        </tr>
        <!--第十一行-->
        <tr style="height: 150px;">
            <td>YSD215一次</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;" colspan="2"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #fee699;"></td>
            <td style="background-color: #fee699;"></td>
            <td></td>
        </tr>
        <!--第十二行-->
        <tr style="height: 150px;">
            <td>YSD215二次</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;" colspan="2"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #fee699;"></td>
            <td style="background-color: #fee699;"></td>
            <td></td>
        </tr>
        <!--第十三行-->
        <tr style="height: 20px;">
            <td colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
        </tr>
        <!--第十四行-->
        <tr style="height: 50px;">
            <td>YSD103</td>
            <td style="background-color: #c6e0b4;">粉提1</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td rowspan="2" style="background-color: #5b9bd5">录入PH</td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #fee699;" rowspan="2"></td>
            <td style="background-color: #fee699;" rowspan="2"></td>
            <td></td>
        </tr>
        <!--第十五行-->
        <tr style="height: 70px;">
            <td>YSD102</td>
            <td style="background-color: #c6e0b4">粉提2</td>
            <td style="background-color: #c6e0b4;"></td>
            <td></td>
        </tr>
        <!--第十六行-->
        <tr style="height: 50px;">
            <td>开始升温</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;" colspan="2"></td>
            <td style="background-color: #c6e0b4;">瞬时蒸汽压力：MPa</td>
            <td style="background-color: #c6e0b4;" colspan="2"></td>
            <td style="background-color: #aeaaaa;" colspan="3" rowspan="6"></td>
            <td style="background-color: #fee699;" rowspan="2"></td>
            <td></td>
        </tr>
        <!--第十七行-->
        <tr style="height: 150px;">
            <td>升温至高温度</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #5b9bd5">录入PH</td>
            <td style="background-color: #aeaaaa;"></td>
            <td></td>
        </tr>
        <!--第十八行-->
        <tr style="height: 150px;">
            <td>一次保温10分钟测PH</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #5b9bd5">录入PH</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td></td>
        </tr>
        <!--第十九行-->
        <tr style="height: 150px;">
            <td>降温开始</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;" colspan="2"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td></td>
        </tr>
        <!--第二十行-->
        <tr style="height: 150px;">
            <td>降温停止</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;" colspan="2"></td>
            <td style="background-color: #fee699;">降温时间</td>
            <td></td>
        </tr>
        <!--第二十一行-->
        <tr style="height: 150px;">
            <td>加酸并计时</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #5b9bd5">录入加酸量</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #5b9bd5">录入PH</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td></td>
        </tr>
        <!--第二十二行-->
        <tr style="height: 60px;">
            <td>冰水雾点</td>
            <td style="background-color: #aeaaaa;" colspan="2" rowspan="3"></td>
            <td style="background-color: #fee699;" colspan="2"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa;" colspan="2"></td>
            <td>冰水时间</td>
            <td style="background-color: #5b9bd5"></td>
            <td></td>
        </tr>
        <!--第二十三行-->
        <tr style="height: 60px;">
            <td>20度雾点</td>
            <td style="background-color: #fee699;" colspan="2"></td>
            <td style="background-color: #c6e0b4;">℃</td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td>缩聚总时间</td>
            <td style="background-color: #5b9bd5"></td>
            <td></td>
        </tr>
        <!--第二十四行-->
        <tr style="height: 60px;">
            <td>二次降温</td>
            <td style="background-color: #c6e0b4;">：</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa;" rowspan="3" colspan="3"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td></td>
        </tr>
        <!--第二十五行-->
        <tr style="height: 50px;">
            <td>加碱</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #5b9bd5">录入加碱量</td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa;" colspan="2" rowspan="2"></td>
            <td style="background-color: #5b9bd5">录入PH</td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td></td>
        </tr>
        <!--第二十六行-->
        <tr style="height: 70px;">
            <td>70度终止降温</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #fee699;"></td>
            <td></td>
        </tr>
        <!--第二十七行-->
        <tr style="height: 150px;">
            <td>二次粉料加入</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #5b9bd5"></td>
            <td></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #fee699;"></td>
            <td style="background-color: #fee699;"></td>
            <td></td>
        </tr>
        <!--第二十八行-->
        <tr style="height: 30px;">
            <td>二次保温时间20</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;">：</td>
            <td style="background-color: #aeaaaa;" colspan="2"></td>
            <td style="background-color: #aeaaaa;" rowspan="4"></td>
            <td style="background-color: #aeaaaa;"  ></td>
            <td style="background-color: #aeaaaa;" colspan="3"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td></td>
        </tr>
        <!--第二十九行-->
        <tr style="height: 100px;">
            <td>助剂6加入</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;" rowspan="2"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td rowspan="2" style="background-color: #c6e0b4;"></td>
            <td rowspan="2" style="background-color: #c6e0b4;"></td>
            <td style="background-color: #fee699;" rowspan="2"></td>
            <td style="background-color: #fee699;" rowspan="2"></td>
            <td></td>
        </tr>
        <!--第三十行-->
        <tr style="height: 100px;">
            <td>水加入</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td></td>
        </tr>
        <!--第三十一行-->
        <tr style="height: 100px;">
            <td>冷却至结束</td>
            <td colspan="2"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #fee699;"></td>
            <td></td>
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
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #aeaaaa;" rowspan="2"></td>
            <td>20℃标准：</td>
            <td>250-400</td>
            <td>7.4-8.0</td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td></td>
        </tr>
        <!-- 第三十四-->
        <tr style="height: 50px;">
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td>实测：</td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td style="background-color: #aeaaaa;"></td>
            <td></td>
        </tr>
        <!-- 第三十五-->
        <tr style="height: 100px;">
            <td rowspan="2">排 胶</td>
            <td>日期与时间：</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #fee699;">排胶时间差</td>
            <td>打入胶罐1：</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #fee699;"></td>
            <td></td>
            <td style="background-color: #fee699;" rowspan="2"></td>
            <td></td>
        </tr>
        <!-- 第三十六-->
        <tr style="height: 100px;">
            <td>反应釜打胶前后重量</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #fee699;">反应釜重量差</td>
            <td>打入胶罐2：</td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #c6e0b4;"></td>
            <td style="background-color: #fee699;"></td>
            <td></td>
        </tr>
    </table>
</div>
</body>
</html>
