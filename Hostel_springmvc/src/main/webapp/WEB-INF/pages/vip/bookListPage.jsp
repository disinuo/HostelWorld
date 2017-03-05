<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 01:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VIP|预订记录</title>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

Hello 亲爱的VIP<br>
这是预订记录~~~~
<jsp:include page="component/selfPanel.jsp" flush="true"/>
<c:if test="${empty bookBills}">
    没有预订记录
</c:if>
<!-- 如果预订列表非空 -->
<c:if test="${!empty bookBills}">
    <table>
        <thead>
            <tr>
                <td>客栈名</td>
                <td>房间类型</td>
                <td>房间价格</td>
                <td>入住日期</td>
                <td>下单日期</td>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookBills}" var="bookBill">
            <tr>
            <td>${bookBill.hostel.name}</td>
            <td>${bookBill.room.name}</td>
            <td>${bookBill.room.price}</td>
            <td>${bookBill.liveInDate}</td>
            <td>${bookBill.createDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>
</body>
</html>
