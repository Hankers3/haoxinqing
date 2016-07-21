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
	
	<!--查看分账明细start-->

		<div class="modal-dialog modal_1000">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos"><aebiz:showTitle titleId="orderrouting.m.viewDetail"/></h3>
			</div>
			<div class="modal-body">
				<div class="box">	
					<div class="box-content nopadding">
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable-reorder">
							<thead>		
								<tr>
									<th><aebiz:showTitle titleId="orderrouting.m.orderUuid"/></th>
									<th><aebiz:showTitle titleId="orderrouting.m.orderCreater"/></th>
									<th><aebiz:showTitle titleId="orderrouting.m.mobile"/></th>	
									<th><aebiz:showTitle titleId="orderrouting.m.orderDate"/></th>										
									<th><aebiz:showTitle titleId="orderrouting.m.orderState"/></th>							
									<th><aebiz:showTitle titleId="orderrouting.m.orderTotalCost"/></th>
									<th><aebiz:showTitle titleId="orderrouting.m.systemRouting"/></th>				
									<th><aebiz:showTitle titleId="orderrouting.m.staffRouting"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${details}" var="order">
								<tr>
									<td>${order.orderId}</td>
									<td>${order.customerName}</td>
									<td>${order.customerMobile}</td>											
									<td>${order.orderTime}</td>
									<td>
									<c:if test="${orderMain.state=='1'}">
										<aebiz:showTitle titleId="ordermain.m.waitPay"/>
									</c:if>
									<c:if test="${orderMain.state=='2'}">
										<aebiz:showTitle titleId="ordermain.m.orderclose"/>
									</c:if>
									<c:if test="${orderMain.state=='3'}">
										<aebiz:showTitle titleId="ordermain.m.paid"/>
									</c:if>
									<c:if test="${orderMain.state=='4'}">
										<aebiz:showTitle titleId="ordermain.m.confirmed"/>
									</c:if>
									<c:if test="${orderMain.state=='5'}">
										<aebiz:showTitle titleId="ordermain.m.dropIn"/>
									</c:if>
									<c:if test="${orderMain.state=='6'}">
										<aebiz:showTitle titleId="ordermain.m.dropIncomplete"/>
									</c:if>
									<c:if test="${orderMain.state=='7'}">
										<aebiz:showTitle titleId="ordermain.m.measure"/>
									</c:if>
									<c:if test="${orderMain.state=='8'}">
										<aebiz:showTitle titleId="ordermain.m.working"/>
									</c:if>
									<c:if test="${orderMain.state=='9'}">
										<aebiz:showTitle titleId="ordermain.m.workcomplete"/>
									</c:if>
									<c:if test="${orderMain.state=='10'}">
										<aebiz:showTitle titleId="ordermain.m.waitCustomerAffirm"/>
									</c:if>
									<c:if test="${orderMain.state=='11'}">
										<aebiz:showTitle titleId="ordermain.m.Completed"/>
									</c:if>
									<c:if test="${orderMain.state=='12'}">
										无效订单
									</c:if>
									</td>
									<td>${order.payMoney}</td>
									<td>${routPrice}</td>
									<td>
									<c:forEach items="${order.orderroutings}" var="rout">	
										${rout.serviceStaffName}${rout.serviceStaffMobile}（
										<c:if test="${rout.grade=='1'}">
											<aebiz:showTitle titleId="orderrouting.m.orderCaptain"/>
										</c:if>
										<c:if test="${rout.grade !='1'}">
											<aebiz:showTitle titleId="orderrouting.m.orderNoCaptain"/>
										</c:if>
										，￥${rout.routPrice}）<br />
									</c:forEach>
									</td>
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

	<!--查看分账明细end-->
	
</body>
</html>
