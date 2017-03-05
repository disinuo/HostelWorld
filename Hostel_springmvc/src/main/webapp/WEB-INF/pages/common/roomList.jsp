<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 01:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hostel|所有房间</title>
</head>
<body>
这是所有房间！！！！！！！
<c:if test="${empty rooms}">
    暂时没有可浏览的房间呢~
</c:if>
<c:if test="${!empty rooms}">
    <table>
        <thead>
        <tr>
            <td>客栈名</td>
            <td>房间类型</td>
            <td>房间价格</td>
            <td>图片</td>
            <td>客栈地址</td>
            <td>客栈电话</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rooms}" var="room">
            <tr>
                <td>${room.hostel.name}</td>
                <td>${room.name}</td>
                <td>${room.price}</td>
                <td>${room.img}</td>
                <td>${room.hostel.address}</td>
                <td>${room.hostel.phone}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
