<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../admin_Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="../admin_Css/bootstrap-responsive.css" />
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

@media ( max-width : 980px) {
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
	<form action="adminUpdateArticle.do" method="post"
		class="definewidth m20">
		<input type="hidden" name="id" value="${data.id }" />
		<table class="table table-bordered table-hover ">
			<tr>
				<td width="10%" class="tableleft">Title</td>
				<td><input type="text" name="title" value="${data.title }"
					class="input-xxlarge"></td>
			</tr>
			<tr>
				<td class="tableleft">Content</td>
				<td>
					<textarea rows="5" cols="50" name="content" id="content">${contentValue }</textarea>
				</td>
				<script>
					var content = $('#content').val();
					var find = '\001';
					var re = new RegExp(find, 'g');
					content = content.replace(re, '&gt;');
					find = "\002";
					re = new RegExp(find, 'g');
					content = content.replace(re, '&lt;');
					$('#content').val(content);
				</script>
			</tr>
			<tr>
				<td class="tableleft">Summary View Image</td>
				<td><input type="text" name="summaryImg"
					value="${data.summaryImg }" class="input-xxlarge"></td>
			</tr>
			<tr>
				<td class="tableleft">Category</td>
				<td id="category_list"></td>
			</tr>
			<tr>
				<td class="tableleft">Download URL</td>
				<td>
					<input type="text" name="downloadUrl" value="${data.downloadUrl }" class="input-xxlarge">
				</td>
			</tr>
			<tr>
				<td class="tableleft">Key Words</td>
				<td><input type="text" name="keyWords"
					value="${data.keyWords }"></td>
			</tr>
			<tr>
				<td class="tableleft">Author</td>
				<td><input type="text" name="author" value="${data.author }">
				</td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" type="button">Update</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid">Cancel</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<script type="text/javascript">
	function appendCategoriesCheckbox(dataList) {
		$('#category_list').empty();
		$
				.each(
						dataList,
						function(i, item) {
							var checkbox = "<input type=\"checkbox\" name=\"category\" value=\""+item.name+"\">"
									+ item.name + "&nbsp;&nbsp;";
							$('#category_list').append(checkbox);
						});
		var cats = '${data.category}';
		var arrCat = cats.split(",");
		var len = arrCat.length;
		for ( var i = 0; i < len; i++) {
			if (typeof arrCat[i] != "undefined" && arrCat[i] != null
					&& arrCat[i] != "") {
				$('input[name=category]').each(function(k, item) {
					if (arrCat[i] == $(this).val()) {
						$(this).attr("checked", "true");
					}
				});
			}
		}
	}
	CKEDITOR.replace('content');
	$(function() {
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
		initBackPage("gotoBlogArticlePage.do");
	});
</script>