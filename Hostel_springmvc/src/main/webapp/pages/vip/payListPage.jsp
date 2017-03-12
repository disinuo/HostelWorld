<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../common/head.jsp" %>

<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

Hello 亲爱的VIP
<jsp:include page="component/selfPanel.jsp" flush="true"/>
这是您的所有消费记录
<c:if test="${empty payBills}">
    没有消费记录
</c:if>
<!-- 如果预订列表非空 -->
<c:if test="${!empty payBills}">
    <table>
        <thead>
        <tr>
            <td>客栈名</td>
            <td>房间类型</td>
            <td>房间价格</td>
            <td>消费日期</td>
            <td>价格</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${payBills}" var="payBill">
            <tr>
                <td>${payBill.hostel.name}</td>
                <td>${payBill.room.name}</td>
                <td>${payBill.room.price}</td>
                <td>${payBill.createDateStr}</td>
                <td>${payBill.money}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>
</body>
</html>
