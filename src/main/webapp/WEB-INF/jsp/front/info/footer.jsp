<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<footer>
	<p>
	</p>
</footer>
<script type="text/javascript">
$(function(){
	$.ajax({
		  type: "POST",
		  url: "getFoorterInfo.do?randomid="+new Date().getTime(),
		  success: function(data) {
			  $('footer p').empty();
			  $('footer p').html(data);
		  },
		  dataType: "html"
		});
	
});
</script>