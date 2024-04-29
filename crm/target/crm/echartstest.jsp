<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <!--引入jquery-->
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <!--引入echarts插件-->
    <script type="text/javascript" src="jquery/echarts/echarts.min.js"></script>
    <title>演示echarts插件</title>
    <script type="text/javascript">
        $(function () {
            //当容器加载完成之后，对容器调用工具函数

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {//标题
                    text: 'ECharts 入门示例',
                    subtext:'测试副标题',
                    textStyle:{
                        fontStyle:'italic'
                    }
                },
                tooltip: {//提示框
                    textStyle:{
                        color:'blue'
                    }
                },
                legend: {//图例
                    data:['销量']
                },
                xAxis: {//x轴：数据项轴
                    data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                },
                yAxis: {},//y轴：数量轴
                series: [{//系列
                    name: '销量',//系列的名称
                    type: 'line',//系列的类型：bar--柱状图，line--折线图
                    data: [5, 20, 36, 10, 10, 20]//系列的数据
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        });
    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>
