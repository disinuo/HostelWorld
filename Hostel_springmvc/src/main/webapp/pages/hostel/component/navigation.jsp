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
                <span class="sr-only">Toggle navigation </span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">HostelWorld | 客栈</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>${valid}</li>
                <li class="active"><a href="/hostel/rooms">所有房间</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        预订记录 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/hostel/bookBills">详情</a></li>
                        <li> <a href="/hostel/bookBills/analyze">统计</a></li>
                    </ul>
                </li>
                <li><a href="/hostel/payBills">财政收入</a></li>
                <li><a href="/hostel/liveBills">住房记录</a></li>
                <li><a href="/hostel/info">基本信息</a></li>

                <li><a class="btn btn-primary" href="/logout">登出</a></li>
            </ul>
        </div>
    </div>
</nav>
