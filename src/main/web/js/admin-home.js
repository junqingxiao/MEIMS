/**
 * Created by mk on 16/4/25.
 */
//跳转日志
function logInfo()
{
    $('#logInfoTab').addClass('active');
    $('#tenantManageTab').removeClass('active');
    $('#overallTab').removeClass('active');
    $('#registerCheckTab').removeClass('active');
    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            document.getElementById("main").innerHTML=xmlhttp.responseText;
        }
    }
    //先测试一下log
    xmlhttp.open("get","app/admin/logInfo",true);
    xmlhttp.send();
}

//跳转overall
function overall()
{
    $('#overallTab').addClass('active');
    $('#tenantManageTab').removeClass('active');
    $('#logInfoTab').removeClass('active');
    $('#registerCheckTab').removeClass('active');
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
    $('#logInfoTab').removeClass('active');
    $('#registerCheckTab').removeClass('active');
    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            $("#main").html(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/admin/tenantManage",true);
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
    xmlhttp.open("get","app/admin/showNewDayLog?date="+date,true);
    xmlhttp.send();
}

//跳转注册审核
function registerCheck()
{
    $('#overallTab').removeClass('active');
    $('#registerCheckTab').addClass('active');
    $('#logInfoTab').removeClass('active');
    $('#tenantManageTab').removeClass('active');

    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            $("#main").html(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/admin/registerCheck",true);
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
        xmlhttp.open("get","app/admin/showTenantTime?name="+name,true);
        xmlhttp.send();
    }
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
            $(span).parent().parent().parent().parent().parent().html(xmlhttp.responseText);
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
        "<a href='#' class='remove'><span class='glyphicon glyphicon-remove' onclick='tenantManage()'></span></a> "+
        "<a href='#' class='trash'><span class='glyphicon glyphicon-trash'></span></a> ");
}

//添加租户check
function addTenantCheck()
{
    if($("#addDiv").html() == "")
    {
        $("#addDiv").html('<li>'+'<div class="chat-body">'+
            '<div class="header" >'+
            '<strong class="primary-font" ><input class="tenantInput" type="text" placeholder="账号"></strong>'+
            '<small class="text-muted"><input class="tenantInput" type="text" placeholder="密码"></small>'+
            '<div class="pull-right action-buttons">'+
            '<a href="#" class="pencil"><span class="glyphicon glyphicon-ok" onclick="addTenant(this)"></span></a> '+
            '<a href="#" class="remove"><span class="glyphicon glyphicon-remove" onclick="tenantManage()"></span></a> '+
            '</div>'+
            '</div>'+
            '</div>'+
            '</li>');
    }
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
            var parentLi=$("#addDiv").parent();
            $("#addDiv").remove();
            parentLi.prepend(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/admin/tenantAdd?name="+name+"&password="+password,true);
    xmlhttp.send();
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
    xmlhttp.open("get","app/admin/changeAccount?name="+name,true);
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

    xmlhttp.open("get","app/admin/changePassword?password="+password,true);
    xmlhttp.send();

    $("#updatePasswordLi").html('<a href="#" onclick="showUpdatePassword()">'+
        '<span class="glyphicon glyphicon-edit"></span> 修改密码'+
        '</a>');
}

//同意注册
function registerOk(span)
{
    var name=$(span).parent().prev().prev().prev().html();
    var password=$(span).parent().prev().prev().html();

    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            $("#main").html(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/admin/registerOk?name="+name+"&password="+password,true);
    xmlhttp.send();
}

//不同意注册
function registerRemove(span)
{
    var name=$(span).parent().prev().prev().html();
    var password=$(span).parent().prev().html();

    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            $("#main").html(xmlhttp.responseText);
        }
    }
    xmlhttp.open("get","app/admin/registerRemove?name="+name+"&password="+password,true);
    xmlhttp.send();
}