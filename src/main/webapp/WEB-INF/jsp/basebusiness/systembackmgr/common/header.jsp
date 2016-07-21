<%@ page contentType="text/html; charset=utf-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<title>好欣晴 -管理后台</title>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz_header-0.1.js"></script>
</head>

<body class="theme-darkblue">
		<div class="container-fluid">
			<a href="#" id="brand"></a>	
				<a href="#" class="toggle-nav" rel="tooltip" data-placement="bottom" title="Toggle navigation">
					<i class="fa fa-bars"></i>
				</a>
			
				<ul class='main-nav'>
					<c:forEach items="${Menu_One}" var="smmOne">	
						<li>
							<a href="${pageContext.servletContext.contextPath}/admin/toMenu/${smmOne.uuid}" target="menuFrame">
								<span>${smmOne.name}</span>
							</a>
						</li>
					</c:forEach>
				</ul>
			<div class="user">
				<div class="dropdown">
					<a href="#" class='dropdown-toggle' data-toggle="dropdown">${Login_User.name}
						<img src="${pageContext.servletContext.contextPath}/static/sysback/img/demo/user-avatar.jpg" alt="">
					</a>
					<ul class="dropdown-menu pull-right">
						<li>
							<a href="${pageContext.servletContext.contextPath}/admin/logout">退出</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
</body>
</html>
