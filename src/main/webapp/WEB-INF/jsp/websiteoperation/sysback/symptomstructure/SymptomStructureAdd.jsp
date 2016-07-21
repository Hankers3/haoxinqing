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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="symptomstructure.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="symptomstructure.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="symptomstructure.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="symptomstructure.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/symptomstructure/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="symptomstructure.m.firstClassification"/></label>
							<div class="col-sm-10">
							<input type="text" name="firstClassification" class="form-control">
							</div>
					</div>
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="symptomstructure.m.secondaryClassification"/></label>
							<div class="col-sm-10">
							<input type="text" name="secondaryClassification" class="form-control">
							</div>
					</div>
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="symptomstructure.m.symptom"/></label>
							<div class="col-sm-10">
							<input type="text" name="symptom" class="form-control">
							</div>
					</div>
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="symptomstructure.m.breakdownSymptom"/></label>
							<div class="col-sm-10">
							<input type="text" name="breakdownSymptom" class="form-control">
							</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='±£´æ'>
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