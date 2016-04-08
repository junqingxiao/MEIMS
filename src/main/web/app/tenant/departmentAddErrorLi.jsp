<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/8
  Time: 下午9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="addDDiv" class="col-md-4">
    <div class="panel panel-info">
        <div class="panel-heading dark-overlay">
            <input class="departmentInput" type="text" placeholder="新部门">
        </div>
        <div class="panel-body pre-scrollable note-body">
            <div class="panel note">
                <div class="panel-heading note-main" id="addDOkIcon" onclick="addDepartment(this)">确定</div><div class="panel-heading note-sub" id="addDCancelIcon" onclick="departmentInfo()">取消</div>
                <div class="add-department-error-div"><span class="glyphicon glyphicon-exclamation-sign" title=<s:property value="message" />></span><div>
            </div>
        </div>
    </div>
</div>
