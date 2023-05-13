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
            getPreExcel();
        })

        function getPreExcel(){
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
    <table border="1px" style="width:100%;border-collapse:collapse;text-align:center;font: normal 400 16px '微软雅黑';">
        <tr>
            <td colspan="13" style="height: 50px;">
                <input type="hidden" id="batchID_hid"/>
                <span id="pdf-title" style="font: normal 600 24px '微软雅黑'">M类 （ ）胶 生产记录</span>
                <%-- <span>自动表单设计：张发 设计号：ZJZD20211225</span>--%>
            </td>
        </tr>
        <%--第二行--%>
        <tr style="height: 30px;">
            <td style="width:5%">YSD101信息</td>
            <td style="background-color: #5b9bd5;width: 5%">
                <%--甲醛厂家信息，可后期录入--%>
            </td>
            <td style="width: 5%">YSD102信息</td>
            <td style="background-color: #5b9bd5;width: 7%">
                <%--三安厂家信息可后期录入--%>
            </td>
            <td style="width: 7%;"></td>
            <td colspan="2" style="width: 13%">当班操作员：</td>
            <td colspan="2" style="background-color: #c6e0b4;width: 13%">
                <%--直接摘抄登录名--%>
            </td>
            <td colspan="2" style="width: 13%">接班操作员：</td>
            <td colspan="2" style="background-color: #c6e0b4;width: 13%">
                <%--直接摘抄登录名--%>
            </td>
        </tr>
        <%--第三行--%>
        <tr style="height: 70px">
            <td>生产编号</td>
            <td id="td3_2" style="background-color: #fee699">
                <%--每生产1釜加1--%>
            </td>
            <td>反应釜：</td>
            <td id="td3_4" colspan="2" style="background-color: #c6e0b4">
                <%--反应釜号--%>
            </td>
            <td>开始时间</td>
            <td id="td3_6" style="background-color: #c6e0b4">
                <%--备料开始时间--%>
            </td>
            <td>结束时间</td>
            <td id="td3_8" style="background-color: #c6e0b4">
                <%--冷却结束时间--%>
            </td>
            <td>生产工时</td>
            <td id="td3_10" style="background-color: #fee699">
                <%--min--%>
            </td>
            <td>生产日期： </td>
            <td style="background-color: #c6e0b4">
            </td>
        </tr>
        <%--第四行--%>
        <tr style="height: 30px;">
            <td>在用F料罐</td>
            <td style="background-color: #5b9bd5">  □ 1罐</td>
            <td style="background-color: #5b9bd5">浓度：</td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5">  □ 2罐</td>
            <td style="background-color: #5b9bd5">浓度：</td>
            <td style="background-color: #5b9bd5"></td>
            <td></td>
            <td colspan="5" rowspan="2">备注</td>
        </tr>
        <%--第五行--%>
        <tr style="height: 150px">
            <td>1号罐用前重</td>
            <td style="background-color: #fee699"></td>
            <td>1号罐用后重</td>
            <td style="background-color: #fee699"></td>
            <td>2号罐用前重</td>
            <td style="background-color: #fee699"></td>
            <td>2号罐用后重</td>
            <td style="background-color: #fee699"></td>
        </tr>
        <%--第六行--%>
        <tr style="height: 40px">
            <td rowspan="2">原 辅 料kg</td>
            <td rowspan="2" colspan="2">实际重量kg</td>
            <td colspan="2">操作时间</td>
            <td rowspan="2">温 度℃</td>
            <td rowspan="2"> PH(25℃)</td>
            <td rowspan="2">浑浊度检测T.T</td>
            <td colspan="2">计量罐或反应釜重量 kg</td>
            <td rowspan="2">重量差 kg</td>
            <td rowspan="2">操作时间min</td>
            <td style="width: 200px">备  注</td>
        </tr>
        <tr style="height: 40px">
            <td>动作开始</td>
            <td>动作结束</td>
            <td>加料前kg</td>
            <td>加料后kg</td>
            <td></td>
        </tr>
        <%--第七行--%>
        <tr style="height: 40px">
            <td>YSD101</td>
            <%--甲醛实际进料重量--%>
            <td style="background-color: #c6e0b4" id="td7_2" colspan="2"></td>
            <%--甲醛备料开始时间--%>
            <td style="background-color: #c6e0b4" id="td7_3" rowspan="2"></td>
            <%--甲醛放料完成时间--%>
            <td style="background-color: #c6e0b4" id="td7_4" rowspan="2"></td>
            <%--甲醛放料完成反应釜温度--%>
            <td style="background-color: #c6e0b4" id="td7_5" rowspan="2"></td>
            <%--加碱前PH输入值--%>
            <td style="background-color: #c6e0b4" id="td7_6"  rowspan="2"></td>
            <td style="background-color: #aeaaaa" rowspan="2"></td>
            <%--甲醛备料开始釜称重--%>
            <td style="background-color: #c6e0b4" id="td7_8" rowspan="2"></td>
            <%--甲醛放料完成釜称重--%>
            <td style="background-color: #c6e0b4" id="td7_9" rowspan="2"></td>
            <%--甲醛备料开始到放料完成重量差 --%>
            <td style="background-color: #fee699" id="td7_10" rowspan="2"></td>
            <%--甲醛备料开始到放料完成时间差 --%>
            <td style="background-color: #fee699" id="td7_11" rowspan="2"></td>
            <td></td>
        </tr>
        <%--第八行--%>
        <tr style="height: 40px">
            <td>纯净水</td>
            <%-- 加水实际重量 --%>
            <td style="background-color: #c6e0b4" id="td8_2" colspan="2"></td>
            <td></td>
        </tr>
        <%--第九行--%>
        <tr style="height: 40px">
            <td>YSD109</td>
            <%-- 加碱量提示 --%>
            <td style="background-color: #c6e0b4" id="td9_2" colspan="2"></td>
            <td style="background-color: #c6e0b4" colspan="3"></td>
            <%-- 加碱后PH输入值 --%>
            <td style="background-color: #c6e0b4" id="td9_4"></td>
            <td style="background-color: #aeaaaa" colspan="4"></td>
            <td style="background-color: #fee699"></td>
            <td></td>
        </tr>
        <%--第十行--%>
        <tr style="height: 60px">
            <td>YSD106</td>
            <%-- 助剂计量罐1-2称重 --%>
            <td style="background-color: #c6e0b4" id="td10_2" colspan="2"></td>
            <%-- 允许一次加助剂时间 --%>
            <td style="background-color: #c6e0b4" id="td10_3"></td>
            <%-- 所有助剂加料完成1时间 --%>
            <td style="background-color: #c6e0b4" id="td10_4"></td>
            <%-- 所有助剂加料完成1反应釜温度 --%>
            <td  style="background-color: #c6e0b4" id="td10_5"></td>
            <td style="background-color: #aeaaaa" colspan="2"></td>
            <%-- 允许一次加助剂釜称重 --%>
            <td style="background-color: #c6e0b4" id="td10_7"></td>
            <!-- 所有助剂加料完成1釜称重 -->
            <td style="background-color: #c6e0b4" id="td10_8"></td>
            <!-- 加料前后重量差 -->
            <td style="background-color: #fee699" id="td10_9"></td>
            <!-- 允许一次加助剂到助剂2加料完成时间 -->
            <td style="background-color: #fee699" id="td10_10"></td>
            <td></td>
        </tr>
        <%--第十一行--%>
        <tr style="height: 20px;">
            <td colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
        </tr>
        <%--第十二行--%>
        <tr style="height: 60px;">
            <td>YSD102</td>
            <td style="background-color: #c6e0b4" id="td12_2" colspan="2"></td>
            <td style="background-color: #c6e0b4" id="td12_3"></td>
            <td style="background-color: #c6e0b4" id="td12_4"></td>
            <td style="background-color: #c6e0b4" id="td12_5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #c6e0b4" id="td12_7"></td>
            <td style="background-color: #c6e0b4" id="td12_8"></td>
            <td style="background-color: #fee699" id="td12_9"></td>
            <td style="background-color: #fee699" id="td12_10"></td>
            <td></td>
        </tr>
        <%--第十三行--%>
        <tr style="height: 40px">
            <td>开始升温</td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #c6e0b4" id="td13_4" colspan="2"></td>
            <td style="background-color: #c6e0b4" id="td13_5" colspan="3"></td>
            <td style="background-color: #aeaaaa" colspan="3" rowspan="2"></td>
            <td style="background-color: #fee699" id="td14_9" rowspan="2"></td>
            <td></td>
        </tr>
        <%--第十四行--%>
        <tr style="height: 100px">
            <td>PH检测（中温温度）</td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #c6e0b4" id="td14_4" colspan="2"></td>
            <td style="background-color: #c6e0b4" id="td14_5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa" id="td14_7"></td>
            <td></td>
        </tr>
        <%--第十五行--%>
        <tr style="height: 60px">
            <td>YSD104</td>
            <td style="background-color: #c6e0b4" colspan="2"></td>
            <td style="background-color: #c6e0b4" id="td15_3"></td>
            <td style="background-color: #c6e0b4" id="td15_4"></td>
            <td style="background-color: #c6e0b4" id="td15_5"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #c6e0b4" id="td15_8"></td>
            <td style="background-color: #c6e0b4" id="td15_9"></td>
            <td style="background-color: #fee699" id="td15_10"></td>
            <td style="background-color: #fee699" id="td15_11"></td>
            <td></td>
        </tr>
        <%--第十六行--%>
        <tr style="height: 60px">
            <td>停汽</td>
            <td style="background-color: #aeaaaa" colspan="2"></td>
            <td style="background-color: #c6e0b4" id="td16_3" colspan="2"></td>
            <td style="background-color: #c6e0b4" id="td16_4"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #c6e0b4" id="td16_6"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #fee699" id="td16_10"></td>
            <td></td>
        </tr>
        <%--第十七行--%>
        <tr style="height: 60px">
            <td rowspan="3">保温</td>
            <td style="background-color: #aeaaaa">冰水检测</td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #5b9bd5" id="td17_8"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td></td>
        </tr>
        <%--第十八行--%>
        <tr style="height: 60px">
            <td style="background-color: #aeaaaa">20度检测</td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #5b9bd5" id="td18_8"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td></td>
        </tr>
        <%--第十九行--%>
        <tr style="height: 60px">
            <td>降温水数</td>
            <td style="background-color: #fee699"></td>
            <td style="background-color: #fee699"></td>
            <td style="background-color: #c6e0b4">：</td>
            <td style="background-color: #c6e0b4">聚合终点温度</td>
            <td style="background-color: #5b9bd5" id="td19_7"></td>
            <td style="background-color: #5b9bd5" id="td19_8"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td></td>
        </tr>
        <%--第二十行--%>
        <tr style="height: 100px">
            <td>冷却</td>
            <td colspan="2">夏季（35）℃，</br>冬季（43）℃</td>
            <td style="background-color: #5b9bd5" id="td20_3"></td>
            <td style="background-color: #c6e0b4" id="td20_4"></td>
            <td style="background-color: #c6e0b4">降温完成时温度</td>
            <td style="background-color: #aeaaaa" id="td20_6"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #fee699" id="td20_11"></td>
            <td></td>
        </tr>
        <%--第二十一行--%>
        <tr style="height: 20px">
            <td rowspan="3">质量终检</td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td>水数W.T</td>
            <td>PH</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <%--第二十二行--%>
        <tr style="height: 100px">
            <td>生产总重</td>
            <td style="background-color: #c6e0b4"></td>
            <td rowspan="2"></td>
            <td>20℃标准：</td>
            <td>配方终检水数上下限</td>
            <td>配方终检PH上下限</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <%--第二十三行--%>
        <tr style="height: 50px">
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td>实测：</td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #5b9bd5"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td style="background-color: #aeaaaa"></td>
            <td></td>
        </tr>
        <%--第二十四行--%>
        <tr style="height: 80px;">
            <td rowspan="2">排 胶</td>
            <td>日期与时间：</td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #fee699"></td>
            <td>打入胶罐1：</td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #c6e0b4"> </td>
            <td style="background-color: #fee699"></td>
            <td rowspan="2"></td>
            <td style="background-color: #fee699" rowspan="2"></td>
            <td></td>
        </tr>
        <%--第二十五行--%>
        <tr style="height: 100px">
            <td>反应釜打胶前后重量</td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #c6e0b4"> </td>
            <td style="background-color: #fee699"></td>
            <td>打入胶罐2：</td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #c6e0b4"></td>
            <td style="background-color: #c6e0b4"></td>
            <tdc style="background-color: #fee699"></tdc>
            <td></td>
        </tr>
    </table>
</div>
</body>
</html>
