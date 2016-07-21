<%@page import="com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html  class="overflow_html">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
	<title>FLAT - Dynamic tables</title>
	<!-- Bootstrap -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/bootstrap.min.css">
	<!-- Theme CSS FLAT模版基本样式 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/style.css">
	
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/font-awesome-ie7.min.css">
	<![endif]-->
	
	<!-- Color CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/themes.css">
	<!-- aebiz CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/aebiz-0.1.css">

	<!-- jQuery 库 -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/jquery.min.js"></script>
	

	<!-- Favicon -->
	<link rel="shortcut icon" href="img/favicon.ico" />
	<!-- Apple devices Homescreen icon -->
	<link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-precomposed.png" />
	
	<!--[if lt IE 9]>
    <script src="${pageContext.servletContext.contextPath}/static/sysback/js/respond.min.js"></script>
  <![endif]-->
</head>


<body class="theme-darkblue">
	<%
	 	BaseModel m = (BaseModel)session.getAttribute("Login_User");
	 	
		if(session.getAttribute("Login_User") == null) {
			request.getRequestDispatcher("/admin/toLogin").forward(request, response);
		}else{
			String loginUserUuid =m.getUuid();
	%>
		<div id="navigation"></div>
		<div id="content" class="container-fluid">
			<div id="left"><iframe name="menuFrame" frameborder=0 width="100%" height="100%" scrolling=no src="${pageContext.servletContext.contextPath}/admin/toMenu/-1?loginUserUuid=<%=loginUserUuid%>"></iframe></div>	
			<div id="main" class="aeb_main"><iframe name="mainFrame" frameborder=0 width="100%" height="100%" scrolling=yes style="overflow-x:hidden" src="${pageContext.servletContext.contextPath}/admin/toWelcome"></iframe></div>
		</div>
		<script>
			var loginUserUuid ="<%=loginUserUuid%>";
			
			//$("#navigation").load("${pageContext.servletContext.contextPath}/admin/toHeader");
		$("#navigation").load("${pageContext.servletContext.contextPath}/admin/toHeader?loginUserUuid="+loginUserUuid);
		</script>
	<%
		}
	%>
	
	<!-- Bootstrap 框架的js-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/bootstrap.min.js"></script>
	<!-- jQuery UI 这4个jquery.ui插件，左侧宽度可拖动-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.mouse.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.resizable.min.js"></script>
	<!-- top left scripts 头部、左侧全局公共的js-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz_global-0.1.js"></script>
</body>
</html>
