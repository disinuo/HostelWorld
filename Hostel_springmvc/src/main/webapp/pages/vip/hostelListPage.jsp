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
<div class="big-container">
    <jsp:include page="component/selfPanel.jsp" flush="true"/>
    <%--<div class="table-responsive">--%>
        <table id="table" class="col-lg-9 table table-striped">
        </table>
    <%--</div>--%>
</div>



<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/hostelListPage.js"></script>
</body>
</html>
