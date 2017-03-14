<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 01:25
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

客栈管理员你好
支付界面
就只输入vipid（可选）和金额


<div class="big-container">
    <div class="col-lg-3 col-md-3 ">
        <jsp:include page="component/sideBar.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 clo-md-9">
        <form id="payForm">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">会员编号</span>
                <input id="vipId" name="vipId" type="text" class="form-control" placeholder="不是会员的话不用填" aria-describedby="sizing-addon3">
            </div>

            <div class="input-group input-group-sm">
                <span class="input-group-addon">金额</span>
                <input id="money" name="money" type="number" class="form-control" placeholder="" aria-describedby="sizing-addon3" required>
            </div>
            <input id="btnLogin"  class="btn btn-primary" type="submit" value="保存"/>
        </form>
        <div id="msg" class="alert alert-success" role="alert"></div>
    </div>
</div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>

<script type="text/javascript" src="../../js/hostel/payPage.js"></script>


</body>
</html>
