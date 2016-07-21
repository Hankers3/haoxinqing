<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
<style>
	.form-horizontal.form-bordered .form-group .control-label {
    border-right: none;
	}	
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>患者充值记录</h1>
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
					<span>患者充值记录详情</span>
				</li>						
			</ul>				
		</div>		
		
			<div class="box box-bordered bordered-top">	
				<!--患者基本信息-->
				<div class="box-content nopadding" id="baseInfo">
					<div  class='form-horizontal form-validate form-bordered'>							
						<!--患者基本信息-->
						<div class="form-group">
							<div class="col-sm-12"  style="border-left:none">
								<div class="col-sm-3">
									<i class="fa fa-user"></i>患者基本信息
								</div>
							</div>
						</div>
						
						<!--流水号-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">患者ID</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${customerModel.customerId}
								</div>
							</div>
						</div>
						
						<!--用户名-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">用户名</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${customerModel.customerName}
								</div>
							</div>
						</div>
						
						<!--真实姓名-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">患者姓名</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${customerInfoModel.realName}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">性别</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${customerInfoModel.sex}
								</div>
							</div>
						</div>
						
						<!--手机号码-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">手机号</label>
							<div class="col-sm-10">
								<div class="col-sm-4">
										&nbsp;${customerModel.mobile}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">账户余额</label>
							<div class="col-sm-10">
								<div class="col-sm-4">
										&nbsp;${customerModel.accountAmount}
								</div>
							</div>
						</div>
							
						<!--患者基本信息-->
						<div class="form-group">
							<div class="col-sm-12"  style="border-left:none">
								<div class="col-sm-3">
									<i class="fa fa-user"></i>充值记录
								</div>
							</div>
						</div>
							
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th>充值方式</th>
									<th style="border-top:none;">充值金额</th>
									<th style="border-top:none;">充值时间</th>
								</tr>	
								<c:forEach items="${virtualAccountCustomerChargeModelList}" var="dpp">	
									<tr>										
										<td>
											<c:choose>
												<c:when test="${dpp.payType== '1'}">    
													微信   
											  </c:when>
											  <c:when test="${dpp.payType== '2'}">    
													支付宝   
											  </c:when>
											  <c:otherwise> 
													其他
											  </c:otherwise>
											</c:choose>						
										</td>
										<td>${dpp.operAmount}</td>
										<td>${dpp.createTime}</td>
									</tr>	
								</c:forEach>																	
						 	</tbody>
					  </table>						
					</div>
					
					<div class="y_fixbtnbox">
						<div class="form-actions col-sm-offset-2 col-sm-10 y_fixedbtn" style="width: 1423px;">
							<button type="button" class="btn btn-primary btn-large goback">返回</button>
					 </div>
				  </div>
					
				</div>
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