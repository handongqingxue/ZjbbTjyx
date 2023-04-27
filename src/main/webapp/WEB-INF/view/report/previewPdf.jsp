<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../report/inc/js.jsp"%>
<input type="button" value="导出pdf" onclick="outputPdf()"/>
<div id="noVarRep_div" style="display: block;">
    <table class="m_body_table" border="1px">
        <tr class="tr1">
            <td colspan="13">
                <input type="hidden" id="batchID_hid"/>
                <span class="onetd1" id="pdf-title">M类 （ ）胶 生产记录</span>
                <%-- <span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>--%>
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
            </td>
            <td class="td2_5"></td>
            <td class="td2_6" colspan="2">当班操作员：</td>
            <td class="td2_7 green" colspan="2">
                <%--直接摘抄登录名--%>
            </td>
            <td class="td2_8" colspan="2">接班操作员：</td>
            <td class="td2_9 green" colspan="2">
                <%--直接摘抄登录名--%>
            </td>
        </tr>
        <%--第三行--%>
        <tr class="tr3">
            <td>生产编号</td>
            <td class="yellow" id="td3_2">
                <%--每生产1釜加1--%>
            </td>
            <td>反应釜：</td>
            <td class="green" id="td3_4" colspan="2">
                <%--反应釜号--%>
            </td>
            <td>开始时间</td>
            <td class="green" id="td3_6">
                <%--备料开始时间--%>
            </td>
            <td>结束时间</td>
            <td class="green" id="td3_8">
                <%--冷却结束时间--%>
            </td>
            <td>生产工时</td>
            <td class="yellow" id="td3_10">
                <%--min--%>
            </td>
            <td>生产日期： </td>
            <td class="green">
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
            <%--甲醛备料开始到放料完成时间差 --%>
            <td class="yellow" id="td7_11" rowspan="2"></td>
            <td></td>
        </tr>
        <%--第八行--%>
        <tr class="tr8">
            <td>纯净水</td>
            <%-- 加水实际重量 --%>
            <td class="green" id="td8_2" colspan="2"></td>
            <td></td>
        </tr>
        <%--第九行--%>
        <tr class="tr9">
            <td>YSD109</td>
            <%-- 加碱量提示 --%>
            <td class="green" id="td9_2" colspan="2"></td>
            <td class="green" colspan="3"></td>
            <%-- 加碱后PH输入值 --%>
            <td class="blue" id="td9_4"></td>
            <td class="grey" colspan="4"></td>
            <td class="yellow"></td>
            <td></td>
        </tr>
        <%--第十行--%>
        <tr class="tr10">
            <td>YSD106</td>
            <%-- 助剂计量罐1-2称重 --%>
            <td class="green" id="td10_2" colspan="2"></td>
            <%-- 允许一次加助剂时间 --%>
            <td class="green" id="td10_3"></td>
            <%-- 所有助剂加料完成1时间 --%>
            <td class="green" id="td10_4"></td>
            <%-- 所有助剂加料完成1反应釜温度 --%>
            <td class="green" id="td10_5"></td>
            <td class="grey" colspan="2"></td>
            <%-- 允许一次加助剂釜称重 --%>
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
<script type="text/javascript">
$(function(){
	
});

function outputPdf(){
	html2canvas(
	        $("#noVarRep_div"),
	        {
	            scale: '5',
	            dpi: '500',//导出pdf清晰度
	            //dpi: '172',//导出pdf清晰度
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
	                
	                //var qpbh=$("#pdf-title").text();
	                // var zzrqY=$("#outputPdf_div #zzrqY_span").text();
	                // var zzrqM=$("#outputPdf_div #zzrqM_span").text();
	                pdf.save('111.pdf');
	                // $("#pdf_div").css("border-color","#000");
	                //$("#pdf-title").empty();
	            },
	            //背景设为白色（默认为黑色）
	            background: "#fff"
	        }
	    )
}
</script>
</body>
</html>