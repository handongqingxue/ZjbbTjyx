<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
<%--    <link rel="stylesheet" href="<%=basePath%>resource/css/layui.css">--%>
<%--    <script type="text/javascript" src="<%=basePath%>resource/js/layui.js"></script>--%>
<title>Title</title>
    <style type="text/css">
        .m_body_table .tr1{
            height: 50px;
        }
        .m_body_table .tr2,
        .m_body_table .tr4{
            height: 30px;
        }
        .m_body_table .td2_1,
        .m_body_table .td2_2,
        .m_body_table .td2_3{
            width: 7%;
        }
        .m_body_table .td2_4{
            width: 9%;
        }
        .m_body_table .td2_5{
            width: 9%;
        }
        .m_body_table .td2_6,
        .m_body_table .td2_8{
            width: 15%;
        }
        .m_body_table .td2_7,
        .m_body_table .td2_9{
            width: 15%;
        }

        .m_body_table .tr3{
            height: 70px;
        }

        .m_body_table .tr5{
            height: 150px;
        }
        .m_body_table .tr6{
            height: 40px;
        }
        .m_body_table .tr6_1{
            height: 40px;
        }
        .m_body_table .tr7,
        .m_body_table .tr8,
        .m_body_table .tr9{
            height: 40px;
        }
        .m_body_table .tr10,
        .m_body_table .tr12{
            height: 60px;
        }
        .m_body_table .tr13{
            height: 40px;
        }
        .m_body_table .tr14{
            height: 100px;
        }
        .m_body_table .tr15{
            height: 60px;
        }
        .m_body_table .tr16{
            height: 60px;
        }
        .m_body_table .tr17{
            height: 60px;
        }
        .m_body_table .tr18{
            height: 60px;
        }
        .m_body_table .tr19{
            height: 60px;
        }
        .m_body_table .tr20{
            height: 100px;
        }
        .m_body_table .tr21{
            height: 20px;
        }
        .m_body_table .tr22{
            height: 100px;
        }
        .m_body_table .tr23{
            height: 50px;
        }
        .m_body_table .tr24{
            height: 80px;
        }
        .m_body_table .tr25{
            height: 100px;
        }
    </style>
<script>
$(function(){
	initPagerDiv();
});

function initPagerDiv(){
	
}

    function getReportFMPageList() {
        var glueType = $("#glue").text();
        var typeSelect = $("#typeSelect").val()
        $.post("<%=basePath%>report/getReportFMPageList",
            {
                type:glueType,
                batchID: typeSelect,
            },
            function(result){
                if(result.msg=="ok"){
                    var list=result.data;
                    alert(list.length);
                    var reportFMListlist=list[0];
                    for (var i = 0; i < reportFMListlist.length; i++) {
                        var varMap=reportFMListlist[i];
                        var rowNumber=varMap.rowNumber;
                        var colNumber=varMap.colNumber;
                        var value=varMap.value;
                        console.log(rowNumber+","+colNumber+","+value);
                        $("#opcMSTable #td"+rowNumber+"_"+colNumber).text(value);
                    }
                }
            }
            ,"json");
    }
    // laypage.render({
    //     elem: 'paging'
    //     ,count: 100
    //     ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
    //     ,jump: function(obj){
    //         console.log(obj)
    //     }
    // });
