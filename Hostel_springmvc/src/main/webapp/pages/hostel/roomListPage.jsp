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
<jsp:include page="component/navigation.jsp" ><jsp:param name="valid" value='呵呵哈哈哈' /></jsp:include>


<div class="big-container ">
    <div class="col-lg-3 col-md-3 col-xs-2">
        <jsp:include page="component/sideBar.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 col-md-9 col-xs-10">
        <table id="table" class="col-lg-9 table table-striped">
        </table>
    </div>
</div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>

<script type="text/javascript" src="../../js/hostel/roomListPage.js"></script>


</body>
</html>
