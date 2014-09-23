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
<link href="css/message.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
<link href='http://fonts.googleapis.com/css?family=Architects+Daughter'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<header>
		<jsp:include page="menu/topnav.jsp"></jsp:include>
	</header>
	<article class="aboutcon">
		<h1 class="t_nav">
			<span>No pain No gain</span><a href="index.do" class="n1">Home</a><a
				href="gbook.do" class="n2">Guest Book</a>
		</h1>

		<div class="book left">
			<!-- Duoshuo Comment BEGIN -->
			<div class="ds-thread" id="ds-thread">
				<div id="ds-reset">
						
					<div class="ds-comments-info">
						<div class="ds-sort">
							<!-- 
							<a class="ds-order-desc ds-current" href="javascript:queryMessageList(1)">Latest</a><a href="javascript:queryMessageList(1) class="ds-order-asc">Earliest</a>
							 -->
						</div>
						<ul class="ds-comments-tabs">
							<li class="ds-tab">Total: <span class="ds-highlight">${totalCount }</span> Messages</li>
						</ul>
					</div>
					<ul class="ds-comments">
					<!-- message body goes here -->
					</ul>
					<div class="ds-paginator">
						
					</div>
					<a name="respond"></a>
					<div class="ds-login-buttons">
					</div>
					<div class="ds-replybox">
						<a class="ds-avatar" href="javascript:void(0);"
							onclick="return false"><img
							src="images/post.png" alt=""></a>
						<form method="post" name="postform">
							<div class="ds-textarea-wrapper ds-rounded-top">
								<textarea name="content" placeholder="Leave message for me..."></textarea>
								<pre class="ds-hidden-text"></pre>
							</div>
							<div class="ds-post-toolbar">
								<div class="ds-post-options ds-gradient-bg">
									<span class="ds-sync"></span>
								</div>
								<button class="ds-post-button" type="button">Post</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- Duoshuo Comment END -->
		</div>

		<aside class="right">
			<div class="about_c">
				<p>
					网名：<span>${data.username }</span>
				</p>
				<p>姓名：${data.realname }</p>
				<p>生日：${data.birthday }</p>
				<p>籍贯：${data.party }</p>
				<p>现居：${data.currentLocation }</p>
				<p>职业：${data.career }</p>
				<p>经验：${data.years }</p>
				<p>喜欢的书：${data.book }</p>
				<p>喜欢的运动：${data.hoppy }</p>
				<a target="_blank"
					href="http://wpa.qq.com/msgrd?v=3&uin=297383949&site=qq&menu=yes"><img
					border="0" src="http://wpa.qq.com/pa?p=2:297383949:51" alt="联系我吧"
					title="联系我吧" /></a> <a target="_blank"
					href="http://shang.qq.com/wpa/qunwpa?idkey=11a7f76e608a85bb788ff6c85be71993a46888a5f6cbe794804a356b17295d32"><img
					border="0" src="http://pub.idqqimg.com/wpa/images/group.png"
					alt="java爱好者" title="java爱好者"></a> <a target="_blank"
					href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=uIqBj4uAi4GMgfjJyZbb19U"
					style="text-decoration: none;"><img
					src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png" /></a>
				<img alt="" src="${data.imgUrl }">
			</div>
		</aside>
	</article>
	<jsp:include page="info/footer.jsp"></jsp:include>
	<script src="js/jquery.form.min.js"></script>
