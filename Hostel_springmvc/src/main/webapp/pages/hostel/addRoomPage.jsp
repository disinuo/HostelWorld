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
    <div class="top-padding-container col-lg-12 col-md-12 col-xs-12">
        <form id="addRoomForm">

            <div class="input-group input-group-sm">
                <span class="input-group-addon">房型</span>
                <input id="name" name="name" type="text" class="form-control" aria-describedby="sizing-addon3"required>
            </div>
            <div class="input-group input-group-sm">
                <span class="input-group-addon">房价</span>
                <input id="price" name="price" type="number" class="form-control" placeholder="" aria-describedby="sizing-addon3" required>
            </div>

            <div class="input-group input-group-sm">
                <span class="input-group-addon">房间数</span>
                <input id="totalNum" name="totalNum" type="number" class="form-control" placeholder="" aria-describedby="sizing-addon3" required>
            </div>
            <div class="input-group input-group-sm">
                <span class="input-group-addon">房间容纳最大人数</span>
                <input id="capacity" name="capacity" type="number" class="form-control" placeholder="" aria-describedby="sizing-addon3" required>
            </div>


            <div class="input-group input-group-sm">
                <span class="input-group-addon">房间上市日期</span>
                <input id="startDate"/>
            </div>
            <div class="input-group input-group-sm">
                <span class="input-group-addon">房间下市日期</span>
                <input id="endDate"/>
            </div>


            <div class="input-group input-group-sm">
                <span class="input-group-addon">展示图</span>
                <input id="img" type="file" name="file" >
            </div>

            <div class="input-group input-group-sm">
                <span class="input-group-addon">描述</span>
                <textarea id="descrip" name="descrip" ></textarea>
            </div>
            <input id="btnLogin"  class="btn btn-primary" type="submit" value="发布"/>
        </form>
        <div id="msg" class="alert alert-success" role="alert"></div>
    </div>
</div>

<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>
<script type="text/javascript" src="../../js/hostel/addRoomPage.js"></script>
</body>
</html>
