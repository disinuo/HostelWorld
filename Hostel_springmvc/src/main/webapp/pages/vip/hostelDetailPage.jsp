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

<jsp:include page="component/selfPanel.jsp" flush="true"/>

<h1 id="name"></h1>
<span >
    地址:<span id="address"></span>
    电话:<span id="phone"></span>
</span>
<div class="table-responsive">
    <table id="table" class="table table-striped">
    </table>
</div>

<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/vip/hostelDetailPage.js"></script>

</body>
</html>
