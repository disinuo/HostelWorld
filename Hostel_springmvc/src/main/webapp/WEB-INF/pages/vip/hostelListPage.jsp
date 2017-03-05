<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 01:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VIP|所有客栈</title>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

Hello 亲爱的VIP
<jsp:include page="component/selfPanel.jsp" flush="true"/>

<jsp:include page="../common/hostelList.jsp" flush="true"/>
</body>
</html>
