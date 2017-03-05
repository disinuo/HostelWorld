<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/5
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="../../css/vip_selfPanel.css"/>
</head>
<body>
<div >
    <img class="vip-avatar" src="../../img/head.jpg" alt="头像">

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
