<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <%@include file="../common/head.jsp" %>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

<div>
    <h3>尊敬的<em id="name"></em></h3>
    <h5>您的会员卡余额为：</h5>
    <div id="moneyLeft"></div>
</div>
<form  action="/vip/topUp" method="post">
    <label>请选择金额</label>
    <select id="money_select">
        <option>50</option>
        <option>100</option>
        <option>200</option>
        <option>300</option>
        <option>500</option>
        <option>1000</option>
    </select>
    <input type="hidden" id="money" name="money" value="50" />
    <label >密码</label>
    <input type="password" name="bankPassword" required="true"/>

    <input type="submit" value="充值"/>
</form>
<div>${message}</div>
<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/topUpPage.js"></script>
<jsp:include page="component/selfPanel.jsp" flush="true"/>

</body>
</html>
