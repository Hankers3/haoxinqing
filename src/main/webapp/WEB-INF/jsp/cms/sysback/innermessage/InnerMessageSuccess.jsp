<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/SuccessImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="innermessage.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="innermessage.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="innermessage.menuTwo"/></span>
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
					<a class="btn btn-primary" href="#">
						<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>
					</a>  
					<!-- 表示添加医生的消息返回到查看医生消息列表的-->
					<c:if test="${judge=='1'}">
						<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/sysback/innermessage/toInnerMessageDoctorList">
					</c:if>
					 	<!-- 表示添加患者的消息返回到查看患者消息列表的-->
					<c:if test="${judge=='0'}">
						<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/sysback/innermessage/toInnerMessageCustomerList">
					</c:if>
							<aebiz:showTitle titleId="basebusiness.showmessage.return"/>
						</a>
				</p>	
			</div>
		</div>
	</div>
</body>

</html>