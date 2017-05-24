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
    <a href="/hostel/modifyInfo"><i class="glyphicon glyphicon-pencil"></i></a>
    <div class="hostel-info">
        <img class="avatar" id="hostel_info_img" >
        <form id="openRequestForm">
            <input type="submit" class="btn btn-primary" value="申请开店">
        </form>
        <div  class="vip-self-li">
            <span id="hostel_info_descrip"></span>
        </div>
        <div  class="vip-self-li">
            <i class="glyphicon glyphicon-asterisk"></i>
            编号：<span id="hostel_info_id"></span>
        </div>
        <div  class="vip-self-li">
            <i class="glyphicon glyphicon-heart-empty"></i>
            店名：<span id="hostel_info_name"></span>
        </div>
        <div  class="vip-self-li">
            <i class="glyphicon glyphicon-map-marker"></i>
            城市：<span id="hostel_info_city"></span>
        </div>
        <div  class="vip-self-li">
            <i class="glyphicon glyphicon-map-marker"></i>
            地址：<span id="hostel_info_address"></span>
        </div>
        <div  class="vip-self-li">
            <i class="glyphicon glyphicon-earphone"></i>
            电话：<span id="hostel_info_phone"></span>
        </div>
        <div  class="vip-self-li">
            <i class="glyphicon glyphicon-yen"></i>
            银行卡余额：<span id="hostel_info_money"></span>元
        </div>
    </div>
    <div  class="vip-self-li">
        <a type="button" class="btn btn-primary" href="/hostel/liveIn">住店</a>
        <a type="button" class="btn btn-primary" href="/hostel/checkOut">离店</a><br><br>
        <a type="button" class="btn btn-primary" href="/hostel/enrollPay">记账</a>
        <a type="button" class="btn btn-primary" href="/hostel/pay">付款</a><br><br>
        <a type="button" class="btn btn-warning" href="/hostel/addRoom">发布房间计划</a>
    </div>
</div>