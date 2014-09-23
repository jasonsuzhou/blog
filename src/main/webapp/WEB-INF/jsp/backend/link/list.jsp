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
	<form class="form-inline definewidth m20" action="#"
		method="get">
		Filter： <input type="text" name="rolename" id="rolename"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">Search</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">Add</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>Name</th>
				<th>URL</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody id="dataTableBody">
			<!-- row data goes here -->
		</tbody>
		
	</table>
	<div class="inline pull-right page" id="paginator">
		
	</div>
</body>
</html>
<script type="text/javascript">
function appendRowData(dataList) {
	$('#dataTableBody').empty();
	$.each(dataList, function(i,item){
		var rowData = "<tr>"+
						"<td>"+item.name+"</td>"+
						"<td>"+item.url+"</td>"+
						"<td><a href=\"javascript:edit("+item.id+")\">Edit</a>"+
						" / <a href=\"javascript:del("+item.id+")\">Delete</a></td>"+
					"</tr>";
		$('#dataTableBody').append(rowData);
	});	
}
function edit(id) {
	initEditAction('gotoEditLinkPage.do', id);
}
function del(id) {
	initDelAction('adminDeleteLink.do', id);
}
$(function() {
	queryList(1, 'adminQueryLinks.do');
	initAddAction('gotoAddLinkPage.do');
});
</script>