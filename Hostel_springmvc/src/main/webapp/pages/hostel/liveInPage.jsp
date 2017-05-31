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

<div class="big-container">
    <div class="col-lg-3 col-md-3 col-xs-4">
    <jsp:include page="sideBar.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 col-md-9 col-xs-8">
        <form id="liveInForm">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">预订单号</span>
                <input id="bookBillId" name="bookBillId" type="number" class="form-control" placeholder="预订单号" aria-describedby="sizing-addon3" required>
            </div>
            <div class="input-group input-group-sm">
                <span class="input-group-addon">房间编号</span>
                <input id="roomId" name="roomId" type="number" class="form-control" placeholder="" aria-describedby="sizing-addon3" required>
            </div>
            <h3>顾客信息   <div id="addBtn" class="btn btn-primary btn-toolbar">+</div></h3>

            <div id="guests">
                <div class="guest panel panel-default">
                    <div class="panel-heading">顾客</div>
                    <div class="panel-body">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">姓名</span>
                            <input name="userRealName" type="text" class="userName form-control" placeholder="真实的名字~" aria-describedby="sizing-addon3" required>
                        </div>
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">身份证号</span>
                            <input name="idCard" type="number" class="idCard form-control" placeholder="18位哦" aria-describedby="sizing-addon3" required>
                        </div>
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">会员编号</span>
                            <input name="vipId" type="number" class="vipId form-control" placeholder="不是会员的话不用填" aria-describedby="sizing-addon3">
                        </div>
                    </div>
                </div>
            </div>
            <input id="btnLogin"  class="btn btn-primary" type="submit" value="保存"/>
        </form>
        <div id="msg" class="alert alert-success" role="alert"></div>
    </div>
</div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>

<script type="text/javascript" src="../../js/hostel/liveInPage.js"></script>


</body>
</html>
