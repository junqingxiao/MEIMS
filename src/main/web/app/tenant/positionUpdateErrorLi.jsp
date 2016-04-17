<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/17
  Time: 下午2:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="panel-heading note-main"><input class="departmentInput" type="text" value=<s:property value="oldPName"/>>
</div>
<div class="panel-heading note-sub"><input class="departmentInput" type="text" value=<s:property value="salary"/>></div>
<div class="panel-heading note-edit"><span class='glyphicon glyphicon-trash' onclick='deletePosition(this)'></span>
</div>
<div class="panel-heading note-main addDOkIcon" onclick="changePosition(this)">确定</div>
<div class="panel-heading note-sub addDCancelIcon" onclick="departmentInfo()">取消</div>
<div class="add-department-error-div"><span class="glyphicon glyphicon-exclamation-sign" title=
        <s:property value="message"/>></span>
</div>

