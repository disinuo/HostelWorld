<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="nju.edu.hostel.vo.OnLineUserVO"%>
<%@ page import="nju.edu.hostel.vo.VipVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/vip_info.css"/>
    <title>selfInfo</title>
</head>
<body>
<div class="container">
<img class="avatar" src="../../img/head.jpg" alt="头像">

<%--${onlineUser.id}--%>
<%--${onlineUser.userName}--%>
    <table>
        <tbody>
        <tr><td>用户名：</td><td>${vip.realName}</td></tr>
        <tr><td>卡余额：</td><td>${vip.moneyLeft}元</td></tr>
        <tr><td>等级：</td><td>${vip.level}</td></tr>
        <tr><td>积分：</td><td>${vip.score}</td></tr>
        <tr><td>已消费：</td><td>${vip.moneyPaid}元</td></tr>
        <tr><td>状态：</td><td>${vip.state.toChineseString()}</td></tr>
        </tbody>
    </table>

</div>

</body>
</html>
