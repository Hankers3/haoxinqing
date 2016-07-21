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
				<h1><aebiz:showTitle titleId="favorite.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="favorite.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="favorite.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="favorite.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/favorite/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
							
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="favorite.m.userId"/></label>
	<div class="col-sm-10">
	<form:input path="userId" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="favorite.m.favoriteTime"/></label>
	<div class="col-sm-10">
	<form:input path="favoriteTime" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="favorite.m.contentUuid"/></label>
	<div class="col-sm-10">
	<form:input path="contentUuid" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="favorite.m.videoUuid"/></label>
	<div class="col-sm-10">
	<form:input path="videoUuid" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="favorite.m.userType"/></label>
	<div class="col-sm-10">
	<form:input path="userType" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="favorite.m.favoriteType"/></label>
	<div class="col-sm-10">
	<form:input path="favoriteType" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="favorite.m.state"/></label>
	<div class="col-sm-10">
	<form:input path="state" class='form-control'  />
	</div>
	</div>
	<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="favorite.m.tags"/></label>
	<div class="col-sm-10">
	<form:input path="tags" class='form-control'  />
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