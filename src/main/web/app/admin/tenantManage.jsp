<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/3/27
  Time: 下午7:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
    <ol class="breadcrumb">
        <li><a href="#"><span class="glyphicon glyphicon-th"></span></a></li>
        <li class="active">租户管理</li>
    </ol>
</div><!--/.row-->

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">租户管理</h1>
    </div>
</div><!--/.row-->

<div class="row">
    <div class="col-md-8">
        <div class="panel panel-default chat">
            <div class="panel-heading" id="accordion">
                <span class="glyphicon glyphicon-list"></span> 租户<span  class="glyphicon glyphicon-plus pull-right" id="addIcon" onclick="addTenantCheck()"></span>
            </div>
            <div class="panel-body">
                <ul>
                    <div id="addDiv"></div>
                    <s:iterator value="list" status="st">
                    <li class="tenantLi" onclick="showRightArea(this)">
                        <div class="chat-body">
                            <div class="header" >
                                <strong class="primary-font" ><input class="tenantInput" type="text" value=<s:property value="name" /> readonly></strong>
                                <small class="text-muted"><input class="tenantInput" type="text" value=<s:property value="password" /> readonly></small>
                                <div class="pull-right action-buttons">
                                    <a href="#" class="pencil"><span class="glyphicon glyphicon-pencil" onclick="updateTenantCheck(this)"></span></a>
                                    <a href="#" class="trash"><span  class="glyphicon glyphicon-trash"  onclick="deleteTenant(this)"></span></a>
                                </div>
                            </div>
                        </div>
                    </li>
                    </s:iterator>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-md-4" id="rightArea">
    </div>
</div><!--/.col-->

<script>
    showRightArea($("#addDiv").next());
</script>
