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
    <div class="col-lg-3 col-md-3">
        <%@include file="component/sideBar.jsp" %>
    </div>
    <div class="col-lg-9 col-md-9">
        <h3>开店申请</h3>
        <table id="requestOpenTable" class="col-lg-9 table table-striped">
        </table>
    </div>

</div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/manager/requestPage.js"></script>


</body>
</html>