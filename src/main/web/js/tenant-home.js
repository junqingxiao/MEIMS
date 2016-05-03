/**
 * Created by mk on 16/4/25.
 */

//跳转日志
function logInfo()
{
    $('#logInfoTab').addClass('active');
    $('#employeeInfoTab').removeClass('active');
    $('#overallTab').removeClass('active');
    $('#departmentInfoTab').removeClass('active');
    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("main").innerHTML=xmlhttp.responseText;
        }
    }
    //先测试一下log
    xmlhttp.open("get","app/tenant/logInfo",true);
    xmlhttp.send();
}

//跳转overall
function overall()
{
    $('#overallTab').addClass('active');
    $('#employeeInfoTab').removeClass('active');
    $('#departmentInfoTab').removeClass('active');
    $('#logInfoTab').removeClass('active');
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
    $('#logInfoTab').removeClass('active');
    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            //这里用dom元素的话是不能加载script的
            $("#main").html(xmlhttp.responseText);
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
    $('#logInfoTab').removeClass('active');
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

//跳转新的一天的日志
function showNewDayLog()
{
    var date=$('#dateInput').get(0).value;

    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            $("#main").html(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/tenant/showNewDayLog?date="+date,true);
    xmlhttp.send();
}

//显示右边区域
function showRightArea(li)
{
    var name=$(li).children(":first").children(":first").children(":first").children(":first").get(0).value;
    var rightName=$("#rightArea").children(":first").children(":first").html();

    if(name != rightName || $("#updated-chat-body").length != 0)
    {//如果 updated-chat-body 存在则说明是已更新过的 需要更新右边
        var xmlhttp=new XMLHttpRequest();
        xmlhttp.onreadystatechange=function()
        {
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
            {
                $("#rightArea").html(xmlhttp.responseText);
            }
        }
        xmlhttp.open("get","app/tenant/showEPChange?name="+name,true);
        xmlhttp.send();
    }
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
        $("#rightArea").html("");
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
            '<div class="panel-heading note-main addDOkIcon" onclick="addDepartment(this)">确定</div><div class="panel-heading note-sub addDCancelIcon" onclick="departmentInfo()">取消</div>'+
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
        "<span class='glyphicon glyphicon-remove' onclick='departmentInfo()'></span>"+
        "<span class='glyphicon glyphicon-trash' onclick='deleteDepartment(this)'></span>");
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

//删除部门
function deleteDepartment(span)
{
    var name=$(span).parent().prev().get(0).value;
    var no=$(span).parent().prev().attr("no");

    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            $("#main").html(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/tenant/departmentDelete?name="+name+"&no="+no,true);
    xmlhttp.send();
}

//增加职位check
function addPositionCheck(span)
{
    if($(span).parent().parent().next().children(":first").html() == "")
    {
        $(span).parent().parent().next().children(":first").html('<div class="panel note">'+
            '<div class="panel-heading note-main"><input class="departmentInput" type="text" placeholder="新职位"></div>'+
            '<div class="panel-heading note-sub"><input class="departmentInput" type="text" placeholder="新工资"></div>'+
            '<div class="panel-heading note-main addDOkIcon"  onclick="addPosition(this)">确定</div><div class="panel-heading note-sub addDCancelIcon" onclick="departmentInfo()">取消</div>'+
            '</div>');
    }
}

//增加职位
function addPosition(span)
{
    //获得即时的值
    var pName=$(span).prev().prev().children(":first").get(0).value;
    var salary=$(span).prev().children(":first").get(0).value;
    var dNo=$(span).parent().parent().parent().prev().children(":first").attr("no");
    var dName=$(span).parent().parent().parent().prev().children(":first").get(0).value;//用于日志

    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            var parentDiv=$(span).parent().parent().parent();
            $(span).parent().parent().remove();
            parentDiv.prepend(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/tenant/positionAdd?name="+pName+"&salary="+salary+"&dNo="+dNo+"&dName="+dName,true);
    xmlhttp.send();
}

//修改职位check
function updatePositionCheck(span)
{
    $(span).parent().prev().prev().children(":first").removeAttr("readonly");
    $(span).parent().prev().children(":first").removeAttr("readonly");

    $(span).parent().after('<div class="panel-heading note-main addDOkIcon" onclick="changePosition(this)">确定</div><div class="panel-heading note-sub addDCancelIcon" onclick="departmentInfo()">取消</div>');
    $(span).parent().html("<span class='glyphicon glyphicon-trash' onclick='deletePosition(this)'></span>");
}

//删除职位
function deletePosition(span)
{
    //得到以前的值
    var pName=$(span).parent().prev().prev().children(":first").attr("value");
    var dNo=$(span).parent().parent().parent().prev().children(":first").attr("no");
    //dName用于日志
    var dName=$(span).parent().parent().parent().prev().children(":first").attr("value");

    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            $("#main").html(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/tenant/positionDelete?pName="+pName+"&dNo="+dNo+"&dName="+dName,true);
    xmlhttp.send();
}

//修改职位
function changePosition(span)
{
    //获得即时的值
    var pName=$(span).prev().prev().prev().children(":first").get(0).value;
    var salary=$(span).prev().prev().children(":first").get(0).value;
    var dNo=$(span).parent().parent().prev().children(":first").attr("no");


    //得到以前的值
    var oldPName=$(span).prev().prev().prev().children(":first").attr("value");
    var dName=$(span).parent().parent().prev().children(":first").attr("value");//用于日志

    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            $(span).parent().html(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/tenant/positionUpdate?pName="+pName+"&salary="+salary+"&oldPName="+oldPName+"&dName="+dName+"&dNo="+dNo,true);
    xmlhttp.send();
}