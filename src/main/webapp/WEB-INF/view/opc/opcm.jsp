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
    <script type="text/javascript" src="<%=basePath%>resource/js/jquery-3.3.1.js"></script>
    <title>Title</title>
    <link rel="stylesheet" href="<%=basePath%>resource/css/style.css">
    <script type="text/javascript">
<<<<<<< HEAD
        function addSCJL() {
           $.ajax(
               {
                   url:"<%=basePath%>opc/addSCJL",
                   type:"post",
                   data:{
                       "scbh": $("scbh_input").innerText,
                       "fyfh": $("scbh_input").innerText,
                       "kssj": "2023-03-24 17:30:00",
                       "jssj": "2023-03-25 17:30:00",
                       "scgs": $("scbh_input").innerText,
                       "scrq": "2023-03-24 17:30:00",
                       "ysd101": $("scbh_input").innerText,
                       "ysd102": $("scbh_input").innerText,
                       "ysd103": "2",
                       "dbczy":$("scbh_input").innerText,
                       "jbczy":$("scbh_input").innerText,
                       "lx": 2
                   },
                   // contentType:application/json,
                   success:function (response) {
                       close(response);
                       alert(response.message)
                       // var name=response.username
                       // $("#dqczy_span").text(name)
=======
        function hello() {
           $.ajax(
               {
                   url:"<%=basePath%>opc/getUser",
                   type:"post",
                   success:function (response) {
                       var name=response.username
                       $("#dqczy_span").text(name)
>>>>>>> 491426194df694570c5cc7984958710382b102ad
                   }
               }
           )
        }
    </script>
</head>
<body>
    <div>
        <table class="tab" border="1px">
            <tr>
                <td colspan="13">
<<<<<<< HEAD
                    <span class="onetd1">M类 （ ）胶 生产记录</span>
                    <span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>
                    <button onclick="addSCJL()">保存</button>
=======
                    <button onclick="hello()">点</button>
                    <span class="onetd1">M类 （ ）胶 生产记录</span>
                    <span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>
>>>>>>> 491426194df694570c5cc7984958710382b102ad
                </td>
            </tr>
            <%--第二行--%>
            <tr>
                <td>YSD101信息</td>
<<<<<<< HEAD
                <td class="blue">
<%--                    甲醛厂家信息，可后期录入--%>
                    <input type="text" id="ysd101_input">
                </td>
                <td>YSD102信息</td>
                <td class="blue">
                    <input type="text" id="ysd102_input">
<%--                    三安厂家信息可后期录入--%>
                </td>
                <td></td>
                <td colspan="2">当班操作员：

                </td>
                <td class="green" colspan="2">
                    <input type="text" id="dbczy_input">
<%--                    直接摘抄登录名--%>
                </td>
                <td colspan="2">接班操作员：</td>
                <td class="green" colspan="2">
                    <input type="text" id="jbczy_input">
<%--                    直接摘抄登录名--%>
                </td>
            </tr>
            <%--第三行--%>
            <tr>
                <td>生产编号

                </td>
                <td class="yellow">
                    <input type="text" id="scbh_input">
<%--                    每生产1釜加1--%>
                </td>
                <td>反应釜：</td>
                <td class="green" colspan="2">
<%--                    反应釜号--%>
                    <input type="text" id="fyfh_input">
                </td>
                <td>开始时间</td>
                <td class="green">
                    <input type="date" id="kssj_input">
<%--                    备料开始时间--%>
                </td>
                <td>结束时间</td>
                <td class="green">冷却结束时间</td>
                <td>生产工时</td>
                <td class="yellow">
                    <input type="text" id="scgs_input">
<%--                    min--%>
                </td>
=======
                <td class="blue">甲醛厂家信息，可后期录入</td>
                <td>YSD102信息</td>
                <td class="blue">三安厂家信息可后期录入</td>
                <td></td>
                <td colspan="2">当班操作员：
                    <span id="dqczy_span"></span>
                </td>
                <td class="green" colspan="2">直接摘抄登录名</td>
                <td colspan="2">接班操作员：</td>
                <td class="green" colspan="2">直接摘抄登录名</td>
            </tr>
            <%--第三行--%>
            <tr>
                <td>生产编号</td>
                <td class="yellow">每生产1釜加1</td>
                <td>反应釜：</td>
                <td class="green" colspan="2">反应釜号</td>
                <td>开始时间</td>
                <td class="green">备料开始时间</td>
                <td>结束时间</td>
                <td class="green">冷却结束时间</td>
                <td>生产工时</td>
                <td class="yellow">min</td>
>>>>>>> 491426194df694570c5cc7984958710382b102ad
                <td>生产日期： </td>
                <td class="green"></td>
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
            </tr><%--第十九行--%>
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
                <td>打入胶罐1：/td>
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
</body>
</html>
