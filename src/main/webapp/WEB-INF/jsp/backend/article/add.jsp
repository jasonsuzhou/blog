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
<form action="addArticleCommit.do" method="post" novalidate>
<table class="table table-bordered table-hover definewidth m10">
	<tr>
    	<td width="10%" class="tableleft">Title</td>
    	<td>
    		<input type="text" name="title" class="input-xxlarge">
    	</td>
    </tr>
    <tr>
        <td class="tableleft">Content</td>
        <td>
        	<textarea rows="5" cols="50" name="content" id="content"></textarea>
        </td>
    </tr>
    <tr>
    	<td class="tableleft">Summary View Image</td>
    	<td>
    		<input type="text" name="summaryImg" class="input-xxlarge" value="images/icon/blog_java_icon.png">
    	</td>
    </tr>
    <tr>
    	<td class="tableleft">Category</td>
    	<td id="category_list">
    		
    	</td>
    </tr>
    <tr id="download_url" style="display: none">
    	<td class="tableleft">Download URL</td>
    	<td>
    		<input type="text" name="downloadUrl" class="input-xxlarge">
    	</td>
    </tr>
    <tr>
    	<td class="tableleft">Key Words</td>
    	<td>
    		<input type="text" name="keyWords">
    	</td>
    </tr>
    <tr>
    	<td class="tableleft">Author</td>
    	<td>
    		<input type="text" name="author" value="JasonYao(姚敏华)">
    	</td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">Save</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">Cancel</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
function appendCategoriesCheckbox(dataList) {
	$('#category_list').empty();
	$.each(dataList, function(i, item){
		var checkbox = "<input onclick=\"checkCategory(this)\" type=\"checkbox\" name=\"category\" value=\""+item.name+"\">"+item.name+"&nbsp;&nbsp;";
		$('#category_list').append(checkbox);
	});
}
function checkCategory(obj) {
	if (obj.checked && (obj.value == 'Work Display' || obj.value == '作品展示')) {
		$('#download_url').show();
	} else {
		$('#download_url').hide();
	}
}
$(function () {    
	CKEDITOR.replace('content');
	$.ajax({
		url : 'queryAllCategory.do',
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			appendCategoriesCheckbox(data.dataList);
		},
		error : function(data) {
			alert('Internal server error, please try later.');
		}
	});
	$('#backid').click(function(){
		window.location.href="gotoBlogArticlePage.do";
	});
});
</script>