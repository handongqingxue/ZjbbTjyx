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
    <title>Title</title>
    <link rel="stylesheet" href="<%=basePath%>resources/css/style.css">
</head>
<body>
    <div>
        <table border="1" class="tab">
            <%-- 第一行--%>
            <tr>
                <td colspan="2">SOP</td>
                <td colspan="13">
                    <span>M类（）胶</span>
                    <span>生产记录</span>
                    <span>自动表单设计：张发</span>
                </td>
            </tr>
            <!-- 第二行 -->
            <tr>
                <td colspan="2">一、自检数据</td>
                <td>YSD101信息</td>
                <td>录入</td>
                <td>YSD101信息</td>
                <td>录入</td>
                <td></td>
                <td colspan="2">当班操作员：</td>
                <td colspan="2">直接摘抄登陆名</td>
                <td colspan="2">接班操作员：</td>
                <td colspan="2">直接摘抄登陆名</td>
            </tr>
             <!-- 第三行-->
            <tr>
                <td>1蒸汽压力</td>
                <td>Map</td>
                <td>生产编号</td>
                <td>#</td>
                <td>反应釜：</td>
                <td colspan="2"></td>
                <td>开始时间</td>
                <td></td>
                <td>结束时间</td>
                <td></td>
                <td>生产工时</td>
                <td>min</td>
                <td>生产日期</td>
                <td></td>
            </tr>
            <!--第四行-->
            <tr>
                <td></td>
                <td></td>
                <td>在用F料罐</td>
                <td>1罐</td>
                <td>PH:</td>
                <td>浓度:</td>
                <td>2罐</td>
                <td>PH:</td>
                <td>浓度：</td>
                <td></td>
                <td colspan="5" rowspan="2">备注</td>
            </tr>
            <!--第五行-->
            <tr>
                <td>2冷塔温度</td>
                <td>°C</td>
                <td>1号罐用前重</td>
                <td></td>
                <td>1号罐用后重</td>
                <td></td>
                <td>2号罐用前重</td>
                <td></td>
                <td>2号罐用后重</td>
                <td></td>
