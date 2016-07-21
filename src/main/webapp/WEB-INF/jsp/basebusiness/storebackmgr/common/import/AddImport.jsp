<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />

	<title>FLAT - Wizard</title>
	
	<!-- Bootstrap基本样式 页面基本样式，每个页面都引-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/bootstrap.min.css">
	<!-- icheck -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/icheck/all.css">
	<!-- Theme CSS FLAT模版基本样式，每个页面都引 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/style.css">
	<!-- 引用时间选择插件的CSS-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/timepicker/bootstrap-timepicker.min.css">
	<!-- 字体图标ie7兼容性处理 -->
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="css/font-awesome-ie7.min.css">
	<![endif]-->
	<!-- Color CSS FLAT模版皮肤样式，每个页面都引-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/themes.css">
	<!-- aebiz CSS 产品自定义样式，每个页面都引-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/aebiz-0.1.css">
	
	<!-- jQuery 库 ， 每个页面都引-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/jquery.min.js"></script>
	<!-- Bootstrap 框架的js，每个页面都引-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/bootstrap.min.js"></script>
	<!-- Nice Scroll 页面左侧边栏使用美化滚动条插件，每个页面都引 -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
	<!-- jQuery UI 这5个jquery.ui插件，每个页面都引-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/jquery-ui/jquery.ui.mouse.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/jquery-ui/jquery.ui.resizable.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/jquery-ui/jquery.ui.sortable.min.js"></script>
	
	<!-- Validation 表单验证插件-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/validation/jquery.validate.min.js"></script>
	<!-- 调用validate表单验证的js -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.validate.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
	<!-- 浮动按钮的js -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.buttonfloat.js"></script><!--页面中如果没有浮动按钮则不引入-->
	<!-- top left scripts 头部、左侧全局公共的js，每个页面都引-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz_global-0.1.js"></script>
	<!-- 调用时间选择插件的js-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.timepicker.js"></script>
	
	<!-- icheck -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/icheck/jquery.icheck.min.js"></script>
	<!-- icheck 调用 -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.checkbox.js"></script>
	
	<!--[if lte IE 9]>
		<script src="js/plugins/placeholder/jquery.placeholder.min.js"></script>
		<script>
			$(document).ready(function() {
				$('input, textarea').placeholder();
			});
		</script>
	<![endif]-->
	
	<!--ie8及以下支持响应式的js-->
	<!--[if lte IE 8]>
    <script src="js/respond.min.js"></script>
  <![endif]-->

	<!-- Favicon -->
	<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/static/storeback/img/favicon.ico" />
	<!-- Apple devices Homescreen icon -->
	<link rel="apple-touch-icon-precomposed" href="${pageContext.servletContext.contextPath}/static/storeback/img/apple-touch-icon-precomposed.png" />
	