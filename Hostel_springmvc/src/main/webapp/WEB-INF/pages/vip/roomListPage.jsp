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
    <title>Hostel|VIP</title>
</head>
<body>
<%@include file="navigation.jsp" %>

Hello 亲爱的VIP
<jsp:include page="info.jsp" flush="true"/>

<jsp:include page="../common/roomList.jsp" flush="true"/>
</body>
</html>
