<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/25
  Time: 下午3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
    <ol class="breadcrumb">
        <li><a href="#"><span class="glyphicon glyphicon-tags"></span></a></li>
        <li class="active">注册审核</li>
    </ol>
</div><!--/.row-->

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">注册审核</h1>
    </div>
</div><!--/.row-->

<div class="row">
    <div class="col-lg-12">
        <s:iterator value="list" status="st">
            <div class="alert bg-register" role="alert">
                <span class="glyphicon glyphicon-info-edit"></span><span class="register-span"><s:property value="name" /></span><span class="register-span"><s:property value="password" /></span><a href="#" class="pull-right register-icon"><span class="glyphicon glyphicon-remove" onclick="registerRemove(this)"></span></a><a href='#' class='pull-right register-icon'><span class='glyphicon glyphicon-ok' onclick='registerOk(this)'></span></a>
            </div>
        </s:iterator>
    </div>
</div>
