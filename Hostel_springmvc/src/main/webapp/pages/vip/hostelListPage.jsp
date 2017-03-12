<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 01:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="../css/hostelList.css"/>
</head>


<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>
<jsp:include page="component/selfPanel.jsp" flush="true"/>

<h1>所有客栈</h1>
<div class="table-responsive">
    <table id="table" class="table table-striped">
    </table>
</div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/hostelListPage.js"></script>
</body>
</html>
