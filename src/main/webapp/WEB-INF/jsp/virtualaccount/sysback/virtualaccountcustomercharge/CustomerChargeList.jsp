<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>  
</head>

<body>
	
	<!--查看充值明细-->

		<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos"><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.chargedetails"/></h3>
			</div>
			<div class="modal-body">
				<div class="box">	
					<div class="box-content nopadding">									
						<table class="table table-bordered mb_10">
							<thead>
								<tr>
								  <!--<th><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.chargeid"/></th>-->
									<th><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.customername"/></th>
									<th><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.mobile"/></th>
									<th><aebiz:showTitle titleId="virtualaccountcustomercharge.m.operAmount"/></th>
									<th><aebiz:showTitle titleId="virtualaccountcustomercharge.m.createTime"/></th>
									<th>充值状态</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${charges}" var="charge">
								<tr>
									<!--<td>${charge.chargeNo}</td>-->
									<td>${charge.customerName}</td>
									<td>${charge.mobile }</td>
									<td>${charge.operAmount}</td>
									<td>${charge.createTime}</td>
									<td>	
									<c:if test="${charge.chargeState=='0'}">
										待支付
									</c:if>
									<c:if test="${charge.chargeState=='1'}">
										充值成功
									</c:if>
									<c:if test="${charge.chargeState=='2'}">
										充值失败
									</c:if>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn moresearch" data-dismiss="modal"><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.close"/></button>
			</div>
		</div>
	</div>

	<!--查看充值明细-->
	
</body>
</html>
