<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../inc/js.jsp"%>
    <script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
<title>Title</title>
<script type="text/javascript">

var path='<%=basePath%>';
$(function(){
    getReportFUPageList();
});


function getReportFUPageList() {
    var glueType = $("#glue").text();
    var typeSelect = $("#typeSelect").val()
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    $.post(path+"report/getReportFUPageList",
        {type:glueType,batchID:typeSelect,startTime:startTime,endTime:endTime},
        function(result){
            // console.log(result)
            if(result.status==1){
                var reportFUPageList=result.data;
                initPagerHtml(reportFUPageList);
            }
        }
        ,"json");
}

function prePdfU(){
    var repHtmlStr=$("#reportFUPageList_div").html();
    $.post("savePreReportHtml",
        {repHtmlStr:repHtmlStr},
        function(result){
            if(result.status==1){
                window.open("goPreviewPdf?uuid="+result.data,"newwindow","width=300;");
            }
            else{
                alert(result.msg);
            }
        }
        ,"json");
}

function prePreExcelU(){
    var batchID=$("#opcUSTable #batchID_hid").val();
    window.open("goPreExcelU?batchID="+batchID,"newwindow","width=300;");
}
// function initPagerHtml(reportFUPageList){
//     layui.use(['laypage', 'layer'], function(){
//         var laypage = layui.laypage,layer = layui.layer;
//         //调用分页
//         laypage.render({
//             elem: 'paging'
//             ,count: reportFUPageList.length,
//             limit: 1,
//             prev: '<em><</em>',
//             next: '<em>></em>',
//             theme: '#1E9FFF',
//             layout: ['count', 'prev', 'page', 'next',  'skip'],
//             jump: function(obj){
//                 //模拟渲染
//                 document.getElementById('reportFUPageList_div').innerHTML = function(){
//                     var arr = []
//                         ,thisData = reportFUPageList.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
//                     layui.each(thisData, function(index, item){
//                         var repFUList=item;
//                         var noVarRepDiv=$("#noVarRepU_div");
//                         var noVarRepTab=noVarRepDiv.find("table");//获取未显示变量的报表模版
//                         if (repFUList.length>0){
//                             noVarRepTab.find("#batchID_hid").val(repFUList[0].batchID);
//                             for (var i = 0; i < repFUList.length; i++) {
//                                 var repFU=repFUList[i];
//                                 var rowNumber=repFU.rowNumber;
//                                 var colNumber=repFU.colNumber;
//                                 var value=repFU.value;
//                                 //console.log(repFM)
//                                 noVarRepTab.find("#td"+rowNumber+"_"+colNumber).text(value);//暂时把变量添加到未显示变量的报表模版里
//                             }
//                         }
//                         arr.push(noVarRepDiv.html());
//                         noVarRepTab.find("#batchID_hid").val("");
//                         noVarRepTab.find("td[id^='td']").text("");//模版和变量一起添加到正式报表后，清空未显示变量的报表模版里的变量值
//                     });
//                     // console.log(arr)
//                     return arr.join('');
//                 }();
//             }
//         });
//     });
// }
</script>
</head>
<body>
<div class="home_right_div">
    <div class="home_right_head_div">
        <table class="u_query_head_table" id="u_query_head_table">
            <tr>
                <td style="font-size: 17px">设置检索条件:</td>
                <td></td>
            </tr>
            <tr>
                <td>
                    起始时间&nbsp;&nbsp;
                    <input placeholder="请选择时间" id="startTime" class="Wdate u_query_head_input" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"/>
                </td>
                <td>
                    选择批次&nbsp;&nbsp;
                    <select class="u_query_head_input" id="typeSelect"></select>
                </td>
                <td>
                    <button class="printing_button" onclick="prePreExcelU()">导出Excel</button>
                    <button class="printing_button" onclick="prePdfU()">导出PDF</button>
                </td>
            </tr>
            <tr>
                <td>
                    结束时间&nbsp;&nbsp;
                    <input placeholder="请选择时间" id="endTime" class="Wdate u_query_head_input" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"/>
                </td>
                <td>
                    当前胶种:&nbsp;&nbsp;&nbsp;<span id="glue" class="glue"></span>
                </td>
                <td>
                    <button class="u_query_head_button" onclick="getReportFUPageList()">
                        <i class="layui-icon layui-icon-search" style="font-size: 16px; color: #ffffff;"></i>
                        查询
                    </button>
                    <button class="printing_button">
                        <i class="layui-icon layui-icon-print" style="font-size: 16px; color: #ffffff;"></i>
                        打印
                    </button>
                </td>
            </tr>
        </table>
    </div>
    <div class="home_right_sbody_div" id="reportFUPageList_div">

    </div>
    <div class="home_right_bottom_div">
        <div id="paging" class="home_right_bottom_paging"></div>
    </div>
