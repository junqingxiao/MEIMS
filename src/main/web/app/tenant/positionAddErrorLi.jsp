<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/14
  Time: 下午8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
    <div class="panel note">
        <div class="panel-heading note-main"><input class="departmentInput" type="text" placeholder="新职位"></div>
        <div class="panel-heading note-sub"><input class="departmentInput" type="text" placeholder="新工资"></div>
        <div class="panel-heading note-main addDOkIcon"  onclick="addPosition(this)">确定</div>
        <div class="panel-heading note-sub addDOkIcon" onclick="departmentInfo()">取消</div>
        <div class="add-department-error-div"><span class="glyphicon glyphicon-exclamation-sign" title=
                <s:property value="message"/>></span>
        </div>
    </div>
</div>
