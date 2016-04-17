<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/17
  Time: 下午2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="panel-heading note-main"><input class="departmentInput" type="text" value=
<s:property value="pName"/> readonly></div>
<div class="panel-heading note-sub"><input class="departmentInput" type="text" value=
<s:property value="salary"/> readonly></div>
<div class="panel-heading note-edit"><span class="glyphicon glyphicon-pencil"
                                           onclick="updatePositionCheck(this)"></span></div>

