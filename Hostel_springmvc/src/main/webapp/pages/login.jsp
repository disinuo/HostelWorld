<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="./common/head.jsp" %>
    <link>
</head>
<body>
<%@include file="common/header.jsp" %>
<form action="/login" method="POST">
    <div>
        <label for="userName">用户名</label>
        <input required='true' id='userName' name='userName' value= "${userName}"/>
        <span id="nameMsg" class="resultMessage"></span>
    </div>
    <div>
        <label for="password">密码</label>
        <input type="password" required='true' id='password' name="password" value= "${password}"/>
        <span id="passwordMsg" class="resultMessage"></span>
    </div>
    <input id="btnLogin" type="submit" value="登录"/>
</form>

<li><a href="/register" >去注册</a></li>

<%--<!-- 新 Bootstrap 核心 CSS 文件 -->--%>
<%--<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
<%--<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->--%>
<%--<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>--%>

<%--<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->--%>
<%--<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
<%@include file="./common/tail.jsp" %>
<script type="text/javascript" src="../js/login.js"></script>
</body>
</html>
