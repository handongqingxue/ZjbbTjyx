<%--
  Created by IntelliJ IDEA.
  User: lilekang
  Date: 2023/4/23
  Time: 2:51 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Title</title>
<script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
var path='<%=basePath%>';

function getUnCreRepVarUList(batchID){

    $.post(path+"report/getUnCreRepVarList",
        //{batchID:"MA202300000018"},
        {batchID:batchID},
        function(result){
            $("#opcUCTable td[id^='td']").text("");//先清除表格里的数据
            $("#opcUCTable #batchID_hid").val(batchID);//设置表格里的批次id
            var glueU=batchID.substring(1,2);
            $("#glueU").html(glueU);
            var varMapList=result.varMapList;
            for (var i = 0; i < varMapList.length; i++) {
                var varMap=varMapList[i];
                var rowNumber=varMap.rowNumber;
                var colNumber=varMap.colNumber;
                var value=varMap.value;
                $("#opcUCTable #td"+rowNumber+"_"+colNumber).text(value);
            }
        }
    ,"json");
}

function addReportF_UByBatchID(){
    var batchID=$("#opcUCTable #batchID_hid").val();
	// return false;
    $.post(path+"report/addReportFByBatchID",
        {batchID:batchID},
        function(result){
            if(result.message=="ok"){
                alert(result.info);
                getLeftMenuData("uWsc");
            }
        }
    ,"json");
}
</script>
</head>
<body>
    <div class="home_right_div">
        <div class="home_right_head_div">
            <div class="u_create_head_row1_div">
                <span class="cjxx_span">厂家信息</span>
                <input type="text" placeholder="录入厂家信息" class="m_create_head_input"/>
                <span class="czy_span">操作员</span>
                <select class="u_create_head_input">
                    <option>张三</option>
                </select>
                <span class="bbzt_span">报表状态:</span>
                <span class="wsc_span">未生成</span>
            </div>
            <div class="u_create_head_row2_div">
                <span class="gzlcssr1_span">1号罐重量初始输入</span>
                <input type="text" size="5" class="u_create_head_input"/>

                <span class="gzlcssr2_span">2号罐重量初始输入</span>
                <input type="text" size="5" class="u_create_head_input"/>

                <div class="but_div scbb_but_div" onclick="addReportF_UByBatchID()">生成报表</div>
                <div class="but_div sjfw_but_div">数据复位</div>
            </div>
        </div>
        <div class="home_right_cbody_div">
            <table class="u_body_table" border="1px" id="opcUCTable">
                <tr class="tr1">
                    <th colspan="13">
                        <input type="hidden" id="batchID_hid"/>
                        <span class="onetd1">U类（<span id="glueU"></span>）胶 生产记录</span>
                        <%--<span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>--%>
                    </th>
                </tr>
                <!--第二行-->
                <tr class="tr2">
                    <td class="td2_1">YSD101信息</td>
                    <td id="td2_2" class="td2_2 blue"></td>
                    <td class="td2_3">YSD102信息</td>
                    <td id="td2_4" class="td2_4 blue"></td>
                    <td id="td2_5" class="td2_5"></td>
                    <td colspan="2" class="td2_6">当班操作员：</td>
                    <td id="td2_7" colspan="2" class="td2_7 green"></td>
                    <td colspan="2" class="td2_8">接班操作员：</td>
                    <td colspan="2" class="td2_9 green"></td>
                </tr>
                <!--第三行-->
                <tr class="tr3">
                    <td>生产编号</td>
                    <td class="yellow" id="td3_2"></td>
                    <td>反应釜：</td>
                    <td colspan="2" class="green" id="td3_4"></td>
                    <td style="width: 130px">开始时间</td>
                    <td style="width: 130px"     class="green"  id="td3_6"></td>
                    <td style="width: 130px">结束时间</td>
                    <td style="width: 130px" class="green"  id="td3_8"></td>
                    <td style="width: 130px">生产工时</td>
                    <td style="width: 130px" class="yellow"  id="td3_10"></td>
                    <td style="width: 130px">生产日期：</td>
                    <td style="width: 130px" class="green"  id="td3_12"></td>
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
                    <td colspan="5" rowspan="2" id="td4_9"></td>
                </tr>
                <!--第五行-->
                <tr class="tr5">
                    <td>1号罐用前重</td>
                    <td class="yellow" id="td5_2"></td>
                    <td>1号罐用后重</td>
                    <td class="yellow" id="td5_4"></td>
                    <td>2号罐用前重</td>
                    <td class="yellow" id="td5_6"></td>
                    <td>2号罐用后重</td>
                    <td class="yellow" id="td5_8"></td>
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
                    <td colspan="2" class="green" id="td8_2"></td>
                    <td rowspan="2" class="green" id="td8_3"></td>
                    <td rowspan="2" class="green" id="td8_4"></td>
                    <td rowspan="2" class="green" id="td8_5"></td>
                    <td rowspan="2" class="green" id="td8_6"></td>
                    <td rowspan="2" class="grey" id="td8_7"></td>
                    <td rowspan="2" class="green" id="td8_8"></td>
                    <td rowspan="2" class="green" id="td8_9"></td>
                    <td rowspan="2" class="yellow" id="td8_10"></td>
                    <td rowspan="2" class="green" id="td8_11"></td>
                    <td rowspan="2" id="td8_12"></td>
                </tr>
                <!--第九行-->
                <tr class="tr9">
                    <td>纯净水</td>
                    <td colspan="2" class="green" id="td9_2"></td>
                </tr>
                <!--第十行-->
                <tr class="tr10">
                    <td>YSD109</td>
                    <td colspan="2" class="green" id="td10_2"></td>
                    <td class="green" id="td10_3"></td>
                    <td class="green" id="td10_4"></td>
                    <td class="green" id="td10_5"></td>
                    <td class="green" id="td10_6"></td>
                    <td colspan="4" class="grey" id="td10_7"></td>
                    <td class="yellow" id="td10_8"></td>
                    <td></td>
                </tr>
                <!--第十一行-->
                <tr class="tr11">
                    <td>YSD215一次</td>
                    <td class="green" id="td11_2"></td>
                    <td class="green" id="td11_3"></td>
                    <td class="green" id="td11_4"></td>
                    <td class="green" id="td11_5"></td>
                    <td class="green" id="td11_6"></td>
                    <td colspan="2" class="grey" id="td11_7"></td>
                    <td class="green" id="td11_8"></td>
                    <td class="green" id="td11_9"></td>
                    <td class="yellow" id="td11_10"></td>
                    <td class="yellow" id="td11_11"></td>
                    <td id="td11_12"></td>
                </tr>
                <!--第十二行-->
                <tr class="tr12">
                    <td>YSD215二次</td>
                    <td class="green" id="td12_2"></td>
                    <td class="green" id="td12_3"></td>
                    <td class="green" id="td12_4"></td>
                    <td class="green" id="td12_5"></td>
                    <td class="green" id="td12_6"></td>
                    <td colspan="2" class="grey" id="td12_7"></td>
                    <td class="green" id="td12_8"></td>
                    <td class="green" id="td12_9"></td>
                    <td class="yellow" id="td12_10"></td>
                    <td class="yellow" id="td12_11"></td>
                    <td id="td12_12"></td>
                </tr>
                <!--第十三行-->
                <tr class="tr13">
                    <td colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
                </tr>
                <!--第十四行-->
                <tr class="tr14">
                    <td>YSD103</td>
                    <td class="green">粉提1</td>
                    <td class="green" id="td14_3"></td>
                    <td class="green" rowspan="2" id="td14_4"></td>
                    <td class="green" rowspan="2" id="td14_5"></td>
                    <td class="green" rowspan="2" id="td14_6"></td>
                    <td rowspan="2" class="blue" id="td14_7">录入PH</td>
                    <td class="green" rowspan="2" id="td14_8"></td>
                    <td class="green" rowspan="2" id="td14_9"></td>
                    <td class="green" rowspan="2" id="td14_10"></td>
                    <td class="yellow" rowspan="2" id="td14_11"></td>
                    <td class="yellow" rowspan="2" id="td14_12"></td>
                    <td id="td14_13"></td>
                </tr>
                <!--第十五行-->
                <tr class="tr15">
                    <td>YSD102</td>
                    <td class="green">粉提2</td>
                    <td class="green" id="td15_3"></td>
                    <td id="td15_4"></td>
                </tr>
                <!--第十六行-->
                <tr class="tr16">
                    <td>开始升温</td>
                    <td class="green" id="td16_2"></td>
                    <td class="grey" id="td16_3"></td>
                    <td class="green" colspan="2" id="td16_4"></td>
                    <td class="green">瞬时蒸汽压力：MPa</td>
                    <td class="green" colspan="2" id="td16_6"></td>
                    <td colspan="3" rowspan="6" class="grey" id="td16_7"></td>
                    <td class="yellow" rowspan="2" id="td16_8"></td>
                    <td id="td16_9"></td>
                </tr>
                <!--第十七行-->
                <tr class="tr17">
                    <td>升温至高温度</td>
                    <td class="grey" id="td17_2"></td>
                    <td class="grey" id="td17_3"></td>
                    <td class="green" id="td17_4"></td>
                    <td class="grey" id="td17_5"></td>
                    <td class="green" id="td17_6"></td>
                    <td class="blue" id="td17_7">录入PH</td>
                    <td class="grey" id="td17_8"></td>
                    <td id="td17_9"></td>
                </tr>
                <!--第十八行-->
                <tr class="tr18">
                    <td>一次保温10分钟测PH</td>
                    <td class="grey" id="td18_2"></td>
                    <td class="grey" id="td18_3"></td>
                    <td class="green" id="td18_4"></td>
                    <td class="grey" id="td18_5"></td>
                    <td class="green" id="td18_6"></td>
                    <td class="blue" id="td18_7">录入PH</td>
                    <td class="grey" id="td18_8"></td>
                    <td class="green" id="td18_9"></td>
                    <td id="td18_10"></td>
                </tr>
                <!--第十九行-->
                <tr class="tr19">
                    <td>降温开始</td>
                    <td class="grey" id="td19_2"></td>
                    <td class="grey" id="td19_3"></td>
                    <td class="green" id="td19_4"></td>
                    <td class="grey" id="td19_5"></td>
                    <td class="green" id="td19_6"></td>
                    <td class="grey" colspan="2" id="td19_7"></td>
                    <td class="grey" id="td19_8"></td>
                    <td id="td19_9"></td>
                </tr>
                <!--第二十行-->
                <tr class="tr20">
                    <td>降温停止</td>
                    <td class="grey" id="td20_2"></td>
                    <td class="grey" id="td20_3"></td>
                    <td class="green" id="td20_4"></td>
                    <td class="grey" id="td20_5"></td>
                    <td class="green" id="td20_6"></td>
                    <td class="grey" colspan="2" id="td20_7"></td>
                    <td class="yellow" id="td20_8"></td>
                    <td id="td20_9"></td>
                </tr>
                <!--第二十一行-->
                <tr class="tr21">
                    <td>加酸并计时</td>
                    <td class="green" id="td21_2"></td>
                    <td class="blue" id="td21_3"></td>
                    <td class="green" id="td21_4"></td>
                    <td class="green" id="td21_5"></td>
                    <td class="green" id="td21_6"></td>
                    <td class="blue" id="td21_7"></td>
                    <td class="green" id="td21_8"></td>
                    <td class="grey" id="td21_9"></td>
                    <td id="td21_10"></td>
                </tr>
                <!--第二十二行-->
                <tr class="tr22">
                    <td>冰水雾点</td>
                    <td class="grey" colspan="2" rowspan="3" id="td22_2"></td>
                    <td class="yellow" colspan="2" id="td22_3"></td>
                    <td class="grey" id="td22_4"></td>
                    <td class="blue" id="td22_5"></td>
                    <td class="blue" id="td22_6"></td>
                    <td class="grey" colspan="2" id="td22_7"></td>
                    <td>冰水时间</td>
                    <td class="blue" id="td22_9"></td>
                    <td id="td22_10"></td>
                </tr>
                <!--第二十三行-->
                <tr class="tr23">
                    <td>20度雾点</td>
                    <td class="yellow" colspan="2" id="td23_2"></td>
                    <td class="green" id="td23_3">℃</td>
                    <td class="blue" id="td23_4"></td>
                    <td class="blue" id="td23_5"></td>
                    <td class="grey" id="td23_6"></td>
                    <td class="grey" id="td23_7"></td>
                    <td>缩聚总时间</td>
                    <td class="blue" id="td23_9"></td>
                    <td id="td23_10"></td>
                </tr>
                <!--第二十四行-->
                <tr class="tr24">
                    <td>二次降温</td>
                    <td class="green" id="td24_2"></td>
                    <td class="grey" id="td24_3"></td>
                    <td class="grey" id="td24_4"></td>
                    <td class="blue" id="td24_5"></td>
                    <td class="blue" id="td24_6"></td>
                    <td rowspan="3" class="grey" colspan="3" id="td24_7"></td>
                    <td class="grey" id="td24_8"></td>
                    <td id="td24_9"></td>
                </tr>
                <!--第二十五行-->
                <tr class="tr25">
                    <td>加碱</td>
                    <td class="green" id="td25_2"></td>
                    <td class="blue" id="td25_3">录入加碱量</td>
                    <td class="blue" id="td25_4"></td>
                    <td class="grey" colspan="2" rowspan="2" id="td25_5"></td>
                    <td class="blue" id="td25_6">录入PH</td>
                    <td class="blue" id="td25_7"></td>
                    <td class="grey" id="td25_8"></td>
                    <td id="td25_9"></td>
                </tr>
                <!--第二十六行-->
                <tr class="tr26">
                    <td>70度终止降温</td>
                    <td class="light_gray" id="td26_2"></td>
                    <td class="light_gray" id="td26_3"></td>
                    <td class="green" id="td26_4"></td>
                    <td class="grey" id="td26_5"></td>
                    <td class="grey" id="td26_6"></td>
                    <td class="yellow" id="td26_7">从降温到70度时间</td>
                    <td id="td26_8"></td>
                </tr>
                <!--第二十七行-->
                <tr class="tr27">
                    <td>二次粉料加入</td>
                    <td class="green" id="td27_2"></td>
                    <td class="green" id="td27_3"></td>
                    <td class="green" id="td27_4"></td>
                    <td class="green" id="td27_5"></td>
                    <td class="green" id="td27_6"></td>
                    <td class="blue" id="td27_7">录入PH</td>
                    <td class="grey" id="td27_8"></td>
                    <td class="green" id="td27_9"></td>
                    <td class="green" id="td27_10"></td>
                    <td class="yellow" id="td27_11"></td>
                    <td class="yellow" id="td27_12"></td>
                    <td id="td27_13"></td>
                </tr>
                <!--第二十八行-->
                <tr class="tr28">
                    <td>二次保温时间20</td>
                    <td class="grey" id="td28_2"></td>
                    <td class="grey" id="td28_3"></td>
                    <td class="green" id="td28_4">：</td>
                    <td class="grey" id="td28_5" colspan="2"></td>
                    <td class="grey" id="td28_6" rowspan="4"></td>
                    <td class="grey" id="td28_7"></td>
                    <td class="grey" id="td28_8" colspan="3"></td>
                    <td class="grey" id="td28_9"></td>
                    <td id="td28_10"></td>
                </tr>
                <!--第二十九行-->
                <tr class="tr29">
                    <td>助剂6加入</td>
                    <td class="green" id="td29_2"></td>
                    <td class="green" id="td29_3"></td>
                    <td class="green" id="td29_4"></td>
                    <td class="green" id="td29_5"></td>
                    <td class="green" id="td29_6" rowspan="2"></td>
                    <td class="grey" id="td29_7"></td>
                    <td class="green" id="td29_8" rowspan="2"></td>
                    <td class="green" id="td29_9" rowspan="2"></td>
                    <td rowspan="2" id="td29_10" class="yellow"></td>
                    <td rowspan="2" id="td29_11" class="yellow"></td>
                    <td id="td29_12"></td>
                </tr>
                <!--第三十行-->
                <tr class="tr30">
                    <td>水加入</td>
                    <td class="green" id="td30_2"></td>
                    <td class="green" id="td30_3"></td>
                    <td class="green" id="td30_4"></td>
                    <td class="green" id="td30_5"></td>
                    <td class="grey" id="td30_6"></td>
                    <td id="td30_7"></td>
                </tr>
                <!--第三十一行-->
                <tr class="tr31">
                    <td>冷却至结束</td>
                    <td colspan="2" id="td31_2"></td>
                    <td class="grey" id="td31_3"></td>
                    <td class="green" id="td31_4"></td>
                    <td class="green" id="td31_5"></td>
                    <td class="green" id="td31_6"></td>
                    <td class="green" id="td31_7"></td>
                    <td class="green" id="td31_8"></td>
                    <td class="grey" id="td31_9"></td>
                    <td class="yellow" id="td31_10"></td>
                    <td id="td31_11"></td>
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
                    <td class="green" id="td33_2"></td>
                    <td class="grey" rowspan="2" id="td33_3"></td>
                    <td id="td33_4">20℃标准：</td>
                    <td id="td33_5">250-400</td>
                    <td id="td33_6">7.4-8.0</td>
                    <td id="td33_7" class="grey"></td>
                    <td id="td33_8" class="grey"></td>
                    <td id="td33_9" class="grey"></td>
                    <td id="td33_10" class="grey"></td>
                    <td id="td33_11" class="grey"></td>
                    <td id="td33_12"></td>
                </tr>
                <!-- 第三十四-->
                <tr class="tr34">
                    <td class="grey" id="td34_1"></td>
                    <td class="grey" id="td34_2"></td>
                    <td id="td34_3">实测：</td>
                    <td class="blue" id="td34_4"></td>
                    <td class="blue" id="td34_5"></td>
                    <td class="grey" id="td34_6"></td>
                    <td class="grey" id="td34_7"></td>
                    <td class="grey" id="td34_8"></td>
                    <td class="grey" id="td34_9"></td>
                    <td class="grey" id="td34_10"></td>
                    <td id="td34_11"></td>
                </tr>
                <!-- 第三十五-->
                <tr class="tr35">
                    <td rowspan="2">排 胶</td>
                    <td>日期与时间：</td>
                    <td class="green" id="td35_3"></td>
                    <td class="green" id="td35_4"></td>
                    <td class="yellow" id="td35_5">时间差</td>
                    <td id="td35_6"></td>
                    <td class="green" id="td35_7">罐号1</td>
                    <td class="green" id="td35_8"></td>
                    <td class="green" id="td35_9"></td>
                    <td class="yellow" id="td35_10"></td>
                    <td rowspan="2" id="td35_11"></td>
                    <td rowspan="2" id="td35_12" class="yellow"></td>
                    <td id="td35_13"></td>
                </tr>
                <!-- 第三十六-->
                <tr class="tr36">
                    <td>反应釜打胶前后重量</td>
                    <td class="green" id="td36_2"></td>
                    <td class="green" id="td36_3"></td>
                    <td class="yellow" id="td36_4">反应釜重量差</td>
                    <td id="td36_5"></td>
                    <td class="green" id="td36_6">罐号2</td>
                    <td class="green" id="td36_7"></td>
                    <td class="green" id="td36_8"></td>
                    <td class="yellow" id="td36_9"></td>
                    <td id="td36_10"></td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
