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
				<h1>病症详情</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>基础数据库</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>病症管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>病症详情</span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/symptomstructure/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>
						<div class="form-group" style="border-right: 1px solid #ddd;">
				    	<div class="col-sm-12">
					  	 <i class="fa fa-user"></i>基本信息
						  </div>
		    	 	</div>	
							
						<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="symptomstructure.m.firstClassification"/></label>
						<div class="col-sm-10">
						${m.firstClassification}
						</div>
						</div>
						
						<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="symptomstructure.m.secondaryClassification"/></label>
						<div class="col-sm-10">
						${m.secondaryClassification}
						</div>
						</div>
						
						<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="symptomstructure.m.symptom"/></label>
						<div class="col-sm-10">
						${m.symptom}
						</div>
						</div>
						
						<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="symptomstructure.m.breakdownSymptom"/></label>
						<div class="col-sm-10">
						${m.breakdownSymptom}
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