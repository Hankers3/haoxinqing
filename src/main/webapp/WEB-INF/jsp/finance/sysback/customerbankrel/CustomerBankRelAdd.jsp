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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customerbankrel.moduleName_CN"/></h1>
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
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customerbankrel.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customerbankrel/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.customerUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="customerUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.cardNo"/></label>
	<div class="col-sm-10">
	<input type="text" name="cardNo" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.openAccountBank"/></label>
	<div class="col-sm-10">
	<input type="text" name="openAccountBank" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.openAccountName"/></label>
	<div class="col-sm-10">
	<input type="text" name="openAccountName" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.cardValidateState"/></label>
	<div class="col-sm-10">
	<input type="text" name="cardValidateState" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.mobile"/></label>
	<div class="col-sm-10">
	<input type="text" name="mobile" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.vilidateCode"/></label>
	<div class="col-sm-10">
	<input type="text" name="vilidateCode" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.vilidateMount"/></label>
	<div class="col-sm-10">
	<input type="text" name="vilidateMount" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.mobileVilidateState"/></label>
	<div class="col-sm-10">
	<input type="text" name="mobileVilidateState" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.failTimes"/></label>
	<div class="col-sm-10">
	<input type="text" name="failTimes" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.idCard1"/></label>
	<div class="col-sm-10">
	<input type="text" name="idCard1" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.idcard2"/></label>
	<div class="col-sm-10">
	<input type="text" name="idcard2" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.idcardNo"/></label>
	<div class="col-sm-10">
	<input type="text" name="idcardNo" class="form-control">
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