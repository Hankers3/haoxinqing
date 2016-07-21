<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/SuccessImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>s</h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>1</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>1</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.opeOkTitle"/></span>
				</li>
			</ul>					
		</div>
		
		<div class="y_masuccess">
			<span class="y_succpic text-success"><i class="glyphicon-ok"></i></span>
			<div class="y_succms">
				<h2 class="text-success">
					<aebiz:showTitle titleId="basebusiness.showmessage.opeOkTitle"/>
				</h2>
					
				<p class="y_successtext">
					<a class="btn btn-primary" href="#"><aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/></a>  <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/sysback/region/toList"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
				</p>	
			</div>
		</div>
	</div>
</body>

</html>