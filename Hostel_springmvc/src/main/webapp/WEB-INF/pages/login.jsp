<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Hostel|登录</title>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->

</head>

<body>
<form:form method="post" action="/" role="form">
    <table>
        <tr>
            <td><form:label path="userName">用户名</form:label></td>
            <td><form:input path="userName" value= "${userName}"/></td>
        </tr>
        <tr>
            <td><form:label path="password">密码</form:label></td>
            <td><form:password path="password" value= "${password}"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="登录"/>
            </td>
        </tr>
    </table>
</form:form>
<li><a href="/register" >注册</a></li>
<%--<!-- 新 Bootstrap 核心 CSS 文件 -->--%>
<%--<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
<%--<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->--%>
<%--<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>--%>

<%--<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->--%>
<%--<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
</body>
</html>
