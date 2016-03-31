
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
        xmlhttp.open("get","app/admin/overall",true);
        xmlhttp.send();

        //跳转overall
        function overall()
        {
            //todo 更多的模块之后active都要改
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
            xmlhttp.open("get","app/admin/overall",true);
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
            xmlhttp.open("get","app/admin/tenantManage",true);
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
            xmlhttp.open("get","app/admin/tenantDelete?name="+name+"&password="+password,true);
            xmlhttp.send();
        }

        //修改租户信息
        function updateTenant(span)
        {
            //获得即时的值
            var name=$(span).parent().parent().prev().prev().children(":first").get(0).value;
            var password=$(span).parent().parent().prev().children(":first").get(0).value;
            //获得原来的值(在chrome中;ie中是即时的值)
            var oldName=$(span).parent().parent().prev().prev().children(":first").attr("value");
            var oldPassword=$(span).parent().parent().prev().children(":first").attr("value");


            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("main").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","app/admin/tenantUpdate?name="+name+"&password="+password+"&oldName="+oldName+"&oldPassword="+oldPassword,true);
            xmlhttp.send();
        }

        //修改租户信息check
        function updateTenantCheck(span)
        {
            //input可编辑
            $(span).parent().parent().prev().prev().children(":first").removeAttr("readonly");
            $(span).parent().parent().prev().children(":first").removeAttr("readonly");
            //禁止删除 增加确认取消
            $(span).parent().parent().html("<a href='#' class='pencil'><span class='glyphicon glyphicon-ok' onclick='updateTenant(this)'></span></a> " +
                    "<a href='#' class='remove'><span class='glyphicon glyphicon-remove' onclick='updateTenantCancel(this)'></span></a> "+
                    "<a href='#' class='trash'><span class='glyphicon glyphicon-trash'></span></a> ");
        }

        //添加租户check
        function addTenantCheck()
        {
            $("#addDiv").html('<li>'+'<div class="chat-body">'+
                                        '<div class="header" >'+
                                            '<strong class="primary-font" ><input type="text" placeholder="账号"></strong>'+
                                            '<small class="text-muted"><input type="text" placeholder="密码"></small>'+
                                            '<div class="pull-right action-buttons">'+
                                                '<a href="#" class="pencil"><span class="glyphicon glyphicon-ok" onclick="addTenant(this)"></span></a>'+
                                                '<a href="#" class="remove"><span class="glyphicon glyphicon-remove" onclick="tenantManage()"></span></a>'+
                                            '</div>'+
                                        '</div>'+
                                     '</div>'+
                             '</li>');
        }

        //添加租户
        function addTenant(span)
        {
            //获得即时的值
            var name=$(span).parent().parent().prev().prev().children(":first").get(0).value;
            var password=$(span).parent().parent().prev().children(":first").get(0).value;

            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("main").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","app/admin/tenantAdd?name="+name+"&password="+password,true);
            xmlhttp.send();
        }

        //取消动作
        function updateTenantCancel(span)
        {
            var oldName=$(span).parent().parent().prev().prev().children(":first").attr("value");
            var oldPassword=$(span).parent().parent().prev().children(":first").attr("value");
            //设置为以前的值
            $(span).parent().parent().prev().prev().children(":first").val(oldName);
            $(span).parent().parent().prev().children(":first").val(oldPassword);

            $(span).parent().parent().prev().prev().children(":first").attr("readonly","readonly");
            $(span).parent().parent().prev().children(":first").attr("readonly","readonly");

            $(span).parent().parent().html("<a href='#' class='pencil'><span class='glyphicon glyphicon-pencil' onclick='updateTenantCheck(this)'></span></a>" +
                    "<a href='#' class='trash'><span class='glyphicon glyphicon-trash' onclick='deleteTenant(this)'></span></a> ");
        }

        //显示子菜单
        function showUpdateAccount()
        {
            if($("#updateAccountInput").length == 0)
            {//还未展开
                $("#updateAccountLi").html( '<a href="#" onclick="showUpdateAccount()">'+
                                                '<span class="glyphicon glyphicon-edit"></span> 修改账号'+
                                            '</a>'+
                                            '<ul class="children">'+
                                                '<li>'+
                                                    '<a href="#">'+
                                                        '<input id="updateAccountInput" type="text" placeholder="新账号"><span class="glyphicon glyphicon-ok" onclick="updateAccount()"></span>'+
                                                    '</a>'+
                                                '</li>'+
                                            '</ul>');
            }
            else
            {
                $("#updateAccountLi").html( '<a href="#" onclick="showUpdateAccount()">'+
                                                '<span class="glyphicon glyphicon-edit"></span> 修改账号'+
                                            '</a>');
            }


        }

        //左侧边栏修改账号确认
        function updateAccount()
        {
            var name=$("#updateAccountInput").get(0).value;

            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("updateAccountLi").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","app/admin/changeAccount?name="+name,true);
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
        <li id="tenantManageTab"><a href="#" onclick="tenantManage()"><span class="glyphicon glyphicon-list"></span> 租户管理</a></li>
        <li role="presentation" class="divider"></li>
        <li class="parent" id="updateAccountLi">
            <a href="#" onclick="showUpdateAccount()">
                <span class="glyphicon glyphicon-edit"></span> 修改账号
            </a>
        </li>
    </ul>
</div><!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main" id="main">

</div>

</body>
</html>
