<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Hostel|所有客栈</title>
</head>
<body>
这是所有客栈！！！！！！！
<c:if test="${empty hostels}">
    暂时没有可浏览的客栈呢~
</c:if>
<c:if test="${!empty hostels}">
    <table>
        <thead>
        <tr>
            <td>客栈名</td>
            <td>图片</td>
            <td>客栈地址</td>
            <td>客栈电话</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${hostels}" var="hostel">
            <tr>
                <td><a href="/hostel/rooms?hostelId=${hostel.id}">${hostel.name}</a></td>
                <td>${hostel.img}</td>
                <td>${hostel.address}</td>
                <td>${hostel.phone}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
