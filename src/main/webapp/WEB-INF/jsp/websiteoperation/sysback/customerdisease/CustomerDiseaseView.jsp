<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>疾病详情</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>基础数据库</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>疾病管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>疾病详情</span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customerdisease/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
					<div class="form-group" style="border-right: 1px solid #ddd;">
				    <div class="col-sm-12">
					   <i class="fa fa-user"></i>基本信息
					 </div>
		     	</div>
		     	
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.diseaseCategory"/></label>
					<div class="col-sm-10">
					${m.diseaseCategory}
					</div>
					</div>
					
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.diseaseName"/></label>
					<div class="col-sm-10">
					${m.diseaseName}
					</div>
					</div>
					
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.diseaseDescription"/></label>
					<div class="col-sm-10">
					${m.diseaseDescription}
					</div>
					</div>
					
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.pathogeny"/></label>
					<div class="col-sm-10">
					${m.pathogeny}
					</div>
					</div>
					
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.clinicalFeature"/></label>
					<div class="col-sm-10">
					${m.clinicalFeature}
					</div>
					</div>
					
					
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.remedy"/></label>
					<div class="col-sm-10">
					${m.remedy}
					</div>
					</div>
					
					
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.morbidity"/></label>
					<div class="col-sm-10">
					${m.morbidity}
					</div>
					</div>
					
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerdisease.m.precaution"/></label>
					<div class="col-sm-10">
					${m.precaution}
					</div>
					</div>
					
							
							
							
				 <div class="y_fixedbtn">
				 	<button type="button" class="btn btn-primary cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
			  </div>	
							
				</form:form>
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