<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/6
  Time: 下午5:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="chat-body">
    <div class="header" >
        <small class="text-muted" ><input class="employeeInput" type="text" value=<s:property value="name" /> ></small>
        <small class="text-muted"><input class="employeeInput" type="text" value=<s:property value="pName" /> ></small>
        <small class="text-muted"><input class="employeeInput" type="text" value=<s:property value="dName" /> ></small>
        <small class="text-muted"><input class="hidden" type="text" value=<s:property value="no" /> readonly></small>
        <div class="pull-right action-buttons">
            <a href="#" class="warning"><span class="glyphicon glyphicon-exclamation-sign" title=<s:property value="message" />></span></a>
            <a href='#' class='pencil'><span class='glyphicon glyphicon-ok' onclick='updateEmployee(this)'></span></a>
            <a href='#' class='remove'><span class='glyphicon glyphicon-remove' onclick='employeeInfo()'></span></a>
            <a href="#" class="trash"><span  class="glyphicon glyphicon-trash"  onclick="deleteEmployee(this)"></span></a>

        </div>
    </div>
</div>
