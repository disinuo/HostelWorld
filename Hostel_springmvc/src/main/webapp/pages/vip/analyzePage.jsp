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
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>


<div class="big-container ">

    <div class="dark-container col-lg-12 col-md-12 col-xs-12">
        <%--<div id="container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>--%>
        <div id="dateChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
        <div id="pieChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
        <div id="mapChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
    </div>
</div>

<%@include file="../common/tail.jsp" %>
<%@include file="component/vipTail.jsp"%>

<script type="text/javascript" src="../../js/vip/analyzePage.js"></script>


</body>
</html>
