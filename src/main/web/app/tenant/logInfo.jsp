<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/20
  Time: 下午3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
    <ol class="breadcrumb">
        <li><a href="#"><span class="glyphicon glyphicon-book"></span></a></li>
        <li class="active">日志</li>
    </ol>
</div>
<!--/.row-->

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">日志</h1>
    </div>
</div>
<!--/.row-->

<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading" id="accordion">
            <span><span class="glyphicon glyphicon-book"></span> <s:property value="dateString" /> </span>
            <span><span class="glyphicon glyphicon-search pull-right" id="dateIcon" onclick="showNewDayLog()"></span><input type="date" class="pull-right" id="dateInput" ></span>
        </div>
        <div class="panel-body log pre-scrollable">
            <s:iterator value="text" status="st">
                <s:property/><br>
            </s:iterator>
        </div>
    </div>
</div>
