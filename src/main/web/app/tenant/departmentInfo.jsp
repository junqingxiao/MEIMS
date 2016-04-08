<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/8
  Time: 下午2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">部门管理</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">部门信息<span  class="glyphicon glyphicon-plus pull-right" id="addDIcon" onclick="addDepartmentCheck()"></span></h1>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div id="addDDiv"></div>
        <s:iterator value="list" status="st">
        <div class="col-md-4">
            <div class="panel panel-info">
                <div class="panel-heading dark-overlay">
                    <input class="departmentInput" type="text" value=<s:property value="name" /> readonly>
                </div>
                <div class="panel-body pre-scrollable note-body">
                    <s:iterator value="list" status="st">
                    <div class="panel note">
                        <div class="panel-heading note-main"><input class="departmentInput" type="text" value=<s:property value="name" /> readonly></div><div class="panel-heading note-sub"><input class="departmentInput" type="text" value=<s:property value="salary" /> readonly></div>
                    </div>
                    </s:iterator>
                </div>
            </div>
        </div>
        </s:iterator>
    </div><!-- /.row -->


