<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

<!--设置浮动按钮-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>

</head>

<body>

		<div class="page-header">
			<div class="pull-left">
				<h1>患者消费明细</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>财务管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>患者财务管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>患者消费明细</span>
				</li>
			</ul>
		</div>
				
			<div class="box-title">
				<div class="actions"><a class="btn btn-mini content-slideUp" href="#"><i class="fa fa-angle-down"></i></a></div>
			</div>
			
			
				<div class="row y_orderbox">
					<div class="col-sm-12">
						<div class="y_odxxbx">
							<h4>患者基本信息</h4>
					    <table class="table table-bordered">
								<tbody>
									<tr>
										<th width="15%">患者ID</th>
										<td width="85%">${cm.customerId}</td>
									</tr>
									<tr>	
										<th>用户名</th>
										<td>
									 ${cm.customerName}
										</td>
									</tr>
									<tr>
										<th>姓名</th>
										<td>
										${cim.realName}
										</td>
									</tr>
									<tr>
										<th width="15%">性别</th>
										<td width="85%"><c:if test="${cim.sex=='1'}">男</c:if></td>
										<td width="85%"><c:if test="${cim.sex=='2'}">女</c:if></td>
									</tr>
									<tr>	
										<th>手机号</th>
										<td>
										${cm.mobile}
										</td>
									</tr>
									<tr>
										<th>账户余额</th>
										<td>
									  ${vm.nowBalance}
										</td>
									</tr>
								</tbody>
							</table>	
						</div>
					</div>
					<div class="col-sm-12">
						<div class="y_odxxbx">
							<h4>消费记录</h4>
					    <table class="table table-bordered">
								<tbody>
										<tr>
										<th width="15%">消费类型</th>
								    <td width="85%"><c:if test="${vm.payType=='1'}">电话咨询</c:if></td>
										<td width="85%"><c:if test="${vm.payType=='2'}">私人医生</c:if></td>
									</tr>
									<tr>	
										<th>消费金额</th>
										<td>
									 ${vm.operAmount}
										</td>
									</tr>
									<tr>
										<th>支付方式</th>
										
										<td ><c:if test="${vm.orderType=='1'}">线上支付</c:if>
												<c:if test="${vm.orderType=='2'}">线下支付</c:if>
										</td>
									</tr>
									<tr>
										<th>消费时间</th>
										<td>
										${vm.orderTime}
										</td>
								</tbody>
							</table>
								
						</div>
						 
					</div>
					<div class="y_fixedbtn">
         <button type="button" class="btn cancel btn-large a_size_1">返回</button>
			    </div>
				</div>
      
	
</body>
</html>
<script>
    $(document).ready(function() {
			$(".cancel").click(function(){
				history.go(-1) ;
			});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>