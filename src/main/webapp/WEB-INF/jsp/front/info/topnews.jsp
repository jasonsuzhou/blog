<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3 class="ph">
	<p>
		Top<span>News</span>
	</p>
</h3>
<ul class="paih">
</ul>
<script type="text/javascript">
<!--
function appendTopRows(dataList) {
	$('.paih').empty();
	$.each(dataList, function(i,item){
		var showContent = "<li><a href=\"gotoBlogDetail.do?id="+
						item.id +
						"\" title=\""+
						item.title + 
						"\" target=\"_blank\">" +
						item.title+
						"</a></li>";
		$('.paih').append(showContent);
	});
}
$(function(){
	$.ajax({
		url : 'queryTopArticle.do',
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			appendTopRows(data.dataList);
		}
	});
});
//-->
</script>
