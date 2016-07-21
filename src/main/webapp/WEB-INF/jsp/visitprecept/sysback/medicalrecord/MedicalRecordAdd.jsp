<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="medicalrecord.moduleName_CN"/></h1>
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
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="medicalrecord.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/medicalrecord/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.customerUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="customerUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.caseCategoryUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="caseCategoryUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.hospitalUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="hospitalUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.divisionUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="divisionUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.serviceStaffUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="serviceStaffUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.visitRecordUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="visitRecordUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.mainsuit"/></label>
	<div class="col-sm-10">
	<input type="text" name="mainsuit" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.illness"/></label>
	<div class="col-sm-10">
	<input type="text" name="illness" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.previous"/></label>
	<div class="col-sm-10">
	<input type="text" name="previous" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.personal"/></label>
	<div class="col-sm-10">
	<input type="text" name="personal" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.family"/></label>
	<div class="col-sm-10">
	<input type="text" name="family" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.somastate"/></label>
	<div class="col-sm-10">
	<input type="text" name="somastate" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.spiritCheck"/></label>
	<div class="col-sm-10">
	<input type="text" name="spiritCheck" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.assistCheck"/></label>
	<div class="col-sm-10">
	<input type="text" name="assistCheck" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.abnormal"/></label>
	<div class="col-sm-10">
	<input type="text" name="abnormal" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.diagnosis"/></label>
	<div class="col-sm-10">
	<input type="text" name="diagnosis" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.date"/></label>
	<div class="col-sm-10">
	<input type="text" name="date" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.process"/></label>
	<div class="col-sm-10">
	<input type="text" name="process" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.attackNum"/></label>
	<div class="col-sm-10">
	<input type="text" name="attackNum" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.comorbidity"/></label>
	<div class="col-sm-10">
	<input type="text" name="comorbidity" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.complication"/></label>
	<div class="col-sm-10">
	<input type="text" name="complication" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.scaleAppraisal"/></label>
	<div class="col-sm-10">
	<input type="text" name="scaleAppraisal" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.curePreceptUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="curePreceptUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.cureCourse"/></label>
	<div class="col-sm-10">
	<input type="text" name="cureCourse" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.startTime"/></label>
	<div class="col-sm-10">
	<input type="text" name="startTime" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.endTime"/></label>
	<div class="col-sm-10">
	<input type="text" name="endTime" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.preAssistCheck"/></label>
	<div class="col-sm-10">
	<input type="text" name="preAssistCheck" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.image1"/></label>
	<div class="col-sm-10">
	<input type="text" name="image1" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.image2"/></label>
	<div class="col-sm-10">
	<input type="text" name="image2" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.image3"/></label>
	<div class="col-sm-10">
	<input type="text" name="image3" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.image4"/></label>
	<div class="col-sm-10">
	<input type="text" name="image4" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="medicalrecord.m.image5"/></label>
	<div class="col-sm-10">
	<input type="text" name="image5" class="form-control">
	</div>
</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>						
				</form>
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