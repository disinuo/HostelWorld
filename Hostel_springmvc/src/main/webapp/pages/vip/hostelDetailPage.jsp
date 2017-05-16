<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 21:33
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
        <h1 id="hostelName"></h1>
        <span >
            地址:<span id="address"></span>
            电话:<span id="phone"></span>
        </span>
        <table id="table" class="table table-striped">
        </table>
    </div>
    <div id="msg" class="msg alert alert-success " role="alert"></div>
</div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/vipTail.jsp"%>

<script type="text/javascript" src="../../js/vip/hostelDetailPage.js"></script>

</body>
</html>
