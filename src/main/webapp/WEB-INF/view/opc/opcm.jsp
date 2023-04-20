<%--
  Created by IntelliJ IDEA.
  User: lilekang
  Date: 2023/3/24
  Time: 9:53 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>resource/css/form_style.css">
<script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
<title>Title</title>
<script type="text/javascript">
var path='<%=basePath%>';

function addPiCiU() {
	var scbh=$("#scbh_input").val();
	var fyfh=$("#fyfh_input").val();
	var kssj=$("#kssj_input").val();
	var jssj=$("#jssj_input").val();
	var scgs=$("#scgs_input").val();
	var scrq=$("#scrq_input").val();
	var ysd101=$("#ysd101_input").val();
	var ysd102=$("#ysd102_input").val();
	var ysd103="2";
	var dbczyBsh=$("#dbczyBsh_input").val();
	var jbczyBsh=$("#jbczyBsh_input").val();
	
    $.post(path+"opc/addPiCiU",
    	{scbh:scbh,fyfh:fyfh,kssj:kssj,jssj:jssj,scgs:scgs,scrq:scrq,ysd101:ysd101,ysd102:ysd102,ysd103:ysd103,dbczyBsh:dbczyBsh,jbczyBsh:jbczyBsh},
    	function(data){
    		if(data.message=="ok"){
    			alert(data.info);
    		}
    	}
    ,"json");
}

function base64 (content) {
    return window.btoa(unescape(encodeURIComponent(content)));
}

function exportExcel() {
    var table = $("#opcMTable");
    var excelContent = table[0].innerHTML;
    var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
    excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
    excelFile += "<body><table>";
    excelFile += excelContent;
    excelFile += "</table></body>";
    excelFile += "</html>";
    var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
    var a = document.createElement("a");
    a.download = "M类()胶生产记录.xlsx";
    a.href = link;
    a.click();
}

