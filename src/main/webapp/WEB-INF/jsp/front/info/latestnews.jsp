<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<h3>
	<p>
		Latest<span>News</span>
	</p>
</h3>
<ul class="rank">
</ul>
<script type="text/javascript">
<!--
function appendRows(dataList) {
	$('.rank').empty();
	$.each(dataList, function(i,item){
		var showContent = "<li><a href=\"gotoBlogDetail.do?id="+
						item.id +
						"\" title=\""+
						item.title + 
						"\" target=\"_blank\">" +
						item.title+
						"</a></li>";
		$('.rank').append(showContent);
	});
}
$(function(){
	$.ajax({
		url : 'queryLatestArticle.do',
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			appendRows(data.dataList);
		}
	});
});
//-->
</script>
