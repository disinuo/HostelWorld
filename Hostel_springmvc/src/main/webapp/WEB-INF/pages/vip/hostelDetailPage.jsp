<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="component/navigation.jsp" %>

<jsp:include page="component/info.jsp" flush="true"/>
<jsp:include page="../common/roomList.jsp" flush="true"/>
</body>
</html>
