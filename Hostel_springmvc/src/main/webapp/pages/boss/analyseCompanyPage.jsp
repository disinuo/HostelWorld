<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 01:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/analyzePage.css"/>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

总经理你好！
这是查看整个公司的统计数据界面
<%--<div class="big-container">--%>
<div class="big-container">
    <div class="col-lg-6 col-md-6 chart-container">
        <h2>收入情况</h2>
        <div id="income"  ></div>
    </div>
    <div class="col-lg-6 col-md-6 chart-container">
        <h2>入住情况</h2>
        <div id="liveIn" ></div>
    </div>

</div>

<%--</div>--%>

<%@include file="../common/tail.jsp" %>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<%--<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>--%>

<script type="text/javascript" src="../../js/manager/analyseCompanyPage.js"></script>
</body>
</html>
