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

<div class="big-container ">
    <div class="col-lg-3 col-md-3 col-vip-self-panel">
        <jsp:include page="component/selfPanel.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 clo-md-9">
        <div>
            <h3>尊敬的<em id="name"></em></h3>
            <h5>您的会员卡余额为：</h5>
            <div id="moneyLeft"></div>
        </div>
        <form  id="topUPForm">
            <label>请选择金额</label>
            <select id="money_select">
                <option>300</option>
                <option>50</option>
                <option>100</option>
                <option>200</option>
                <option>500</option>
                <option>1000</option>
                <option>2000</option>
                <option>3000</option>
                <option>5000</option>
            </select>
            <label >密码</label>
            <input type="password" id="bankPassword" required="true"/>
            <input class="btn btn-primary" type="submit" value="充值"/>
        </form>
    </div>
    <div id="msg" class="msg alert alert-success " role="alert"></div>
</div>




<%@include file="../common/tail.jsp" %>
<%@include file="component/vipTail.jsp"%>

<script type="text/javascript" src="../../js/vip/topUpPage.js"></script>

</body>
</html>
