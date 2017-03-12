<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="nju.edu.hostel.vo.output.OnLineUserVO"%>
<%@ page import="nju.edu.hostel.vo.output.VipVO" %>
<%@ page import="nju.edu.hostel.util.VIPState" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/vip_selfPanel.css"/>
    <title>selfInfo</title>
</head>
<body>
<div class="vip-self-container">
    <jsp:include page="info.jsp" flush="true"/>
    <button><a href="/vip/topUp">充值</a></button>
    <% VIPState stopState=VIPState.STOP; %>

    <c:if test="${!(vip.state eq stopSate)}">
            <button>停卡</button>
    </c:if>

</div>


</body>
</html>
