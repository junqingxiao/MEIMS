<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/8
  Time: 下午9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="addDDiv"></div>
<div class="col-md-4">
    <div class="panel panel-info">
        <div class="panel-heading dark-overlay">
            <input class="departmentHeadInput" type="text" value=
            <s:property value="name"/> readonly no=<s:property value="no"/>>
            <div class="changeDDiv pull-right">
                <span class="glyphicon glyphicon-plus pull-right addPIcon" onclick="addPositionCheck(this)"></span>
            </div>
        </div>
        <div class="panel-body pre-scrollable note-body">
            <div id="addPDiv"></div>
        </div>
    </div>
</div>
