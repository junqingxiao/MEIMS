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
                <input class="departmentInput" type="text" value=<s:property value="name" /> readonly>
            </div>
            <div class="panel-body pre-scrollable note-body"></div>
        </div>
    </div>
