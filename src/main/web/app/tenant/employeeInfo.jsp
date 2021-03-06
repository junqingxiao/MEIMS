<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/1
  Time: 下午2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
    <ol class="breadcrumb">
        <li><a href="#"><span class="glyphicon glyphicon-list"></span></a></li>
        <li class="active">员工信息</li>
    </ol>
</div><!--/.row-->

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">员工信息</h1>
    </div>
</div><!--/.row-->

<div class="row">
    <div class="col-md-8">
        <div class="panel panel-default chat">
            <div class="panel-heading" id="accordion">

                <span class="glyphicon glyphicon-list"></span> 员工<span  class="glyphicon glyphicon-plus pull-right" id="addIcon" onclick="addEmployeeCheck()"></span>
            </div>
            <div class="panel-body">
                <ul>
                    <div id="addDiv"></div>
                    <s:iterator value="list" status="st">
                        <li class="employeeLi" onclick="showRightArea(this)">
                            <div class="chat-body">
                                <div class="header" >
                                    <small class="text-muted" ><input class="employeeInput" type="text" value=<s:property value="name" /> readonly></small>
                                    <small class="text-muted"><input class="employeeInput" type="text" value=<s:property value="pName" /> readonly></small>
                                    <small class="text-muted"><input class="employeeInput" type="text" value=<s:property value="dName" /> readonly></small>
                                    <small class="text-muted"><input class="hidden" type="text" value=<s:property value="no" /> readonly></small>
                                    <div class="pull-right action-buttons">
                                        <a href="#" class="pencil"><span class="glyphicon glyphicon-pencil" onclick="updateEmployeeCheck(this)"></span></a>
                                        <a href="#" class="trash"><span  class="glyphicon glyphicon-trash"  onclick="deleteEmployee(this)"></span></a>
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