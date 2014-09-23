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
				<th>Title</th>
				<th>Post Date</th>
				<th>Access Counts</th>
				<th>Category</th>
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
						"<td>"+item.title+"</td>"+
						"<td>"+item.strPostDate+"</td>"+
						"<td>"+item.total+"</td>"+
						"<td>"+item.category+"</td>"+
						"<td><a href=\"javascript:gotoEdit("+item.id+")\">Edit</a>"+
						" / <a href=\"javascript:del("+item.id+")\">Delete</a></td>"+
					"</tr>";
		$('#dataTableBody').append(rowData);
	});	
}
function appendPaginator(curPage, perPage, totalCount) {
	var totalPage = totalCount < perPage ? 1 : (totalCount % perPage == 0 ? totalCount / perPage : parseInt(totalCount / perPage)+1);
	var prePage = curPage == 1 ? 1 : curPage -1;
	var nextPage = curPage == totalPage ? totalPage : curPage + 1;
	var lastPage = totalPage;
	$('#paginator').empty();
	var pager = totalCount + " Records Page:" + curPage + "/" + totalPage + 
				"<a href='javascript:queryMessage("+1+")'>First</a>"+
				"<a href='javascript:queryMessage("+prePage+")'>Previous</a>"+
				"<a href='javascript:queryMessage("+nextPage+")'>Next</a>"+
				"<a href='javascript:queryMessage("+lastPage+")'>Last</a>";
	$('#paginator').append(pager);
	
}
function queryMessage(curPage, searchCondition) {
	$.ajax({
		url : 'adminQueryArticle.do',
		type : 'POST',
		data : {curPage:curPage, condition:searchCondition},
		dataType : 'json',
		success : function(data) {
			appendRowData(data.dataList);
			appendPaginator(curPage, 10, data.totalCount);
		},
		error : function(data) {
			alert("Internal server error, please try later.");
		}
	});
}
function gotoEdit(id) {
	initEditAction("gotoEditBlogArticlePage.do", id);
}

$(function() {
	queryMessage(1);
	initAddAction("gotoAddBlogArticlePage.do");
});

function del(id) {
	initDelAction("adminDeleteBlogArticle.do", id);
}
</script>