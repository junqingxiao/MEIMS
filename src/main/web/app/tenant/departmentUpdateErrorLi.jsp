<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/12
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<input class="departmentHeadInput" type="text" value=<s:property value="name"/> no=<s:property value="no"/>>
<div class="changeDDiv pull-right">
    <a href="#" class="warning"><span class="glyphicon glyphicon-exclamation-sign" title=<s:property value="message" />></span></a>
    <span class='glyphicon glyphicon-ok' onclick='changeDepartment(this)'></span>
    <span class='glyphicon glyphicon-remove remove' onclick='departmentInfo()'></span>
</div>
