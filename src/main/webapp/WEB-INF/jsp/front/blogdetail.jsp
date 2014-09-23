<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="include_js.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${data.title }</title>
<meta name="keywords" content="java,j2ee,maven,SOA,oracle,mysql" />
<meta name="description" content="blog of jason yao" />
<link href="css/base.css" rel="stylesheet">
<link href="css/new.css" rel="stylesheet">
<link href="admin_Js/ckeditor/plugins/codesnippet/lib/highlight/styles/school_book.css" rel="stylesheet">
<script src="admin_Js/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
</head>
<body>
<header>
  <jsp:include page="menu/topnav.jsp"></jsp:include>
</header>
<article class="blogs">
  <h1 class="t_nav"><span>Current Location：<a href="index.do">Home</a>&nbsp;&gt;&nbsp;<a href="blog.do">Blog</a>&nbsp;&gt;&nbsp;<a href="#">${data.category }</a></span>
  	<a href="index.do" class="n1">Home</a><a href="blog.do" class="n2">Blog</a>
  </h1>
  <div class="index_about">
    <h2 class="c_titile">${data.title }</h2>
    <p class="box_c"><span class="d_time">Date：${data.strPostDate }</span><span>Author：${data.author }</span><span>Access: ${data.total}</span></p>
    <ul class="infos">
      <p>${data.content }</p>
    </ul>
    <c:if test="${data.category == '作品展示' || data.category == 'Work Display'}">
	    <div class="infops">
	      <p class="bt-blue"><a href="${data.downloadUrl }" target="_blank">下 载</a></p>
	    </div>
    </c:if>
    <div class="keybq">
    <p><span>Key Words</span>：${data.keyWords }</p>
    
    </div>
    <div class="ad"> </div>
    <div class="nextinfo">
      <p>Previous Article：<a href="gotoBlogDetail.do?id=${preArticleId }">${preArticleTitle }</a></p>
      <p>Next Article：<a href="gotoBlogDetail.do?id=${nextArticleId }">${nextArticleTitle }</a></p>
    </div>
    <div class="otherlink">
      <h2>Related Articles</h2>
      <ul>
        ${relatedArticles }
      </ul>
    </div>
  </div>
  <aside class="right">
  <!-- Baidu Button BEGIN -->
    <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"><a class="bds_tsina"></a><a class="bds_qzone"></a><a class="bds_tqq"></a><a class="bds_renren"></a><span class="bds_more"></span><a class="shareCount"></a></div>
    <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585" ></script> 
    <script type="text/javascript" id="bdshell_js"></script> 
    <script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
</script> 
    <!-- Baidu Button END -->
  
    <div class="blank"></div>
    <div class="news">
      <jsp:include page="info/latestnews.jsp"></jsp:include>
      <jsp:include page="info/topnews.jsp"></jsp:include>
    </div>
    <div class="visitors">
      <h3>
        <p>TODO...</p>
      </h3>
      <ul>
      </ul>
    </div>
  </aside>
</article>
<jsp:include page="info/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
hljs.initHighlightingOnLoad();
</script>
</html>