<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include_js.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="gb2312">
<title>姚敏华的Blog</title>
<meta name="keywords" content="java,j2ee,maven,SOA,oracle,mysql" />
<meta name="description" content="blog of jason yao" />
<link href="css/base.css" rel="stylesheet">
<link href="css/mood.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
</head>
<body>
	<header>
		<jsp:include page="menu/topnav.jsp"></jsp:include>
	</header>
	<div class="moodlist">
		<h1 class="t_nav">
			<span>Never say Never</span> <a href="index.do" class="n1">Home</a><a
				href="moments.do" class="n2">Moments</a>
		</h1>
		<div class="bloglist">
			<!-- blog list goes here -->
		</div>
		<div class="page">
			<!-- pagination goes here -->
		</div>
	</div>
	<jsp:include page="info/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
function appendMomentsHtml(dataList) {
	$('.bloglist').empty();
	$.each(dataList, function(i, item){
		var bloglist = "<ul class=\"arrow_box\">"+
						"<div class=\"sy\">"+
							"<p>"+item.content+"</p>"+
						"</div>"+
						"<span class=\"dateview\">"+item.strPostDate+"</span>"+
						"</ul>";
		$('.bloglist').append(bloglist);
	});
}
function appendPaginationHtml(curPage, perPage, totalCount) {
	$('.page').empty();
	var totalPage = totalCount < perPage ? 1 : (totalCount % perPage == 0 ? totalCount / perPage : parseInt(totalCount / perPage)+1);
	var prePage = curPage == 1 ? 1 : curPage -1;
	var nextPage = curPage == totalPage ? totalPage : curPage + 1;
	var lastPage = totalPage;
	var pager = "<a title=\"Total record\"><b>"+totalCount+"</b></a>"+
				"<a title=\"Page\"><b>"+curPage+"/"+totalPage+"</b></a>"+
				"<a href=\"javascript:queryMoments("+1+");\">&lt;&lt;</a>"+
				"<a href=\"javascript:queryMoments("+prePage+");\">&lt;</a>"+
				"<a href=\"javascript:queryMoments("+nextPage+");\">&gt;</a>"+
				"<a href=\"javascript:queryMoments("+lastPage+");\">&gt;&gt;</a>";
	$('.page').append(pager);
}
function queryMoments(curPage) {
	$.ajax({
		url : 'queryMoments.do',
		type : 'POST',
		data : {curPage:curPage},
		dataType : 'json',
		success : function(data) {
			appendMomentsHtml(data.dataList);
			appendPaginationHtml(curPage, 10, data.totalCount);
		},
		error : function(data) {
			alert("Internal server error, please try later.");
		}
	});
}
$(function(){
	queryMoments(1);
});
</script>
</html>