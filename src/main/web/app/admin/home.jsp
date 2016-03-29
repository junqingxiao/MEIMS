
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
        xmlhttp.open("get","overall",true);
        xmlhttp.send();

        //跳转overall
        function overall()
        {
            $('#overallTab').addClass('active');
            $('#tenantManageTab').removeClass('active');
            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("main").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","overall",true);
            xmlhttp.send();
        }

        //跳转租户管理
        function tenantManage()
        {
            $('#overallTab').removeClass('active');
            $('#tenantManageTab').addClass('active');
            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("main").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","tenantManage",true);
            xmlhttp.send();
        }

        //删除租户
        function deleteTenant(span)
        {
            var name=$(span).parent().parent().prev().prev().children(":first").attr("value");
            var password=$(span).parent().parent().prev().children(":first").attr("value");

            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("main").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","tenantDelete?name="+name+"&password="+password,true);
            xmlhttp.send();
        }
    </script>

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
        <li id="tenantManageTab"><a href="#" onclick="tenantManage()"><span class="glyphicon glyphicon-th"></span> 租户管理</a></li>
        <li role="presentation" class="divider"></li>
    </ul>
</div><!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main" id="main">

</div>

</body>
</html>
