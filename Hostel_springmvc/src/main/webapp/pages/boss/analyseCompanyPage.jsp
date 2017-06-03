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

    <div class="col-lg-12 col-md-12 col-xs-12">
        <div id="summaryChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>

    </div>

</div>

<%--</div>--%>

<%@include file="../common/tail.jsp" %>

<%--<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>--%>

<script type="text/javascript" src="../../js/manager/analyseCompanyPage.js"></script>
</body>
</html>