</script>
</head>
<body>
<div class="m-query_div">
    <div class="m_query_head_div">
        <table>
            <tr>
                <td>设置检索条件</td>
                <td></td>
            </tr>
            <tr>
                <td>
                    起始时间
                    <input type="date" placeholder="请选择时间">
                </td>
                <td>
                    选择批次
                    <select>
                        <option>m1</option>
                        <option>m2</option>
                        <option>m3</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    结束时间
                    <input type="date" placeholder="请选择时间">
                </td>
                <td>
                    当前胶种
                </td>
                <td>
                    <button>查询</button>
                </td>
            </tr>
        </table>
    </div>
    <div>
        <table class="tab" border="1px" id="opcMTable">
            <tr>
                <td colspan="13">
                    <span class="onetd1">M类 （ ）胶 生产记录</span>
                    <span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>
                </td>
            </tr>
            <%--第二行--%>
            <tr>
                <td>YSD101信息</td>
                <td class="blue">
                    <%--甲醛厂家信息，可后期录入--%>
                    <input type="text" id="ysd101_input" placeholder="甲醛厂家信息"/>
                </td>
                <td>YSD102信息</td>
                <td class="blue">
                    <%--三安厂家信息可后期录入--%>
                    <input type="text" id="ysd102_input" placeholder="三安厂家信息">
                </td>
                <td></td>
                <td colspan="2">当班操作员：</td>
                <td class="green" colspan="2">
                    <%--直接摘抄登录名--%>
                    <input type="text" id="dbczyBsh_input" placeholder="登录名">
                </td>
                <td colspan="2">接班操作员：</td>
                <td class="green" colspan="2">
                    <%--直接摘抄登录名--%>
                    <input type="text" id="jbczyBsh_input" placeholder="登录名">
                </td>
            </tr>
            <%--第三行--%>
            <tr>
                <td>生产编号</td>
                <td class="yellow">
                    <%--每生产1釜加1--%>
                    <input type="text" id="scbh_input" placeholder="生产编号">
                </td>
                <td>反应釜：</td>
                <td class="green" colspan="2">
                    <%--反应釜号--%>
                    <input type="text" id="fyfh_input" placeholder="反应釜号">
                </td>
                <td>开始时间</td>
                <td class="green">
                    <%--备料开始时间--%>
                    <input type="datetime-local" id="kssj_input">
                </td>
                <td>结束时间</td>
                <td class="green">
                    <%--冷却结束时间--%>
                    <input type="datetime-local" id="jssj_input">
                </td>
                <td>生产工时</td>
                <td class="yellow">
                    <%--min--%>
                    <input type="text" id="scgs_input" placeholder="min/分">
                </td>
                <td>生产日期： </td>
                <td class="green">
                    <input type="date" id="scrq_input">
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
                <td class="green" colspan="2">甲醛实际加入量</td>
                <td class="green" rowspan="2">甲醛备料开始</td>
                <td class="green" rowspan="2">甲醛水加料完成</td>
                <td class="green" rowspan="2">甲醛水加料完成时温度℃</td>
                <td class="blue" rowspan="2">甲醛水加料完成人工输入PH</td>
                <td class="grey" rowspan="2"></td>
                <td class="green" rowspan="2">甲醛水加料前反应釜重</td>
                <td class="green" rowspan="2">甲醛水加料完成反应釜重</td>
                <td class="yellow" rowspan="2">加料前后重量差</td>
                <td class="yellow" rowspan="2">甲醛放料开始到放料完成时间</td>
                <td></td>
            </tr>
            <%--第九行--%>
            <tr>
                <td>纯净水</td>
                <td class="green" colspan="2">水实际加入量</td>
                <td></td>
            </tr>
            <%--第十行--%>
            <tr>
                <td>YSD109</td>
                <td class="green" colspan="2">实际加碱量</td>
                <td class="green" colspan="3"></td>
                <td class="blue">录入PH</td>
                <td class="grey" colspan="4"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
            <%--第十一行--%>
            <tr>
                <td>YSD106</td>
                <td class="green" colspan="2">助剂1加助剂2计量罐备料量总和</td>
                <td class="green">允许一次加助剂时间</td>
                <td class="green">助剂2加料完成时间</td>
                <td class="green">助剂2加料完成反应釜温度℃</td>
                <td class="grey" colspan="2"></td>
                <td class="green">允许一次加助剂时反应釜重量</td>
                <td class="green">助剂2加料完成反应釜重量</td>
                <td class="yellow">加料前后重量差</td>
                <td class="yellow">允许一次加助剂到助剂2加料完成时间</td>
                <td></td>
            </tr>
            <%--第十二行--%>
            <tr>
                <td colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
            </tr>
            <%--第十三行--%>
            <tr>
                <td>YSD102</td>
                <td class="green" colspan="2">粉提提示重量</td>
                <td class="green">插板阀开时间</td>
                <td class="green">插板阀关时间</td>
                <td class="green">插板阀关温度</td>
                <td class="blue">录入PH</td>
                <td class="grey">加粉料PH合格时的加粉料PH输入值</td>
                <td class="green">加粉料反应釜前重量</td>
                <td class="green">加粉料反应釜后重量</td>
                <td class="yellow">加料前后重量差</td>
                <td class="yellow">插板阀开关时间差</td>
                <td></td>
            </tr>
            <%--第十四行--%>
            <tr>
                <td>开始升温</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green" colspan="2">开始升温时间</td>
                <td class="green" colspan="3">瞬时蒸汽压力：     MPa</td>
                <td class="grey" colspan="3" rowspan="2"></td>
                <td class="yellow" rowspan="2">从升温至85度时间</td>
                <td></td>
            </tr>
            <%--第十五行--%>
            <tr>
                <td>PH检测（中温温度）</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green" colspan="2">达到升温中加料温度设定时间</td>
                <td class="green">升至中温时温度</td>
                <td class="blue">录入PH</td>
                <td class="grey">二次投料PH输入</td>
                <td></td>
            </tr>
            <%--第十六行--%>
            <tr>
                <td>YSD104</td>
                <td class="green" colspan="2">3-5助剂计量罐备料累计值</td>
                <td class="green">允许二次加助剂时间</td>
                <td class="green">助剂5加料完成时间</td>
                <td class="green">二次投料完成温度℃</td>
                <td class="grey" colspan="2"></td>
                <td class="green">二次加助剂反应釜前重</td>
                <td class="green">二次加助剂反应釜后重</td>
                <td class="yellow">加料前后重量</td>
                <td class="yellow">允许二次加助剂到助剂5加注完成时间</td>
                <td></td>
            </tr>
            <%--第十七行--%>
            <tr>
                <td>停汽</td>
                <td class="grey" colspan="2">保温开始计时器自动启动，显示累积分钟</td>
                <td class="green" colspan="2">升温完成时间</td>
                <td class="green">升至最高温温度</td>
                <td class="blue">温度98度PH录入值</td>
                <td class="grey"></td>
                <td class="grey" colspan="3" rowspan="4"></td>
                <td class="yellow">从升温至停汽时间</td>
                <td></td>
            </tr>
            <%--第十八行--%>
            <tr>
                <td rowspan="3">保温</td>
                <td class="grey">冰水检测</td>
                <td class="green" colspan="2"></td>
                <td class="grey" colspan="2" rowspan="2"></td>
                <td class="grey" rowspan="4"></td>
                <td class="blue">测量冰水雾点输入值_F1</td>
                <td class="grey" rowspan="3"></td>
                <td></td>
            </tr>
            <%--第十九行--%>
            <tr>
                <td class="grey">20度检测</td>
                <td class="green" colspan="2"></td>
                <td class="blue">测20雾点输入值_F2</td>
                <td></td>
            </tr>
            <%--第二十行--%>
            <tr>
                <td>降温水数</td>
                <td class="yellow">人工点击时记录分钟数</td>
                <td class="yellow">人工点击时记录时间</td>
                <td class="green">：</td>
                <td class="green">聚合终点温度</td>
                <td class="blue">聚合终点水数录入</td>
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
                <td class="green">降温完成时间</td>
                <td class="green">降温完成时温度</td>
                <td class="blue"></td>
                <td class="blue"></td>
                <td class="blue"></td>
                <td class="grey"></td>
                <td class="yellow">从开始降温到停止降温时间</td>
                <td></td>
            </tr>
            <%--第二十二行--%>
            <tr>
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
            <%--第二十三行--%>
            <tr>
                <td>生产总重</td>
                <td class="yellow"></td>
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
            <%--第二十四行--%>
            <tr>
                <td class="grey"></td>
                <td class="grey"></td>
                <td>实测：</td>
                <td class="blue">终检水数录入</td>
                <td class="blue">终检PH录入</td>
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
                <td class="green">允许排胶时间</td>
                <td class="green">排胶完成时间</td>
                <td class="yellow">排胶时间差</td>
                <td>打入胶罐1：</td>
                <td class="green">罐号1</td>
                <td class="green">之前重量</td>
                <td class="green">之后重量 </td>
                <td class="yellow">前后重量差</td>
                <td rowspan="2">反应釜与胶罐前后重量对比</td>
                <td class="yellow" rowspan="2">反应釜与胶罐前后重量对比</td>
                <td></td>
            </tr>
            <%--第二十六行--%>
            <tr>
                <td>反应釜打胶前后重量</td>
                <td class="green">之前重量</td>
                <td class="green">之后重量 </td>
                <td class="yellow">反应釜重量差</td>
                <td>打入胶罐2：</td>
                <td class="green">罐号2</td>
                <td class="green">之前重量</td>
                <td class="green">后重量</td>
                <td class="yellow">前后重量差</td>
                <td></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