<%--                <td colspan="5" rowspan="2"></td>--%>
            </tr>
            <!--第六行-->
            <tr>
                <td>3冷塔流量</td>
                <td>m3/h</td>
                <td rowspan="2">原辅料kg</td>
                <td rowspan="2">设定重量kg</td>
                <td rowspan="2">实际重量kg</td>
                <td colspan="2">操作时间</td>
                <td rowspan="2">温度</td>
                <td rowspan="2">PH(25°C)</td>
                <td rowspan="2">浑浊度检测T.T</td>
                <td colspan="2">计量罐或反应釜重量 kg</td>
                <td rowspan="2">重量差 kg</td>
                <td rowspan="2">操作时间min</td>
                <td>备注</td>
            </tr>
            <!--第七行-->
            <tr>
                <td>4釜底蝶阀关闭</td>
                <td></td>
                <td>活动开始</td>
                <td>活动结束</td>
                <td>加料前kg</td>
                <td>加料后kg</td>
                <td></td>
            </tr>
        <!--第八行-->
            <tr>
                <td>5除尘阀门</td>
                <td></td>
                <td>YSD101</td>
                <td>复制配方数据</td>
                <td>计量罐前后差</td>
                <td>:</td>
                <td>:</td>
                <td>°C</td>
                <td rowspan="2">录入PH</td>
                <td rowspan="2"></td>
                <td></td>
                <td></td>
                <td>加料前后重量差与实际重量再差一次</td>
                <td>动作开始到结束时间</td>
                <td></td>
            </tr>
        <!--第九行-->
            <tr>
                <td colspan="2">二、备料</td>
                <td>纯净水</td>
                <td>复制配方数据</td>
                <td>计量罐前后差</td>
                <td>:</td>
                <td>:</td>
                <td>°C</td>
                <td></td>
                <td></td>
                <td>加料前后重量差与实际重量再差一次</td>
                <td>动作开始到结束时间</td>
                <td></td>
            </tr>
            <!--第十行 -->
            <tr>
                <td rowspan="2">6检查后台配方</td>
                <td></td>
                <td>YSD109</td>
                <td>复制配方数据</td>
                <td></td>
                <td>:</td>
                <td>:</td>
                <td>°C</td>
                <td>录入PH</td>
                <td colspan="4"></td>
                <td>动作开始到结束时间</td>
                <td></td>
            </tr>

            <!--第十一行-->
            <tr>
                <td></td>
                <td>YSD106</td>
                <td>复制配方数据</td>
                <td>计量罐前后差</td>
                <td>:</td>
                <td>:</td>
                <td>°C</td>
                <td colspan="2"></td>
                <td></td>
                <td></td>
                <td>加料前后重量差与实际重量再差一次</td>
                <td>动作开始到结束时间</td>
                <td></td>
            </tr>

            <!--第十二行-->
            <tr>
                <td>7对照配方存档</td>
                <td></td>
                <td colspan="13">1投料前后一定确定料斗重量 2关闭取样器下阀门  3关闭主通风管道  4打开除尘箱2阀门    4加入过滤棉</td>
            </tr>

            <!--第十三行-->
            <tr>
                <td>8至少两人确定</td>
                <td></td>
                <td>YSD102</td>
                <td>复制配方数据</td>
                <td></td>
                <td>:</td>
                <td>:</td>
                <td>°C</td>
                <td>录入PH</td>
                <td></td>
                <td></td>
                <td></td>
                <td>加料前后重量差与实际重量再差一次</td>
                <td>动作开始到结束时间</td>
                <td></td>
            </tr>
            <!--第十四行-->
            <tr>
                <td colspan="2">三、加料	</td>
                <td>开始升温</td>
                <td>复制配方数据</td>
                <td></td>
                <td colspan="2">:</td>
                <td colspan="3">瞬时蒸汽压力：     MPa		</td>
                <td colspan="3" rowspan="2"></td>
                <td rowspan="2">从升温至85度时间</td>
                <td></td>
            </tr>
            <!--第十五行-->
            <tr>
                <td></td>
                <td></td>
                <td>PH检测（85度）</td>
                <td></td>
                <td></td>
                <td colspan="2">:</td>
                <td>°C</td>
                <td>录入PH</td>
                <td></td>
                <td></td>
            </tr>
            <!--第十六行-->
            <tr>
                <td>9 除尘阀门开闭</td>
                <td></td>
                <td>YSD104</td>
                <td>复制配方数据</td>
                <td>计量罐前后差</td>
                <td colspan="2">:</td>
                <td>加料结束温度</td>
                <td colspan="2"></td>
                <td></td>
                <td></td>
                <td>加料前后重量差与实际重量再差一次</td>
                <td>加料时间</td>
                <td></td>
            </tr>
            <!--第十七行-->
            <tr>
                <td>10 关回流阀门</td>
                <td></td>
                <td>停汽</td>
                <td colspan="2">保温开始计时器自动启动，显示累积分钟</td>
                <td colspan="2">:</td>
                <td>°C</td>
                <td>录入PH</td>
                <td></td>
                <td colspan="3" rowspan="7"></td>
                <td>从升温至停汽时间</td>
                <td></td>
            </tr>
            <!--第十八行-->
            <tr>
                <td></td>
                <td></td>
                <td rowspan="6">保温</td>
                <td>冰水检测</td>
                <td>（40）min</td>
                <td>:</td>
                <td colspan="2" rowspan="5"></td>
                <td rowspan="7"></td>
                <td>录入分钟数</td>
                <td rowspan="6"></td>
                <td></td>
            </tr>
            <!--第十九行-->
            <tr>
                <td>11加YSD102后关碟阀</td>
                <td></td>
                <td>20度检测</td>
                <td>（65）min</td>
                <td>:</td>
                <td>录入分钟数</td>
                <td></td>
            </tr>
            <!--第二十行-->
            <tr>
                <td>12除尘阀门开闭</td>
                <td></td>
                <td>水数检测1</td>
                <td>人工点击时记录分钟数</td>
                <td>人工点击时记录时间</td>
                <td>录入水数</td>
                <td></td>
            </tr>
            <!--第二十一行-->
            <tr>
                <td>13冲洗除尘箱</td>
                <td></td>
                <td>水数检测2</td>
                <td>人工点击时记录分钟数</td>
                <td>人工点击时记录时间</td>
                <td>录入水数</td>
                <td></td>
            </tr>
            <!--第二十二行-->
            <tr>
                <td>14冲洗反应釜</td>
                <td></td>
                <td>水数检测3</td>
                <td>人工点击时记录分钟数</td>
                <td>人工点击时记录时间</td>
                <td>录入水数</td>
                <td></td>
            </tr>
            <!--第二十三行-->
            <tr>
                <td colspan="2">四、过程取样</td>
                <td>降温水数</td>
                <td>人工点击时记录分钟数</td>
                <td>人工点击时记录时间</td>
                <td>:</td>
                <td></td>
                <td>录入水数</td>
                <td></td>
            </tr>
            <!--第二十四行-->
            <tr>
                <td></td>
                <td></td>
                <td>冷却</td>
                <td colspan="2">
                    "夏季（35）°C，
                    冬季（43）°C"
                </td>
                <td></td>
                <td>:</td>
                <td>°C</td>
                <td>是否中间停泵</td>
                <td>是</td>
                <td>否</td>
                <td>→反应釜</td>
                <td>从开始降温到停止降温时间</td>
                <td></td>
            </tr>
            <!--第二十五行-->
            <tr>
                <td>15每次洗涮取样杯</td>
                <td></td>
                <td rowspan="3">质量终检</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>水数W.T</td>
                <td>PH</td>
                <td>固化时间s</td>
                <td>粘度Vis</td>
                <td>浑浊时间</td>
                <td>固含Solid</td>
                <td>外观</td>
                <td></td>
            </tr>
            <!--第十二六行-->
            <tr>
                <td colspan="2">五、冷却泵操作	</td>
                <td>生产总重</td>
                <td></td>
                <td rowspan="2"></td>
                <td>20℃标准：</td>
                <td>1.4-1.7</td>
                <td>9.2-9.8</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <!--第二十七行 -->
            <tr>
                <td>16先开阀门再开泵</td>
                <td></td>
                <td></td>
                <td></td>
                <td>实测：</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <!--第二十八行 -->
            <tr>
                <td>17先关泵再关阀门</td>
                <td></td>
                <td rowspan="2">排 胶</td>
                <td>日期与时间：</td>
                <td>:</td>
                <td>:</td>
                <td>时间差</td>
                <td>打入胶罐1：</td>
                <td>罐号1</td>
                <td>之前重量</td>
                <td>之后重量</td>
                <td>前后重量差</td>
                <td rowspan="2">反应釜与胶罐前后重量对比</td>
                <td rowspan="2">反应釜与胶罐前后重量对比</td>
                <td></td>
            </tr>
            <!--第二十九行 -->
            <tr>
                <td colspan="2">六、终点取样</td>
                <td>反应釜打胶前后重量</td>
                <td>之前重量</td>
                <td>之后重量 </td>
                <td>反应釜重量差</td>
                <td>打入胶罐2：</td>
                <td>罐号2</td>
                <td>之前重量</td>
                <td>后重量</td>
                <td>前后重量差</td>
                <td></td>
            </tr>
            <!--第三十行 -->
            <tr>
                <td>18反应釜取样</td>
                <td></td>
                <td colspan="13" rowspan="4"></td>
            </tr>
            <!--第三十一行 -->
            <tr>
                <td>19取样进行检测</td>
                <td></td>
            </tr>
            <!--第三十二行 -->
            <tr>
                <td colspan="2">七、排胶</td>
            </tr>
            <!--第三十三行 -->
            <tr>
                <td>20开釜底蝶阀</td>
                <td></td>
            </tr>
            <!--第三十四行 -->
            <tr>
                <td>21打胶后关蝶阀</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
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
            <!--第三十五行 -->
            <tr>
                <td>22清理过滤器并查看拧紧★</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
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
            <!--第三十六行 -->
            <tr>
                <td>23填写打胶记录"</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
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
        </table>
    </div>
</body>
</html>
