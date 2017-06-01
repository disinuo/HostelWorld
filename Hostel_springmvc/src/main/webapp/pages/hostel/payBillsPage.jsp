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

    <div class="col-lg-10 col-md-10 col-xs-10">

        <h2>总收入：<span id="value" class="label label-success"></span>
        </h2>
        <div class="flex-container">
            <button id="btn_month" class="btn btn-primary">最近30天</button>
            <button id="btn_week" class="btn btn-primary">最近7天</button>
            <button id="btn_year" class="btn btn-primary">今年</button>
            <button id="btn_all" class="btn btn-primary">所有</button>
            <button id="btn_unCounted" class="btn btn-primary">未结算的</button>
        </div>
        <table id="table" class="col-lg-9 table table-striped">
        </table>
    </div>
</div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>
<script type="text/javascript" src="../../js/hostel/payBillsPage.js"></script>

</body>
</html>
