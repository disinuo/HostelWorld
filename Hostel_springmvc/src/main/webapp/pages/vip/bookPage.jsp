<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/6
  Time: 01:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="../../css/bookPage.css"/>

</head>

<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

<div class="big-container ">
    <div class="col-lg-3 col-md-3 col-vip-self-panel">
        <jsp:include page="component/selfPanel.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 clo-md-9">
        <h1 id="hostelName"></h1>
        <div class="row">
            <div class="col-lg-6 clo-md-6">
                <img id="image" class="image-middle">
            </div>
            <div class=" roomInfo col-lg-5 col-md-5">
                <h3 id="roomName"></h3>
                <p id="descrip"></p>
                <h2><span id="roomPrice" class="label label-info label-large"></span>元</h2>
            </div>
        </div>
        <br>
        <div>
            <form id="form" >
                <div class="form-group">
                    <label class="col-md-2 control-label">入住日期</label>
                    <%--<p><input required='true' class="flatpickr flatpickr-input selector" id="flatpicker" type="text" placeholder="选择日期" readonly="readonly"></p>--%>
                    <div class="input-group " >
                        <input required='true' id="liveInDate" />
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-2 control-label">离店日期</label>
                    <div class="input-group date form_date col-md-5 myDatePicker" data-date="" data-date-format="" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                        <input required='true' id="checkOutDate" />
                    </div>
                    <input type="hidden" id="roomId" name="roomId" value="" /><br/>
                </div>
                <input type="submit" class="btn btn-primary" value="确认预订"/>
            </form>
        </div>

    </div>
    <div id="msg-book" class="msg alert alert-success " role="alert"></div>
</div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/vipTail.jsp"%>

<script type="text/javascript" src="../../js/vip/bookPage.js"></script>
</body>
</html>
