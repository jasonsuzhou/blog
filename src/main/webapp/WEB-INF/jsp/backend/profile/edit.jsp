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
    <script type="text/javascript" src="../admin_Js/jquery.form.min.js"></script>
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
<form action="updateProfileCommit.do" name="mainform" method="post" class="definewidth m20" novalidate>
<input type="hidden" name="id" value="${data.id}" />
<table class="table table-bordered table-hover definewidth m10"> 
	<tr>
        <td width="10%" class="tableleft">Total Access</td>
        <td>
        	${data.totalAccess }
        </td>
        <td width="10%" class="tableleft">Birthday</td>
        <td>
        	<input type="text" name="birthday" value="${data.birthday }"> 
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">User Name</td>
        <td>
        	<input type="text" name="username" value="${data.username }"> 
        </td>
        <td width="10%" class="tableleft">Real Name</td>
        <td>
        	<input type="text" name="realname" value="${data.realname }"> 
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">Birthplace</td>
        <td>
        	<input type="text" name="party" value="${data.party }"> 
        </td>
        <td width="10%" class="tableleft">Location</td>
        <td>
        	<input type="text" name="currentLocation" value="${data.currentLocation }"> 
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">Career</td>
        <td>
        	<input type="text" name="career" value="${data.career }"> 
        </td>
        <td width="10%" class="tableleft">Years</td>
        <td>
        	<input type="text" name="years" value="${data.years }"> 
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">Books</td>
        <td>
        	<input type="text" name="book" value="${data.book }"> 
        </td>
        <td width="10%" class="tableleft">Hobby</td>
        <td>
        	<input type="text" name="hoppy" value="${data.hoppy }"> 
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">Photo(182*325)</td>
        <td>
        	<input type="text" name="imgUrl" value="${data.imgUrl }"> 
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">About Me</td>
        <td colspan="3">
        	<textarea rows="5" cols="100" name="aboutMe" id="aboutMe">${data.aboutMe}</textarea>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">About Blog</td>
        <td colspan="3">
        	<textarea rows="5" cols="100" name="aboutBlog" id="aboutBlog">${data.aboutBlog}</textarea>
        </td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" id="saveButton" class="btn btn-primary" type="button">Save</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
<!--
CKEDITOR.replace('aboutMe');
CKEDITOR.replace('aboutBlog');
<%--
$(function(){
	$('#saveButton').click(function(){
		var options = {
				url: 'updateFooterContent.do',
				type: 'POST',
				success: function(data){
					alert("Success");
				},
				error:function(data){
					alert("Error");
				}
		};
		$('form[name=mainform]').ajaxSubmit(options);
		return false;
	});
});
--%>
//-->
</script>
