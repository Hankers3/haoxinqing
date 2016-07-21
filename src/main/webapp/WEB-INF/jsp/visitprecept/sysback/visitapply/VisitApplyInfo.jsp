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
				<h1><aebiz:showTitle titleId="medicalrecord.moduleName_CN"/>查看详细</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="medicalrecord.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="medicalrecord.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="medicalrecord.moduleName_CN"/>查看详细</span>
				</li>
			</ul>
		</div>
				
		<div style="border: 1px solid #ddd;">								
			<form:form id="baseInfoForm" action="${pageContext.servletContext.contextPath}/sysback/medicalrecord/updateBaseInfo" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">
				<!--患者基本信息-->
				<div class="form-group">
					<div class="col-sm-12" style="border-left:none">
						<i class="fa fa-lock"></i>患者基本信息
					</div>
				</div>
				<!--流水号 患者ID-->
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
				
				<!--患者姓名-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">患者姓名</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${customerInfoModel.realName}
						</div>
					</div>
				</div>
				<!--患者性别-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">患者性别</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${customerInfoModel.sex}
						</div>
					</div>
				</div>
				<!--出生日期-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">出生日期</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${customerInfoModel.birthday}
						</div>
					</div>
				</div>
				<!--身份证号-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">身份证号</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${customerInfoModel.certCode}
						</div>
					</div>
				</div>
				<!--手机号-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">手机号</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${customerModel.mobile}
						</div>
					</div>
				</div>
				<!--职业-->
				<!--
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">职业</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${customerInfoModel.certCode}
						</div>
					</div>
				</div>
				-->
				<!--住址-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">住址</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${customerInfoModel.address}
						</div>
					</div>
				</div>
				
				<!--医生基本信息-->
				<div class="form-group">
					<div class="col-sm-12" style="border-left:none">
						<i class="fa fa-lock"></i>医生基本信息
					</div>
				</div>
				
				<!--流水号 医生ID-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">医生ID</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${servicestaffModel.doctorNo}
						</div>
					</div>
				</div>
				
				<!--医生姓名-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">医生姓名</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${servicestaffModel.realName}
						</div>
					</div>
				</div>
				<!--医院-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">医院</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${servicestaffModel.hospitalName}
						</div>
					</div>
				</div>
				<!--科室-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">科室</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${servicestaffModel.departmentName}
						</div>
					</div>
				</div>
				<!--联系方式-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">联系方式</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${servicestaffModel.mobile}
						</div>
					</div>
				</div>
							
				<!--患者随访表单-->
				<div  class="form-horizontal form-bordered">	
					<div class="form-group" style="border-bottom:none">
						<div class="col-sm-12" style="border-left:none">
							<i class="fa fa-user"></i>患者随访单
						</div>
					</div>
					<table class="table mb_0 h_no_bordered">
		      	<tbody>
							<tr>
								<th width="15%">病情记录</th>
								<td width="85%">${visitRecordModel.illnessRecord}</td>
							</tr>
							<tr>	
								<th>服药记录</th>
								<td>
								<table class="table table-bordered mb_0">
			            <tbody>
								   	<tr>
											<th width="15%">药物名称</th>
											<td width="35%">${doctorAdviceModel.medicineUuid}</td>
									
											<th width="15%">单量</th>
											<td width="35%">
												${doctorAdviceModel.dosage}
											</td>
									  </tr>
									  
									  <tr>
											<th>频率</th>
											<td>
												${doctorAdviceModel.frequency}
											</td>
									
											<th>用法</th>
											<td>
												${doctorAdviceModel.directions}
											</td>
									  </tr>
									</tbody>
								</table>	
								</td>
							</tr>
							
							<tr>
								<th>药物不良反应</th>
								<td>
									<table class="table table-bordered mb_0">
				            <tbody>
									   	<tr>
												<th width="15%">发生时间</th>
												<td width="85%">${drugReactionModel.occurrenceTime}</td>
											</tr>
											<tr>
												<th>持续时间</th>
												<td>
												${drugReactionModel.dosageTime}
												</td>
										  </tr>
										  <tr>
											<th>症状描述</th>
												<td>
													${drugReactionModel.frequency}
												</td>
										  </tr>	
										  <tr>
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

				<!--随访方案-->
				<div class="form-group border1-top">
					<div class="col-sm-12" style="border-left:none;">
						<i class="fa fa-lock"></i>随访方案
					</div>
				</div>

				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">方案名称</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.preceptName}
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">药物治疗</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.drugTherapy}
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">药物不良反应处理</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.sideEffects}
						</div>
					</div>
				</div>												
				
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">随访周期</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.period}
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">心电图检查周期</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.electrocardiogram}
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">血常规</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.bloodRoutine}
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">肝功能</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.hepatic}
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">饮食指导</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.dietGuide}
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">运动指导</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.sport}
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2  ">睡眠指导</label>
					<div class="col-sm-10">
						<div class="col-sm-3">
							&nbsp;${visitPreceptModel.sleep}
						</div>
					</div>
				</div>
			</form:form>
		</div>

	</div>
	
	<div class="y_fixedbtn">
		<button type="button" class="btn btn-primary cancel btn-large a_size_1">返回</button>
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