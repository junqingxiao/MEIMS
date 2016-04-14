<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/14
  Time: 下午8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div></div>
<div class="panel note">
    <div class="panel-heading note-main"><input class="departmentInput" type="text" value=
    <s:property value="name"/> readonly></div>
    <div class="panel-heading note-sub"><input class="departmentInput" type="text" value=
    <s:property value="salary"/> readonly></div>
    <div class="panel-heading note-edit"><span class="glyphicon glyphicon-pencil" onclick=""></span></div>
    <!-- todo 修改的时候要注意onclick-->
</div>
