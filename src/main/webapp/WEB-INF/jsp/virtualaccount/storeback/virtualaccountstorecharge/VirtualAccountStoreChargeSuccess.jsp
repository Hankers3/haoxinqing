<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/AddImport.jsp" %>
</head>

<body>
<!-- 头部 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
<div class="container-fluid" id="content">
<!-- 左侧 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
	<div id="main">	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="virtualaccountstorecharge.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="virtualaccountstorecharge.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="withdrawapply.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="virtualaccountstorecharge.moduleName_CN"/></span>
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
					<a class="btn btn-primary" href="#"><aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/></a>  <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/storeback/virtualaccountstorecharge/toMyAccount"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
				</p>	
			</div>
		</div>
	</div>
	</div>
</div>
</body>

</html>