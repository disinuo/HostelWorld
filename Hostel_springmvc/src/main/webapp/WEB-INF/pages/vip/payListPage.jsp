<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="navigation.jsp" %>

Hello 亲爱的VIP
<jsp:include page="info.jsp" flush="true"/>
这是您的所有消费记录
</body>
</html>
