<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Title</title>
<script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
var path='<%=basePath%>';
$(function () {
    initDataResetMButDiv();
})

function initDataResetMButDiv() {
    $("#jqcjxx_m_inp").val('');//初始化甲醛厂家信息
    $("#sacjxx_m_inp").val('');//初始化三安厂家信息
    
    /*
    操作员下拉框改为输入框模式了，这块逻辑暂时不用了
    var dbczySel=$("#dbczy_m_sel");//初始化当班操作员信息
    var jbczySel=$("#jbczy_m_sel");//初始化接班操作员信息
    dbczySel.empty();
    dbczySel.append("<option value=''>请选择</option>")
    jbczySel.empty();
    jbczySel.append("<option value=''>请选择</option>")
    //获取全部操作员
    $.post(path+"/report/getOperatorList",
        {},
        function(request){
            if(request.msg=="ok"){
                var list=request.data;
                for (var i=0;i<list.length;i++){
                    dbczySel.append("<option value=\""+list[i].id+"\">"+list[i].realName+"</option>");
                    jbczySel.append("<option value=\""+list[i].id+"\">"+list[i].realName+"</option>");
                }
            }
        }
    ,"json");
    */
    $("#dbczy_m_inp").val('');
    $("#jbczy_m_inp").val('');
    
    $("#tank1DataM").val('');//初始化罐1数据信息
    $("#tank2DataM").val('');//初始化罐2数据信息
    
    $("#tank1AgoM").html("");
    $("#tank2AgoM").html("");
}

function getUnCreRepVarMList(batchID){
    $("#opcMCTable td[id^='td']").text("");//先清除表格里的数据
    $("#opcMCTable #batchID_hid").val(batchID);//设置表格里的批次id
    var glueM=batchID.substring(1,batchID.length-12);
    $("#glueM").html(glueM);

    preCreateMTab();
}

function checkInputInfoM(){
	if(checkJQCJXXM()){
		if(checkSACJXXM()){
			if(checkDBCZYM()){
				if(checkJBCZYM()){
					addReportF_MByBatchID();
				}
			}
		}
	}
}

function checkJQCJXXM(){
	var jqcjxx=$("#jqcjxx_m_inp").val();
	if(jqcjxx==""||jqcjxx==null){
		alert("请输入甲醛厂家信息");
		return false;
	}
	else
		return true;
}

function checkSACJXXM(){
	var sacjxx=$("#sacjxx_m_inp").val();
	if(sacjxx==""||sacjxx==null){
		alert("请输入三安厂家信息");
		return false;
	}
	else
		return true;
}

function checkDBCZYM(){
	//var dbczy=$("#dbczy_m_sel").val();
	var dbczy=$("#dbczy_m_inp").val();
	if(dbczy==""||dbczy==null){
		alert("请输入当班操作员");
		return false;
	}
	else
		return true;
}

function checkJBCZYM(){
	//var jbczy=$("#jbczy_m_sel").val();
	var jbczy=$("#jbczy_m_inp").val();
	if(jbczy==""||jbczy==null){
		alert("请输入接班操作员");
		return false;
	}
	else
		return true;
}

function addReportF_MByBatchID(){
    layer.confirm("是否要生成报表 ？", {
        btn: ["确定", "取消"] //按钮
    },
    function (index) {
        var batchID = $("#opcMCTable #batchID_hid").val();
        var jqcjxx=$("#jqcjxx_m_inp").val();
        var sacjxx=$("#sacjxx_m_inp").val();
        //var dbczy=$("#dbczy_m_sel").find("option:selected").text();
        //var jbczy=$("#jbczy_m_sel").find("option:selected").text();
        var dbczy=$("#dbczy_m_inp").val();
        var jbczy=$("#jbczy_m_inp").val();
        var inputJOStr="{\""+jqcjxxKey+"\":\""+jqcjxx+"\",\""+sacjxxKey+"\":\""+sacjxx+"\",\""+dbczyKey+"\":\""+dbczy+"\",\""+jbczyKey+"\":\""+jbczy+"\"}";
        $.post(path + "report/addReportFByBatchID",
            {batchID:batchID,inputJOStr:inputJOStr},
            function (result) {
                if (result.message == "ok") {
                    layer.msg(result.info, {icon: 1});
                    getLeftMenuData("mWsc");
                    preCreateMTab();//刷新页面数据
                    initDataResetMButDiv();//清空输入框
                    $("#m_wsc_span").text("已生成");
                }
            }
        , "json");
        layer.close(index);
    });
}

function preCreateMTab(){
	var batchID=$("#opcMCTable #batchID_hid").val();
	$.post(path+"report/getReportFMByBatchID",
        {batchID:batchID},
        function(result){
            var repFMList=result.data;
            var opcMCTable=$("#opcMCTable");
            opcMCTable.find("td[id^='td']").text("");
            for (var i = 0; i < repFMList.length; i++) {
                var repFM=repFMList[i];
                var rowNumber=repFM.rowNumber;
                var colNumber=repFM.colNumber;
                var value=repFM.value;
                opcMCTable.find("#td"+rowNumber+"_"+colNumber).text(value);//暂时把变量添加到未显示变量的报表模版里
            }
        }
    ,"json");
}

