<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VIP|住房记录</title>
</head>
<body>
<%@include file="navigation.jsp" %>

Hello 亲爱的VIP<br>
这是住房记录
<jsp:include page="info.jsp" flush="true"/>
<c:if test="${empty liveBills}">
    没有住房记录
</c:if>
<!-- 如果住房列表非空 -->
<c:if test="${!empty liveBills}">
    <table>
        <thead>
        <tr>
            <td>类型</td>
            <td>客栈名</td>
            <td>房间类型</td>
            <td>入住日期</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${liveBills}" var="liveBill">
            <tr>
                <td><c:if test="${liveBill.type}">
                    入店
                </c:if>
                    <c:if test="!${liveBill.type}">
                        离店
                    </c:if>
                </td>
                <td>${liveBill.hostel.name}</td>
                <td>${liveBill.room.name}</td>
                <td>${liveBill.date}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>
</body>
</html>
