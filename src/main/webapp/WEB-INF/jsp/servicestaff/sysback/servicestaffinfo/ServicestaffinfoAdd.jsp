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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="servicestaffinfo.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="servicestaffinfo.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="servicestaffinfo.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="servicestaffinfo.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaffinfo/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.serviceStaffUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="serviceStaffUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.nickName"/></label>
	<div class="col-sm-10">
	<input type="text" name="nickName" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.sex"/></label>
	<div class="col-sm-10">
	<input type="text" name="sex" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.birthday"/></label>
	<div class="col-sm-10">
	<input type="text" name="birthday" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.hobby"/></label>
	<div class="col-sm-10">
	<input type="text" name="hobby" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.realName"/></label>
	<div class="col-sm-10">
	<input type="text" name="realName" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.province"/></label>
	<div class="col-sm-10">
	<input type="text" name="province" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.city"/></label>
	<div class="col-sm-10">
	<input type="text" name="city" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.region"/></label>
	<div class="col-sm-10">
	<input type="text" name="region" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.address"/></label>
	<div class="col-sm-10">
	<input type="text" name="address" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.image"/></label>
	<div class="col-sm-10">
	<input type="text" name="image" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></label>
	<div class="col-sm-10">
	<input type="text" name="certCode" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.education"/></label>
	<div class="col-sm-10">
	<input type="text" name="education" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.industry"/></label>
	<div class="col-sm-10">
	<input type="text" name="industry" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.zipCode"/></label>
	<div class="col-sm-10">
	<input type="text" name="zipCode" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.certType"/></label>
	<div class="col-sm-10">
	<input type="text" name="certType" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.certImage"/></label>
	<div class="col-sm-10">
	<input type="text" name="certImage" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.synopsis"/></label>
	<div class="col-sm-10">
	<input type="text" name="synopsis" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinfo.m.ageLimit"/></label>
	<div class="col-sm-10">
	<input type="text" name="ageLimit" class="form-control">
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