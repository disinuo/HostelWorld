<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/13
  Time: 06:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="../../css/modifyInfo.css"/>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>


<div class="big-container ">
    <div class="col-lg-3 col-md-3 col-vip-self-panel">
        <jsp:include page="component/selfPanel.jsp" flush="true"/>
    </div>
    <div class="col-lg-9 clo-md-9 middle">
        <form id="modifyInfoForm">
            <div class="input">
                <label>姓名</label>
                <input id="name" type="text" placeholder="输入真名哦~" required>
            </div>
            <div class="input">
                <label>身份证号</label>
                <input id="idCard" type="number" placeholder="住店用~" required>
            </div>
            <input type="submit" class="btn btn-primary" value="修改"/>
        </form>
    </div>
</div>
<div id="message" class="msg alert alert-success " role="alert"></div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/vipTail.jsp"%>
<script type="text/javascript" src="../../js/vip/modifyInfoPage.js"></script>

</body>
</html>
