<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/3/27
  Time: 下午7:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
    <ol class="breadcrumb">
        <li><a href="#"><span class="glyphicon glyphicon-th"></span></a></li>
        <li class="active">租户管理</li>
    </ol>
</div><!--/.row-->

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">租户管理</h1>
    </div>
</div><!--/.row-->

<div class="row">
    <div class="col-md-8">
        <div class="panel panel-default chat">
            <div class="panel-heading" id="accordion"><span class="glyphicon glyphicon-th"></span> 租户</div>
            <div class="panel-body">
                <ul>
                    <s:iterator value="list" status="st">
                    <li>
                        <div class="chat-body">
                            <div class="header">
                                <strong class="primary-font"><input type="text" value=<s:property value="name" /> ></strong>
                                <small class="text-muted"><input type="text" value=<s:property value="password" /> ></small>
                                <div class="pull-right action-buttons" >
                                    <a href="#" class="pencil"><span class="glyphicon glyphicon-pencil chat-body-glyphicon-pencil"></span></a>
                                    <a href="#" class="trash"><span class="glyphicon glyphicon-trash" ></span></a>
                                </div>
                            </div>
                        </div>
                    </li>
                    </s:iterator>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-md-4">

        <div class="panel panel-blue">
            <div class="panel-heading dark-overlay"><span class="glyphicon glyphicon-check"></span>To-do List</div>
            <div class="panel-body">
                <ul class="todo-list">
                    <li class="todo-list-item">
                        <div class="checkbox">
                            <label for="checkbox">Make a plan for today</label>
                        </div>
                        <div class="pull-right action-buttons">
                            <a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
                            <a href="#" class="flag" ><span class="glyphicon glyphicon-flag" ></span></a>
                            <a href="#" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
                        </div>
                    </li>
                    <li class="todo-list-item">
                        <div class="checkbox">
                            <label for="checkbox">Update Basecamp</label>
                        </div>
                        <div class="pull-right action-buttons">
                            <a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
                            <a href="#" class="flag"><span class="glyphicon glyphicon-flag"></span></a>
                            <a href="#" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div><!--/.col-->
