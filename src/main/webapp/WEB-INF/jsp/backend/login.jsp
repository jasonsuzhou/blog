<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
	<meta charset="UTF-8">
   <link rel="stylesheet" type="text/css" href="admin_Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="admin_Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="admin_Css/style.css" />
    <script type="text/javascript" src="admin_Js/jquery.js"></script>
    <script type="text/javascript" src="admin_Js/bootstrap.js"></script>
    <script type="text/javascript" src="admin_Js/ckform.js"></script>
    <script type="text/javascript" src="admin_Js/common.js"></script>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }

    </style>  
</head>
<body>
<div class="container">

    <form class="form-signin" method="post" action="admin.do">
        <h2 class="form-signin-heading">Admin Login</h2>
        <input type="text" name="username" class="input-block-level" placeholder="Username">
        <input type="password" name="password" class="input-block-level" placeholder="Password">
        <input type="text" name="verify" class="input-medium" placeholder="Code">
        <p><button class="btn btn-large btn-primary" type="submit">Sign In</button></p>
    </form>

</div>
</body>
</html>