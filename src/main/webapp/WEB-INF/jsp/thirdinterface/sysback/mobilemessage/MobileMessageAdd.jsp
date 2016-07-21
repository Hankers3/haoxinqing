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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="mobilemessage.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="mobilemessage.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="mobilemessage.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="mobilemessage.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/mobilemessage/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="mobilemessage.m.url"/></label>
						<div class="col-sm-10">
							<div class="col-xs-6">
								<input type="text" name="url" class="form-control" data-rule-required="true" data-rule-maxlength="100">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="mobilemessage.m.userId"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<input type="text" name="userId" class="form-control" data-rule-required="true" data-rule-maxlength="50">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="mobilemessage.m.password"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<input type="passWord" name="password" class="form-control" data-rule-required="true" data-rule-maxlength="50" onblur="checkpwd()">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="mobilemessage.m.repeatPassWord"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<input type="passWord" name="repeatPassWord" class="form-control" data-rule-required="true" data-rule-maxlength="50" onblur="checkpwd()">
							</div>
							<div class="col-xs-9">
								<p id="checkpwd" class="y_input-groupts text-danger"></p>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="mobilemessage.m.scorpid"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<input type="text" name="scorpid" class="form-control" data-rule-maxlength="50">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="mobilemessage.m.sprdid"/></label>
						<div class="col-sm-10">
							<div class="col-xs-4">
								<input type="text" name="sprdid" class="form-control" data-rule-required="true" data-rule-maxlength="50">
							</div>
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
    function checkpwd(){
    	var pwd1 = $("input[name='smtpPassword']").val();
    	var pwd2 = $("input[name='repeatPassWord']").val();
    	if(pwd2=="" || pwd1==""){
    		return;	
    	}
    	if(pwd1 != pwd2){
    		$("#checkpwd").text("2次密码输入不匹配……");	
    	}else{
    		$("#checkpwd").text("");		
    	}	
    }
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>