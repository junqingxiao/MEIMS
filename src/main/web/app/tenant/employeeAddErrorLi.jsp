<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/5
  Time: 下午3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="addDiv">
    <li>
        <div class="chat-body">
            <div class="header" >
                <small class="text-muted"><input class="employeeAddInput" type="text" placeholder="姓名"></small>
                <small class="text-muted"><input class="employeeAddInput" type="text" placeholder="职位"></small>
                <small class="text-muted"><input class="employeeAddInput" type="text" placeholder="部门"></small>
                <small class="text-muted"><input class="employeeAddInput" type="date" ></small>
                <div class="pull-right action-buttons">
                    <a href="#" class="warning"><span class="glyphicon glyphicon-exclamation-sign" title=<s:property value="message" />></span></a>
                    <a href='#' class='pencil'><span class='glyphicon glyphicon-ok' onclick='addEmployee(this)'></span></a>
                    <a href='#' class='remove'><span class='glyphicon glyphicon-remove' onclick='employeeInfo()'></span></a>
                </div>
            </div>
        </div>
    </li>
</div>
