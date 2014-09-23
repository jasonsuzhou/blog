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
<form action="updateFooterContent.do" name="mainform" method="post" class="definewidth m20" novalidate>
<input type="hidden" name="id" value="${primaryKey}" />
<table class="table table-bordered table-hover definewidth m10"> 
	<tr>
        <td width="10%" class="tableleft">Total Access</td>
        <td>
        	${accessCount }
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">Footer Content</td>
        <td>
        	<textarea rows="5" cols="100" name="content" id="content">${footerContent}</textarea>
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
CKEDITOR.replace('content');
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