</script>
</head>
<body>
<div class="home_right_div">
    <div class="home_right_head_div">
        <table class="m_query_head_table">
            <tr>
                <td style="font-size: 17px">设置检索条件:</td>
                <td></td>
            </tr>
            <tr>
                <td>
                    起始时间&nbsp;&nbsp;
                    <input type="date" placeholder="请选择时间" class="m_query_head_input">
                </td>
                <td>
                    选择批次&nbsp;&nbsp;
                    <select class="m_query_head_input" id="typeSelect"></select>
                </td>
            </tr>
            <tr>
                <td>
                    结束时间&nbsp;&nbsp;
                    <input type="date" placeholder="请选择时间" class="m_query_head_input">
                </td>
                <td>
                    当前胶种&nbsp;&nbsp;<span id="glue">MA</span>
                </td>
                <td>
                    <button class="m_query_head_button" onclick="getReportFMPageList()">
                        <i class="layui-icon layui-icon-search" style="font-size: 16px; color: #ffffff;"></i>
                        查询
                    </button>
                </td>
            </tr>
        </table>
    </div>
    <div class="home_right_body_div">
        <table class="m_body_table" border="1px" id="opcMSTable">
            <tr class="tr1">
                <td colspan="13">
                    <span class="onetd1">M类 （ ）胶 生产记录</span>
                    <%--                    <span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>--%>
                </td>
            </tr>
            <%--第二行--%>
            <tr class="tr2">
                <td class="td2_1">YSD101信息</td>
                <td class="td2_2 blue">
                    <%--甲醛厂家信息，可后期录入--%>
                    <%--<input type="text" id="ysd101_input" placeholder="甲醛厂家信息"/>--%>
                </td>
                <td class="td2_3">YSD102信息</td>
                <td class="td2_4 blue">
                    <%--三安厂家信息可后期录入--%>
                    <%--                    <input type="text" id="ysd102_input" placeholder="三安厂家信息">--%>
                </td>
                <td class="td2_5"></td>
                <td class="td2_6" colspan="2">当班操作员：</td>
                <td class="td2_7 green" colspan="2">
                    <%--直接摘抄登录名--%>
                    <%--                    <input type="text" id="dbczyBsh_input" placeholder="登录名">--%>
                </td>
                <td class="td2_8" colspan="2">接班操作员：</td>
                <td class="td2_9 green" colspan="2">
                    <%--直接摘抄登录名--%>
                    <%--                    <input type="text" id="jbczyBsh_input" placeholder="登录名">--%>
                </td>
            </tr>
            <%--第三行--%>
            <tr class="tr3">
                <td>生产编号</td>
                <td class="yellow" id="td3_2">
                    <%--每生产1釜加1--%>
                    <%--                    <input type="text" id="scbh_input" placeholder="生产编号">--%>
                </td>
                <td>反应釜：</td>
                <td class="green" id="td3_4" colspan="2">
                    <%--反应釜号--%>
                    <%--                    <input type="text" id="fyfh_input" placeholder="反应釜号">--%>
                </td>
                <td>开始时间</td>
                <td class="green" id="td3_6">
                    <%--备料开始时间--%>
                    <%--                    <input type="datetime-local" id="kssj_input">--%>
                </td>
                <td>结束时间</td>
                <td class="green" id="td3_8">
                    <%--冷却结束时间--%>
                    <%--                    <input type="datetime-local" id="jssj_input">--%>
                </td>
                <td>生产工时</td>
                <td class="yellow" id="td3_10">
                    <%--min--%>
                    <%--                    <input type="text" id="scgs_input" placeholder="min/分">--%>
                </td>
                <td>生产日期： </td>
                <td class="green">
                    <%--                    <input type="date" id="scrq_input">--%>
                </td>
            </tr>
            <%--第四行--%>
            <tr class="tr4">
                <td>在用F料罐</td>
                <td class="blue">  □ 1罐</td>
                <td class="blue">浓度：</td>
                <td class="blue"></td>
                <td class="blue">  □ 2罐</td>
                <td class="blue">浓度：</td>
                <td class="blue"></td>
                <td></td>
                <td colspan="5" rowspan="2">备注</td>
            </tr>
            <%--第五行--%>
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
            <%--第六行--%>
            <tr class="tr6">
                <td rowspan="2">原 辅 料kg</td>
                <td rowspan="2" colspan="2">实际重量kg</td>
                <td colspan="2">操作时间</td>
                <td rowspan="2">温 度℃</td>
                <td rowspan="2"> PH(25℃)</td>
                <td rowspan="2">浑浊度检测T.T</td>
                <td colspan="2">计量罐或反应釜重量 kg</td>
                <td rowspan="2">重量差 kg</td>
                <td rowspan="2">操作时间min</td>
                <td class="remarks">备  注</td>
            </tr>
            <tr class="tr6_1">
                <td>动作开始</td>
                <td>动作结束</td>
                <td>加料前kg</td>
                <td>加料后kg</td>
                <td></td>
            </tr>
            <%--第七行--%>
            <tr class="tr7">
                <td>YSD101</td>
                <%--甲醛实际进料重量--%>
                <td class="green" id="td7_2" colspan="2"></td>
                <%--甲醛备料开始时间--%>
                <td class="green" id="td7_3" rowspan="2"></td>
                <%--甲醛放料完成时间--%>
                <td class="green" id="td7_4" rowspan="2"></td>
                <%--甲醛放料完成反应釜温度--%>
                <td class="green" id="td7_5" rowspan="2"></td>
                <%--加碱前PH输入值--%>
                <td class="blue" id="td7_6" rowspan="2"></td>
                <td class="grey" rowspan="2"></td>
                <%--甲醛备料开始釜称重--%>
                <td class="green" id="td7_8" rowspan="2"></td>
                <%--甲醛放料完成釜称重--%>
                <td class="green" id="td7_9" rowspan="2"></td>
                <%--甲醛备料开始到放料完成重量差 --%>
                <td class="yellow" id="td7_10" rowspan="2"></td>
                <!--甲醛备料开始到放料完成时间差 -->
                <td class="yellow" id="td7_11" rowspan="2"></td>
                <td></td>
            </tr>
            <%--第八行--%>
            <tr class="tr8">
                <td>纯净水</td>
                <!-- 加水实际重量 -->
                <td class="green" id="td8_2" colspan="2"></td>
                <td></td>
            </tr>
            <%--第九行--%>
            <tr class="tr9">
                <td>YSD109</td>
                <!-- 加碱量提示 -->
                <td class="green" id="td9_2" colspan="2"></td>
                <td class="green" colspan="3"></td>
                <!-- 加碱后PH输入值 -->
                <td class="blue" id="td9_4"></td>
                <td class="grey" colspan="4"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
            <%--第十行--%>
            <tr class="tr10">
                <td>YSD106</td>
                <!-- 助剂计量罐1-2称重 -->
                <td class="green" id="td10_2" colspan="2"></td>
                <!-- 允许一次加助剂时间 -->
                <td class="green" id="td10_3"></td>
                <!-- 所有助剂加料完成1时间 -->
                <td class="green" id="td10_4"></td>
                <!-- 所有助剂加料完成1反应釜温度 -->
                <td class="green" id="td10_5"></td>
                <td class="grey" colspan="2"></td>
                <!-- 允许一次加助剂釜称重 -->
                <td class="green" id="td10_7"></td>
                <!-- 所有助剂加料完成1釜称重 -->
                <td class="green" id="td10_8"></td>
                <!-- 加料前后重量差 -->
                <td class="yellow" id="td10_9"></td>
                <!-- 允许一次加助剂到助剂2加料完成时间 -->
                <td class="yellow" id="td10_10"></td>
                <td></td>
            </tr>
            <%--第十一行--%>
            <tr class="tr11">
                <td colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
            </tr>
            <%--第十二行--%>
            <tr class="tr12">
                <td>YSD102</td>
                <td class="green" id="td12_2" colspan="2"></td>
                <td class="green" id="td12_3"></td>
                <td class="green" id="td12_4"></td>
                <td class="green" id="td12_5"></td>
                <td class="blue"></td>
                <td class="grey"></td>
                <td class="green" id="td12_7"></td>
                <td class="green" id="td12_8"></td>
                <td class="yellow" id="td12_9"></td>
                <td class="yellow" id="td12_10"></td>
                <td></td>
            </tr>
            <%--第十三行--%>
            <tr class="tr13">
                <td>开始升温</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green" id="td13_4" colspan="2"></td>
                <td class="green" id="td13_5" colspan="3"></td>
                <td class="grey" colspan="3" rowspan="2"></td>
                <td class="yellow" id="td14_9" rowspan="2"></td>
                <td></td>
            </tr>
            <%--第十四行--%>
            <tr class="tr14">
                <td>PH检测（中温温度）</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green" id="td14_4" colspan="2"></td>
                <td class="green" id="td14_5"></td>
                <td class="blue"></td>
                <td class="grey" id="td14_7"></td>
                <td></td>
            </tr>
            <%--第十五行--%>
            <tr class="tr15">
                <td>YSD104</td>
                <td class="green" colspan="2"></td>
                <td class="green" id="td15_3"></td>
                <td class="green" id="td15_4"></td>
                <td class="green" id="td15_5"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green" id="td15_8"></td>
                <td class="green" id="td15_9"></td>
                <td class="yellow" id="td15_10"></td>
                <td class="yellow" id="td15_11"></td>
                <td></td>
            </tr>
            <%--第十六行--%>
            <tr class="tr16">
                <td>停汽</td>
                <td class="grey" colspan="2"></td>
                <td class="green" id="td16_3" colspan="2"></td>
                <td class="green" id="td16_4"></td>
                <td class="blue"></td>
                <td class="green" id="td16_6"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="yellow" id="td16_10"></td>
                <td></td>
            </tr>
            <%--第十七行--%>
            <tr class="tr17">
                <td rowspan="3">保温</td>
                <td class="grey">冰水检测</td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="blue" id="td17_8"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <%--第十八行--%>
            <tr class="tr18">
                <td class="grey">20度检测</td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="blue" id="td18_8"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <%--第十九行--%>
            <tr class="tr19">
                <td>降温水数</td>
                <td class="yellow"></td>
                <td class="yellow"></td>
                <td class="green">：</td>
                <td class="green">聚合终点温度</td>
                <td class="blue" id="td19_7"></td>
                <td class="blue" id="td19_8"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <%--第二十行--%>
            <tr class="tr20">
                <td>冷却</td>
                <td colspan="2">夏季（35）℃，</br>冬季（43）℃</td>
                <td class="blue" id="td20_3"></td>
                <td class="green" id="td20_4"></td>
                <td class="green">降温完成时温度</td>
                <td class="grey" id="td20_6"></td>
                <td class="blue"></td>
                <td class="blue"></td>
                <td class="blue"></td>
                <td class="grey"></td>
                <td class="yellow" id="td20_11"></td>
                <td></td>
            </tr>
            <%--第二十一行--%>
            <tr class="tr21">
                <td rowspan="3">质量终检</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
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
            <tr class="tr22">
                <td>生产总重</td>
                <td class="green"></td>
                <td class="grey" rowspan="2"></td>
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
            <tr class="tr23">
                <td class="grey"></td>
                <td class="grey"></td>
                <td>实测：</td>
                <td class="blue"></td>
                <td class="blue"></td>
                <td class="blue"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <%--第二十四行--%>
            <tr class="tr24">
                <td rowspan="2">排 胶</td>
                <td>日期与时间：</td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow"></td>
                <td>打入胶罐1：</td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="green"> </td>
                <td class="yellow"></td>
                <td rowspan="2"></td>
                <td class="yellow" rowspan="2"></td>
                <td></td>
            </tr>
            <%--第二十五行--%>
            <tr class="tr25">
                <td>反应釜打胶前后重量</td>
                <td class="green"></td>
                <td class="green"> </td>
                <td class="yellow"></td>
                <td>打入胶罐2：</td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
        </table>
    </div>
    <div class="home_right_bottom_div">
        <div id="paging" class="home_right_bottom_paging"></div>
    </div>
</div>
</body>
</html>
