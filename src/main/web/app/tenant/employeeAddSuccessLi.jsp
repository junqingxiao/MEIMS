<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/5
  Time: 下午3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="addDiv"></div>
<li class="employeeLi">
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

<script>
    employeeInfo();
    //showRightArea($("#addDiv").next().get(0));
</script>
