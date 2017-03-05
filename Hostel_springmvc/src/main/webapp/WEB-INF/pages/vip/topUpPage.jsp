<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VIP|消费记录</title>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

Hello 亲爱的VIP
<jsp:include page="component/selfPanel.jsp" flush="true"/>
<div>
    <h3>您的余额为：</h3>
    <p>${vip.moneyLeft}</p>
</div>
<form:form commandName="topUp" action="/vip/topUp" method="post">
    <form:label path="money">金额</form:label>
    <form:input path="money"></form:input>
    <form:label path="bankPassword">密码</form:label>
    <form:password path="bankPassword" required=""></form:password>
    <input type="submit" value="充值"/>
</form:form>
<div>${message}</div>
</body>
</html>
