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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="contentlist.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="contentlist.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="contentlist.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="contentlist.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/contentlist/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="contentlist.m.doctorUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="doctorUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="contentlist.m.doctorMobile"/></label>
	<div class="col-sm-10">
	<input type="text" name="doctorMobile" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="contentlist.m.contentUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="contentUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="contentlist.m.detail"/></label>
	<div class="col-sm-10">
	<input type="text" name="detail" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="contentlist.m.createTime"/></label>
	<div class="col-sm-10">
	<input type="text" name="createTime" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="contentlist.m.email"/></label>
	<div class="col-sm-10">
	<input type="text" name="email" class="form-control">
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