<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<%@include file="/WEB-INF/jsp/basebusiness/common/import/SuccessImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<c:if test="${userType=='1'}" >
						<aebiz:showTitle titleId="vipclubintegrallog.moduleName_CN" />
					</c:if>
					
					<c:if test="${userType=='2'}" >
						医生积分管理
					</c:if>
					<aebiz:showTitle titleId="basebusiness.showmessage.manager" />
				</h1>
			</div>
		</div>

		<div class="breadcrumbs">
			<ul>
					<c:if test="${userType=='1'}" >
						<li><span><aebiz:showTitle titleId="vipclubintegrallog.menuOne" /></span> <i class="fa fa-angle-right"></i></li>
						<li><span><aebiz:showTitle titleId="vipclubintegrallog.menuTwo" /></span> <i class="fa fa-angle-right"></i></li>
				</c:if>
				<c:if test="${userType=='2'}" >
						<li><span>医生管理</span> <i class="fa fa-angle-right"></i></li>
						<li><span>医生积分管理</span> <i class="fa fa-angle-right"></i></li>
				</c:if>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.opeOkTitle" /></span></li>
			</ul>
		</div>

		<div class="y_masuccess">
			<span class="y_succpic text-success"><i class="glyphicon-ok"></i></span>
			<div class="y_succms">
				<h2 class="text-success">
					<aebiz:showTitle titleId="basebusiness.showmessage.opeOkTitle" />
				</h2>

				<p class="y_successtext">
					<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/toLogList/${customerUuid}"> <aebiz:showTitle titleId="basebusiness.showmessage.opeOk" /></a> 
					<c:if test="${userType=='1'}" >
						<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/sysback/vipclubintegralstat/toList"> <aebiz:showTitle titleId="basebusiness.showmessage.return" /></a>
					</c:if>
					
					<c:if test="${userType=='2'}" >
						<a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/sysback/vipclubintegralstat/toDcList"> <aebiz:showTitle titleId="basebusiness.showmessage.return" /></a>
					</c:if>
				</p>
			</div>
		</div>
	</div>
</body>

</html>