<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/13
  Time: 06:11
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
<h3>积分兑换界面</h3>
<h5>您当前的积分</h5>
<div id="scoreOriginal"></div>
<h5>您当前的余额</h5>
<div id="moneyLeft"></div>

<div>
    <input id="score" type="text" placeholder="积分换钱啦！~~">
    <button id="converseBtn" class="btn btn-primary">兑换</button>
</div>
<div id="message"></div>
<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/convertScore.js"></script>

</body>
</html>
