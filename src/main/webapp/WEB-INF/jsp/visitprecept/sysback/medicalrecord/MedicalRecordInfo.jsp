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
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">					
					<form:form id="baseInfoForm" class='form-horizontal form-validate form-bordered' >
						<!--患者基本信息-->
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
								<i class="fa fa-lock"></i>患者基本信息
								</div>
							</div>
						</div>
							
						<!--流水号 患者ID-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">患者ID</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.cutomreId}
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
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-lock"></i>医生基本信息
								</div>
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
									
						<!--病历-->
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-lock"></i>病历
								</div>
							</div>
						</div>	

						<!--就诊类型-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">就诊类型</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;
									<c:choose>
                    <c:when test="${m.caseCategoryType eq '0'}">
                      住院
                    </c:when>
                    <c:otherwise>
                      门诊
                    </c:otherwise>
                  </c:choose>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "> 就诊时间</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<c:choose>
                    <c:when test="${m.caseCategoryType eq '0'}">
                      住院时间：${m.createTime}
                      出院时间：${m.endTime}
                    </c:when>
                    <c:otherwise>
										&nbsp;${m.createTime}
                    </c:otherwise>
                  </c:choose>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "> 就诊医院</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.hospitalName}
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">  医生</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.doctorName}
								</div>
							</div>
						</div>

						<!--
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">病历记录</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.doctorName}
								</div>
							</div>
						</div>
						-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">主诉</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.mainsuit}
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">现病史</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.illness}
								</div>
							</div>
						</div>						

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">既往史</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.previous}
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">个人史</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.personal}
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">需要关注的躯体状况</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.previous}
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">精神检查</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.spiritCheck}
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">辅助检查</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.assistCheck}
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">病程</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.process}
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">发作次数</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.attackNum}
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">共病</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.comorbidity}
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">合并症</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.complication}
								</div>
							</div>
						</div>

						<!--治疗方案-->
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
								<i class="fa fa-lock"></i>治疗方案
								</div>
							</div>
						</div>

						<div class="nopadding mb_0">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th width="5%">药物名称</th>
										<td width="45%">
											${doctorAdviceModel.medicineUuid} 
										</td>
										<th width="5%" style="border-top:none;">单量</th>
										<td width="45%">
											${doctorAdviceModel.dosage} 
										</td>
									</tr>
									<tr>
										<th>用法</th>
										<td>
											&nbsp;${doctorAdviceModel.directions} 
										</td>
										<th>频率</th>
										<td>
											${doctorAdviceModel.frequency} 
										</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">其他治疗</label>
							<div class="col-sm-10">
									其他治疗其他治疗其他治疗其他治疗其他治疗其他治疗
							</div>
						</div>
											
					</form:form>	
			</div>
		</div>
		
		
		<div class="y_fixbtnbox">
			<div class="y_fixedbtn">		
				<button type="button" id="returenId" class="btn btn-primary cancel btn-large a_size_1">返回</button>
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