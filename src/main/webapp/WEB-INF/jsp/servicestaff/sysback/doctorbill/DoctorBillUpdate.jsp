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
				<h1><aebiz:showTitle titleId="doctorbill.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="doctorbill.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="doctorbill.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="doctorbill.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/doctorbill/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
							
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="doctorbill.m.doctorUuid"/></label>
	<div class="col-sm-10">
	<form:input path="doctorUuid" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="doctorbill.m.bankUuid"/></label>
	<div class="col-sm-10">
	<form:input path="bankUuid" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="doctorbill.m.settlementUuid"/></label>
	<div class="col-sm-10">
	<form:input path="settlementUuid" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="doctorbill.m.dealType"/></label>
	<div class="col-sm-10">
	<form:input path="dealType" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="doctorbill.m.money"/></label>
	<div class="col-sm-10">
	<form:input path="money" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="doctorbill.m.state"/></label>
	<div class="col-sm-10">
	<form:input path="state" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="doctorbill.m.createTime"/></label>
	<div class="col-sm-10">
	<form:input path="createTime" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="doctorbill.m.note"/></label>
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