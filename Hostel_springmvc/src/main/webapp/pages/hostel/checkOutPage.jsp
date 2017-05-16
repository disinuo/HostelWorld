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
    <div class="col-lg-3 col-md-3 ">
    <jsp:include page="component/sideBar.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 clo-md-9">
         <form id="checkOutForm">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">住户姓名</span>
                <input id="liveInId" name="liveInId" type="number" class="form-control" placeholder="住店记录id~ 这样的交互是暂时哒！" aria-describedby="sizing-addon3" required>
            </div>

            <input class="btn btn-primary" type="submit" value="保存"/>
        </form>
        <div id="msg" class="alert alert-success" role="alert"></div>
    </div>
</div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>

<script type="text/javascript" src="../../js/hostel/checkOutPage.js"></script>


</body>
</html>
