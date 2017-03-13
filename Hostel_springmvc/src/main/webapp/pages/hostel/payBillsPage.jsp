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

客栈管理员你好
所有账单界面
<div class="big-container">
    <jsp:include page="component/sideBar.jsp" flush="true"/>
    <%--<div class="table-responsive">--%>
    <table id="table" class="col-lg-9 table table-striped">
    </table>
    <div>
        <h4>总收入</h4>
        <div id="income"></div>
    </div>
    <%--</div>--%>
</div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/hostel/payBillsPage.js"></script>

</body>
</html>
