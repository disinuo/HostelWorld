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
    <div class="top-padding-container col-lg-12 col-md-12 col-xs-12">
    <div class="flex-container">
            <button id="btn_month" class="btn btn-primary">最近30天</button>
            <button id="btn_week" class="btn btn-primary">最近7天</button>
            <button id="btn_year" class="btn btn-primary">今年</button>
            <button id="btn_all" class="btn btn-primary">所有</button>
        </div>
        <table id="table" class="col-lg-9 table table-striped">
        </table>
        <div id="container" style="height: 500px; min-width: 310px; max-width: 600px; margin: 0 auto"></div>
    </div>
</div>

<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>

<script type="text/javascript" src="../../js/hostel/bookBillsPage.js"></script>


</body>
</html>
