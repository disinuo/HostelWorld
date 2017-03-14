<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">HostelWorld | 总经理</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/boss/analyse/company">公司情况</a></li>
                <li><a href="/boss/analyse/hostel">客栈情况</a></li>
                <li><a href="/boss/analyse/vip">会员情况</a></li>
                <li><a href="/boss/count">结算</a></li>
                <li><a href="/boss/checkRequestOpen">查看申请</a></li>
                <li><a class="btn btn-primary" href="/logout">登出</a></li>
            </ul>
        </div>
    </div>
</nav>
