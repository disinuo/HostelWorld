<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="./common/head.jsp" %>
<body>
<%@include file="common/header.jsp" %>
<form:form method="post" action="/login" commandName="user" role="form">
    <div>
        <form:label path="userName">用户名</form:label>
        <form:input required='true' path="userName" value= "${userName}"/>
    </div>
    <div>
        <form:label path="password">密码</form:label>
        <form:password required='true'  path="password" value= "${password}"/>
    </div>
    <input type="submit" value="登录"/>
</form:form>
<li><a href="/register" >去注册</a></li>
<div id="message">${message}</div>

<%--<!-- 新 Bootstrap 核心 CSS 文件 -->--%>
<%--<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
<%--<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->--%>
<%--<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>--%>

<%--<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->--%>
<%--<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
<script type="text/javascript" src="../js/login.js"></script>
</body>
</html>
