<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 09:55
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

<h3>住店记录</h3>
<div class="table-responsive">
    <table id="table" class="table table-striped">
    </table>
</div>

<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/liveListPage.js"></script>
</body>
</html>
