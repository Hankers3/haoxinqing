<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<head>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>  
</head>
<body>
<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos"><aebiz:showTitle titleId="orderrouting.m.wagesStaffRoutDetail"/></h3>
			</div>
			<div class="modal-body">
				<div class="box">	
					<div class="box-content nopadding">									
						<table class="table table-bordered mb_10">
							<thead>
								<tr>
									<th><aebiz:showTitle titleId="performance.m.serviceStaffName"/></th>
									<th><aebiz:showTitle titleId="orderrouting.m.wagesStaffRoutMonth"/></th>
									<th><aebiz:showTitle titleId="orderrouting.m.wagesStaffRoutTime"/></th>
									<th><aebiz:showTitle titleId="orderrouting.m.wagesStaffRoutMoney"/></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${details}" var="wage">
								<tr>
									<td>${wage.serviceStaffName}</td>
									<td>${routMonth}月</td>
									<td>${wage.routTime}</td>
									<td>${wage.routPrice}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn moresearch" data-dismiss="modal"><aebiz:showTitle titleId="orderrouting.m.close"/></button>
			</div>
		</div>
	</div>