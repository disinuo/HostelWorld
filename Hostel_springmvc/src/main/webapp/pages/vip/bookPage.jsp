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
<div>
    <li>${room.name}</li>
    <li>${room.img}</li>
    <li>${room.price}元</li>
</div>
    <form:form commandName="bookBill" method="post" action="/vip/book" >
        <label >入住日期</label>
        <form:input path="liveInDate"/>
        <input type="submit" value="确认预订"/>
    </form:form>
</body>
</html>
