<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../admin_Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../admin_Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../admin_Css/style.css" />
    <script type="text/javascript" src="../admin_Js/jquery.js"></script>
    <script type="text/javascript" src="../admin_Js/bootstrap.js"></script>
    <script type="text/javascript" src="../admin_Js/ckform.js"></script>
    <script type="text/javascript" src="../admin_Js/common.js"></script> 
    <script type="text/javascript" src="../admin_Js/ckeditor/ckeditor.js"></script>

    <style type="text/css">
        body { 
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form action="adminUpdatePassword.do" method="post" class="definewidth m20">
<input type="hidden" name="id" id="id" value="${data.username }" />
<table class="table table-bordered table-hover ">
	<tr>
        <td width="10%" class="tableleft">Old Password</td>
        <td><input type="password" name="old_password" id="old_password" required="required">
        	<span id="old_password_msg" style="color: red"></span>
        </td>
    </tr>
 	<tr>
        <td width="10%" class="tableleft">New Password</td>
        <td><input type="password" name="new_password" id="new_password" required="required">
        	<span id="new_password_msg" style="color: red"></span>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">Confirm Password</td>
        <td><input type="password" name="confirm_password" id="confirm_password" required="required">
        	<span id="confirm_password_msg" style="color: red"></span>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">Verify Code</td>
        <td><input type="text" name="verifyCode" value="${data.verifyCode }"></td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="button" id="update_button" class="btn btn-primary" type="button">Update</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">Cancel</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
    $(function () {  
    	$("#update_button").click(function(){
    		var id = $('#id').val();
    		var oldPass = $('#old_password').val();
    		var newPass = $('#new_password').val();
    		var conPass = $('#confirm_password').val();
    		if (newPass != conPass) {
    			$('#confirm_password_msg').empty();
    			$('#confirm_password_msg').html("Confirm password is not same as new password.");
    		} else {
    			$('#confirm_password_msg').empty();
    			$('#old_password_msg').empty();
    			$.ajax({
    				url : 'adminUpdatePassword.do',
    				type : 'POST',
    				dataType : 'json',
    				data : {newPass:newPass},
    				success : function(data) {
    					alert('Update Success.');
    				},
    				error : function(data) {
    					alert('Intenal server error, please try later again.');
    				}
    			});
    		}
    	});
    	$('#old_password').blur(function(){
    		var id = $('#id').val();
    		var oldPass = $('#old_password').val();
    		$.ajax({
    			type : 'POST',
    			url : 'checkOldPassword.do',
    			data : {username:id, oldPass:oldPass},
    			dataType : 'json',
    			success : function(data){
    				if(data.is_pass_correct == true) {
    					$('#old_password_msg').empty();
    				} else {
    					$('#old_password_msg').empty();
    					$('#old_password_msg').html("Original password is incorrect.");
    				}
    			},
    			error : function(){
    				alert('Intenal server error, please try later again.');
    			}
    		});
    	});
		//initBackPage('gotoAdminAuditorPage.do');
    });
</script>