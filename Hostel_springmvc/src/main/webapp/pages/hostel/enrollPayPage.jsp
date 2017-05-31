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

<div class="big-container">
    <div class="col-lg-3 col-md-3 ">
        <jsp:include page="sideBar.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 clo-md-9">
        <form id="enrollPayForm">
            未付款的顾客
            <select id="liveInId">

            </select>
            <input id="btnLogin"  class="btn btn-primary" type="submit" value="保存"/>
        </form>
        <div id="msg" class="alert alert-success" role="alert"></div>
    </div>
</div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>

<script type="text/javascript" src="../../js/hostel/enrollPayPage.js"></script>


</body>
</html>
