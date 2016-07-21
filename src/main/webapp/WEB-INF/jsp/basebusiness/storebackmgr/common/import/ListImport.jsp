<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />

	<title>FLAT - 商户列表页</title>

	<!-- Bootstrap基本样式 页面基本样式，每个页面都引-->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/bootstrap.min.css">
	<!-- chosen -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/chosen/chosen.css"><!--模拟下拉框的css，datatable左下角下拉框,页面没用到datatable和美化下拉框就不需引用-->
	<!-- Theme CSS FLAT模版基本样式，每个页面都引 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/style.css">
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/font-awesome-ie7.min.css">
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
	<!-- Bootbox -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/bootbox/jquery.bootbox.js"></script><!-- 点击删除表数据时的弹出提示框 -->
	
		<!-- datepicker -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/datepicker/bootstrap-datepicker.js"></script><!-- 日历插件的js -->
	<!-- dataTables -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/datatable/jquery.dataTables.min.js"></script><!--基本的动态数据表插件-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/datatable/ColReorderWithResize.js"></script><!--动态数据表格列可拖动的插件-->
	<!-- Chosen -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/chosen/chosen.jquery.min.js"></script><!--模拟下拉框的js，datatable左下角下拉框,页面没用到datatable和美化下拉框就不需引用-->
	<!-- datepicker use-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.datepicker.js"></script><!-- 调用日历插件的js -->
	<!-- top left scripts 头部、左侧全局公共的js，每个页面都引-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz_global-0.1.js"></script>

	<!-- Favicon -->
	<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/static/storeback/img/favicon.ico" />
	<!-- Apple devices Homescreen icon -->
	<link rel="apple-touch-icon-precomposed" href="${pageContext.servletContext.contextPath}/static/storeback/img/apple-touch-icon-precomposed.png" />