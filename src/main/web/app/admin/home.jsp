
<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/3/26
  Time: 下午4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home</title>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script src="js/admin-home.js" ></script>

    <link rel="stylesheet" type="text/css"  href="css/style.css">
</head>
<body>
<jsp:include page="../../template/_navbar.jsp" flush="true" />

<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
    <form role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
        </div>
    </form>
    <ul class="nav menu">
        <li class="active" id="overallTab"><a href="#" onclick="overall()"><span class="glyphicon glyphicon-dashboard"></span> 总览</a></li>
        <li id="tenantManageTab"><a href="#" onclick="tenantManage()"><span class="glyphicon glyphicon-list"></span> 租户管理</a></li>
        <li id="registerCheckTab"><a href="#" onclick="registerCheck()"><span class="glyphicon glyphicon-tags"></span> 注册审核</a></li>
        <li id="logInfoTab"><a href="#" onclick="logInfo()"><span class="glyphicon glyphicon-book"></span> 日志</a></li>
        <li role="presentation" class="divider"></li>
        <li class="parent" id="updateAccountLi">
            <a href="#" onclick="showUpdateAccount()"><span class="glyphicon glyphicon-edit"></span> 修改账号</a>
        </li>
        <li class="parent" id="updatePasswordLi">
            <a href="#" onclick="showUpdatePassword()"><span class="glyphicon glyphicon-edit"></span> 修改密码</a>
        </li>
    </ul>
</div><!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main" id="main">

</div>

</body>
</html>

<script>
    //一开始是overall 界面
    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("main").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("get","app/admin/overall",true);
    xmlhttp.send();
</script>
