<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/1
  Time: 下午6:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="addDiv">
    <li>
        <div class="chat-body">
            <div class="header" >
                <strong class="primary-font" ><input class="tenantInput" type="text" placeholder="账号" /> </strong>
                <small class="text-muted"><input class="tenantInput" type="text" placeholder="密码" /> </small>
                <div class="pull-right action-buttons">
                    <a href="#" class="warning"><span class="glyphicon glyphicon-exclamation-sign" title=<s:property value="message" />></span></a>
                    <a href='#' class='pencil'><span class='glyphicon glyphicon-ok' onclick='addTenant(this)'></span></a>
                    <a href='#' class='remove'><span class='glyphicon glyphicon-remove' onclick='tenantManage()'></span></a>
                </div>
            </div>
        </div>
    </li>
</div>