function resetMCTabInp(){
    layer.confirm("是否要复位数据 ？", {
        btn: ["确定", "取消"] //按钮
    }, function (index) {
        var batchID=$("#opcMCTable #batchID_hid").val();
        $.post(path+"report/resetCTabInp",
            {batchID:batchID},
            function(result){
                if(result.status=="ok"){
                    layer.msg(result.info, {icon: 1});
                    preCreateMTab();
                    initDataResetMButDiv();
                }
            }
            ,"json");
        layer.close(index);
    });
}
</script>
</head>
<body>
<div class="home_right_div">
    <div class="home_right_head_div">
        <div class="m_create_head_row1_div">
            <span class="jqcjxx_span">甲醛厂家信息</span>
            <input type="text" placeholder="录入甲醛厂家信息" id="jqcjxx_m_inp" class="m_create_head_input"/>
            <span class="sacjxx_span">三安厂家信息</span>
            <input type="text" placeholder="录入三安厂家信息" id="sacjxx_m_inp" class="m_create_head_input"/>
            <span class="dbczy_span">当班操作员</span>
            <!-- 
            <select class="m_create_head_input" id="dbczy_m_sel"></select>
             -->
            <input type="text" placeholder="录入当班操作员信息" id="dbczy_m_inp" class="m_create_head_input"/>
            <span class="bbzt_span">报表状态:</span>
            <span class="wsc_span" id="m_wsc_span"></span>
        </div>
        <div class="m_create_head_row2_div">
            <span class="gzlcssr1_span">1号罐重量初始输入</span>
            <input type="text" size="5" id="tank1DataM" class="m_create_head_input"/>
            
            <span class="gzlcssr2_span">2号罐重量初始输入</span>
            <input type="text" size="5" id="tank2DataM" class="m_create_head_input"/>

            <span class="jbczy_span">接班操作员</span>
            <!-- 
            <select class="m_create_head_input" id="jbczy_m_sel"></select>
             -->
            <input type="text" placeholder="录入接班操作员信息"  id="jbczy_m_inp" class="m_create_head_input"/>
            <c:if test="${userAllRole[0].id==1||userAllRole[0].id==2||userAllRole[0].id==3}">
                <div class="but_div scbb_but_div" onclick="checkInputInfoM()">生成报表</div>
                <div class="but_div sjfw_but_div" onclick="resetMCTabInp()">数据复位</div>
            </c:if>
        </div>
    </div>
    <div class="home_right_cbody_div">
        <table class="m_body_table" border="1px" id="opcMCTable">
            <tr class="tr1">
                <td colspan="13">
                	<input type="hidden" id="batchID_hid"/>
                    <span class="onetd1">M类（<span id="glueM"></span>）胶 生产记录</span>
                    <%--<span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>--%>
                </td>
            </tr>
            <%--第二行--%>
            <tr class="tr2">
                <td class="td2_1">YSD101信息</td>
                <td class="td2_2 blue" id="td2_2">
                    <%--甲醛厂家信息，可后期录入--%>
                </td>
                <td class="td2_3">YSD102信息</td>
                <td class="td2_4 blue" id="td2_4">
                    <%--三安厂家信息可后期录入--%>
                </td>
                <td class="td2_5"></td>
                <td class="td2_6" colspan="2">当班操作员：</td>
                <td class="td2_7 green" colspan="2" id="td2_7">
                    <%--直接摘抄登录名--%>
                </td>
                <td class="td2_8" colspan="2">接班操作员：</td>
                <td class="td2_9 green" colspan="2" id="td2_9">
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
                <td style="width: 150px">结束时间</td>
                <td class="green" id="td3_8">
                    <%--冷却结束时间--%>
                </td>
                <td>生产工时</td>
                <td class="yellow" id="td3_10">
                    <%--min--%>
                </td>
                <td style="width: 150px;">生产日期： </td>
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
                <td class="yellow" id="tank1AgoM"></td>
                <td>1号罐用后重</td>
                <td class="yellow"></td>
                <td>2号罐用前重</td>
                <td class="yellow" id="tank2AgoM"></td>
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
                <td class="green" id="td24_3"></td>
                <td class="green" id="td24_4"></td>
                <td class="yellow" id="td24_5"></td>
                <td>打入胶罐1：</td>
                <td class="green" id="td24_7"></td>
                <td class="green" id="td24_8"></td>
                <td class="green" id="td24_9"> </td>
                <td class="yellow" id="td24_10"></td>
                <td rowspan="2" id="td24_11"></td>
                <td class="yellow" rowspan="2" id="td24_12"></td>
                <td></td>
            </tr>
            <%--第二十五行--%>
            <tr class="tr25">
                <td>反应釜打胶前后重量</td>
                <td class="green" id="td25_3"></td>
                <td class="green" id="td25_4"> </td>
                <td class="yellow" id="td25_5"></td>
                <td>打入胶罐2：</td>
                <td class="green" id="td25_7"></td>
                <td class="green" id="td25_8"></td>
                <td class="green" id="td25_9"></td>
                <td class="yellow" id="td25_10"></td>
                <td></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
