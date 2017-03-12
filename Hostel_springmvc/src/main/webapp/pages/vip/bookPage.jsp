<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/6
  Time: 01:47
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
<h1 id="hostelName"></h1>
<div>
    <img id="image" class="image-little">
    <div id="roomName"></div>
    <div id="roomPrice"></div>
</div>
    <form:form commandName="bookBill" method="post" action="/vip/book" >
        <label >入住日期</label>
        <form:input path="liveInDate"/>
        <input type="submit" value="确认预订"/>
    </form:form>

<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/bookPage.js"></script>
</body>
</html>
