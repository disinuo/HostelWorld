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
    <title>Hostel|注册</title>
</head>
<body>
<%@include file="./common/header.jsp" %>
<form:form method="post" action="/register" role="form">
    <table>
        <tr>
            <td><form:label path="userName">用户名</form:label></td>
            <td><form:input path="userName" /></td>
        </tr>
        <tr>
            <td><form:label path="password">密码</form:label></td>
            <td><form:password path="password" /></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="注册"/>
            </td>
        </tr>
    </table>
</form:form>
<li><a href="/login" >登录</a></li>

</body>
</html>
