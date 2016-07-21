<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/SuccessImport.jsp" %>
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>

</head>

<body>
	<div id="navigation">
		<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp" %>
	</div>
	<div class="container-fluid" id="content">
		<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp" %>
		<div class="container-fluid">
			<div id="main">	
				<div class="page-header">
					<div class="pull-left">
					<h1><aebiz:showTitle titleId="editbankbound.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
				</div>
			</div>
			
			<div class="breadcrumbs">
				<ul>
					<li>
						<span><aebiz:showTitle titleId="editbankbound.menuOne"/></span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span><aebiz:showTitle titleId="editbankbound.menuTwo"/></span>
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
						<a class="btn btn-primary" href="#"><aebiz:showTitle titleId="editbankbound.m.reVilidateCode"/></a>  <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/storeback/storeEditBankBound/"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
					</p>	
				</div>
			</div>
		</div>
	</div>
</body>

</html>