</body>
<script type="text/javascript">
function appendMessgeHtml(dataList) {
	$('.ds-comments').empty();
	$.each(dataList, function(i, item) {
		var post = "<li class=\"ds-post\">"+
					"<div class=\"ds-post-self\">"+
						"<div class=\"ds-avatar\">"+
							"<img src=\"images/post.png\">"+
						"</div>"+
						"<div class=\"ds-comment-body\">"+
							"<div class=\"ds-comment-header\">"+
								"<span class=\"ds-user-name\">"+item.userip+"</span>"+
							"</div>"+
							"<p>"+item.content+"</p>"+
							"<div class=\"ds-comment-footer ds-comment-actions\">"+
								"<span class=\"ds-time\">"+item.strPostDate+"</span>"+
								"<a class=\"ds-post-reply\" href=\"javascript:alert('You do not have permission to reply.');\"><span class=\"ds-icon ds-icon-reply\"></span>Reply</a>"+
							"</div>"+
						"</div>"+
					"</div>"+
					appendReplyMessageHtml(item.replyContent,item.strReplyDate) +
				"</li>";
		$('.ds-comments').append(post);
	});
}
function appendReplyMessageHtml(replyContent, replyDate) {
	if(typeof replyContent == "undefined" || null == replyContent || "" == replyContent) {
		return "";
	} else {
		var reply = "<ul class=\"ds-children\">"+
						"<li class=\"ds-post\">"+
						"<div class=\"ds-post-self\">"+
								"<div class=\"ds-avatar\">"+
									"<a><img src=\"images/reply.jpg\"></a>"+
								"</div>"+
								"<div class=\"ds-comment-body\">"+
									"<div class=\"ds-comment-header\">"+
										"<a class=\"ds-user-name\"><font color='red'>JasonYao</font></a>"+
									"</div>"+
									"<p>"+replyContent+"</p>"+
									"<div class=\"ds-comment-footer ds-comment-actions\">"+
										"<span class=\"ds-time\">"+replyDate+"</span>"+
									"</div>"+
								"</div>"+
							"</div></li>"+
					"</ul>";
		return reply;
	}
}
function appendPaginationHtml(curPage, perPage, totalCount) {
	$('.ds-paginator').empty();
	var totalPage = totalCount < perPage ? 1 : (totalCount % perPage == 0 ? totalCount / perPage : parseInt(totalCount / perPage)+1);
	var prePage = curPage == 1 ? 1 : curPage -1;
	var nextPage = curPage == totalPage ? totalPage : curPage + 1;
	var lastPage = totalPage;
	var pager = "<div class=\"ds-border\"></div>"+
				"<a>Page:"+curPage+"/"+totalPage+"</a>"+
				"<a href=\"javascript:queryMessageList("+1+");\" class=\"ds-current\">First</a>"+
				"<a href=\"javascript:queryMessageList("+prePage+");\" class=\"ds-current\">Previous</a>"+
				"<a href=\"javascript:queryMessageList("+nextPage+");\" class=\"ds-current\">Next</a>"+
				"<a href=\"javascript:queryMessageList("+lastPage+");\" class=\"ds-current\">Last</a>";
				
	$('.ds-paginator').append(pager);
}
function queryMessageList(curPage) {
	$.ajax({
		url: 'queryMessage.do',
		type: 'POST',
		dataType: 'json',
		data:{curPage:curPage},
		success: function(data) {
			$('.ds-highlight').empty();
			$('.ds-highlight').html(data.totalCount);
			appendMessgeHtml(data.dataList);
			appendPaginationHtml(curPage, 10, data.totalCount);
		},
		error:function(data) {
			alert("Error");
		}
	});
}
$(function(){
	$(function(){
		queryMessageList(1);
		$('.ds-post-button').click(function(){
			var options = {
					url: 'addMessage.do',
					type: 'POST',
					dataType: 'json',
					success: function(data){
						if(data.errorMessage) {
							alert(data.errorMessage);
						} else {
							$('.ds-highlight').empty();
							$('.ds-highlight').html(data.totalCount);
							appendMessgeHtml(data.dataList);
							appendPaginationHtml(1, 10, data.totalCount);
						}
					},
					error:function(data){
						alert("Internal server error, please try later.");
					}
			};
			$('form[name=postform]').ajaxSubmit(options);
			return false;
		});
	});
});
</script>
</html>
