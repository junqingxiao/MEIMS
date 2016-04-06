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