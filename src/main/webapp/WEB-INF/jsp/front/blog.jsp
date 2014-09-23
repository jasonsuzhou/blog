<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="include_js.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="gb2312">
<title>姚敏华的Blog</title>
<meta name="keywords" content="java,j2ee,maven,SOA,oracle,mysql" />
<meta name="description" content="blog of jason yao" />
<link href="css/base.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<style type="text/css">
.rnav { width: 250px; margin: 20px 0; overflow: hidden; background: url(images/6167.png) no-repeat left top }
.rnav h2 { font-size: 14px; padding-left: 40px; line-height: 30px; height: 30px; margin-bottom: 10px }
</style>
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
</head>
<body>
<header>
  <jsp:include page="menu/topnav.jsp"></jsp:include>
</header>
<article class="blogs">
<h1 class="t_nav"><span>Never say Never</span><a href="index.do" class="n1">Home</a><a href="blog.do" class="n2">Blog</a></h1>
<div class="newblog left" id="article_list">
   	<!-- article list goes here -->
</div>
<aside class="right">
   <div class="rnav">
   	  <h2>Categories</h2>
      <ul id="category_list">
       
     </ul>      
    </div>
<div class="news">
	<jsp:include page="info/latestnews.jsp"></jsp:include>
    <jsp:include page="info/topnews.jsp"></jsp:include>
</div>
    <div class="visitors">
      <h3><p>Share</p></h3>
      <ul>	

      </ul>
    </div>
     <!-- Baidu Button BEGIN -->
    <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"><a class="bds_tsina"></a><a class="bds_qzone"></a><a class="bds_tqq"></a><a class="bds_renren"></a><span class="bds_more"></span><a class="shareCount"></a></div>
    <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585" ></script> 
    <script type="text/javascript" id="bdshell_js"></script> 
    <script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
</script> 
    <!-- Baidu Button END -->   
</aside>
</article>
<jsp:include page="info/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
function appendCategories(dataList) {
	$('#category_list').empty();
	$.each(dataList, function(i, item){
		var category = "<li class=\"rnav"+(i+1)+"\"><a href=\"#\" target=\"_blank\">"+item.name+"("+item.total+")</a></li>";
		$('#category_list').append(category);
	});
}
function appendArticles(dataList, curPage, totalCount) {
	$('#article_list').empty();
	$.each(dataList, function(i, item){
		var content =  "<h2>"+
			"<a href=\"gotoBlogDetail.do?id="+item.id+"\" target=\"_blank\">"+item.title+"</a>"+
			"</h2>"+
		   "<p class=\"dateview\"><span>Date："+item.strPostDate+"</span><span>Author："+item.author+"</span><span>Category：[<a href=\"#\">"+item.category+"</a>]</span><span>Access: "+item.total+"</span></p>"+
		    "<figure><img src=\""+item.summaryImg+"\"></figure>"+
		    "<ul class=\"nlist\">"+
		      "<p>"+getSummaryContent(item.content)+"</p>"+
		      "<a title=\"\" href=\"gotoBlogDetail.do?id="+item.id+"\" target=\"_blank\" class=\"readmore\">Read More>></a>"+
		    "</ul>"+
		    "<div class=\"line\"></div>";
		$('#article_list').append(content);
	});
	$('#article_list').append("<div class=\"blank\"></div>");
	$('#article_list').append(generatePaginator(curPage, 10, totalCount));
}
function generatePaginator(curPage,perPage,totalCount) {
	var pagers = calPagers(curPage, perPage, totalCount);
	var pager = "<div class=\"page\">"+
				"<a title=\"Total record\"><b>"+totalCount+"</b></a>"+
				"<a title=\"Page\"><b>"+curPage+"/"+pagers.totalPage+"</b></a>"+
				"<a href=\"javascript:queryBlog("+1+");\">&lt;&lt;</a>"+
				"<a href=\"javascript:queryBlog("+pagers.prePage+");\">&lt;</a>"+
				"<a href=\"javascript:queryBlog("+pagers.nextPage+");\">&gt;</a>"+
				"<a href=\"javascript:queryBlog("+pagers.lastPage+");\">&gt;&gt;</a>"+
				"</div>";
	return pager;
}
function queryBlog(curPage) {
	$.ajax({
		url : 'queryBlogList.do',
		type : 'POST',
		data : {curPage:curPage, perPage:10},
		dataType : 'json',
		success : function(data) {
			appendCategories(data.categoryList);
			appendArticles(data.dataList, curPage, data.totalCount);
		},
		error: function(data) {
			alert('Internal server error, please try later.');
		}
	});
}
$(function(){
	queryBlog(1);
});
</script>
</html>