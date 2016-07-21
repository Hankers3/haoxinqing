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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customerdisease.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="customerdisease.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="customerdisease.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customerdisease.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customerdisease/add" method="post" class='form-horizontal form-bordered form-validate'>						
						<div class="form-group">
							<div class="col-sm-12">
								<i class="fa fa-list-ul"></i>疾病的基础信息
							</div>
						</div>
					
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.diseaseCategory"/></label>
							<div class="col-sm-10">
							<input type="text" name="diseaseCategory" class="form-control">
							</div>
					 	</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.diseaseName"/></label>
							<div class="col-sm-10">
							<input type="text" name="diseaseName" class="form-control">
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.diseaseDescription"/></label>
							<div class="col-sm-10">
							<textarea rows="5" name="diseaseDescription" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.pathogeny"/></label>
							<div class="col-sm-10">
							<textarea rows="5" name="pathogeny" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.clinicalFeature"/></label>
							<div class="col-sm-10">
							<textarea rows="5" name="clinicalFeature" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.remedy"/></label>
							<div class="col-sm-10">
							<textarea rows="5" name="remedy" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.morbidity"/></label>
							<div class="col-sm-10">
							<textarea rows="5" name="morbidity" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.precaution"/></label>
							<div class="col-sm-10">
							<textarea rows="5" name="precaution" class="form-control" data-rule-maxlength="1500"></textarea>
							</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='保存'>
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