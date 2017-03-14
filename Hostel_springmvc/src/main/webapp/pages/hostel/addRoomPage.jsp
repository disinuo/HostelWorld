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

新增房间界面

<div class="big-container">
    <div class="col-lg-3 col-md-3 ">
        <jsp:include page="component/sideBar.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 clo-md-9">
        <form id="addRoomForm">

            <div class="input-group input-group-sm">
                <span class="input-group-addon">房型</span>
                <input id="name" name="name" type="text" class="form-control" aria-describedby="sizing-addon3"required>
            </div>

            <div class="input-group input-group-sm">
                <span class="input-group-addon">房价</span>
                <input id="price" name="price" type="text" class="form-control" placeholder="" aria-describedby="sizing-addon3" required>
            </div>
            <div class="input-group input-group-sm">
                <span class="input-group-addon">展示图</span>
                <input id="img" type="file" name="file" >
            </div>
            <input id="btnLogin"  class="btn btn-primary" type="submit" value="保存"/>
        </form>
        <div id="msg" class="alert alert-success" role="alert"></div>
    </div>
</div>

<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>
<script type="text/javascript" src="../../js/hostel/addRoomPage.js"></script>
</body>
</html>
