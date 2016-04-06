<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/3/31
  Time: 下午10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<a href="#" onclick="showUpdateAccount()">
    <span class="glyphicon glyphicon-edit"></span> 修改账号
</a>
<ul class="children">
    <li>
        <a href="#"><input id="updateAccountInput" type="text" placeholder="新账号"><span class="glyphicon glyphicon-ok floatOk" onclick="updateAccount()"></span><span class="glyphicon glyphicon-exclamation-sign warning floatOk" title=<s:property value="message" />></span></a>
    </li>
</ul>