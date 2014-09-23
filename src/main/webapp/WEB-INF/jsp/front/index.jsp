<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include_js.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<title>Blog of Jason Yao</title>
<meta charset="UTF-8">
<meta name="keywords" content="java,j2ee,maven,SOA,oracle,mysql" />
<meta name="description" content="blog of jason yao" />
<link href="css/base.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
</head>
<body>
<header>
  <jsp:include page="menu/topnav.jsp"></jsp:include>
</header>
<div class="banner">
  <section class="box">
    <ul class="texts">
      <p>Hello everyone, my name is Jason Yao.</p>
      <p>Welcome to visit my blog.</p>
      <p>Hope you can enjoy here.</p>
    </ul>
    <div class="avatar"><a target="_blank" href="http://blog.csdn.net/yaominhua"><span>姚敏华</span></a> </div>
  </section>
</div>
<div class="template">
<div class="box">
	<h3>
		<p>
			<span>Latest</span> Work Display
		</p>
	</h3>
	<ul>
		<c:forEach items="${workDisplay }" var="data">
			<li>
				<a href="${data.download_url }" target="_blank"><img src="${data.summary_view_url }">
			</a>
			<span>${data.title }</span>
		</li>
		</c:forEach>
		
		<li><a href="#" target="_blank"><img src="images/archive/work_more.png"></a><span>More...</span></li>
	</ul>
</div>
</div>
<article>
  <h2 class="title_tj">
    <p>Top<span>Articles</span></p>
  </h2>
  <div class="bloglist left" id="top_blog_list">
			
  </div>
  <aside class="right">
    <div class=""><iframe width="250" scrolling="no" height="90" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=7"></iframe></div>
    <br/>
    <!-- right side menu start -->
			<div class="news">
				<!-- latest news start -->
				<h3>
					<p>
						Latest<span>News</span>
					</p>
				</h3>
				<ul class="rank">
					<c:forEach items="${latestNews }" var="item">
						<li><a href="gotoBlogDetail.do?id=${item.id }" title="${item.title }" target="_blank">${item.title }</a></li>
					</c:forEach>
				</ul>
				<!-- latest news end -->
				<%-- top news start --%>
				<h3 class="ph">
					<p>
						Top<span>News</span>
					</p>
				</h3>
				<ul class="paih">
					<c:forEach items="${topNews }" var="item">
						<li><a href="gotoBlogDetail.do?id=${item.id }" title="${item.title }" target="_blank">${item.title }</a></li>
					</c:forEach>
				</ul>
				<%-- top news end --%>
				<!-- links start -->
				<h3 class="links">
					<p>
						<span>Links</span>
					</p>
				</h3>
				<ul class="website">
					<c:forEach items="${linkList }" var="item">
						<li><a target="_blank" href="${item.url }">${item.name }</a></li>
					</c:forEach>
				</ul>
				<!-- links end -->
			</div>
			<!-- right side menu end -->
    <!-- Baidu Button BEGIN -->
    <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"><a class="bds_tsina"></a><a class="bds_qzone"></a><a class="bds_tqq"></a><a class="bds_renren"></a><span class="bds_more"></span><a class="shareCount"></a></div>
    <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585" ></script> 
    <script type="text/javascript" id="bdshell_js"></script> 
    <script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
</script> 
    <!-- Baidu Button END -->   
    <a href="#" class="wechat"> </a></aside>
</article>
<jsp:include page="info/footer.jsp"></jsp:include>
</body>
</html>
<script type="text/javascript">
function appendTopArtileRow(dataList) {
	$('#top_blog_list').empty();
	$.each(dataList, function(i, item){
		var article = 	"<h3>"+
		"<a href=\"gotoBlogDetail.do?id="+item.id+"\" target=\"_blank\">"+item.title+"</a>"+
		"</h3>"+
	    "<figure><img src=\""+item.summaryImg+"\"></figure>"+
	    "<ul>"+
	      "<p>"+getSummaryContent(item.content)+"</p>"+
	      "<a href=\"gotoBlogDetail.do?id="+item.id+"\" target=\"_blank\" class=\"readmore\">More>></a>"+
	    "</ul>"+
	    "<p class=\"dateview\"><span>"+item.strPostDate+"</span><span>Author: "+item.author+"</span>"+
	    "<span>Blog [<a href=\"#\">"+item.category+"</a>]</span><span>Access: "+item.total+"</span></p>";
	    $('#top_blog_list').append(article);
	});
}
$(function(){
	$.ajax({
		url : 'queryTopArticle.do',
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			appendTopArtileRow(data.dataList);
		}
	});
});
</script>