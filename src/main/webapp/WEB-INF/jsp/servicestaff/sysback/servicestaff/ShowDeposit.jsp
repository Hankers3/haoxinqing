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
	
	<!--查看退押金-->

		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos">查看</h4>
				</div>
				<div class="modal-body">							
					<div class="box">	
						<div class="box-content nopadding">									
							<table class="table table-bordered mb_10">
								<thead>
									<tr>
										<th>收款人</th>
										<th>收款帐号</th>
										<th>押金金额</th>
										<th>退款时间</th>
										<th>退款状态</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${t.serviceStaffinfoRealName}</td>
										<td>${t.mobile}</td>
										<td>${t.deposit}</td>
										<td>${t.depositTime}</td>	
										<td>
											<c:choose>
												<c:when test="${t.depositState=='1'}">押金未收取</c:when>
												<c:when test="${t.depositState=='2'}">押金已收取</c:when>
												<c:when test="${t.depositState=='3'}">押金已退还</c:when>
											</c:choose>	
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn clearMoreSearch"  data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>

	<!--查看退押金-->
	
</body>
</html>
