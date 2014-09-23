<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<h3 class="links">
	<p>
		<span>Links</span>
	</p>
</h3>
<ul class="website">
	<li><a target="_blank" href="http://open.mail.qq.com/">QQ mail
			API</a></li>
	<li><a target="_blank" href="http://qun.qq.com/join.html">QQ
			group API</a></li>
	<li><a target="_blank" href=" http://shang.qq.com/v3/widget.html">QQ
			chat API</a></li>
	<li><a target="_blank" href="http://i.tianqi.com/index.php?c=code">Wheather
			API</a></li>
</ul>
