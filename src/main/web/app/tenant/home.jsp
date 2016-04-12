<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/1
  Time: 下午1:46
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
        //跳转overall
        function overall()
        {
            //todo 更多的模块之后active都要改
            $('#overallTab').addClass('active');
            $('#employeeInfoTab').removeClass('active');
            $('#departmentInfoTab').removeClass('active');
            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("main").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","app/tenant/overall",true);
            xmlhttp.send();
        }

        //跳转员工信息
        function employeeInfo()
        {
            $('#overallTab').removeClass('active');
            $('#employeeInfoTab').addClass('active');
            $('#departmentInfoTab').removeClass('active');
            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("main").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","app/tenant/employeeInfo",true);
            xmlhttp.send();
        }

        //跳转部门信息
        function departmentInfo()
        {
            $('#overallTab').removeClass('active');
            $('#employeeInfoTab').removeClass('active');
            $('#departmentInfoTab').addClass('active');
            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("main").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","app/tenant/departmentInfo",true);
            xmlhttp.send();
        }

        //添加员工check
        function addEmployeeCheck()
        {
            if($("#addDiv").html() == "")
            {
                $("#addDiv").html('<li>'+'<div class="chat-body">'+
                        '<div class="header" >'+
                        '<small class="text-muted"><input class="employeeAddInput" type="text" placeholder="姓名"></small>'+
                        '<small class="text-muted"><input class="employeeAddInput" type="text" placeholder="职位"></small>'+
                        '<small class="text-muted"><input class="employeeAddInput" type="text" placeholder="部门"></small>'+
                        '<small class="text-muted"><input class="employeeAddInput" type="date" ></small>'+
                        '<div class="pull-right action-buttons">'+
                        '<a href="#" class="pencil"><span class="glyphicon glyphicon-ok" onclick="addEmployee(this)"></span></a> '+
                        '<a href="#" class="remove"><span class="glyphicon glyphicon-remove" onclick="employeeInfo()"></span></a> '+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</li>');
            }
        }

        //添加员工
        function addEmployee(span)
        {
            //获得即时的值
            var name=$(span).parent().parent().prev().prev().prev().prev().children(":first").get(0).value;
            var pName=$(span).parent().parent().prev().prev().prev().children(":first").get(0).value;
            var dName=$(span).parent().parent().prev().prev().children(":first").get(0).value;
            var date=$(span).parent().parent().prev().children(":first").get(0).value;

            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    var parentLi=$("#addDiv").parent();
                    $("#addDiv").remove();
                    parentLi.prepend(xmlhttp.responseText);
                }
            }
            xmlhttp.open("get","app/tenant/employeeAdd?name="+name+"&pName="+pName+"&dName="+dName+"&date="+date,true);
            xmlhttp.send();
        }

        //删除员工
        function deleteEmployee(span)
        {
            var name=$(span).parent().parent().prev().prev().prev().prev().children(":first").get(0).value;
            var no=$(span).parent().parent().prev().children(":first").get(0).value;

            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    document.getElementById("main").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("get","app/tenant/employeeDelete?name="+name+"&no="+no,true);
            xmlhttp.send();
        }

        //修改员工信息
        function updateEmployee(span)
        {
            //获得即时的值
            var name=$(span).parent().parent().prev().prev().prev().prev().children(":first").get(0).value;
            var pName=$(span).parent().parent().prev().prev().prev().children(":first").get(0).value;
            var dName=$(span).parent().parent().prev().prev().children(":first").get(0).value;
            //获得id的值
            var no=$(span).parent().parent().prev().children(":first").get(0).value;


            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    $(span).parent().parent().parent().parent().parent().html(xmlhttp.responseText);
                }
            }
            xmlhttp.open("get","app/tenant/employeeUpdate?name="+name+"&pName="+pName+"&dName="+dName+"&no="+no,true);
            xmlhttp.send();
        }

        //修改员工信息check
        function updateEmployeeCheck(span)
        {
            //input可编辑
            $(span).parent().parent().prev().prev().prev().prev().children(":first").removeAttr("readonly");
            $(span).parent().parent().prev().prev().prev().children(":first").removeAttr("readonly");
            $(span).parent().parent().prev().prev().children(":first").removeAttr("readonly");
            //禁止删除 增加确认取消
            $(span).parent().parent().html("<a href='#' class='pencil'><span class='glyphicon glyphicon-ok' onclick='updateEmployee(this)'></span></a> " +
                    "<a href='#' class='remove'><span class='glyphicon glyphicon-remove' onclick='employeeInfo()'></span></a> "+
                    "<a href='#' class='trash'><span class='glyphicon glyphicon-trash'></span></a> ");
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
                        '<input id="updateAccountInput" type="text" placeholder="新账号"><span class="glyphicon glyphicon-ok floatOk" onclick="updateAccount()" ></span>'+
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
            xmlhttp.open("get","app/tenant/changeAccount?name="+name,true);
            xmlhttp.send();
        }

        //显示子菜单
        function showUpdatePassword()
        {
            if($("#updatePasswordInput").length == 0)
            {//还未展开
                $("#updatePasswordLi").html( '<a href="#" onclick="showUpdatePassword()">'+
                        '<span class="glyphicon glyphicon-edit"></span> 修改密码'+
                        '</a>'+
                        '<ul class="children">'+
                        '<li>'+
                        '<a href="#">'+
                        '<input id="updatePasswordInput" type="text" placeholder="新密码"><span class="glyphicon glyphicon-ok floatOk" onclick="updatePassword()"></span>'+
                        '</a>'+
                        '</li>'+
                        '</ul>');
            }
            else
            {
                $("#updatePasswordLi").html( '<a href="#" onclick="showUpdatePassword()">'+
                        '<span class="glyphicon glyphicon-edit"></span> 修改密码'+
                        '</a>');
            }
        }

        //左侧边栏修改密码确认
        function updatePassword()
        {
            var password=$("#updatePasswordInput").get(0).value;

            xmlhttp.open("get","app/tenant/changePassword?password="+password,true);
            xmlhttp.send();

            $("#updatePasswordLi").html('<a href="#" onclick="showUpdatePassword()">'+
                    '<span class="glyphicon glyphicon-edit"></span> 修改密码'+
                    '</a>');
        }

        //添加部门check
        function addDepartmentCheck()
        {
            if($("#addDDiv").html() == "")
            {
                $("#addDDiv").addClass("col-md-4");
                $("#addDDiv").html('<div class="panel panel-info">'+
                        '<div class="panel-heading dark-overlay">'+
                        '<input class="departmentHeadInput" type="text" placeholder="新部门">'+
                        '</div>'+
                        '<div class="panel-body pre-scrollable note-body">'+
                        '<div class="panel note">'+
                        '<div class="panel-heading note-main" id="addDOkIcon" onclick="addDepartment(this)">确定</div><div class="panel-heading note-sub" id="addDCancelIcon" onclick="departmentInfo()">取消</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>');
            }
        }

        //添加部门
        function addDepartment(span)
        {
            //获得即时的值
            var name=$(span).parent().parent().prev().children(":first").get(0).value;
            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    var parentDiv=$("#addDDiv").parent();
                    $("#addDDiv").remove();
                    parentDiv.prepend(xmlhttp.responseText);
                }
            }
            xmlhttp.open("get","app/tenant/departmentAdd?name="+name,true);
            xmlhttp.send();
        }

        //修改部门check
        function  changeDepartmentCheck()
        {
            //部门可编辑
            $(".departmentHeadInput").removeAttr("readonly");
            //显示确认取消按钮
            $(".changeDDiv").html("<span class='glyphicon glyphicon-ok' onclick='changeDepartment(this)'></span> "+
            "<span class='glyphicon glyphicon-remove' onclick='departmentInfo()'></span>");
        }

        //修改部门
        function changeDepartment(span)
        {
            //获得即时的值
            var name=$(span).parent().prev().get(0).value;
            var no=$(span).parent().prev().attr("no");

            var xmlhttp=new XMLHttpRequest();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    $(span).parent().parent().html(xmlhttp.responseText);
                }
            }
            xmlhttp.open("get","app/tenant/departmentUpdate?name="+name+"&no="+no,true);
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
        <li id="employeeInfoTab"><a href="#" onclick="employeeInfo()"><span class="glyphicon glyphicon-list"></span> 员工信息</a></li>
        <li id="departmentInfoTab"><a href="#" onclick="departmentInfo()"><span class="glyphicon glyphicon-th"></span> 部门信息</a></li>
        <li role="presentation" class="divider"></li>
        <li class="parent" id="updateAccountLi">
            <a href="#" onclick="showUpdateAccount()">
                <span class="glyphicon glyphicon-edit"></span> 修改账号
            </a>
        </li>
        <li class="parent" id="updatePasswordLi">
            <a href="#" onclick="showUpdatePassword()">
                <span class="glyphicon glyphicon-edit"></span> 修改密码
            </a>
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
    xmlhttp.open("get","app/tenant/overall",true);
    xmlhttp.send();
</script>