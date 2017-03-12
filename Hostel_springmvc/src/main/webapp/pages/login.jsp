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
    <%--<div>--%>
        <%--<label for="userName">用户名</label>--%>
        <%--<input required='true' id='userName' name='userName' value= "${userName}"/>--%>
    <%--</div>--%>
    <div>
        <span class="input-group col-lg-3">
            <span class="input-group-addon glyphicon glyphicon-user"></span>
            <input required='true' id='userName' name='userName' type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1">
        </span>
        <span id="nameMsg" class="help-inline"></span>

    </div>

    <div>
        <span class="input-group col-lg-3">
            <span class="input-group-addon glyphicon glyphicon-lock"></span>
            <input required='true' id='password' name='password' type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon1">
        </span>
        <span id="passwordMsg" class="help-inline"></span>

    </div>

    <input id="btnLogin"  class="btn btn-primary" type="submit" value="登录"/>
</form>

<a class="btn btn-default" href="/register" >去注册</a>

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
