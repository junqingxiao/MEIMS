<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/12
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<input class="departmentHeadInput" type="text" value=<s:property value="name"/> readonly no=<s:property value="no"/>>
<div class="changeDDiv pull-right">
    <span class="glyphicon glyphicon-plus pull-right addPIcon" onclick="addPositionCheck(this)"></span>
</div>

<script>
    $(".changeDDiv").html('<span class="glyphicon glyphicon-plus pull-right addPIcon" onclick="addPositionCheck(this)"></span>');
    $(".departmentHeadInput").attr("readonly","readonly");
</script>

