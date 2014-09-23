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
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>Type</th>
				<th>IP</th>
				<th>Is Black</th>
				<th>Attempt Times</th>
				<th>Total Attempt Times</th>
				<th>Success</th>
				<th>Remark</th>
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
						"<td>"+item.type+"</td>"+
						"<td>"+item.ip+"</td>"+
						"<td>"+item.isBlack+"</td>"+
						"<td>"+item.attempTimes+"</td>"+
						"<td>"+item.totalAttempTimes+"</td>"+
						"<td>"+item.success+"</td>"+
						"<td>"+item.remark+"</td>"+
						"<td><a href=\"javascript:edit("+item.id+")\">Edit</a>"+
						" / <a href=\"javascript:del("+item.id+")\">Delete</a></td>"+
					"</tr>";
		$('#dataTableBody').append(rowData);
	});	
}
function edit(id) {
	initEditAction('gotoEditAuditorPage.do', id);
}
function del(id) {
	initDelAction('adminDeleteAuditor.do', id);
}
$(function() {
	queryList(1, 'adminQueryAuditor.do');
});
</script>