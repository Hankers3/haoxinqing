<!--头部导航#navigation-->
<div id="top">
</div>
<!--头部导航#navigation end-->
<script>
$(function(){
	$.get("${pageContext.servletContext.contextPath}/store/getHead",
			function(data) {
				$("#top").html(data);
			});
});
</script>