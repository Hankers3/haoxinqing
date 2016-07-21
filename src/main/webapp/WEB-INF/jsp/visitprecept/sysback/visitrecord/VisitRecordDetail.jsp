<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
	<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>随访申请详情</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>随访系统</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>随访管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>随访申请</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>随访申请详情</span>
					<i class="fa fa-angle-right"></i>
				</li>
			</ul>
		</div>

	
		<div  class="form-horizontal form-bordered border1-top">	
			<div class="form-group" style="border-right: 1px solid #ddd;">
				<div class="col-sm-12">
					<i class="fa fa-user"></i>患者基本信息
				</div>
			</div>
			<table class="table table-bordered mb_0">
	      <tbody>
						<tr>
							<th width="15%">患者ID</th>
							<td width="85%">${m.customerId}</td>
						</tr>
						<tr>	
							<th>用户名</th>
							<td>
							 ${m.customerName}
							</td>
						</tr>
						<tr>
							<th>姓名</th>
							<td>
						 		${customerInfoModel.realName}
							</td>
						</tr>
						<tr>
							<th>性别</th>
							<td>
								<c:if test="${customerInfoModel.sex=='1'}">
									男
								</c:if>
								<c:if test="${customerInfoModel.sex=='2'}">
									女
								</c:if>
							</td>
						</tr>
						
						<tr>
							<th>出生日期</th>
							<td>
						    ${customerInfoModel.birthday}
							</td>
						</tr>
						<tr>
							<th>身份证号</th>
							<td>
						    ${customerInfoModel.certCode}
							</td>
						</tr>
						<tr>
							<th>手机号</th>
							<td>
						     ${m.customerMobile}
							</td>
						</tr>
						<tr>
							<th>住址</th>
							<td>
						    ${customerInfoModel.address}
							</td>
						</tr>
					</tbody>
			</table>	
		</div>
		
		<div  class="form-horizontal form-bordered border1-top">	
			<div class="form-group" style="border-right: 1px solid #ddd;">
				<div class="col-sm-12">
					<i class="fa fa-user"></i>医生基本信息
				</div>
			</div>
			<table class="table table-bordered mb_0 ">
      	<tbody>
					<tr>
						<th width="15%">医生ID</th>
						<td width="85%">${m.doctorNo}</td>
					</tr>
					<tr>	
						<th>姓名</th>
						<td>
							${m.doctorName}
						</td>
					</tr>
					<tr>
						<th>医院</th>
						<td>
							${servicestaffModel.hospitalName}
						</td>
					</tr>
					<tr>
						<th>科室</th>
						<td>
							${servicestaffModel.departmentName}
						</td>
					</tr>
					<tr>
						<th>联系方式</th>
						<td>
					   ${m.doctorMobile}
						</td>
					</tr>
				</tbody>
			</table>	
		</div>
		
		<div  class="form-horizontal form-bordered border1-top">	
			<div class="form-group" style="border-right: 1px solid #ddd;">
				<div class="col-sm-12">
					<i class="fa fa-user"></i>患者随访单
				</div>
			</div>
			<table class="table table-bordered mb_0">
	      <tbody>
						<tr>
							<th width="15%">病情记录</th>
							<td width="85%">好转</td>
						</tr>
						<tr>	
							<th>服药记录</th>
							<td>
								<table class="table table-bordered">
			            <tbody>
							   	<tr>
										<th width="10%">药物名称</th>
										<td width="40%">${doctorAdviceModel.medicineUuid}</td>
										<th width="10%">单量</th>
										<td width="40%">
											${doctorAdviceModel.dosage}
										</td>
								  </tr>
								  <tr>
								  	<th>用法</th>
										<td>
											${doctorAdviceModel.directions}
										</td>
										<th>频率</th>
										<td>
											${doctorAdviceModel.frequency}
										</td>
								  </tr>
									</tbody>
								</table>	
							</td>
						</tr>
						
						<tr>
							<th>药物不良反应</th>
							<td>
								<table class="table table-bordered">
			            <tbody>
								   	<tr>
											<th width="10%">发生时间</th>
											<td width="40%">${drugReactionModel.occurrenceTime}</td>
											<th width="10%">持续时间</th>
											<td width="40%">
												${drugReactionModel.dosageTime}
											</td>
									  </tr>
									  <tr>
											<th>症状描述</th>
											<td>
												${drugReactionModel.frequency}
											</td>
											<th>影响</th>
											<td>
												${drugReactionModel.impact}
											</td>
									  </tr>
									</tbody>
								</table>	
							</td>
						</tr>
						 
					 	<c:forEach items="${visitRecordExtendModelList}" var="dpp">	
					 	<tr>
							<th width="15%">${dpp.name}</th>
							<td width="20%">${dpp.result}</td>
					  </tr>
				    </c:forEach>
						<tr>
								<th>随访表单填写时间</th>
								<td>
									${m.createTime}
								</td>
						</tr>
					</tbody>
			</table>	
		</div>
		
		<div class="form-horizontal form-bordered border1-top">	
			<div class="form-group" style="border-right: 1px solid #ddd;">
				<div class="col-sm-12">
					<i class="fa fa-user"></i>随访方案
				</div>
			</div>
			<table class="table table-bordered mb_0">
	      <tbody>
					<tr>
						<th width="15%">方案名称</th>
						<td width="85%">${m.preceptName}</td>
					</tr>
				</tbody>
			</table>	
		</div>
				
	</div>
			 <div class="y_fixedbtn">
				<button type="button" class="btn btn-primary cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
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