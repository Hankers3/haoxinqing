<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- jQuery 库 -->
	<script src="${pageContext.servletContext.contextPath}/static/frontpage/js/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/userfront/css/jquery-ui.css">
	<script src="${pageContext.servletContext.contextPath}/static/userfront/js/jquery-ui.js"></script>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/userfront/css/jquery.autocomplete.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/userfront/css/jquery-ui-1.8.17.custom.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/frontpage/css/index.css">
<!-- colorbox -->
	<!--头部-->
	<div id="simpleTop">
	</div>
	<script>
		$("#simpleTop").load("${pageContext.servletContext.contextPath}/getSimpleTop?ranNum=" + Math.random());
	</script>


