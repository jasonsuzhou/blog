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
<form action="adminUpdateMoments.do" method="post" class="definewidth m20">
<input type="hidden" name="id" value="${data.id }" />
<table class="table table-bordered table-hover ">
    <tr>
        <td width="10%" class="tableleft">Post Date</td>
        <td>${data.strPostDate }</td>
    </tr>
    <tr>
        <td class="tableleft">Content</td>
        <td>
        	<textarea rows="5" cols="50" name="content" id="content">${data.content }</textarea>
        </td>
    </tr> 
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">Update</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">Cancel</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
CKEDITOR.replace('content');
    $(function () {       
		$('#backid').click(function(){
				self.location.href="gotoMomentsPage.do";
		 });

    });
</script>