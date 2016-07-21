<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

	<title>好欣晴 - 登录</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/bootstrap.min.css">
	<!-- icheck -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
	<!-- Theme CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/style.css">
	<!-- Color CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/themes.css">

	<!-- jQuery -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/bootstrap.min.js"></script>
	<!-- Nice Scroll -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
	<!-- Validation -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/additional-methods.min.js"></script>
	<!-- icheck -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>

	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.js"></script>

	
	<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/static/sysback/img/favicon.ico" />	
</head>

<body class='login'>
	<div class="wrapper">
		<h1>
			<a href="index.html">
				<img src="${pageContext.servletContext.contextPath}/static/sysback/img/logo-big.png" alt="" class='retina-ready' width="210" height="70"></a>
		</h1>
		<div class="login-body">
			<h2>登录</h2>
			
			<form action="${pageContext.request.contextPath }/admin/login" method='post' class='form-validate' id="test">
				<div class="form-group">
					<div class="email controls">
						<input type="text" name='userId' placeholder="用户名" value="${loginName}" class='form-control' data-rule-required="true">
					</div>
				</div>
				<div class="form-group">
					<div class="pw controls">
						<input type="password" name="pwd" placeholder="密码" class='form-control' data-rule-required="true">
					</div>
				</div>
				<div class="submit">
					<div class="remember">
						<input type="checkbox" name="remember" value="1" class='icheck-me' data-skin="square" data-color="blue" id="remember">
						<label for="remember">记住我</label>
					</div>
					<input type="submit" value="登录" class='btn btn-primary'>
				</div>
			</form>
			
			<div class="forget">
				 <a href="#">
					<span></span>
				 </a> 
			</div>
		</div>
	</div>
</body>

</html>
