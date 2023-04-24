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
<script type="text/javascript">
var path='<%=basePath%>';
$(function () {
	getUnCreRepVarList();
})

function getUnCreRepVarList(){
	$.post(path+"report/getUnCreRepVarList",
		{batchID:"MA202323000022"},
		function(result){
			var varMapList=result.varMapList;
			for (var i = 0; i < varMapList.length; i++) {
				var varMap=varMapList[i];
				var rowNumber=varMap.rowNumber;
				var colNumber=varMap.colNumber;
				var value=varMap.value;
				console.log(rowNumber+","+colNumber+","+value);
				$("#td"+rowNumber+"_"+colNumber).text(value);
			}
		}
	,"json");
}
</script>
</head>
<body>
<div class="m-query_div">
    <div class="m_query_head_div">
        <table class="m_query_head_table">
            <tr>
                <td style="font-size: 17px">
                    厂家信息
                    <input type="text" placeholder="录入厂家信息">
                </td>
                <td>
                    操作员
                    <select>
                        <option>张三</option>
                    </select>
                </td>
                <td>
                    报表状态:<span></span>
                </td>
            </tr>
            <tr>
                <td>
                    1号罐重量初始输入
                    <input type="text">
                </td>
                <td>
                    2号罐重量初始输入
                    <input type="text">
                </td>
                <td>
                    <button>生成报表</button>
                    <button>数据复位</button>
                </td>
            </tr>
        </table>
    </div>
    <div class="m_query_body_div">
        <table class="m_query_body_table" border="1px" id="opcMTable">
            <tr>
                <td colspan="13">
                    <span class="onetd1">M类 （ ）胶 生产记录</span>
                    <%--                    <span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>--%>
                </td>
            </tr>
            <%--第二行--%>
            <tr>
                <td>YSD101信息</td>
                <td class="blue">
                    <%--甲醛厂家信息，可后期录入--%>
                    <%--                    <input type="text" id="ysd101_input" placeholder="甲醛厂家信息"/>--%>
                </td>
                <td>YSD102信息</td>
                <td class="blue">
                    <%--三安厂家信息可后期录入--%>
                    <%--                    <input type="text" id="ysd102_input" placeholder="三安厂家信息">--%>
                </td>
                <td></td>
                <td colspan="2">当班操作员：</td>
                <td class="green" colspan="2">
                    <%--直接摘抄登录名--%>
                    <%--                    <input type="text" id="dbczyBsh_input" placeholder="登录名">--%>
                </td>
                <td colspan="2">接班操作员：</td>
                <td class="green" colspan="2">
                    <%--直接摘抄登录名--%>
                    <%--                    <input type="text" id="jbczyBsh_input" placeholder="登录名">--%>
                </td>
            </tr>
            <%--第三行--%>
            <tr>
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
            <tr>
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
            <tr>
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
            <tr>
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
            <%--第七行--%>
            <tr>
                <td>动作开始</td>
                <td>动作结束</td>
                <td>加料前kg</td>
                <td>加料后kg</td>
                <td></td>
            </tr>
            <%--第八行--%>
            <tr>
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
            <%--第九行--%>
            <tr>
                <td>纯净水</td>
                <td class="green" id="td8_2" colspan="2"></td>
                <td></td>
            </tr>
            <%--第十行--%>
            <tr>
                <td>YSD109</td>
                <td class="green" colspan="2"></td>
                <td class="green" colspan="3"></td>
                <td class="blue"></td>
                <td class="grey" colspan="4"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
            <%--第十一行--%>
            <tr>
                <td>YSD106</td>
                <td class="green" colspan="2"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="green">℃</td>
                <td class="grey" colspan="2"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
            <%--第十二行--%>
            <tr>
                <td colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
            </tr>
            <%--第十三行--%>
            <tr>
                <td>YSD102</td>
                <td class="green" colspan="2"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="blue"></td>
                <td class="grey"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
            <%--第十四行--%>
            <tr>
                <td>开始升温</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green" colspan="2"></td>
                <td class="green" colspan="3">：     MPa</td>
                <td class="grey" colspan="3" rowspan="2"></td>
                <td class="yellow" rowspan="2"></td>
                <td></td>
            </tr>
            <%--第十五行--%>
            <tr>
                <td>PH检测（中温温度）</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green" colspan="2"></td>
                <td class="green"></td>
                <td class="blue"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <%--第十六行--%>
            <tr>
                <td>YSD104</td>
                <td class="green" colspan="2"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="green">℃</td>
                <td class="grey" colspan="2"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
            <%--第十七行--%>
            <tr>
                <td>停汽</td>
                <td class="grey" colspan="2"></td>
                <td class="green" colspan="2"></td>
                <td class="green"></td>
                <td class="blue"></td>
                <td class="grey"></td>
                <td class="grey" colspan="3" rowspan="4"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
            <%--第十八行--%>
            <tr>
                <td rowspan="3">保温</td>
                <td class="grey">冰水检测</td>
                <td class="green" colspan="2"></td>
                <td class="grey" colspan="2" rowspan="2"></td>
                <td class="grey" rowspan="4"></td>
                <td class="blue"></td>
                <td class="grey" rowspan="3"></td>
                <td></td>
            </tr>
            <%--第十九行--%>
            <tr>
                <td class="grey">20度检测</td>
                <td class="green" colspan="2"></td>
                <td class="blue"></td>
                <td></td>
            </tr>
            <%--第二十行--%>
            <tr>
                <td>降温水数</td>
                <td class="yellow"></td>
                <td class="yellow"></td>
                <td class="green">：</td>
                <td class="green"></td>
                <td class="blue"></td>
                <td></td>
            </tr>
            <%--第二十一行--%>
            <tr>
                <td>冷却</td>
                <td colspan="2">
                    夏季（35）℃，</br>
                    冬季（43）℃
                </td>
                <td class="grey">
                    聚合终点</br>
                    开始时间
                </td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="blue"></td>
                <td class="blue"></td>
                <td class="blue"></td>
                <td class="grey"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
            <%--第二十二行--%>
            <tr>
                <td rowspan="3">质量终检</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td></td>
                <td>PH</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <%--第二十三行--%>
            <tr>
                <td>生产总重</td>
                <td class="yellow"></td>
                <td class="grey" rowspan="2"></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <%--第二十四行--%>
            <tr>
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
            <%--第二十五行--%>
            <tr>
                <td rowspan="2">排 胶</td>
                <td>日期与时间：</td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow"></td>
                <td></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="green"> </td>
                <td class="yellow"></td>
                <td rowspan="2"></td>
                <td class="yellow" rowspan="2"></td>
                <td></td>
            </tr>
            <%--第二十六行--%>
            <tr>
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
    <div class="m_query_bottom_div">
        <div id="paging" class="m_query_bottom_paging"></div>
    </div>
</div>
</body>
</html>
