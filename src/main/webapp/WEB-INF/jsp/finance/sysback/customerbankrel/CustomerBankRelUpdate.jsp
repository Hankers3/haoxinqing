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
				<h1><aebiz:showTitle titleId="customerbankrel.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customerbankrel/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
							
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.customerUuid"/></label>
	<div class="col-sm-10">
	<form:input path="customerUuid" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.cardNo"/></label>
	<div class="col-sm-10">
	<form:input path="cardNo" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.openAccountBank"/></label>
	<div class="col-sm-10">
	<form:input path="openAccountBank" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.openAccountName"/></label>
	<div class="col-sm-10">
	<form:input path="openAccountName" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.cardValidateState"/></label>
	<div class="col-sm-10">
	<form:input path="cardValidateState" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.mobile"/></label>
	<div class="col-sm-10">
	<form:input path="mobile" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.vilidateCode"/></label>
	<div class="col-sm-10">
	<form:input path="vilidateCode" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.vilidateMount"/></label>
	<div class="col-sm-10">
	<form:input path="vilidateMount" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.mobileVilidateState"/></label>
	<div class="col-sm-10">
	<form:input path="mobileVilidateState" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.failTimes"/></label>
	<div class="col-sm-10">
	<form:input path="failTimes" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.idCard1"/></label>
	<div class="col-sm-10">
	<form:input path="idCard1" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.idcard2"/></label>
	<div class="col-sm-10">
	<form:input path="idcard2" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.idcardNo"/></label>
	<div class="col-sm-10">
	<form:input path="idcardNo" class='form-control'  />
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