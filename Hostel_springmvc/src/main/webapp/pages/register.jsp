<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="./common/head.jsp" %>
</head>
<body>
<%@include file="common/header.jsp" %>
<form:form method="post" commandName="user" action="/register" role="form">
    <div>
        <form:label path="userName">用户名</form:label>
        <form:input required='true' path="userName" value= "${userName}"/>
    </div>
    <div>
        <form:label path="password">密码</form:label>
        <form:password required='true'  path="password" value= "${password}"/>
    </div>
    <input type="submit" value="注册"/>
</form:form>
<li><a href="/login" >去登录</a></li>
<div id="message">${message}</div>
<%@include file="./common/tail.jsp" %>
</body>
</html>
