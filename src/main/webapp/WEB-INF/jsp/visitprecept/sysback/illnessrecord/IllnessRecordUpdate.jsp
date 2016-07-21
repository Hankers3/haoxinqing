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
				<h1><aebiz:showTitle titleId="illnessrecord.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="illnessrecord.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="illnessrecord.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="illnessrecord.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/illnessrecord/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
							
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="illnessrecord.m.customerUuid"/></label>
	<div class="col-sm-10">
	<form:input path="customerUuid" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="illnessrecord.m.serviceStaffUuid"/></label>
	<div class="col-sm-10">
	<form:input path="serviceStaffUuid" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="illnessrecord.m.visitRecordUuid"/></label>
	<div class="col-sm-10">
	<form:input path="visitRecordUuid" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="illnessrecord.m.previons"/></label>
	<div class="col-sm-10">
	<form:input path="previons" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="illnessrecord.m.newCondition"/></label>
	<div class="col-sm-10">
	<form:input path="newCondition" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="illnessrecord.m.createTime"/></label>
	<div class="col-sm-10">
	<form:input path="createTime" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="illnessrecord.m.updateTime"/></label>
	<div class="col-sm-10">
	<form:input path="updateTime" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="illnessrecord.m.note"/></label>
	<div class="col-sm-10">
	<form:input path="note" class='form-control'  />
	</div>
	</div>
	
							
							
							
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
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