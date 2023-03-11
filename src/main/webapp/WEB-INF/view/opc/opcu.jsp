<%--
  Created by IntelliJ IDEA.
  User: lilekang
  Date: 2023/3/9
  Time: 9:37 上午
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

    </script>
</head>
<body>
    <div>
        <table class="tab" border="1px">
            <tr>
                <th colspan="13">
                    <span class="onetd1">U类（）胶生产记录</span>
                    <span class="onetd4">自动表单设计：张发 设计号：ZJZD20211225</span>
                </th>
            </tr>
            <!--第二行-->
            <tr>
                <td rowspan="2">YSD101</br>信息</td>
                <td rowspan="2" class="blue">录入</td>
                <td rowspan="2">YSD102</br>信息</td>
                <td rowspan="2" class="blue">录入</td>
                <td rowspan="2"></td>
                <td rowspan="2">YSD103</br>信息</td>
                <td rowspan="2" class="blue">录入</td>
                <td rowspan="2">当班操作员：</td>
                <td class="greens" rowspan="2">直接摘抄登录名</td>
                <td rowspan="2">接班操作员：</td>
                <td class="greens" rowspan="2" class="aa">直接摘抄登录名</td>
                <td rowspan="2" colspan="2"></td>
            </tr>
            <tr>

            </tr>
            <!--第三行-->
            <tr>
                <td>生产编号</td>
                <td class="yellow">#</td>
                <td>反应釜：</td>
                <td colspan="2" class="green"></td>
                <td>开始时间</td>
                <td class="green">:</td>
                <td>结束时间</td>
                <td class="green">:</td>
                <td>生产工时</td>
                <td class="yellow">min</td>
                <td>生产日期： </td>
                <td>20  .  .</td>
            </tr>
            <!--第四行-->
            <tr>
                <td>在用F料罐</td>
                <td class="blue">  □ 1罐</td>
                <td class="blue">PH：</td>
                <td class="blue">浓度：</td>
                <td class="blue">□ 2罐</td>
                <td class="blue">PH：</td>
                <td class="blue">浓度：</td>
                <td></td>
                <td colspan="5" rowspan="2">备注</td>
            </tr>
            <!--第五行-->
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
            <!--第六行-->
            <tr>
                <td rowspan="2">原辅料</br>kg</td>
                <td rowspan="2">设定重量</br>kg</td>
                <td rowspan="2">实际重量</br>kg</td>
                <td colspan="2">操作时间</td>
                <td rowspan="2">温 度℃</td>
                <td rowspan="2"> PH(25℃)</td>
                <td rowspan="2">浑浊度检测T.T</td>
                <td colspan="2">计量罐或反应釜重量 kg</td>
                <td rowspan="2">重量差 kg</td>
                <td rowspan="2">操作时间</br>min</td>
                <td class="remarks">备注</td>
            </tr>
            <!--第七行-->
            <tr>
                <td>动作开始</td>
                <td>动作结束</td>
                <td>加料前kg</td>
                <td>加料后kg</td>
                <td></td>
            </tr>
            <!--第八行-->
            <tr>
                <td>YSD101</td>
                <td class="green"></td>
                <td class="green">计量罐前后差</td>
                <td class="green" rowspan="2">开始时间</td>
                <td class="green" rowspan="2">:</td>
                <td class="green" rowspan="2">℃</td>
                <td rowspan="2" class="blue">录入PH</td>
                <td rowspan="2" class="grey"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow">加料前后重量差与实际重量再差一次</td>
                <td class="yellow">动作开始到结束时间</td>
                <td></td>
            </tr>
            <!--第九行-->
            <tr>
                <td>纯净水</td>
                <td class="green"></td>
                <td class="green">计量罐前后差</td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow">加料前后重量差与实际重量再差一次</td>
                <td class="yellow">动作开始到结束时间</td>
                <td></td>
            </tr>
            <!--第十行-->
            <tr>
                <td>YSD109</td>
                <td class="green"></td>
                <td class="blue">录入</td>
                <td class="green">:</td>
                <td class="green">:</td>
                <td class="green">℃</td>
                <td class="blue">录入PH</td>
                <td colspan="4" class="grey"></td>
                <td class="yellow">动作开始到结束时间</td>
                <td></td>
            </tr>
            <!--第十一行-->
            <tr>
                <td>YSD215一次</td>
                <td class="green"></td>
                <td class="green">计量罐前后差</td>
                <td class="green">:</td>
                <td class="green">:</td>
                <td class="green">℃</td>
                <td colspan="2" class="grey"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow">加料前后重量差与实际重量再差一次</td>
                <td class="yellow">动作开始到结束时间</td>
                <td></td>
            </tr>
            <!--第十二行-->
            <tr>
                <td>YSD215二次</td>
                <td class="green"></td>
                <td class="green">计量罐前后差</td>
                <td class="green">:</td>
                <td class="green">:</td>
                <td class="green">℃</td>
                <td colspan="2" class="grey"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow">加料前后重量差与实际重量再差一次</td>
                <td class="yellow">动作开始到结束时间</td>
                <td></td>
            </tr>
            <!--第十三行-->
            <tr>
                <td colspan="13"></td>
            </tr>
            <!--第十四行-->
            <tr>
                <td>YSD103</td>
                <td class="green">粉提1</td>
                <td class="green" rowspan="2">后面个M类相同</td>
                <td class="green" rowspan="2">:</td>
                <td class="green" rowspan="2">:</td>
                <td class="green" rowspan="2">℃</td>
                <td rowspan="2" class="blue">录入PH</td>
                <td class="grey"></td>
                <td class="green" rowspan="2"></td>
                <td class="green" rowspan="2"></td>
                <td class="yellow" rowspan="2">加料前后重量差与实际重量再差一次</td>
                <td class="yellow" rowspan="2">动作开始到结束时间</td>
                <td></td>
            </tr>
            <!--第十五行-->
            <tr>
                <td>YSD102</td>
                <td class="green">粉提2</td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <!--第十六行-->
            <tr>
                <td>开始升温</td>
                <td class="green"></td>
                <td class="grey"></td>
                <td class="green">开始升温时间</td>
                <td class="grey"></td>
                <td class="green" colspan="3">瞬时蒸汽压力：MPa</td>
                <td class="grey" colspan="3" rowspan="6"></td>
                <td class="yellow" rowspan="2">从升温至95度时间</td>
                <td></td>
            </tr>
            <!--第十七行-->
            <tr>
                <td>升温至高温度</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green">升至高温时间</td>
                <td class="grey"></td>
                <td class="green">高温温度</td>
                <td class="blue">录入PH</td>
                <td class="grey">温度98PH_F1U</td>
                <td></td>
            </tr>
            <!--第十八行-->
            <tr>
                <td>一次保温10分钟测PH</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green">第一次保温启动时间</td>
                <td class="grey"></td>
                <td class="green">第一次保温启动温度</td>
                <td class="blue">录入PH</td>
                <td class="grey">第一次保温完成PH录入</td>
                <td class="green"></td>
                <td></td>
            </tr>
            <!--第十九行-->
            <tr>
                <td>降温开始</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green">第一次降温启动时间</td>
                <td class="grey"></td>
                <td class="green">℃</td>
                <td class="grey" colspan="2"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <!--第二十行-->
            <tr>
                <td>降温停止</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green">一次降温加酸提醒_F1U时间</td>
                <td class="grey"></td>
                <td class="green">一次降温加酸提醒_F1U温度</td>
                <td class="grey" colspan="2"></td>
                <td class="yellow">降温时间</td>
                <td></td>
            </tr>
            <!--第二十一行-->
            <tr>
                <td>加酸并计时</td>
                <td></td>
                <td class="blue">录入加酸量</td>
                <td class="green" colspan="2"></td>
                <td class="green">℃</td>
                <td class="blue">录入PH</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <!--第二十二行-->
            <tr>
                <td>冰水雾点</td>
                <td class="grey" rowspan="3" colspan="2">保温开始计时器自动启动，显示累积分钟</td>
                <td class="yellow" colspan="2"></td>
                <td class="grey"></td>
                <td class="blue">录入分钟数</td>
                <td class="grey"></td>
                <td class="grey" colspan="2"></td>
                <td>冰水时间</td>
                <td class="green">摘抄录入时间即可</td>
                <td></td>
            </tr>
            <!--第二十三行-->
            <tr>
                <td>20度雾点</td>
                <td class="yellow" colspan="2"></td>
                <td class="green">℃</td>
                <td class="blue">录入分钟数</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td>缩聚总时间</td>
                <td class="green">摘抄录入时间即可</td>
                <td></td>
            </tr>
            <!--第二十四行-->
            <tr>
                <td>降温</td>
                <td class="green">:</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="blue">录入PH</td>
                <td class="grey"></td>
                <td class="grey" colspan="3" rowspan="3"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <!--第二十五行-->
            <tr>
                <td>加碱</td>
                <td class="green"></td>
                <td class="blue">录入加碱量</td>
                <td class="green"></td>
                <td class="grey" rowspan="2" colspan="2"></td>
                <td class="blue">录入PH</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <!--第二十六行-->
            <tr>
                <td>70度终止降温</td>
                <td></td>
                <td></td>
                <td class="green">:</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="yellow">从降温到70度时间</td>
                <td></td>
            </tr>
            <!--第二十七行-->
            <tr>
                <td>二次粉料加入</td>
                <td class="green">复制配方数据</td>
                <td class="green"></td>
                <td class="green">:</td>
                <td class="green">:</td>
                <td class="green">℃</td>
                <td class="blue">录入PH</td>
                <td class="grey"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="yellow"></td>
                <td class="yellow"></td>
                <td></td>
            </tr>
            <!--第二十八行-->
            <tr>
                <td>二次保温时间20</td>
                <td class="grey"></td>
                <td class="grey"></td>
                <td class="green">:</td>
                <td class="grey" colspan="2"></td>
                <td class="grey" rowspan="4"></td>
                <td class="grey"></td>
                <td class="grey" colspan="3"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <!--第二十九行-->
            <tr>
                <td>助剂6加入</td>
                <td class="green">复制配方数据</td>
                <td class="green"></td>
                <td class="green" rowspan="2"></td>
                <td class="green" colspan="2" rowspan="2"></td>
                <td class="grey"></td>
                <td class="green" rowspan="2"></td>
                <td class="green" rowspan="2"></td>
                <td class="yellow" rowspan="2"></td>
                <td class="yellow" rowspan="2"></td>
                <td></td>
            </tr>
            <!--第三十行-->
            <tr>
                <td>水加入</td>
                <td class="green">复制配方数据</td>
                <td class="green"></td>
                <td class="grey"></td>
                <td></td>
            </tr>
            <!--第三十一行-->
            <tr>
                <td>冷却至结束</td>
                <td colspan="2">35℃	</td>
                <td class="grey"></td>
                <td class="green">结束时间</td>
                <td class="green">℃</td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="green"></td>
                <td class="grey"></td>
                <td class="yellow">从开始降温到停止降温时间</td>
                <td></td>
            </tr>
            <!--第三十二行-->
            <tr>
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
            <tr>
                <td>生产总重</td>
                <td class="yellow"></td>
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

            <!-- 第三十五-->
            <tr>
                <td rowspan="2">排 胶</td>
                <td>日期与时间：</td>
                <td class="green">:</td>
                <td class="green">:</td>
                <td class="yellow">时间差</td>
                <td>打入胶罐1：</td>
                <td class="green">罐号1</td>
                <td class="green">之前重量</td>
                <td class="green">之后重量 </td>
                <td class="yellow">前后重量差</td>
                <td rowspan="2">反应釜与胶罐前后重量对比</td>
                <td rowspan="2">反应釜与胶罐前后重量对比</td>
                <td></td>
            </tr>

            <!-- 第三十六-->
            <tr>
                <td>反应釜打胶前后重量</td>
                <td> class="green"之前重量</td>
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
