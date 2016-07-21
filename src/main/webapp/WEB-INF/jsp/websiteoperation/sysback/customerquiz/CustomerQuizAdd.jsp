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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customerquiz.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="customerquiz.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="customerquiz.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customerquiz.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/customerquiz/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerquiz.m.userUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="userUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerquiz.m.userName"/></label>
	<div class="col-sm-10">
	<input type="text" name="userName" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerquiz.m.quizCategoryUuid"/></label>
	<div class="col-sm-10">
	<input type="text" name="quizCategoryUuid" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerquiz.m.result"/></label>
	<div class="col-sm-10">
	<input type="text" name="result" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerquiz.m.totalScore"/></label>
	<div class="col-sm-10">
	<input type="text" name="totalScore" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerquiz.m.createTime"/></label>
	<div class="col-sm-10">
	<input type="text" name="createTime" class="form-control">
	</div>
</div><div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerquiz.m.note"/></label>
	<div class="col-sm-10">
	<input type="text" name="note" class="form-control">
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