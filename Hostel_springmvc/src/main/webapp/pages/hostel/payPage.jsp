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
    <div class="component-box">
        <!-- Text fields example -->
        <div class="row">
            <div class="col-md-12">
                <div class="pmd-card pmd-z-depth pmd-card-custom-form">
                    <div class="pmd-card-body">
                        <form id="payForm">
                            <div class="form-group pmd-textfield pmd-textfield-floating-label">
                                <label for="vipId" class="control-label">会员编号</label>
                                <input type="text" id="vipId" name="vipId" class="form-control">
                                <p class="help-block">不是会员的话不用填</p>

                            </div>
                            <div class="form-group pmd-textfield pmd-textfield-floating-label">
                                <label for="money" class="control-label">金额</label>
                                <input id="money" name="money" type="number" class="form-control">
                            </div>

                            <input id="btnLogin"  class="btn btn-primary" type="submit" value="保存"/>
                        </form>
                        <div id="msg" class="alert alert-success" role="alert"></div>



                    </div>
                </div>
            </div>
        </div><!-- end Text fields example -->

    </div>
</div><!--end Text fields code, example -->
    <div class="top-padding-container col-lg-12 col-md-12 col-xs-12">

    </div>
</div>


<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>

<script type="text/javascript" src="../../js/hostel/payPage.js"></script>


</body>
</html>
