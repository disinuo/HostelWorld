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


<div class="big-container ">
    <div class="col-lg-3 col-md-3 col-vip-self-panel">
        <jsp:include page="component/selfPanel.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 clo-md-9">
        <h5>您当前的积分</h5>
        <div id="scoreOriginal"></div>
        <h5>您当前的余额</h5>
        <div id="moneyLeft"></div>
        <h5>积分转换公式</h5>
        <div id="scoreToMoneyRule"></div>

        <form id="convertScoreForm">
            <input id="score" type="number" placeholder="积分换钱啦！~~" required>
            <input type="submit" class="btn btn-primary" value="兑换"/>
        </form>
    </div>
</div>
<div id="message" class="msg alert alert-success " role="alert"></div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/vipTail.jsp"%>

<script type="text/javascript" src="../../js/vip/convertScore.js"></script>

</body>
</html>
