<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/1
  Time: 下午4:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="chat-body" id="updated-chat-body">
    <div class="header">
        <strong class="primary-font"><input class="tenantInput" type="text" value=
        <s:property value="name"/> readonly></strong>
        <small class="text-muted"><input class="tenantInput" type="text" value=
        <s:property value="password"/> readonly></small>
        <div class="pull-right action-buttons">
            <a href="#" class="pencil"><span class="glyphicon glyphicon-pencil"
                                             onclick="updateTenantCheck(this)"></span></a>
            <a href="#" class="trash"><span class="glyphicon glyphicon-trash" onclick="deleteTenant(this)"></span></a>
        </div>
    </div>
</div>

<script>
    showRightArea($("#updated-chat-body").parent().get(0));
</script>

