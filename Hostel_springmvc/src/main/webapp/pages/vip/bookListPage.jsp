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
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript" src="../../js/bookListPage.js"></script>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>
<jsp:include page="component/selfPanel.jsp" flush="true"/>

<h3>预订记录</h3>
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
                <td>预订日期</td>
            </tr>
        </thead>
        <tbody id="table">
        <c:forEach items="${bookBills}" var="bookBill">
            <tr>
            <td>${bookBill.hostel.name}</td>
            <td>${bookBill.room.name}</td>
            <td>${bookBill.room.price}</td>
            <td>${bookBill.liveInDateStr}</td>
            <td>${bookBill.createDateStr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
${message}
</c:if>
</body>
</html>
