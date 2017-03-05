<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hostel|房间列表</title>
</head>
<body>
<c:if test="${empty rooms}">
    该客栈暂时没有可住的房间呢~
</c:if>
<c:if test="${!empty rooms}">
    <table>
        <thead>
        <tr>
            <td>房间名</td>
            <td>图片</td>
            <td>房间价格</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rooms}" var="room">
            <tr>
                <td>${room.name}</td>
                <td>${room.img}</td>
                <td>${room.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
