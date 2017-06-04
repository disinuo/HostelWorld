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


<div class="big-container ">
    <div class="col-lg-2 col-md-2 col-xs-2">
        <h4>入住情况</h4>
        <ul class="nav nav-pills nav-stacked">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    时间 <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li id="year"><a href="#">近3年</a></li>
                    <li id="month"><a href="#">月</a></li>
                    <li id="week"><a href="#">周</a></li>
                    <li id="day"><a href="#">时段</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    房间 <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li id="roomType"><a href="#roomType">房型</a></li>
                    <li id="roomPrice"><a href="#">房价</a></li>
                </ul>
            </li>

            <li id="guestType"><a href="#">住户类型</a></li>
            <li id="guestAge"><a href="#">住户年龄</a></li>

        </ul>
    </div>


    <div class="dark-container col-lg-10 col-md-10 col-xs-10">

        <div id="dateChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
        <div id="weekChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
        <div id="dayChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
        <div id="pieChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
        <div id="mapChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
    </div>
</div>

<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>

<script type="text/javascript" src="../../js/hostel/analyzeLiveInBillPage.js"></script>


</body>
</html>
