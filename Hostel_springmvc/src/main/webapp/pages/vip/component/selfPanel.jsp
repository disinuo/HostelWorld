<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="../../css/vip_selfPanel.css"/>
<div class="self-info-container">
    <img class="vip-avatar" src="/../img/head.jpg" alt="头像">
    <a href="/vip/modify"><i class="glyphicon glyphicon-pencil"></i></a>
    <div class="vip-self-li">
        <i class="glyphicon glyphicon-user"></i>名字：<span id="vip_info_name"></span>
    </div>
    <div class="vip-self-li">
        <i class="glyphicon glyphicon-yen"></i>余额：<span id="vip_info_moneyLeft"></span>元
    </div>
    <div class="vip-self-li">
        <i class="glyphicon glyphicon-pawn"></i>等级：<span id="vip_info_level"></span>
    </div>
    <div class="vip-self-li">
        <i class="glyphicon glyphicon-star"></i>积分：<span id="vip_info_score"></span>
    </div>
    <div class="vip-self-li">
        <i class="glyphicon glyphicon-yen"></i>已消费：<span id="vip_info_moneyPaid"></span>元
    </div>
    <div class="vip-self-li">
        <i class="glyphicon glyphicon-leaf"></i>卡状态：<span id="vip_info_state"></span>
    </div>
    <%--${onlineUser.id}--%>
    <%--${onlineUser.userName}--%>


    <button class="btn btn-default"><a href="/vip/topUp">充值</a></button>
    <button class="btn btn-default"><a href="/vip/convert">积分换钱</a></button><br><br><br>
    <button class="btn btn-default" id="selfPanel_stopCardBtn">停卡</button>

    <%--<a class="close" data-dismiss="alert" href="#">&times;</a>--%>
</div>
