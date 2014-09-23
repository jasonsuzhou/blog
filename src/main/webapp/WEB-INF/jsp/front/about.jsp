<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include_js.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>姚敏华的Blog</title>
<meta name="keywords" content="java,j2ee,maven,SOA,oracle,mysql" />
<meta name="description" content="blog of jason yao" />
<link href="css/base.css" rel="stylesheet">
<link href="css/about.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
<link href='http://fonts.googleapis.com/css?family=Architects+Daughter' rel='stylesheet' type='text/css'>
</head>
<body>
<header>
  <jsp:include page="menu/topnav.jsp"></jsp:include>
</header>
<article class="aboutcon">
<h1 class="t_nav"><span>Never say Never</span><a href="index.do" class="n1">Home</a><a href="about.do" class="n2">About Me</a></h1>
<div class="about left">
  <h2>Little about me</h2>
    <ul> 
     ${data.aboutMe }
    </ul>
    <h2>About my blog</h2>
    ${data.aboutBlog }
</div>
<aside class="right">  
    <div class="about_c">
    <p>网名：<span>${data.username }</span></p>
    <p>姓名：${data.realname } </p>
    <p>生日：${data.birthday }</p>
    <p>籍贯：${data.party }</p>
    <p>现居：${data.currentLocation }</p>
    <p>职业：${data.career }</p>
    <p>经验：${data.years }</p>
    <p>喜欢的书：${data.book }</p>
    <p>喜欢的运动：${data.hoppy }</p>
<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=297383949&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:297383949:51" alt="联系我吧" title="联系我吧"/></a>
<a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=11a7f76e608a85bb788ff6c85be71993a46888a5f6cbe794804a356b17295d32"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="java爱好者" title="java爱好者"></a>
<a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=uIqBj4uAi4GMgfjJyZbb19U" style="text-decoration:none;"><img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png"/></a>
<img alt="" src="${data.imgUrl }">
</div>     
</aside>
</article>
<jsp:include page="info/footer.jsp"></jsp:include>
</body>
</html>