</div>

<%--&lt;%&ndash;未显示变量的报表模版&ndash;%&gt;--%>
<div id="noVarRepU_div" style="display: block;">
    <table class="u_body_table" border="1px" id="opcUSTable">
        <tr class="tr1">
            <th colspan="13">
                <input type="hidden" id="batchID_hid" value="1"/>
                <span class="onetd1">U类（）胶生产记录</span>
<%--                <span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>--%>
            </th>
        </tr>
        <!--第二行-->
        <tr class="tr2">
            <td class="td2_1">YSD101信息</td>
            <td class="td2_2" class="blue"></td>
            <td class="td2_3">YSD102信息</td>
            <td class="td2_4" class="blue"></td>
            <td class="td2_5"></td>
            <td class="td2_6" colspan="2">当班操作员：</td>
            <td class="td2_7" colspan="2" class="green"></td>
            <td class="td2_8" colspan="2">接班操作员：</td>
            <td class="td2_9" colspan="2" class="green"></td>
        </tr>
        <!--第三行-->
        <tr class="tr3">
            <td>生产编号</td>
            <td class="yellow"></td>
            <td>反应釜：</td>
            <td colspan="2" class="green"></td>
            <td>开始时间</td>
            <td class="green"></td>
            <td>结束时间</td>
            <td class="green"></td>
            <td>生产工时</td>
            <td class="yellow"></td>
            <td>生产日期</td>
            <td class="green"></td>
        </tr>
        <!--第四行-->
        <tr class="tr4">
            <td>在用F料罐</td>
            <td class="blue">□ 1罐</td>
            <td class="blue">浓度：</td>
            <td class="blue"></td>
            <td class="blue">□ 2罐</td>
            <td class="blue">浓度：</td>
            <td class="blue"></td>
            <td></td>
            <td colspan="5" rowspan="2"></td>
        </tr>
        <!--第五行-->
        <tr class="tr5">
            <td>1号罐用前重</td>
            <td class="yellow"></td>
            <td>1号罐用后重</td>
            <td class="yellow"></td>
            <td>2号罐用前重</td>
            <td class="yellow"></td>
            <td>2号罐用后重</td>
            <td class="yellow"></td>
        </tr>
        <!--第六行-->
        <tr class="tr6">
            <td rowspan="2">原辅料kg</br>kg</td>
            <td rowspan="2" colspan="2">实际重量</br>kg</td>
            <td colspan="2">操作时间</td>
            <td rowspan="2">反应釜1温度温 度℃</td>
            <td rowspan="2"> PH(25℃)</td>
            <td rowspan="2">浑浊度检测T.T</td>
            <td colspan="2">计量罐或反应釜重量 kg</td>
            <td rowspan="2">重量差 kg</td>
            <td rowspan="2">操作时间</br>min</td>
            <td rowspan="2" class="remarks">备注</td>
        </tr>
        <!--第七行-->
        <tr class="tr7">
            <td>动作开始</td>
            <td>动作结束</td>
            <td>加料前kg</td>
            <td>加料后kg</td>
        </tr>
        <!--第八行-->
        <tr class="tr8">
            <td>YSD101</td>
            <td colspan="2" class="green"></td>
            <td rowspan="2" class="green"></td>
            <td rowspan="2" class="green"></td>
            <td rowspan="2" class="green"></td>
            <td rowspan="2" class="green"></td>
            <td rowspan="2" class="grey"></td>
            <td rowspan="2" class="green"></td>
            <td rowspan="2" class="green"></td>
            <td rowspan="2" class="yellow"></td>
            <td rowspan="2" class="green"></td>
            <td rowspan="2"></td>
        </tr>
        <!--第九行-->
        <tr class="tr9">
            <td>纯净水</td>
            <td colspan="2" class="green"></td>
        </tr>
        <!--第十行-->
        <tr class="tr10">
            <td>YSD109</td>
            <td colspan="2" class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td colspan="4" class="grey"></td>
            <td class="yellow"></td>
            <td></td>
        </tr>
        <!--第十一行-->
        <tr class="tr11">
            <td>YSD215一次</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td colspan="2" class="grey"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="yellow"></td>
            <td class="yellow"></td>
            <td></td>
        </tr>
        <!--第十二行-->
        <tr class="tr12">
            <td>YSD215二次</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td colspan="2" class="grey"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="yellow"></td>
            <td class="yellow"></td>
            <td></td>
        </tr>
        <!--第十三行-->
        <tr class="tr13">
            <td colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
        </tr>
        <!--第十四行-->
        <tr class="tr14">
            <td>YSD103</td>
            <td class="green">粉提1</td>
            <td class="green"></td>
            <td class="green" rowspan="2"></td>
            <td class="green" rowspan="2"></td>
            <td class="green" rowspan="2"></td>
            <td rowspan="2" class="blue">录入PH</td>
            <td class="green" rowspan="2"></td>
            <td class="green" rowspan="2"></td>
            <td class="green" rowspan="2"></td>
            <td class="yellow" rowspan="2"></td>
            <td class="yellow" rowspan="2"></td>
            <td></td>
        </tr>
        <!--第十五行-->
        <tr class="tr15">
            <td>YSD102</td>
            <td class="green">粉提2</td>
            <td class="green"></td>
            <td></td>
        </tr>
        <!--第十六行-->
        <tr class="tr16">
            <td>开始升温</td>
            <td class="green"></td>
            <td class="grey"></td>
            <td class="green" colspan="2"></td>
            <td class="green">瞬时蒸汽压力：MPa</td>
            <td class="green" colspan="2"></td>
            <td colspan="3" rowspan="6" class="grey"></td>
            <td class="yellow" rowspan="2"></td>
            <td></td>
        </tr>
        <!--第十七行-->
        <tr class="tr17">
            <td>升温至高温度</td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="blue">录入PH</td>
            <td class="grey"></td>
            <td></td>
        </tr>
        <!--第十八行-->
        <tr class="tr18">
            <td>一次保温10分钟测PH</td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="blue">录入PH</td>
            <td class="grey"></td>
            <td class="green"></td>
            <td></td>
        </tr>
        <!--第十九行-->
        <tr class="tr19">
            <td>降温开始</td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="grey" colspan="2"></td>
            <td class="grey"></td>
            <td></td>
        </tr>
        <!--第二十行-->
        <tr class="tr20">
            <td>降温停止</td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="grey" colspan="2"></td>
            <td class="yellow">降温时间</td>
            <td></td>
        </tr>
        <!--第二十一行-->
        <tr class="tr21">
            <td>加酸并计时</td>
            <td class="green"></td>
            <td class="blue">录入加酸量</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="blue">录入PH</td>
            <td class="green"></td>
            <td class="grey"></td>
            <td></td>
        </tr>
        <!--第二十二行-->
        <tr class="tr22">
            <td>冰水雾点</td>
            <td class="grey" colspan="2" rowspan="3"></td>
            <td class="yellow" colspan="2"></td>
            <td class="grey"></td>
            <td class="blue"></td>
            <td class="blue"></td>
            <td class="grey" colspan="2"></td>
            <td>冰水时间</td>
            <td class="green"></td>
            <td></td>
        </tr>
        <!--第二十三行-->
        <tr class="tr23">
            <td>20度雾点</td>
            <td class="yellow" colspan="2"></td>
            <td class="green">℃</td>
            <td class="blue"></td>
            <td class="blue"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td>缩聚总时间</td>
            <td class="green"></td>
            <td></td>
        </tr>
        <!--第二十四行-->
        <tr class="tr24">
            <td>降温</td>
            <td class="green"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="blue"></td>
            <td class="blue"></td>
            <td rowspan="3" colspan="3"></td>
            <td class="grey"></td>
            <td></td>
        </tr>
        <!--第二十五行-->
        <tr class="tr25">
            <td>加碱</td>
            <td class="green"></td>
            <td class="blue">录入加碱量</td>
            <td class="blue"></td>
            <td class="grey" colspan="2" rowspan="2"></td>
            <td class="blue">录入PH</td>
            <td class="blue"></td>
            <td class="grey"></td>
            <td></td>
        </tr>
        <!--第二十六行-->
        <tr class="tr26">
            <td>70度终止降温</td>
            <td class="light_gray"></td>
            <td class="light_gray"></td>
            <td class="green"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="yellow" rowspan="2">从降温到70度时间</td>
            <td></td>
        </tr>
        <!--第二十七行-->
        <tr class="tr27">
            <td>二次粉料加入</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="blue">录入PH</td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="yellow"></td>
            <td></td>
        </tr>
        <!--第二十八行-->
        <tr class="tr28">
            <td>二次保温时间20</td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="green">：</td>
            <td class="grey" colspan="2"></td>
            <td class="grey" rowspan="4"></td>
            <td class="grey"></td>
            <td class="grey" colspan="3"></td>
            <td class="grey"></td>
            <td></td>
        </tr>
        <!--第二十九行-->
        <tr class="tr29">
            <td>助剂6加入</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green" rowspan="2"></td>
            <td class="grey"></td>
            <td class="green" rowspan="2"></td>
            <td class="green" rowspan="2"></td>
            <td rowspan="2" class="yellow"></td>
            <td rowspan="2" class="yellow"></td>
            <td></td>
        </tr>
        <!--第三十行-->
        <tr class="tr30">
            <td>水加入</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="grey"></td>
            <td></td>
        </tr>
        <!--第三十一行-->
        <tr class="tr31">
            <td>冷却至结束</td>
            <td colspan="2"></td>
            <td class="grey"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="grey"></td>
            <td class="yellow"></td>
            <td></td>
        </tr>
        <!--第三十二行-->
        <tr class="tr32">
            <td rowspan="3">质量终检</td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="grey"></td>
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
        <tr class="tr33">
            <td>生产总重</td>
            <td class="green"></td>
            <td class="grey" rowspan="2"></td>
            <td>20℃标准：</td>
            <td>250-400</td>
            <td>7.4-8.0</td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td></td>
        </tr>
        <!-- 第三十四-->
        <tr class="tr34">
            <td class="grey"></td>
            <td class="grey"></td>
            <td>实测：</td>
            <td class="blue"></td>
            <td class="blue"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td class="grey"></td>
            <td></td>
        </tr>
        <!-- 第三十五-->
        <tr class="tr35">
            <td rowspan="2">排 胶</td>
            <td>日期与时间：</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="yellow">时间差</td>
            <td></td>
            <td class="green">罐号1</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="yellow"></td>
            <td rowspan="2"></td>
            <td rowspan="2" class="yellow"></td>
            <td></td>
        </tr>
        <!-- 第三十六-->
        <tr>
            <td>反应釜打胶前后重量</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="yellow">反应釜重量差</td>
            <td></td>
            <td class="green">罐号2</td>
            <td class="green"></td>
            <td class="green"></td>
            <td class="yellow"></td>
            <td></td>
        </tr>
    </table>
</div>
</body>
</html>
