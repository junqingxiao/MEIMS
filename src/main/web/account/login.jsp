<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/3/25
  Time: 下午9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<html>
<head>
    <title>login</title>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/bootstrap.min.js" ></script>

    <link rel="stylesheet" type="text/css"  href="css/style.css">
</head>
    <title>MEIMS</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span >MEIMS</span></a>
        </div>
    </div><!-- /.container-fluid -->
</nav>

<div class="intro">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-md-offset-8">
                    <div class="col-md-12 col-md-offset-2 intro-form-div">
                        <h1 ><span class="brand-heading">MEIMS</span></h1>
                        <form role="form" action="login.action" method="post">
                            <div class="form-group intro-form-group">
                                <div>
                                    <input  type="text" class="form-control" id="name" name="Name" placeholder="账号">
                                </div>
                            </div>
                            <br>
                            <div class="form-group intro-form-group">
                                <div>
                                    <input type="password" class="form-control" id="password" name="Password" placeholder="密码">
                                </div>
                            </div>
                            <br>
                            <div class="form-group" align="center">
                                <button type="submit" class="btn  start">登录</button>
                                <!--button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">注册</button-->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
