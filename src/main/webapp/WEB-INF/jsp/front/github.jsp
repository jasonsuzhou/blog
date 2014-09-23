<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include_js.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>姚敏华的Blog</title>
<meta name="keywords" content="java,j2ee,maven,SOA,oracle,mysql" />
<meta name="description" content="blog of jason yao" />
<link href="css/base.css" rel="stylesheet">
<link href="css/case.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
</head>
<body>
<header>
  <jsp:include page="menu/topnav.jsp"></jsp:include>
</header>
<article class="blogs">
<h1 class="t_nav"><span>You will get what you pay</span><a href="index.do" class="n1">Home</a><a href="github.do" class="n2">GitHub</a></h1>
<div class="caselist left">
<h2><a style="text-decoration: underline; color: blue;" href="https://github.com/jasonsuzhou" target="_blank">Click here to view my GitHub repository</a></h2>
<img alt="" src="images/githubhome.jpg" width="700px">
</div>
<aside class="right">
   <div class="rnav">
      <h2>My Work Display</h2>
      <ul>
       <c:forEach items="${dataList }" var="data">
       		<li><a href="${data.download_url }" target="_blank">${data.title }</a></li>
       </c:forEach>
     </ul>      
    </div>
<div class="news">
	<jsp:include page="info/latestnews.jsp"></jsp:include>
	<jsp:include page="info/topnews.jsp"></jsp:include>
	<jsp:include page="info/links.jsp"></jsp:include>
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
</html>