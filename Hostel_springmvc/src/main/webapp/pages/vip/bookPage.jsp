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
</head>

<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>
<jsp:include page="component/selfPanel.jsp" flush="true"/>
<h1 id="hostelName"></h1>
<div>
    <img id="image" class="image-little">
    <div id="roomName"></div>
    <div id="roomPrice"></div>
</div>
    <form method="post" action="/vip/book" >
        <div class="form-group">
            <label class="col-md-2 control-label">入住日期</label>
            <div class="input-group date form_date col-md-5 myDatePicker" data-date="" data-date-format="" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                <input required='true' name="liveInDate" class="form-control" size="16" type="text" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">离店日期</label>
            <div class="input-group date form_date col-md-5 myDatePicker" data-date="" data-date-format="" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                <input required='true' name="liveOutDate" class="form-control" size="16" type="text" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            <input type="hidden" id="roomId" name="roomId" value="" /><br/>
        </div>

        <input type="submit" class="btn btn-primary" value="确认预订"/>
    </form>




<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/bookPage.js"></script>
</body>
</html>
